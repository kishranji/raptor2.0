package com.neet.raptor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neet.raptor.R;
import com.neet.raptor.adapter.AssignTestAdapter;
import com.neet.raptor.adapter.MenuAdapter;
import com.neet.raptor.model.AssignTestModel;

import java.util.ArrayList;


public class AssignTestFragment extends Fragment {

    View mView;
    RecyclerView mAssignRecyclerView;

    ArrayList<AssignTestModel> mAssignList;
    private AssignTestAdapter mAssignrecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_assign_test, container, false);

        init(mView);
        return mView;
    }

    private void init(View mView) {
        mAssignRecyclerView = mView.findViewById(R.id.assign_task_recyclerView);
        setRecyclerView();
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mAssignRecyclerView.setLayoutManager(layoutManager);

        mAssignList = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            AssignTestModel aTestModel = new AssignTestModel();
            aTestModel.mName = "Test" + i;
            aTestModel.mRollno = "1000" + i;
            aTestModel.mClass = "11";
            aTestModel.mSection = "A";
            aTestModel.mSchedule = "11/09/2019";
            aTestModel.mType = "NEET";

            mAssignList.add(aTestModel);
        }

        mAssignrecyclerAdapter = new AssignTestAdapter(getActivity(), mAssignList);
        mAssignRecyclerView.setAdapter(mAssignrecyclerAdapter);
    }

}
