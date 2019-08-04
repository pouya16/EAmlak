package com.example.pouya.eamlak.Adapters;

import android.graphics.drawable.Drawable;

/**
 * Created by pouya on 10/24/2018.
 */

public class reportClass {
    private Drawable iconPhoto;
    private String iconText;

    public reportClass(Drawable readPhoto, String readText) {
        iconPhoto = readPhoto;
        iconText = readText;
    }


    public Drawable getIconPhoto(){return iconPhoto;}
    public String getIconText(){return iconText;}
}
