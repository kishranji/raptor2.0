package com.neet.raptor.adapter;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neet.raptor.R;
import com.neet.raptor.fragmentmanager.APPFragmentManager;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private APPFragmentManager mFragmentManager;
    FragmentActivity mContext;
    ArrayList<String> mMenuInfoList;
    private ArrayList<Integer> myIcons;
    Menucallback mMenucallback;

    public MenuAdapter(FragmentActivity context, ArrayList<String> aMenuList, Menucallback aMenucallback) {
        try {
            mContext = context;
            mMenuInfoList = aMenuList;
            mFragmentManager = new APPFragmentManager(mContext);
            mMenucallback = aMenucallback;
            myIcons = new ArrayList<>();
            TypedArray aIcons = mContext.getResources().obtainTypedArray(R.array.common_menu_icon);

            for (int i = 0; i < aIcons.length(); i++) {
                myIcons.add(aIcons.getResourceId(i, -1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_inflate_nav_menu_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mMenuName.setText(mMenuInfoList.get(i));
        viewHolder.mMenuImage.setImageResource(myIcons.get(i));

        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenucallback.menuItem(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mMenuInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mMenuName;
        ImageView mMenuImage;
        LinearLayout mLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mMenuName = itemView.findViewById(R.id.menu_name);
            mMenuImage = itemView.findViewById(R.id.menu_image);
            mLayout = itemView.findViewById(R.id.menu_layout);
        }
    }

    public interface Menucallback {
        void menuItem(int position);
    }
}
