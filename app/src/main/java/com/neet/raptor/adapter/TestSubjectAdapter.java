package com.neet.raptor.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neet.raptor.R;
import com.neet.raptor.model.TestPortionModel;

import java.util.ArrayList;

public class TestSubjectAdapter extends RecyclerView.Adapter<TestSubjectAdapter.ViewHolder> {

    FragmentActivity mContext;
    ArrayList<TestPortionModel.TestSubject> mTestSubjectList;

    public TestSubjectAdapter(FragmentActivity aContext, ArrayList<TestPortionModel.TestSubject> aTestSubjectList) {

        mContext = aContext;
        mTestSubjectList = aTestSubjectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_inflate_subject_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        TestPortionModel.TestSubject aSubject = mTestSubjectList.get(i);

        viewHolder.mSubjectName.setText(aSubject.mSubjectName);
        viewHolder.mPortion.setText(aSubject.mPortion);
    }

    @Override
    public int getItemCount() {
        return mTestSubjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mSubjectName, mPortion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSubjectName = itemView.findViewById(R.id.subject_name);
            mPortion = itemView.findViewById(R.id.subject_portion);
        }
    }
}
