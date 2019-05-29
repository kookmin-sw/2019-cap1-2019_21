package com.amazonaws.demo.androidpubsub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SetActivity extends AppCompatActivity {

    TextView temperSet;
    TextView soilMoiSet;
    public static int setting_temper = 25;
    public static int setting_soilMoi= 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);


        temperSet =findViewById(R.id.temperSet);
        soilMoiSet =findViewById(R.id.soilMoiSet);
        /*CollecStatic setStat = ((CollecStatic)getApplicationContext());
        int setting_temper = setStat.getSetting_temper();
        int setting_soilMoi = setStat.getSetting_soilMoi();
        temperSet.setText(setting_temper);
        soilMoiSet.setText(setting_soilMoi);*/

    }
    public void onOKButtonClicked(View v){
        finish();
    }
    public void temperUpButtonClicked(View v){
        setting_temper++;
        String strTem = String.valueOf(setting_temper);
        temperSet.setText(strTem);
    }
    public void temperDownButtonClicked(View v){
        setting_temper--;
        String strTem = String.valueOf(setting_temper);

        temperSet.setText(strTem);
    }
    public void soilMoiUpButtonClicked(View v){
        setting_soilMoi++;

        String strTem = String.valueOf(setting_soilMoi);
        soilMoiSet.setText(strTem);

    }
    public void soilMoiDownButtonClicked(View v){
        setting_soilMoi--;

        String strSoilMoi = String.valueOf(setting_soilMoi);
        soilMoiSet.setText(strSoilMoi);

    }




}
