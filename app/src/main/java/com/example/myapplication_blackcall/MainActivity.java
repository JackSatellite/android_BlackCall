package com.example.myapplication_blackcall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startListenCall(View view) {
        Intent intent=new Intent(this,ListenCallService.class);
        startService(intent);
    }

    public void stopListenCall(View view) {
        Intent intent=new Intent(this,ListenCallService.class);
        stopService(intent);
    }
}
