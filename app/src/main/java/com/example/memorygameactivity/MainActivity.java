package com.example.memorygameactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_NAME = "com.example.android.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private EditText edittext_name;
    private TextView textpoint;
    private TextView text_record;
    private TextView text_recordname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textpoint=findViewById(R.id.text_point);
        edittext_name=findViewById(R.id.edittext_name);
        text_recordname=findViewById(R.id.text_recordname);
        text_record=findViewById(R.id.text_record);
        comprobrarRecord();
    }

    public void onClickExit(View view) {
        finish();
    }

    public void onClickGame(View view) {
        Intent intent=new Intent(this,GameActivity.class);
        String name=edittext_name.getText().toString();
        String textview_maxpoint=textpoint.getText().toString();
        //Envia el valor de getName a GameActivity.
        intent.putExtra(EXTRA_MESSAGE_NAME, name);
        //Inicia la segunda Activity y espera el resultado"startActivityResult();".
                startActivityForResult(intent,TEXT_REQUEST);


    }
    public void comprobrarRecord(){
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.key_file),Context.MODE_PRIVATE);
        int highScore = sharedPref.getInt(getString(R.string.record), 0);
        String getname=sharedPref.getString(getString(R.string.record_playername),"");
        if(getname.equals("")){
            getname="Incognito";
        }
        if(highScore>0){
            text_record.setVisibility(View.VISIBLE);
            text_recordname.setVisibility(View.VISIBLE);
            text_recordname.setText(getString(R.string.name)+": "+getname);
            textpoint.setVisibility(View.VISIBLE);
            textpoint.setText(getString(R.string.textView_point)+" "+Integer.toString(highScore));

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        comprobrarRecord();
    }
}