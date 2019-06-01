package com.neet.raptor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.neet.raptor.R;
import com.neet.raptor.util.KToast;
import com.neet.raptor.views.otp.OTPListener;
import com.neet.raptor.views.otp.OtpTextView;

public class LoginActivity extends AppCompatActivity {

    AlertDialog confirmationAlertDialog;

    EditText mPassword, mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserId = findViewById(R.id.user_id_EDT);
        mPassword = findViewById(R.id.password_EDT);

        findViewById(R.id.txt_forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {
                    callNextScreen();
                }
            }
        });
    }

    private void callNextScreen() {
        KToast.successToast(this, "Login success");
    }

    private boolean validation() {

        boolean mBoolean = true;

        if (mUserId.getText().toString().trim().length() == 0) {
            mBoolean = false;
            KToast.errorToast(this, "Enter your user id");
        } else if (mPassword.getText().toString().trim().length() == 0) {
            mBoolean = false;
            KToast.errorToast(this, "Enter your password");
        }

        return mBoolean;
    }

    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_login_confirmation, viewGroup, false);


        EditText aEditText = dialogView.findViewById(R.id.forgot_userid_EDT);


        dialogView.findViewById(R.id.btn_okay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (aEditText.getText().toString().trim().length() == 0) {
                   // KToast.errorToast(LoginActivity.this, "Enter your user id");
                    Toast.makeText(LoginActivity.this,"Enter your user id",Toast.LENGTH_SHORT).show();
                    return;
                }
                confirmationAlertDialog.cancel();
                showOTPDialog();
            }
        });

        dialogView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmationAlertDialog.cancel();
            }
        });

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        confirmationAlertDialog = builder.create();
        confirmationAlertDialog.show();
    }

    private void showOTPDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_otp_confirmation, viewGroup, false);


        OtpTextView otpTextView = dialogView.findViewById(R.id.otp_view);
        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {
                // fired when user types something in the Otpbox
            }

            @Override
            public void onOTPComplete(String otp) {

                // fired when user has entered the OTP fully.

            }
        });

        dialogView.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (otpTextView.getOTP().trim().length() != 6) {
                 //   KToast.errorToast(LoginActivity.this, "Enter your OTP");
                    Toast.makeText(LoginActivity.this,"Enter your OTP",Toast.LENGTH_SHORT).show();
                    return;
                }
                callForgotPassword();
            }
        });

        dialogView.findViewById(R.id.btn_resend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmationAlertDialog.cancel();
            }
        });

        dialogView.findViewById(R.id.txt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmationAlertDialog.cancel();
            }
        });

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        confirmationAlertDialog = builder.create();
        confirmationAlertDialog.show();
    }

    private void callForgotPassword() {
        confirmationAlertDialog.cancel();
        KToast.successToast(LoginActivity.this, "OTP validation success");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        }, 2000);

    }
}
