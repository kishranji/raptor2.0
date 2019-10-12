package com.neet.raptor.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.neet.raptor.R;
import com.neet.raptor.util.KeyboardUtils;

public class ForgotPassword extends AppCompatActivity {

    EditText aEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        init();
    }

    private void init() {
        aEditText = findViewById( R.id.forgot_userid_EDT );

        findViewById( R.id.btn_okay ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                if( aEditText.getText().toString().trim().length() == 0 ) {
                    // KToast.errorToast(LoginActivity.this, "Enter your user id");
                    Toast.makeText( ForgotPassword.this, "Enter your user id", Toast.LENGTH_SHORT ).show();
                    KeyboardUtils.showSoftKeyboard( ForgotPassword.this, aEditText );
                    return;
                }

                callOtpActivity();
            }
        } );
    }

    private void callOtpActivity() {

        startActivity( new Intent( ForgotPassword.this, OtpActivity.class ) );
    }
}
