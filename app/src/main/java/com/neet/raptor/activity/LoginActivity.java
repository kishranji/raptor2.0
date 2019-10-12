package com.neet.raptor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.neet.raptor.R;
import com.neet.raptor.util.KToast;
import com.neet.raptor.util.KeyboardUtils;

public class LoginActivity extends AppCompatActivity {

   AlertDialog confirmationAlertDialog;

   EditText mPassword, mUserId;

   ImageView mShowPassBtn;

   Boolean showPass = false;

   @Override
   protected void onCreate( Bundle savedInstanceState ) {
      super.onCreate( savedInstanceState );
      setContentView( R.layout.activity_login );

      mUserId = findViewById( R.id.user_id_EDT );
      mPassword = findViewById( R.id.password_EDT );
      mShowPassBtn = findViewById( R.id.login_show_pass );

      findViewById( R.id.txt_forgot_password ).setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick( View view ) {
            callForgotPassActivity();
         }
      } );

      mShowPassBtn.setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick( View view ) {
            showPass = !showPass;
            changePasswordView();
         }
      } );

      findViewById( R.id.btn_login ).setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick( View view ) {

            if( validation() ) {

               callNextScreen();
            }
         }
      } );
   }

   private void callForgotPassActivity() {
      startActivity( new Intent( LoginActivity.this, ForgotPassword.class ) );
   }

   private void changePasswordView() {

      //If you want to change show password icon on click change here.

      if( showPass ) {
         mPassword.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
         mShowPassBtn.setImageResource( R.drawable.password );
      }
      else {
         mPassword.setInputType( InputType.TYPE_CLASS_TEXT |
                 InputType.TYPE_TEXT_VARIATION_PASSWORD );
         mShowPassBtn.setImageResource( R.drawable.password );
      }

      int pos = mPassword.getText().length();
      mPassword.setSelection( pos );

   }

   private void callNextScreen() {
      KToast.successToast( this, "Login success" );

      Intent aIntent = new Intent( this, MainActivity.class );


      if( mUserId.getText().toString().equalsIgnoreCase( "s" ) ) {
         aIntent.putExtra( "value", "student" );
      } else if( mUserId.getText().toString().equalsIgnoreCase( "p" ) ) {
         aIntent.putExtra( "value", "parents" );
      } else {
         aIntent.putExtra( "value", "teacher" );
      }
      startActivity( aIntent );
   }

   private boolean validation() {

      boolean mBoolean = true;

      if( mUserId.getText().toString().trim().length() == 0 ) {
         mBoolean = false;
         KToast.errorToast( this, "Enter your user id" );
         KeyboardUtils.showSoftKeyboard( this, mUserId );
      } else if( mPassword.getText().toString().trim().length() == 0 ) {
         mBoolean = false;
         KToast.errorToast( this, "Enter your password" );
         KeyboardUtils.showSoftKeyboard( this, mPassword );
      } else if( !mUserId.getText().toString().trim().equals( mPassword.getText().toString().trim() ) ) {
         mBoolean = false;
         KToast.errorToast( this, "Invalid user name and password" );
      }

      return mBoolean;
   }

}
