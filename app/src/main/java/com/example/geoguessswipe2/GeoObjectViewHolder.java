package com.example.geoguessswipe2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by marmm on 02/11/2017.
 */

public class GeoObjectViewHolder extends RecyclerView.ViewHolder {

    public TextView geoName;
    public ImageView geoImage;
    public View view;

    public GeoObjectViewHolder(View itemView) {
        super(itemView);
        geoImage =  itemView.findViewById(R.id.geoImageView);
        view = itemView;
    }
}