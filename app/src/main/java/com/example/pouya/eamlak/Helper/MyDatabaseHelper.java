package com.example.pouya.eamlak.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pouya.eamlak.Classes.Base;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Pouya";
    public static final int DB_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, Base.getDirectory() + "/" + DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createCustomers(db);
        createBuyers(db);
        createOwners(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 || newVersion == 2) {
        }
    }



    public void createOwners(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'owner' (" +
                        //0:
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'code' INTEGER UNIQUE, " +
                        "'date' INTEGER, " +
                        //3
                        "'customerNumber' INTEGER, " +
                        "'consMeter' INTEGER, " +
                        "'address1' TEXT, " +
                        //6
                        "'address2' TEXT, " +
                        "'salePrice' INTEGER, " +
                        "'rent' INTEGER, " +
                        //9
                        "'rooms' INTEGER, " +
                        "'facing' INTEGER, " +
                        "'heat' INTEGER, " +
                        //12
                        "'cold' INTEGER, " +
                        "'cabinet' INTEGER, " +
                        "'constructionYears' INTEGER, " +
                        //15
                        "'currentFloor' INTEGER, " +
                        "'unit' INTEGER, " +
                        "'side' INTEGER, " +
                        //18
                        "'phone' INTEGER, " +
                        "'type' INTEGER, " +
                        "'apartment' INTEGER, " +
                        //21
                        "'isConvertable' INTEGER, " +
                        "'meter' INTEGER, " +
                        "'releaseDate' INTEGER, " +
                        //24
                        "'sanad' INTEGER, " +
                        "'mosha' INTEGER, " +
                        "'basement' INTEGER, " +
                        //27
                        "'price' INTEGER, " +
                        "'pricePerMeter' INTEGER, " +
                        "'geo' INTEGER, " +
                        //30
                        "'floors' INTEGER" +
                        ")";

        db.execSQL(query);
    }
    public void createCustomers(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'customers' (" +
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'customerNumber' INTEGER, " +
                        "'name' TEXT, " +
                        "'family' TEXT, " +
                        "'email' TEXT, " +
                        "'telegramID' TEXT, " +
                        "'phone' INTEGER UNIQUE, " +
                        "'child' INTEGER, " +
                        "'marriage' INTEGER, " +
                        "'condition' INTEGER" +
                        ")";

        db.execSQL(query);
    }
    public void createBuyers(SQLiteDatabase db) {
        String query =
                "CREATE TABLE 'buyers' (" +
                        "'autoId' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , " +
                        "'code' INTEGER UNIQUE, " +
                        "'type' INTEGER UNIQUE, " +
                        "'date' INTEGER UNIQUE, " +
                        "'dateLimit' INTEGER UNIQUE, " +
                        "'customerNumber' INTEGER UNIQUE, " +
                        "'rooms' INTEGER UNIQUE, " +
                        "'startMeter' INTEGER UNIQUE, " +
                        "'endMeter' INTEGER UNIQUE, " +
                        "'startPrice' INTEGER UNIQUE, " +
                        "'endPrice' INTEGER UNIQUE, " +
                        "'startLoan' INTEGER UNIQUE, " +
                        "'endLoan' INTEGER UNIQUE, " +
                        "'floors' INTEGER" +
                        "'apartment' INTEGER" +
                        ")";

        db.execSQL(query);
    }
}