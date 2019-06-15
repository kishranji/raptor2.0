package com.neet.raptor.adapter;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neet.raptor.R;
import com.neet.raptor.fragmentmanager.APPFragmentManager;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

   private APPFragmentManager mFragmentManager;
   FragmentActivity mContext;
   ArrayList<String> mMenuInfoList;
   private ArrayList<Integer> myIcons;
   String mUser;

   DashboardMenuCallback mMenuCallback;

   int[] mStringColor;

   public DashboardAdapter( FragmentActivity context, ArrayList<String> aMenuList, DashboardMenuCallback aMenuCallback, String mPassvalue ) {
      try {
         mContext = context;
         //    myMenuInfoList = cropInfoList;
         mMenuInfoList = aMenuList;
         mFragmentManager = new APPFragmentManager( mContext );
         mMenuCallback = aMenuCallback;
         myIcons = new ArrayList<>();
         mUser = mPassvalue;

         TypedArray aIcons;

         if( mPassvalue.equals( "student" ) ) {
            aIcons = mContext.getResources().obtainTypedArray( R.array.dash_icon_student );
         } else if( mPassvalue.equals( "parents" ) ) {
            aIcons = mContext.getResources().obtainTypedArray( R.array.dash_icon_parent );
         } else {
            aIcons = mContext.getResources().obtainTypedArray( R.array.dash_icon_teacher );
         }

         mStringColor = mContext.getResources().getIntArray( R.array.menu_color_student );

         for( int i = 0; i < aIcons.length(); i++ ) {
            myIcons.add( aIcons.getResourceId( i, -1 ) );
         }


      } catch( Exception e ) {
         e.printStackTrace();
      }
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int i ) {
      View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.layout_inflate_menu_item, parent, false );
      return new ViewHolder( itemView );
   }

   @Override
   public void onBindViewHolder( @NonNull ViewHolder viewHolder, int i ) {

      viewHolder.mMenuName.setText( mMenuInfoList.get( i ) );
      viewHolder.mMenuImage.setImageResource( myIcons.get( i ) );

      viewHolder.mView.setBackgroundColor( mStringColor[ i ] );
      viewHolder.mMenuImage.setColorFilter( mStringColor[ i ], android.graphics.PorterDuff.Mode.SRC_IN );

      viewHolder.mMenuLayout.setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick( View v ) {
            mMenuCallback.menuItem( i );
         }
      } );
   }

   @Override
   public int getItemCount() {
      return mMenuInfoList.size();
   }

   class ViewHolder extends RecyclerView.ViewHolder {

      TextView mMenuName;
      ImageView mMenuImage;
      CardView mMenuLayout;
      View mView;

      ViewHolder( @NonNull View itemView ) {
         super( itemView );

         mMenuName = itemView.findViewById( R.id.menu_name );
         mMenuImage = itemView.findViewById( R.id.menu_image );
         mView = itemView.findViewById( R.id.corner_background );
         mMenuLayout = itemView.findViewById( R.id.menu_layout );
      }
   }

   public interface DashboardMenuCallback {
      void menuItem( int position );
   }
}
