package com.neet.raptor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neet.raptor.R;

public class NotificationDetailFragment extends Fragment {

    TextView titleTXT, messageTXT, createdByTXT;

    public NotificationDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_detail, container, false);

        init(view);
        getBundle();

        return view;
    }

    private void getBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle.getString("id", "");
            titleTXT.setText(bundle.getString("title", ""));
            messageTXT.setText(bundle.getString("message", ""));
            createdByTXT.setText(bundle.getString("created_by", ""));
        }
    }

    private void init(View view) {
        titleTXT = view.findViewById(R.id.notification_title_txt);
        messageTXT = view.findViewById(R.id.notification_message_txt);
        createdByTXT = view.findViewById(R.id.notification_message_by_txt);
    }
}
