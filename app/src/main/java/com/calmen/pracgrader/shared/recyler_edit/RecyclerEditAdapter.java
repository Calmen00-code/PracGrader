/***
 * @field edits refer to the items that is editable for each Instructor, Student, Practical
 * @field activity can help us to refer to the parent activity
 */
package com.calmen.pracgrader.shared.recyler_edit;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calmen.pracgrader.R;
import com.calmen.pracgrader.shared.EditAttribute;
import com.calmen.pracgrader.shared.EditCountry;
import com.calmen.pracgrader.shared.EditEntity;
import com.calmen.pracgrader.shared.EditPractical;
import com.calmen.pracgrader.shared.EditStudentPractical;

import java.util.ArrayList;

public class RecyclerEditAdapter extends RecyclerView.Adapter<EditDataViewHolder> {
    ArrayList<EditData> edits;
    Activity activity;

    public RecyclerEditAdapter(Activity inActivity, ArrayList<EditData> inEdits) {
        this.activity = inActivity;
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
        holder.editAttributeVal.setText(singleData.getEditVal());
        holder.selectEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTitle = singleData.getEditTitle();
                String oldVal = singleData.getEditVal();

                Intent intent;
                if (editTitle.equals(EditEntity.EDIT_COUNTRY)) {
                    intent = new Intent(activity, EditCountry.class);
                } else if (editTitle.equals(EditEntity.EDIT_PRACTICAL_LIST)) {
                    intent = new Intent(activity, EditStudentPractical.class);
                    System.out.println("Edit Student Practical List");
                } else {
                    intent = new Intent(activity, EditAttribute.class);
                }
                intent.putExtra("EditTitle", editTitle);
                intent.putExtra("OldValue", oldVal);

                activity.startActivity(intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return edits.size();
    }
}
