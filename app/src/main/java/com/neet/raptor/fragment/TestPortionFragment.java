package com.neet.raptor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neet.raptor.R;
import com.neet.raptor.adapter.TestPortionPagerAdapter;
import com.neet.raptor.model.TestPortionModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestPortionFragment extends Fragment {

    View mView;
    ViewPager mViewPager;
    ArrayList<TestPortionModel> mPortionList;
    FragmentActivity mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_test_portion, container, false);

        init(mView);

        return mView;
    }

    private void init(View aView) {

        mContext = getActivity();
        mPortionList = new ArrayList<>();

        mViewPager = aView.findViewById(R.id.test_portion_pager);
        mViewPager.setOffscreenPageLimit(3);

        setViewPagerAdapter();
    }

    private void setViewPagerAdapter() {

        for (int i = 0; i < 3; i++) {
            TestPortionModel aTestModel = new TestPortionModel();

            if (i == 0) {
                aTestModel.mTestName = "Weekly test -1";
                aTestModel.mTestStatus = "1";
                aTestModel.mTestDate = "5 days";
                aTestModel.mTestMonth = "days more";
            } else if(i==1) {
                aTestModel.mTestName = "Weekly test - 2";
                aTestModel.mTestStatus = "2";
                aTestModel.mTestDate = "5";
                aTestModel.mTestMonth = "days more";
            }else{
                aTestModel.mTestName = "Weekly test - 3";
                aTestModel.mTestStatus = "3";
                aTestModel.mTestDate = "20";
                aTestModel.mTestMonth = "April";
            }

            ArrayList<TestPortionModel.TestSubject> aList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                TestPortionModel.TestSubject aSubject = new TestPortionModel.TestSubject();
                aSubject.mSubjectName = "Physics" + j;
                aSubject.mPortion = "Physics-I" + j;
                aList.add(aSubject);
            }

            aTestModel.mTestSubjectList = aList;
            mPortionList.add(aTestModel);
        }

        TestPortionPagerAdapter mPortionPagerAdapter = new TestPortionPagerAdapter(mPortionList, mContext);
        mViewPager.setAdapter(mPortionPagerAdapter);
    }

}
