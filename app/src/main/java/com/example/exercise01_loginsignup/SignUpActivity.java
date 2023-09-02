package com.example.exercise01_loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    @BindView(R.id.tvBackLogin)
    TextView tvBackLogin;

    @BindView(R.id.edtSignUpPhone)
    EditText edtPhone;

    @BindView(R.id.edtSignUpPass)
    EditText edtPass;

    @BindView(R.id.edtSignUpComfirm)
    EditText edtComfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        btnSignUp.setOnClickListener(this);
        tvBackLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                boolean checkInfor = validateInfor(edtPhone, edtPass, edtComfirm);
                if(checkInfor){
                    Toast.makeText(SignUpActivity.this,"Register success!",Toast.LENGTH_LONG).show();
                    Bundle registationData = new Bundle();
                    registationData.putString("phone",edtPhone.getText().toString());
                    registationData.putString("pass",edtPass.getText().toString());

//                    sent to login
                    Intent intent1 = new Intent(SignUpActivity.this,LoginActivity.class);
                    intent1.putExtra("registationData", registationData);
                    startActivity(intent1);
                    finish();
                }
                break;

            case R.id.tvBackLogin:
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private Boolean validateInfor(EditText edtPhone, EditText edtPass, EditText edtComfirm) {
        String phone = edtPhone.getText().toString();
        String pass = edtPass.getText().toString();
        String comfirm = edtComfirm.getText().toString();
        String regex = "^\\d{10}$";

        if (phone.length() == 0) {
            edtPhone.setError("FILED IS NOT EMPTY!");
            return false;
        } else if (pass.length() == 0) {
            edtPass.setError("FILED IS NOT EMPTY!");
            return false;
        } else if (comfirm.length() == 0) {
            edtComfirm.setError("FILED IS NOT EMPTY!");
            return false;
        } else if (!phone.matches(regex)) {
            edtPhone.setError("Please enter only numbers!");
            return false;
        } else if (!comfirm.equals(pass)) {
            edtComfirm.setError("The fileds do not match!");
            return false;
        } else {
            return true;
        }
    }


}