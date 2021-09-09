package com.calmen.pracgrader.ui.country_recycler;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

public class CountryViewHolder extends RecyclerView.ViewHolder {
    public TextView countryTxt;
    public ImageView countryImg;
    public Button selectCountryBtn;

    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);
        countryTxt = itemView.findViewById(R.id.flagLabelView);
        countryImg = itemView.findViewById(R.id.flagIcon);
        selectCountryBtn = itemView.findViewById(R.id.flagSelectBtn);
    }
}
