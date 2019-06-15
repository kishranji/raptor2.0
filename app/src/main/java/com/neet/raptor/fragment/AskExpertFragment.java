package com.neet.raptor.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neet.raptor.AppHelper;
import com.neet.raptor.R;
import com.neet.raptor.util.KToast;
import com.neet.raptor.util.KeyboardUtils;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.NormalFile;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.vincent.filepicker.activity.BaseActivity.IS_NEED_FOLDER_LIST;


public class AskExpertFragment extends Fragment {

   View mView;
   FragmentActivity mContext;

   EditText mUserNameEDT, mEmailEDT, mMobileEDT, mHeadingEDT, mMessageEDT;
   Button mExpertSubmitBut;
   TextView mBrowserTXT;
   TextView mFileNameTXT;

   @Override
   public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
      // Inflate the layout for this fragment
      mView = inflater.inflate( R.layout.fragment_ask_expert, container, false );

      init( mView );
      return mView;
   }

   private void init( View mView ) {

      mContext = getActivity();

      mUserNameEDT = mView.findViewById( R.id.user_name_EDT );
      mEmailEDT = mView.findViewById( R.id.user_mail_EDT );
      mMobileEDT = mView.findViewById( R.id.user_mobile_number_EDT );
      mHeadingEDT = mView.findViewById( R.id.user_title_EDT );
      mMessageEDT = mView.findViewById( R.id.user_message_EDT );

      mExpertSubmitBut = mView.findViewById( R.id.expert_submit );
      mBrowserTXT = mView.findViewById( R.id.user_browse_txt );
      mFileNameTXT = mView.findViewById( R.id.file_name_txt );

      mExpertSubmitBut.setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick( View view ) {
            validation();
         }
      } );

      mBrowserTXT.setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick( View view ) {
            Intent intent = new Intent( mContext, NormalFilePickActivity.class );
            intent.putExtra( Constant.MAX_NUMBER, 1 );
            intent.putExtra( IS_NEED_FOLDER_LIST, true );
            intent.putExtra( NormalFilePickActivity.SUFFIX,
                    new String[]{ "xlsx", "xls", "doc", "dOcX", "ppt", ".pptx", "pdf" } );
            mContext.startActivityForResult( intent, Constant.REQUEST_CODE_PICK_FILE );
         }
      } );

      // String[] aFile=new String[]
   }


   private boolean validation() {

      boolean mBoolean = true;

      if( mUserNameEDT.getText().toString().trim().length() == 0 ) {
         mBoolean = false;
         KToast.errorToast( mContext, "Enter your user name" );
         KeyboardUtils.showSoftKeyboard( mContext, mUserNameEDT );
      } else if( mEmailEDT.getText().toString().trim().length() == 0 ) {
         mBoolean = false;
         KToast.errorToast( mContext, "Enter your EmailId" );
         KeyboardUtils.showSoftKeyboard( mContext, mEmailEDT );
      } else if( !AppHelper.isValidEmail( mEmailEDT.getText().toString().trim() ) ) {
         mBoolean = false;
         KToast.errorToast( mContext, "Enter your valid EmailId" );
         KeyboardUtils.showSoftKeyboard( mContext, mEmailEDT );
      } else if( mMobileEDT.getText().toString().trim().length() == 0 ) {
         mBoolean = false;
         KToast.errorToast( mContext, "Enter your Mobile number" );
         KeyboardUtils.showSoftKeyboard( mContext, mMobileEDT );
      } else if( mMobileEDT.getText().toString().trim().length() < 10 ) {
         mBoolean = false;
         KToast.errorToast( mContext, "Enter your valid Mobile number" );
         KeyboardUtils.showSoftKeyboard( mContext, mMobileEDT );
      } else if( mHeadingEDT.getText().toString().trim().length() == 0 ) {
         mBoolean = false;
         KToast.errorToast( mContext, "Enter your Title" );
         KeyboardUtils.showSoftKeyboard( mContext, mHeadingEDT );
      } else if( mMessageEDT.getText().toString().trim().length() == 0 ) {
         mBoolean = false;
         KToast.errorToast( mContext, "Enter your Message" );
         // KeyboardUtils.showSoftKeyboard( mContext, mMessageEDT );
      }

      return mBoolean;
   }

   @Override
   public void onActivityResult( int requestCode, int resultCode, Intent data ) {
      switch( requestCode ) {
         case Constant.REQUEST_CODE_PICK_FILE:
            if( resultCode == RESULT_OK ) {
               ArrayList<NormalFile> list = data.getParcelableArrayListExtra( Constant.RESULT_PICK_FILE );
               StringBuilder builder = new StringBuilder();
               for( NormalFile file : list ) {
                  String path = file.getName();
                  builder.append( path + "\n" );
               }
               mFileNameTXT.setText( builder.toString() );
            }
            break;
      }
   }


}
