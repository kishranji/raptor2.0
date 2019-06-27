package com.neet.raptor.fragment.student;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.neet.raptor.R;
import com.neet.raptor.fragmentmanager.APPFragmentManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class TakeTestIntroFragment extends Fragment implements View.OnClickListener {

    View mView;
    FragmentActivity mContext;
    Button mTakeTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_take_test_intro, container, false);

        init(mView);

        return mView;
    }

    private void init(View aView) {

        mContext = getActivity();
        mTakeTest = aView.findViewById(R.id.take_test_btn);

        clickListener();
    }

    private void clickListener() {
        mTakeTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_test_btn:
                new APPFragmentManager(mContext).updateContent(new TakeTestFragment(), "Take Test", null);
                break;
        }
    }
}
