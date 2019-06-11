package com.neet.raptor.adapter;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neet.raptor.R;
import com.neet.raptor.fragmentmanager.APPFragmentManager;
import com.neet.raptor.model.AssignTestModel;

import java.util.ArrayList;

public class AssignTestAdapter extends RecyclerView.Adapter<AssignTestAdapter.ViewHolder> {

    private APPFragmentManager mFragmentManager;
    FragmentActivity mContext;
    ArrayList<AssignTestModel> mAssignList;
    Menucallback mMenucallback;


    public AssignTestAdapter(FragmentActivity activity, ArrayList<AssignTestModel> aAssignList) {
        mContext = activity;
        mAssignList = aAssignList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_inflate_assign_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        AssignTestModel aTestModel = mAssignList.get(i);
        viewHolder.mAssignName.setText(aTestModel.mName);
        viewHolder.mAssignRoll.setText(aTestModel.mRollno);
        viewHolder.mAssignClass.setText(aTestModel.mClass);
        viewHolder.mAssignSection.setText(aTestModel.mSection);
        viewHolder.mAssignSchedule.setText(aTestModel.mSchedule);
        viewHolder.mAssignType.setText(aTestModel.mType);
        viewHolder.mAssignCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mAssignList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mAssignName, mAssignRoll, mAssignClass, mAssignSection;
        TextView mAssignSchedule, mAssignType;
        CheckBox mAssignCheckbox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAssignCheckbox = itemView.findViewById(R.id.assign_check_box);
            mAssignName = itemView.findViewById(R.id.assign_name_txt);
            mAssignRoll = itemView.findViewById(R.id.assign_roll_txt);
            mAssignClass = itemView.findViewById(R.id.assign_class_txt);
            mAssignSection = itemView.findViewById(R.id.assign_section_txt);
            mAssignSchedule = itemView.findViewById(R.id.assign_schedule_txt);
            mAssignType = itemView.findViewById(R.id.assign_type_txt);
        }
    }

    public interface Menucallback {
        void menuItem(int position);
    }
}
