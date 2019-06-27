package com.neet.raptor.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neet.raptor.R;
import com.neet.raptor.model.TakeTestModel;

import java.util.ArrayList;

public class TakeTestAdapter extends RecyclerView.Adapter<TakeTestAdapter.ViewHolder> {

    FragmentActivity mContext;
    ArrayList<TakeTestModel> mList;

    public TakeTestAdapter(FragmentActivity aContext, ArrayList<TakeTestModel> aTestSubjectList) {
        mContext = aContext;
        mList = aTestSubjectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_inflate_take_test_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        TakeTestModel test = mList.get(i);

        viewHolder.mNumber.setText(String.valueOf(test.mNumber));

        if (i == 1) {
            viewHolder.mNumber.setTextColor(mContext.getResources().getColor(R.color.white));
            viewHolder.mNumber.setBackgroundResource(R.drawable.bg_circle_red);
        } else if (i == 5) {
            viewHolder.mNumber.setTextColor(mContext.getResources().getColor(R.color.white));
            viewHolder.mNumber.setBackgroundResource(R.drawable.bg_circle_green);
        } else if (i == 10) {
            viewHolder.mNumber.setTextColor(mContext.getResources().getColor(R.color.black));
            viewHolder.mNumber.setBackgroundResource(R.drawable.bg_circle_yellow);
        } else {
            viewHolder.mNumber.setTextColor(mContext.getResources().getColor(R.color.black));
            viewHolder.mNumber.setBackgroundResource(R.drawable.bg_login_edt);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNumber = itemView.findViewById(R.id.item);
        }
    }
}
