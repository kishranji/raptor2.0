package com.neet.raptor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neet.raptor.R;
import com.neet.raptor.adapter.MenuAdapter;
import com.neet.raptor.fragment.AskExpertFragment;
import com.neet.raptor.fragment.AssignTestFragment;
import com.neet.raptor.fragment.DashboardFragment;
import com.neet.raptor.fragment.HelpFragment;
import com.neet.raptor.fragment.NotificationFragment;
import com.neet.raptor.fragmentmanager.APPFragmentManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    APPFragmentManager mFragmentManager;
    String value;
    BottomNavigationView mNavigation;
    NavigationView mNavigationView;

    ImageView mProfileImage, mMenuIcon;
    TextView mProfileName_TXT;
    RecyclerView mMenuRecyclerView;
    private ArrayList<String> mMenuArrayList;
    private MenuAdapter mMenurecyclerAdapter;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        callDefaultFragment();
    }

    private void init() {
        value = getIntent().getExtras().getString("value");
        mNavigation = findViewById(R.id.bottom_layout);
        mNavigationView = findViewById(R.id.navigation_view);
        View header = mNavigationView.getHeaderView(0);
        mProfileImage = header.findViewById(R.id.iv_profile_pic);
        mProfileName_TXT = header.findViewById(R.id.header_profile_name);
        mMenuRecyclerView = header.findViewById(R.id.menu_recyclerView);
        mDrawerLayout = findViewById(R.id.drawer_view);
        mMenuIcon = findViewById(R.id.activity_menu_right);

        setMenuRecycler();
        mNavigation.setOnNavigationItemSelectedListener(this);

        clickListener();
        mFragmentManager = new APPFragmentManager(this);
    }

    private void clickListener() {
        mMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });
    }

    private void setMenuRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMenuRecyclerView.setLayoutManager(layoutManager);
        mMenuArrayList = new ArrayList<>();

        String[] aMenuTitles = this.getResources().getStringArray(R.array.common_menu_items);
        Collections.addAll(mMenuArrayList, aMenuTitles);


        MenuAdapter.Menucallback aMenucallback = new MenuAdapter.Menucallback() {
            @Override
            public void menuItem(int position) {
                closeDrawer();
                switch (position) {
                    case 0:
                        mFragmentManager.updateContent(new AssignTestFragment(), "AssignTestFragment", null);
                        break;
                    case 6:
                        mFragmentManager.updateContent(new AskExpertFragment(), "AskExpertFragment", null);
                        break;
                    case 7:
                        mFragmentManager.updateContent(new NotificationFragment(), "NotificationFragment", null);
                        break;
                    case 8:
                        mFragmentManager.updateContent(new HelpFragment(), "HelpFragment", null);
                        break;
                }

                Toast.makeText(MainActivity.this, "TEST", Toast.LENGTH_SHORT).show();
            }
        };

        mMenurecyclerAdapter = new MenuAdapter(this, mMenuArrayList, aMenucallback);
        mMenuRecyclerView.setAdapter(mMenurecyclerAdapter);
    }


    private void closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    void openDrawer() {
        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void callDefaultFragment() {
        mFragmentManager.clearAllFragments();
        Bundle aBundle = new Bundle();
        aBundle.putString("passvalue", value);
        mFragmentManager.updateContent(new DashboardFragment(), "DashboardFragment", aBundle);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //  mFragmentManager.clearAllFragments();
        switch (item.getItemId()) {

            case R.id.action_ask_expert:
                mFragmentManager.updateContent(new AskExpertFragment(), "AskExpertFragment", null);
                break;

            case R.id.action_notification:
                mFragmentManager.updateContent(new NotificationFragment(), "NotificationFragment", null);
                break;

            case R.id.action_help:
                mFragmentManager.updateContent(new HelpFragment(), "HelpFragment", null);
                break;
        }

        return true;
    }
}
