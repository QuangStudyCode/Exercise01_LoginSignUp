package com.example.exercise01_loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashBoradActivity extends AppCompatActivity {
    @BindView(R.id.tvDashboard)
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_borad);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("userName");

        textView.setText("Hi, "+nameUser.toString());
    }
}