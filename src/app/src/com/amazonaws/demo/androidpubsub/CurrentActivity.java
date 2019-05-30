package com.amazonaws.demo.androidpubsub;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CurrentActivity extends AppCompatActivity {


    TextView temperCur;
    TextView soilMoiCur;
    TextView moiCur;

    static int current_temper= 25;
    static int current_soilMoi = 200;
    static int current_moi = 200;


     SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);

        temperCur =findViewById(R.id.temperCur);
        soilMoiCur =findViewById(R.id.soilMoiCur);
        moiCur =findViewById(R.id.moiCur);

        String strCuTem=String.valueOf(current_temper);
        String strCuSoilMoi=String.valueOf(current_soilMoi);
        String strCuMoi=String.valueOf(current_moi);
        temperCur.setText(strCuTem);
        soilMoiCur.setText(strCuSoilMoi);
        moiCur.setText(strCuMoi);

        pref = getSharedPreferences("pref", MODE_PRIVATE);

        current_temper = pref.getInt("current_temper",current_temper); //key, value(defaults)
        current_soilMoi= pref.getInt("current_soilMoi",current_soilMoi);
        //current_moi=pref.getInt("current_moi",current_moi);//좀있다 테스트
        temperCur.setText(String.valueOf(current_temper));
        soilMoiCur.setText(String.valueOf(current_soilMoi));
        //moiCur.setText(String.valueOf(current_moi));//좀있다 테스트

    }
    public void onBackButtonClicked(View v){
        finish();
    }
}
