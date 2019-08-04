package com.example.pouya.eamlak.Actitvities;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.widget.GridView;
import android.widget.Toast;

import com.example.pouya.eamlak.Adapters.mainIconAdaptor;
import com.example.pouya.eamlak.Adapters.reportClass;
import com.example.pouya.eamlak.Classes.Base;
import com.example.pouya.eamlak.Classes.UAppCompatActivity;
import com.example.pouya.eamlak.Helper.RequestHelper;
import com.example.pouya.eamlak.R;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends UAppCompatActivity {
    Base createClass = new Base();
    final ArrayList<reportClass> iconArray = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        reportClass addIcon = new reportClass(getDrawable(R.drawable.buy),"خرید");
        iconArray.add(addIcon);
        mainIconAdaptor adaptor = new mainIconAdaptor(this,iconArray);





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView mainGrid = (GridView) findViewById(R.id.mainGridView);
        mainGrid.setAdapter(adaptor);



        requestForWriteSDCardPermission();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


    }

    public void requestForWriteSDCardPermission(){
        RequestHelper request = new RequestHelper(this);
        RequestHelper.OnGrantedListener grantedListenerListener = new RequestHelper.OnGrantedListener() {
            @Override
            public void onGranted() {

            }
        };

        RequestHelper.OnAlreadyGrantedListener onAlreadyGrantedListener = new RequestHelper.OnAlreadyGrantedListener() {
            @Override
            public void onAlreadyGranted() {


            }
        };

        RequestHelper.OnDeniedListener deniedListener = new RequestHelper.OnDeniedListener() {
            @Override
            public void onDenied() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Permission Required")
                        .setMessage("Writing to SDCARD required for this app")
                        .setPositiveButton("Ask me again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestForWriteSDCardPermission();
                            }
                        })
                        .create()
                        .show();
            }
        };
        request.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, grantedListenerListener, deniedListener);
        String directory = Base.getDirectory();
        File dbMaker = new File(directory);
        if(!dbMaker.exists()){
            dbMaker.mkdirs();
            Toast.makeText(MainActivity.this,"پوشه ها ساخته شد",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this,"پوشه ها قبلا ساخته شده بود",Toast.LENGTH_LONG).show();
        }
        try{
            createClass.createOrOpenDataBase(MainActivity.this);
            Toast.makeText(MainActivity.this,"DataBase Successfuly Loaded",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"DataBase didnt Load",Toast.LENGTH_SHORT).show();
        }
    }
}
