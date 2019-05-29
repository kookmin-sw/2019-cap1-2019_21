package com.amazonaws.demo.androidpubsub;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static int setting_temper;
    static int setting_soilMoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*Intent intent = getIntent();
        Data data = (Data)intent.getSerializableExtra("T");
         setting_temper = data.setting_temper;
         setting_soilMoi = data.setting_soilMoi;*/

    }
    public void onTestEnButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), PubSubActivity.class);
        /*Data dataIot = new Data(setting_temper, setting_soilMoi);
        intent.putExtra("dataIot", dataIot);*/
        startActivity(intent);
    }

    public void onSetButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), SetActivity.class);


        startActivity(intent);
    }
}
