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
import com.neet.raptor.adapter.NotificationAdapter;
import com.neet.raptor.model.NotificationModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    NotificationAdapter mAdapter;
    FragmentActivity myContext;
    private ArrayList<NotificationModel> notificationArray;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        notificationArray = new ArrayList<>();

        for (int i = 0; i <= 5; i++) {
            NotificationModel aNotificationModel = new NotificationModel();
            aNotificationModel.setId("" + i);
            aNotificationModel.setTitle("Message Title " + (i + 1));
            aNotificationModel.setCreatedBy("Admin : " + (45 + i) + "m");
            aNotificationModel.setMessage("This is an sample message. you can replace with original");

            notificationArray.add(aNotificationModel);
        }

        mAdapter = new NotificationAdapter(getActivity(), notificationArray);
        recyclerView.setAdapter(mAdapter);
    }

    private void listener() {

    }

    private void loadData() {

    }

}
