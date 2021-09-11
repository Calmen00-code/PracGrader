package com.calmen.pracgrader.shared.recyler_edit;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.shared.EditAttribute;

import java.util.ArrayList;

public class RecyclerEditAdapter extends RecyclerView.Adapter<EditDataViewHolder> {
    ArrayList<EditData> edits;

    public RecyclerEditAdapter(ArrayList<EditData> inEdits) {
        this.edits = inEdits;
    }

    @NonNull
    @Override
    public EditDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.edit_attribute_row, parent, false);
        EditDataViewHolder editDataViewHolder = new EditDataViewHolder(view);
        return editDataViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditDataViewHolder holder, int position) {
        EditData singleData = edits.get(position);
        holder.editAttributeView.setText(singleData.getEditTitle());
        holder.selectEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditAttribute.class);
                String val = singleData.getEditTitle();
                intent.putExtra(val, "EditValue");
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return edits.size();
    }
}
