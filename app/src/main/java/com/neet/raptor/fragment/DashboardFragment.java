package com.neet.raptor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.neet.raptor.R;
import com.neet.raptor.adapter.DashboardAdapter;
import com.neet.raptor.fragment.student.TakeTestIntroFragment;
import com.neet.raptor.fragmentmanager.APPFragmentManager;

import java.util.ArrayList;
import java.util.Collections;


public class DashboardFragment extends Fragment {

    View mView;
    RecyclerView mRecyclerView;
    String mPassvalue;

    FragmentActivity mContext;
    APPFragmentManager mFragmentManager;

    ArrayList<String> mMenuArrayList;
    private DashboardAdapter mDashboardAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        init();
        return mView;
    }

    private void init() {

        mContext = getActivity();
        mFragmentManager = new APPFragmentManager(mContext);

        mRecyclerView = mView.findViewById(R.id.dashboard_recyclerview);
        mMenuArrayList = new ArrayList<>();

        mPassvalue = getArguments().getString("passvalue");
        if (mPassvalue.equals("student")) {
            String[] aMenuTitles = mContext.getResources().getStringArray(R.array.student_dash_items);
            Collections.addAll(mMenuArrayList, aMenuTitles);
        } else if (mPassvalue.equals("parents")) {
            String[] aMenuTitles = mContext.getResources().getStringArray(R.array.parents_dash_items);
            Collections.addAll(mMenuArrayList, aMenuTitles);
        } else {
            String[] aMenuTitles = mContext.getResources().getStringArray(R.array.teacher_dash_items);
            Collections.addAll(mMenuArrayList, aMenuTitles);
        }

        configRecycler();

    }


    private void configRecycler() {

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        DashboardAdapter.DashboardMenuCallback aMenuCallback = new DashboardAdapter.DashboardMenuCallback() {
            @Override
            public void menuItem(int position) {
                switch (position) {
                    case 0:
                        if (mPassvalue.equals("student"))
                            mFragmentManager.updateContent(new TakeTestIntroFragment(), "Tests", null);
                        else
                            Toast.makeText(mContext, "Inprogress", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        mFragmentManager.updateContent(new TestPortionFragment(), "TestPortionFragment", null);
                        break;
                    case 4:
                        mFragmentManager.updateContent(new AssignTestFragment(), "AssignTestFragment", null);
                        break;
                    default:
                        Toast.makeText(mContext, "Inprogress", Toast.LENGTH_SHORT).show();
                }
            }
        };

        mDashboardAdapter = new DashboardAdapter(mContext, mMenuArrayList, aMenuCallback, mPassvalue);
        mRecyclerView.setAdapter(mDashboardAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);


    }

}
