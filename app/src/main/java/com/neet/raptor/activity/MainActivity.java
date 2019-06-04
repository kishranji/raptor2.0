package com.neet.raptor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.neet.raptor.R;
import com.neet.raptor.fragment.AskExpertFragment;
import com.neet.raptor.fragment.DashboardFragment;
import com.neet.raptor.fragment.HelpFragment;
import com.neet.raptor.fragment.NotificationFragment;
import com.neet.raptor.fragmentmanager.APPFragmentManager;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


   APPFragmentManager mFragmentManager;
   String value;
   private BottomNavigationView mNavigation;

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.activity_main );

      init();

      callDefaultFragment();
   }

   private void init() {

      value = getIntent().getExtras().getString( "value" );

      mNavigation = findViewById( R.id.bottom_layout );
      mNavigation.setOnNavigationItemSelectedListener( this );

      mFragmentManager = new APPFragmentManager( this );
   }

   private void callDefaultFragment() {
      mFragmentManager.clearAllFragments();
      Bundle aBundle = new Bundle();
      aBundle.putString( "passvalue", value );
      mFragmentManager.updateContent( new DashboardFragment(), "DashboardFragment", aBundle );
   }

   @Override
   public boolean onNavigationItemSelected( @NonNull MenuItem item ) {
      //  mFragmentManager.clearAllFragments();
      switch( item.getItemId() ) {

         case R.id.action_ask_expert:
            mFragmentManager.updateContent( new AskExpertFragment(), "AskExpertFragment", null );
            break;

         case R.id.action_notification:
            mFragmentManager.updateContent( new NotificationFragment(), "NotificationFragment", null );
            break;

         case R.id.action_help:
            mFragmentManager.updateContent( new HelpFragment(), "HelpFragment", null );
            break;
      }

      return true;
   }
}
