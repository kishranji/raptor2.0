package com.neet.raptor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neet.raptor.R;
import com.neet.raptor.adapter.DashboardAdapter;

import java.util.ArrayList;
import java.util.Collections;


public class DashboardFragment extends Fragment {

   View mView;
   RecyclerView mRecyclerView;

   FragmentActivity mContext;

   ArrayList<String> mMenuArrayList;
   private DashboardAdapter mDashboardAdapter;


   @Override
   public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
      // Inflate the layout for this fragment
      mView = inflater.inflate( R.layout.fragment_dashboard, container, false );

      init();
      return mView;
   }

   private void init() {

      mContext = getActivity();

      mRecyclerView = mView.findViewById( R.id.dashboard_recyclerview );


      mMenuArrayList = new ArrayList<>();

      String aPassvalue = getArguments().getString( "passvalue" );
      if( aPassvalue.equals( "student" ) ) {
         String[] aMenuTitles = mContext.getResources().getStringArray( R.array.student_menu_items );
         Collections.addAll( mMenuArrayList, aMenuTitles );
      } else if( aPassvalue.equals( "parents" ) ) {
         String[] aMenuTitles = mContext.getResources().getStringArray( R.array.student_menu_items );
         Collections.addAll( mMenuArrayList, aMenuTitles );
      } else {
         String[] aMenuTitles = mContext.getResources().getStringArray( R.array.student_menu_items );
         Collections.addAll( mMenuArrayList, aMenuTitles );
      }

      configRecycler();

   }


   private void configRecycler() {
      /*GridLayoutManager layoutManager = new GridLayoutManager( mContext, 2 );
      layoutManager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
         @Override
         public int getSpanSize( int position ) {
            int aSize = mMenuArrayList.size();
            if( aSize == 1 )
               return 2;
            else if( aSize == 2 )
               return 2;
            else {
               return 1;
            }
         }
      } );
*/

      GridLayoutManager layoutManager = new GridLayoutManager( mContext, 2 );
     /* layoutManager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
         @Override
         public int getSpanSize( int position ) {
            int aSize = mMenuArrayList.size();

          *//*  if( aSize == 1 )
               return 6;
            else if( aSize == 2 )
               return 3;
            else if( aSize == 3 ) {
               if( position == 2 ) return 6;
               else return 3;
            } else if( aSize == 4 )
               return 3;
            else {*//*
            if( position == 6 || position == 8 || position == 7 ) return 2;
            else return 3;

            // }
         }
      } );*/

      mRecyclerView.setLayoutManager( layoutManager );
      mDashboardAdapter = new DashboardAdapter( mContext, mMenuArrayList );
      mRecyclerView.setAdapter( mDashboardAdapter );
      mRecyclerView.setNestedScrollingEnabled( false );
      mRecyclerView.setHasFixedSize( true );
   }

}
