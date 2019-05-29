package com.amazonaws.demo.androidpubsub;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class SetActivity extends AppCompatActivity {

    TextView temperSet;
    TextView soilMoiSet;
    //CollecStatic setStat = new CollecStatic();
    /*CollecStatic setStat = (CollecStatic)getApplicationContext();
    int setting_temper = setStat.getSetting_temper();
    int setting_soilMoi = setStat.getSetting_soilMoi();*/
    static int setting_temper = 25;
    static int setting_soilMoi = 200;
    private SharedPreferences pref;

    static int REQUEST_CODE_ALPHA = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);


        temperSet =findViewById(R.id.temperSet);
        soilMoiSet =findViewById(R.id.soilMoiSet);


        String strTem = String.valueOf(setting_temper);
        temperSet.setText(strTem);
        String strSoilMoi = String.valueOf(setting_soilMoi);
        soilMoiSet.setText(strSoilMoi);

    }

    public void onOKButtonClicked(View v){
        int setting_temper = Integer.parseInt(temperSet.getText().toString());
        int setting_soilMoi = Integer.parseInt(soilMoiSet.getText().toString());

        /*// Data 객체 생성
        Data data = new Data(setting_temper, setting_soilMoi);

        // Intent에 Data객체 저장
        Intent intent = new Intent(SetActivity.this, MainActivity.class);
        intent.putExtra("data",data);
        startActivityForResult(intent,REQUEST_CODE_ALPHA);*/
         pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("setting_temper", setting_temper);
        editor.putInt("setting_soilMoi",setting_soilMoi);

        editor.commit();







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

        String strSoilMoi = String.valueOf(setting_soilMoi);
        soilMoiSet.setText(strSoilMoi);

    }
    public void soilMoiDownButtonClicked(View v){
        setting_soilMoi--;

        String strSoilMoi = String.valueOf(setting_soilMoi);
        soilMoiSet.setText(strSoilMoi);

    }




}
