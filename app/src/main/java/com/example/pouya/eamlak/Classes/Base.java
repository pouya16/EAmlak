package com.example.pouya.eamlak.Classes;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import com.example.pouya.eamlak.Helper.MyDatabaseHelper;

/**
 * Created by pouya on 12/1/2018.
 */

public class Base extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        context.getApplicationContext();
        inflater = LayoutInflater.from(context);
    }

    private static Context context = null;
    public static SQLiteDatabase database;
    private static Activity currentAcivity;
    private static LayoutInflater inflater;
    private static String directory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/EAmlak/sampleDatabase";


    public static Context getContext(){
        if(currentAcivity!=null){
            return currentAcivity;
        }else {
            return context;
        }
    }
    public static void setCurrentActivity(Activity activity){
        currentAcivity = activity;
    }

    public static LayoutInflater getInflater(){
        return inflater;
    }

    public static View LayoutInflate(@LayoutRes int res){
        return inflater.inflate(res,null);
    }

    public static String getDirectory(){
        return directory;
    }

    public void createOrOpenDataBase(Context context){
        if(database!=null){
            return;
        }
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

}
