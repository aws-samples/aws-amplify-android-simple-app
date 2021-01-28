package com.example.app.components.SubjectList;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

public class SubjectCardViewHolder extends RecyclerView.ViewHolder {

    public TextView subject_name;

    public SubjectCardViewHolder(@NonNull View itemView) {
        super(itemView);
        subject_name = itemView.findViewById(R.id.subject_name);
    }

}
