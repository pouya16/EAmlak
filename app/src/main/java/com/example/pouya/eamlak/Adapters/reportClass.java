package com.example.pouya.eamlak.Adapters;

import android.graphics.drawable.Drawable;

/**
 * Created by pouya on 10/24/2018.
 */

public class reportClass {
    private Drawable iconPhoto;
    private String iconText;
    private Class intentName;

    public reportClass(Drawable readPhoto, String readText,Class startIntent) {
        iconPhoto = readPhoto;
        iconText = readText;
        intentName = startIntent;
    }


    public Drawable getIconPhoto(){return iconPhoto;}
    public String getIconText(){return iconText;}
    public Class getStartIntent(){return intentName;}
}
