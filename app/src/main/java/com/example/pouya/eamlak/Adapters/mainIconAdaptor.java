package com.example.pouya.eamlak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pouya.eamlak.R;

import java.util.List;

/**
 * Created by pouya on 10/12/2018.
 */

public class mainIconAdaptor extends ArrayAdapter<reportClass> {

    public mainIconAdaptor(@NonNull Context context, List<reportClass> iconsList) {
        super(context,0, iconsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_main,parent,false);
        }
        reportClass currentReport = getItem(position);

        ImageView iconImage = (ImageView) listItemView.findViewById(R.id.item_image);
        iconImage.setImageDrawable(currentReport.getIconPhoto());
        TextView reportShow = (TextView) listItemView.findViewById(R.id.item_text);
        reportShow.setText(currentReport.getIconText());


        return listItemView;

    }

}
