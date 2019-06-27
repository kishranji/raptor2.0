package com.neet.raptor.fragment.student;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.neet.raptor.R;
import com.neet.raptor.adapter.TakeTestAdapter;
import com.neet.raptor.fragmentmanager.APPFragmentManager;
import com.neet.raptor.model.TakeTestModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TakeTestFragment extends Fragment implements View.OnClickListener {

    View mView;
    FragmentActivity mContext;
    RecyclerView mRecycler;
    TakeTestAdapter mAdapter;
    ArrayList<TakeTestModel> mList = new ArrayList<>();
    RadioGroup mGroup;
    Button mFinish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_take_test, container, false);

        init(mView);

        return mView;
    }

    private void init(View aView) {

        mContext = getActivity();

        mRecycler = aView.findViewById(R.id.test_recycler);
        mGroup = aView.findViewById(R.id.radio_group);
        mFinish = aView.findViewById(R.id.finish);

        mGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Log.e("init: ", String.valueOf(checkedId));
        });

        initRecycler();

        clickListener();
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new GridLayoutManager(mContext, 7));
        mRecycler.setHasFixedSize(true);
        mAdapter = new TakeTestAdapter(mContext, mList);
        mRecycler.setAdapter(mAdapter);

        initSampleData();
    }

    private void initSampleData() {
        for (int i = 0; i < 150; i++) {
            TakeTestModel test = new TakeTestModel();
            test.mNumber = i + 1;
            mList.add(test);
        }

        mAdapter.notifyDataSetChanged();
    }

    private void clickListener() {
        mFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish:
                new APPFragmentManager(mContext).updateContent(new TestReviewFragment(), "Take Test", null);
                break;
        }
    }
}
