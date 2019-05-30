package com.amazonaws.demo.androidpubsub;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.amazonaws.demo.androidpubsub.PubSubActivity.current_moi;
import static com.amazonaws.demo.androidpubsub.PubSubActivity.current_soilMoi;
import static com.amazonaws.demo.androidpubsub.PubSubActivity.current_temper;

public class CurrentActivity extends AppCompatActivity {


    TextView temperCur;
    TextView soilMoiCur;
    TextView moiCur;
    TextView receiveMsg;

    PubSubActivity pubSubActivity = new PubSubActivity();


     SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);

        temperCur =findViewById(R.id.temperCur);
        soilMoiCur =findViewById(R.id.soilMoiCur);
        moiCur =findViewById(R.id.moiCur);
        //receiveMsg = findViewById(R.id.receiveMsg);

        //pubSubActivity.receiveMsg.setText("바꿔");


        pref = getSharedPreferences("pref", MODE_PRIVATE);


        current_temper = pref.getFloat("current_temper",current_temper); //key, value(defaults)
        current_soilMoi= pref.getFloat("current_soilMoi",current_soilMoi);
        current_moi=pref.getFloat("current_moi",current_moi);//좀있다 테스트
        temperCur.setText(String.valueOf(current_temper));
        soilMoiCur.setText(String.valueOf(current_soilMoi));
        moiCur.setText(String.valueOf(current_moi));//좀있다 테스트
        String strCuTem=String.valueOf(current_temper);
        String strCuSoilMoi=String.valueOf(current_soilMoi);
        String strCuMoi=String.valueOf(current_moi);
        temperCur.setText(strCuTem);
        soilMoiCur.setText(strCuSoilMoi);
        moiCur.setText(strCuMoi);


    }
    public void onBackButtonClicked(View v){
        finish();
    }
}
