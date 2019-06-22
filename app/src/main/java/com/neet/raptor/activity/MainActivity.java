package com.neet.raptor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neet.raptor.R;
import com.neet.raptor.adapter.MenuAdapter;
import com.neet.raptor.fragment.AskExpertFragment;
import com.neet.raptor.fragment.AssignTestFragment;
import com.neet.raptor.fragment.DashboardFragment;
import com.neet.raptor.fragment.HelpFragment;
import com.neet.raptor.fragment.NotificationFragment;
import com.neet.raptor.fragment.TestPortionFragment;
import com.neet.raptor.fragmentmanager.APPFragmentManager;
import com.neet.raptor.util.KeyboardUtils;

import java.util.ArrayList;
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
    RelativeLayout notificationLay, messageLay, signoutLay;

    AlertDialog confirmationAlertDialog;

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
        notificationLay = findViewById(R.id.notification_lay);
        messageLay = findViewById(R.id.message_lay);
        signoutLay = findViewById(R.id.signout_lay);

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

        notificationLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentManager.updateContent(new NotificationFragment(), "NotificationFragment", null);
            }
        });

        messageLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentManager.updateContent(new NotificationFragment(), "NotificationFragment", null);
            }
        });

        signoutLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutDialog();
            }
        });
    }

    private void setMenuRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMenuRecyclerView.setLayoutManager(layoutManager);
        mMenuArrayList = new ArrayList<>();

        String[] aMenuTitles;
        if (value.equals("student"))
            aMenuTitles = this.getResources().getStringArray(R.array.nav_menu_students);
        else if (value.equals("teacher"))
            aMenuTitles = this.getResources().getStringArray(R.array.nav_menu_teacher);
        else
            aMenuTitles = this.getResources().getStringArray(R.array.nav_menu_parent);

        Collections.addAll(mMenuArrayList, aMenuTitles);


        MenuAdapter.Menucallback aMenucallback = new MenuAdapter.Menucallback() {
            @Override
            public void menuItem(int position) {
                closeDrawer();
                switch (position) {
                    case 3:
                        mFragmentManager.updateContent(new TestPortionFragment(), "TestPortionFragment", null);
                        break;
                    case 4:
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
                    default:
                        Toast.makeText(MainActivity.this, "Inprogress", Toast.LENGTH_SHORT).show();
                }


            }
        };

        mMenurecyclerAdapter = new MenuAdapter(this, mMenuArrayList, aMenucallback, value);
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


    private Fragment getCurrentFragment() {
        FragmentManager aFragmentManager = getSupportFragmentManager();
        String aFragmentTag = aFragmentManager.getBackStackEntryAt(aFragmentManager.getBackStackEntryCount() - 1).getName();
        return getSupportFragmentManager().findFragmentByTag(aFragmentTag);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
         /*for( Fragment fragment : getSupportFragmentManager().getFragments() ) {
            fragment.onActivityResult( requestCode, resultCode, data );
         }*/


            if (getCurrentFragment() instanceof AskExpertFragment) {
                getCurrentFragment().onActivityResult(requestCode, resultCode, data);
            } else if (getCurrentFragment() instanceof HelpFragment) {
                getCurrentFragment().onActivityResult(requestCode, resultCode, data);
            }
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }
    }

    private void logoutDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById( android.R.id.content );

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from( this ).inflate( R.layout.layout_logout_confirmation, viewGroup, false );


        dialogView.findViewById( R.id.btn_logout ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                confirmationAlertDialog.cancel();
                finish();
            }
        } );

        dialogView.findViewById( R.id.btn_cancel ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                confirmationAlertDialog.cancel();
            }
        } );

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder( this );

        //setting the view of the builder to our custom view that we already inflated
        builder.setView( dialogView );

        //finally creating the alert dialog and displaying it
        confirmationAlertDialog = builder.create();
        confirmationAlertDialog.show();
    }
}
