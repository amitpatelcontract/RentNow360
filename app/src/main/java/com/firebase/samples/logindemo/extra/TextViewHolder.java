package com.firebase.samples.logindemo.extra;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.samples.logindemo.R;

/**
 * Created by arms on 2/23/16.
 */
public class TextViewHolder extends RecyclerView.ViewHolder {
    public ImageView productImageView;
    public TextView textViewPrize, textViewTitle;

    public TextViewHolder(View itemView) {
        super(itemView);
        textViewPrize = (TextView) itemView.findViewById(R.id.your_product_prize);
        textViewTitle = (TextView) itemView.findViewById(R.id.your_product_prize);
        productImageView = (ImageView) itemView.findViewById(R.id.my_product_imageview);
    }
}