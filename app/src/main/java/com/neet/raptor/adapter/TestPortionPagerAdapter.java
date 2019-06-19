package com.neet.raptor.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neet.raptor.R;
import com.neet.raptor.fragmentmanager.APPFragmentManager;
import com.neet.raptor.model.TestPortionModel;

import java.util.ArrayList;


public class TestPortionPagerAdapter extends PagerAdapter {

    private ArrayList<TestPortionModel> mPortionModels;
    private FragmentActivity mContext;
    private APPFragmentManager myFragmentManager;

    public TestPortionPagerAdapter(ArrayList<TestPortionModel> aBladeTypes, FragmentActivity aContext) {
        mPortionModels = aBladeTypes;
        mContext = aContext;

        myFragmentManager = new APPFragmentManager(mContext);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_inflate_test_portion_adapter, container, false);

        LinearLayout mPointerLayout = inflate.findViewById(R.id.pointer_layout);
        LinearLayout mTestNameLayout = inflate.findViewById(R.id.test_name_layout);
        TextView mTestNameTXT = inflate.findViewById(R.id.test_name_txt);
        TextView mTestDateTXT = inflate.findViewById(R.id.test_date_txt);
        TextView mTestStatusTXT = inflate.findViewById(R.id.test_status_txt);
        RecyclerView mSubjectRecyclerview = inflate.findViewById(R.id.subject_recyclerview);

        TestPortionModel aModel = mPortionModels.get(position);

        mTestNameTXT.setText(aModel.mTestName);


        if (aModel.mTestStatus.equals("1")) {
            mTestStatusTXT.setText("Completed");
            mTestDateTXT.setVisibility(View.GONE);
            mPointerLayout.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.menu_green));
            GradientDrawable myGrad = (GradientDrawable) mTestNameLayout.getBackground();
            myGrad.setStroke(pxFromDp(mContext, 30f), ContextCompat.getColor(mContext, R.color.menu_green));
        } else if (aModel.mTestStatus.equals("2")) {
            mTestStatusTXT.setText(aModel.mTestMonth);
            mTestDateTXT.setVisibility(View.VISIBLE);
            mTestDateTXT.setText(aModel.mTestDate);
            mPointerLayout.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.red));
            GradientDrawable myGrad = (GradientDrawable) mTestNameLayout.getBackground();
            myGrad.setStroke(pxFromDp(mContext, 30f), ContextCompat.getColor(mContext, R.color.red));
        } else {
            mTestStatusTXT.setText(aModel.mTestMonth);
            mTestDateTXT.setText(aModel.mTestDate);
            mTestDateTXT.setVisibility(View.VISIBLE);
            mPointerLayout.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.colorPrimaryDark));
            GradientDrawable myGrad = (GradientDrawable) mTestNameLayout.getBackground();
            myGrad.setStroke(pxFromDp(mContext, 30f), ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager( mContext );
        mSubjectRecyclerview.setLayoutManager(layoutManager);
        TestSubjectAdapter mDashboardAdapter = new TestSubjectAdapter(mContext, aModel.mTestSubjectList);
        mSubjectRecyclerview.setAdapter(mDashboardAdapter);

        container.addView(inflate);
        return inflate;
    }

    public int pxFromDp(final Context context, final float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }


    @Override
    public int getCount() {
        return mPortionModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(((View) object));
    }
}
