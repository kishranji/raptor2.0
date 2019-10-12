package com.neet.raptor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.neet.raptor.R;
import com.neet.raptor.util.KToast;
import com.neet.raptor.views.otp.OtpTextView;

public class OtpActivity extends AppCompatActivity {
    OtpTextView otpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        init();
    }

    private void init() {

        otpTextView = findViewById(R.id.otp_view);
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (otpTextView.getOTP().trim().length() != 6) {
                    //Toast.makeText(OtpActivity.this, "Enter your OTP", Toast.LENGTH_SHORT).show();
                    KToast.errorToast( OtpActivity.this, "Enter your OTP" );
                    return;
                }
                callForgotPassword();
            }
        });
    }

    private void callForgotPassword() {
        startActivity( new Intent( OtpActivity.this, ResetPasswordActivity.class ) );
    }
}
