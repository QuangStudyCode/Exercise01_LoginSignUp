package com.example.exercise01_loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnLogin)
    Button button;

    @BindView(R.id.edtLoginPhone)
    EditText edtLoginPhone;

    @BindView(R.id.edtLoginPass)
    EditText edtLoginPass;

    @BindView(R.id.tvSignUp)
    TextView tvSignUp;

    @BindView(R.id.tvLogin)
    TextView tvLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        Bundle registationData = getIntent().getBundleExtra("registationData");

        if (registationData != null) {
            String phone = registationData.getString("phone");
            edtLoginPhone.setText(phone.toString());
            tvLogin.setText("Welcome " + phone.toString());
        }

        button.setOnClickListener(view -> {
            boolean check = validateInfor(edtLoginPhone, edtLoginPass);
            if (check) {
                if (registationData != null) {
                    String pass = registationData.getString("pass", " ");
                    if (pass.equals(edtLoginPass.getText().toString())) {
                        Intent intent = new Intent(LoginActivity.this, DashBoradActivity.class);
                        String userName = edtLoginPhone.getText().toString();
                        intent.putExtra("userName", userName);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_LONG).show();
                    }
                }

            } else {
                Toast.makeText(LoginActivity.this, "Check your information again", Toast.LENGTH_LONG).show();
            }


        });

        tvSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }

    private Boolean validateInfor(EditText editText, EditText edtLoginPass) {
        String phoneNumber = editText.getText().toString();
        String pass = edtLoginPass.getText().toString();
        String phoneRegex = "^\\d{10}$";
        if (phoneNumber.length() == 0) {
            editText.requestFocus();
            editText.setError("FILED CANNOT BE EMPTY!");
            return false;
        } else if (pass.length() == 0) {
            edtLoginPass.requestFocus();
            edtLoginPass.setError("FILED CANNOT BE EMPTY!");
            return false;
        } else if (phoneNumber.length() > 10) {
            editText.setError("MAX 10");
            return false;
        } else if (phoneNumber.length() < 0) {
            editText.setError("MINIUM 10");
            return false;
        } else if (!phoneNumber.matches(phoneRegex)) {
            editText.setError("Invalid phone number");
            return false;
        } else {
            return true;
        }
    }


}