package com.example.app.components.SubjectList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.example.app.models.Subject;

import java.util.ArrayList;

public class SubjectListRecyclerViewAdapter extends RecyclerView.Adapter<SubjectCardViewHolder> {

    ArrayList<Subject> subjectList;

    public SubjectListRecyclerViewAdapter(ArrayList<Subject> subjectList) {
        this.subjectList = subjectList;
    }


    @NonNull
    @Override
    public SubjectCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_card, parent, false);

        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KEVIN", "Apreto algo que no se que es");
            }
        });


        return new SubjectCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectCardViewHolder holder, int position) {
        if (subjectList != null && position < subjectList.size()) {
            Subject subject = subjectList.get(position);
            holder.subject_name.setText(subject.getSubjectName());
        }
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }


}
