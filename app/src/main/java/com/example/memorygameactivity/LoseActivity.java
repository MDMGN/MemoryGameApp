package com.example.memorygameactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        this.setFinishOnTouchOutside(false);
    }
    public void onClickexit(View view){
        finish();
    }
    public void onClickRestart(View view){
        Intent intent=new Intent(this,GameActivity.class);
        startActivity(intent);
        finish();
    }
}