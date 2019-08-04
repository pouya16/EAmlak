package com.example.pouya.eamlak.Actitvities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pouya.eamlak.Classes.Base;
import com.example.pouya.eamlak.Classes.StaticVariables;
import com.example.pouya.eamlak.R;

public class BuyerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
        final StaticVariables variables = new StaticVariables();


        //declaring view part ///
        final EditText nameEditText = (EditText) findViewById(R.id.editTextBuyerName);
        final EditText familyEditText = (EditText) findViewById(R.id.editTextBuyerFamily);
        final EditText mobileEditText = (EditText) findViewById(R.id.editTextBuyerMobile);
        final EditText childEditText = (EditText) findViewById(R.id.editTextBuyerChild);
        final EditText customerEditText = (EditText) findViewById(R.id.editTextBuyerID);
        final EditText emailEditText = (EditText) findViewById(R.id.editTextBuyerEmail);
        final EditText telegramEditText = (EditText) findViewById(R.id.editTextBuyerTelegram);
        final RadioGroup marriageRadioGroup = (RadioGroup) findViewById(R.id.marriageRadioGroup);
        RadioGroup requestTypeGroup = (RadioGroup) findViewById(R.id.conditionRadioGroup);
        Button registerBtn = (Button) findViewById(R.id.btnBuyer1Next);
        Button backBtn = (Button) findViewById(R.id.btnBuyer1Back);

        //Btn Configurations
        //Btn Back
         backBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });

        Base createClass = new Base();
        try{
            createClass.createOrOpenDataBase(BuyerActivity.this);
            Toast.makeText(BuyerActivity.this,"Ready to Work",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(BuyerActivity.this,"DataBase Didn't Load",Toast.LENGTH_SHORT).show();
        }

        marriageRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioBtnBuyerMarriageSingle){
                    variables.buyerMarriageStatus = 0;
                }else if(checkedId == R.id.radioBtnMarriageMarried){
                    variables.buyerMarriageStatus = 1;
                }
            }
        });

        requestTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.loanRadioBtn){
                    variables.buyerConditionStatus = 1;
                }
                if(checkedId == R.id.rentRadioBtn){
                    variables.buyerConditionStatus = 2;
                }
            }
        });



         //Btn Next
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bind inputs to static variables
                setInputs(nameEditText,familyEditText,mobileEditText,childEditText,emailEditText,telegramEditText,variables);
                //check inputs
                if(checkInputs(variables)==0){
                    Toast.makeText(BuyerActivity.this,"ورود نام و یا شماره موبایل الزامی است.",Toast.LENGTH_SHORT).show();
                    return;
                }
                //calculate CustomerID
                variables.buyerID = calculateCustomerID(variables);
                if(variables.buyerID==0){
                    Toast.makeText(BuyerActivity.this,"ایراد در محاسبه شماره مشتری.",Toast.LENGTH_SHORT).show();
                    return;
                }
                customerEditText.setText("" + variables.buyerID);

                new AlertDialog.Builder(BuyerActivity.this).setTitle("تایید").setMessage("اطلاعات مورد تایید است؟").setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("بلی", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addCustomerToDataBase(variables);
                        clearVariables(variables);
                        clearEditTextes(nameEditText,familyEditText,mobileEditText,childEditText,emailEditText,telegramEditText,customerEditText);
                    }
                }).show();
                Intent intent = new Intent(BuyerActivity.this,Buyer2Activity.class);
            }
        });
    }




    ///functions
    //set Edit texts inputs function to static variables
     public void setInputs(EditText txtName,EditText txtFamily,EditText txtMobile,EditText txtChild,EditText txtEmail,EditText txtTelegram,StaticVariables variables){

         variables.buyerName = txtName.getText().toString();

         variables.buyerFamily = txtFamily.getText().toString();
        try {
            variables.buyerChild = Integer.parseInt(txtChild.getText().toString());
        }catch (Exception e){}

         variables.buyerMobile = txtMobile.getText().toString();


        variables.buyerEmail = txtEmail.getText().toString();
        variables.buyerTelegram = txtTelegram.getText().toString();
     }

     //check inputs
    public int checkInputs(StaticVariables variables){
         int flag = 0;
         if(variables.buyerName.length()>0||variables.buyerFamily.length()>0){
             if(variables.buyerMobile.length()>0){
                 flag = 1;
             }
         }else{
             flag = 0;
         }
         return flag;
    }

    //calculateCustomerID
    public int calculateCustomerID(StaticVariables variables){
        int customerID =0;
        String request = "SELECT * FROM customers";
        Cursor cursor =  Base.database.rawQuery(request, null);
        cursor.moveToLast();
        int countRows = 0;

        Log.i("Count Rows: ", "" + countRows);
        boolean isData = false;
        isData = cursor.isBeforeFirst();
        if(!isData){
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                countRows++;
            }
            cursor.moveToPrevious();
            customerID = cursor.getInt(1);
            Log.i("CustomerID", ""+ customerID);
            Log.i("CustomerID: ", "id after read db"+ customerID);
            customerID++;
        }else{
            customerID = 100;
        }

            Log.i("CustomerID", ""+ customerID);
        return customerID;
    }
    //add customers database
    private void addCustomerToDataBase(StaticVariables variables) {
        Log.i("VARAIABLES: "," NAME:" +variables.buyerName + " CUSTOMERID: " + variables.buyerID );
        String query = "INSERT INTO 'customers'('customerNumber','name','family','email','telegramID','phone','child','marriage','condition') values ('" +
                variables.buyerID + "', '" + variables.buyerName + "', '" + variables.buyerFamily + "', '" + variables.buyerEmail + "', '" +
                variables.buyerTelegram + "', '" + variables.buyerMobile + "', '" + variables.buyerChild + "', '" + variables.buyerMarriageStatus + "', '"+
                variables.buyerConditionStatus + "')";
        Log.i("QUERY","" + query);
        try {
            Base.database.execSQL(query);

        }catch (Exception e){
            Log.i("DATABASE",""+ e.toString());
        }
        Log.i("DATABASE","Movafagh");
    }

    //clearing Variable class
    private void clearVariables(StaticVariables variables) {
        variables.buyerName = "";
        variables.buyerFamily = "";
        variables.buyerMobile = "";
        variables.buyerTelegram = "";
        variables.buyerChild = -1;
        variables.buyerEmail = "";
    }

    //clearing edit texts
    private void clearEditTextes(EditText nameEditText, EditText familyEditText, EditText mobileEditText, EditText childEditText, EditText emailEditText, EditText telegramEditText, EditText customerID) {
        nameEditText.setText("");
        familyEditText.setText("");
        mobileEditText.setText("");
        childEditText.setText("");
        emailEditText.setText("");
        telegramEditText.setText("");
        customerID.setText("");

    }


}
 