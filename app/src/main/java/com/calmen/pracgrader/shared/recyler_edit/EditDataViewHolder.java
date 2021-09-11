package com.calmen.pracgrader.shared.recyler_edit;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;

public class EditDataViewHolder extends RecyclerView.ViewHolder {
    public TextView editAttributeView, editAttributeVal;
    public Button selectEditBtn;

    public EditDataViewHolder(@NonNull View itemView) {
        super(itemView);
        editAttributeView = itemView.findViewById(R.id.editAttributeView);
        editAttributeVal = itemView.findViewById(R.id.editAttributeVal);
        selectEditBtn = itemView.findViewById(R.id.selEditBtn);
    }
}
