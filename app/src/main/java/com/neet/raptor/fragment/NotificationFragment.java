package com.neet.raptor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neet.raptor.R;
import com.neet.raptor.adapter.AssignTestAdapter;
import com.neet.raptor.adapter.NotificationAdapter;
import com.neet.raptor.model.AssignTestModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    NotificationAdapter mAdapter;
    FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        init(view);
        listener();
        loadData();
        // Inflate the layout for this fragment
        return view;
    }

    private void init(View view) {

        myContext = getActivity();

        recyclerView = view.findViewById(R.id.notification_recycler_view);
        setRecyclerView();
    }

    private void setRecyclerView() {
        /*LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mAssignRecyclerView.setLayoutManager(layoutManager);

        mAssignList = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            AssignTestModel aTestModel = new AssignTestModel();
            aTestModel.mName = "TestName" + i;
            aTestModel.mRollno = "1000" + i;
            aTestModel.mClass = "11";
            aTestModel.mSection = "A";
            aTestModel.mSchedule = "11/09/2019";
            aTestModel.mType = "NEET";

            mAssignList.add(aTestModel);
        }

        mAssignrecyclerAdapter = new AssignTestAdapter(getActivity(), mAssignList);
        mAssignRecyclerView.setAdapter(mAssignrecyclerAdapter);*/
    }

    private void listener() {

    }

    private void loadData() {

    }

}
