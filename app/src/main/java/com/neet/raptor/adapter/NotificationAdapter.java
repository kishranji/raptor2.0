package com.neet.raptor.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neet.raptor.R;
import com.neet.raptor.fragment.NotificationDetailFragment;
import com.neet.raptor.fragmentmanager.APPFragmentManager;
import com.neet.raptor.model.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    FragmentActivity mContext;
    private APPFragmentManager mFragmentManager;
    ArrayList<NotificationModel> mAssignList;

    public NotificationAdapter(FragmentActivity activity, ArrayList<NotificationModel> aAssignList) {
        mContext = activity;
        mAssignList = aAssignList;
        mFragmentManager = new APPFragmentManager(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notification, parent, false);
        return new NotificationAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder viewHolder, int i) {
        NotificationModel notificationModel = mAssignList.get(i);

        viewHolder.titleTXT.setText(notificationModel.getTitle());
        viewHolder.messageTXT.setText(notificationModel.getMessage());
        viewHolder.createdByTXT.setText(notificationModel.getCreatedBy());

        viewHolder.parentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNextfragment(notificationModel);
            }
        });

    }

    private void callNextfragment(NotificationModel notificationModel) {
        Bundle bundle = new Bundle();
        bundle.putString("id", notificationModel.getId());
        bundle.putString("title", notificationModel.getTitle());
        bundle.putString("message", notificationModel.getMessage());
        bundle.putString("created_by", notificationModel.getCreatedBy());
        mFragmentManager.updateContent(new NotificationDetailFragment(),"NotificationDetailFragment", bundle);
    }

    @Override
    public int getItemCount() {
        return mAssignList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTXT, messageTXT, createdByTXT;
        CardView parentCard;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTXT = itemView.findViewById(R.id.notification_title_txt);
            messageTXT = itemView.findViewById(R.id.notification_message_txt);
            createdByTXT = itemView.findViewById(R.id.notification_message_by_txt);
            parentCard = itemView.findViewById(R.id.parent);
        }
    }
}
