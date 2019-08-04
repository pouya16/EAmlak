package com.example.pouya.eamlak.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageButton;

/**
 * Created by pouya on 12/9/2018.
 */

@SuppressLint("AppCompatCustomView")
public class SquareImageButton extends ImageButton {
    public SquareImageButton(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minDimension = Math.min(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(minDimension, minDimension);
    }

}
