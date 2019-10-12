package com.neet.raptor.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.neet.raptor.R;
import com.neet.raptor.util.KToast;
import com.neet.raptor.util.KeyboardUtils;

public class ResetPasswordActivity extends AppCompatActivity {


    EditText mCreatePassword, mConfirmPassword;
    TextView mResetLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mCreatePassword = findViewById(R.id.create_password_EDT);
        mConfirmPassword = findViewById(R.id.confirm_password_EDT);
        mResetLayout = findViewById(R.id.reset_Layout);

        listeners();
    }

    private void listeners() {
        mResetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    callNextScreen();
                }
            }
        });

    }

    private void callNextScreen() {
        KToast.successToast(this, "Password Reset successfully");
    }

    private boolean validation() {

        boolean mBoolean = true;

        if (mCreatePassword.getText().toString().trim().length() == 0) {
            mBoolean = false;
            KToast.errorToast(this, "Enter your create password");
            KeyboardUtils.showSoftKeyboard(this, mCreatePassword);
        } else if (mConfirmPassword.getText().toString().trim().length() == 0) {
            mBoolean = false;
            KToast.errorToast(this, "Enter your confirm password");
            KeyboardUtils.showSoftKeyboard(this, mConfirmPassword);
        } else if (!mCreatePassword.getText().toString().trim().equals(mConfirmPassword.getText().toString().trim())) {
            mBoolean = false;
            KToast.errorToast(this, "Create password and confirm password must be same");
        }

        return mBoolean;
    }
}
