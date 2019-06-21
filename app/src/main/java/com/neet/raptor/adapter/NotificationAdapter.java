package com.neet.raptor.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neet.raptor.R;
import com.neet.raptor.model.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    FragmentActivity mContext;
    ArrayList<NotificationModel> mAssignList;

    public NotificationAdapter(FragmentActivity activity, ArrayList<NotificationModel> aAssignList) {
        mContext = activity;
        mAssignList = aAssignList;
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

    }

    @Override
    public int getItemCount() {
        return mAssignList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTXT, messageTXT, createdByTXT;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTXT = itemView.findViewById(R.id.notification_title_txt);
            messageTXT = itemView.findViewById(R.id.notification_message_txt);
            createdByTXT = itemView.findViewById(R.id.notification_message_by_txt);
        }
    }
}
