package com.amazonaws.demo.androidpubsub;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
    public void onTestEnButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), PubSubActivity.class);
        startActivity(intent);
    }

    public void onSetButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), SetActivity.class);
        startActivity(intent);
    }
}
