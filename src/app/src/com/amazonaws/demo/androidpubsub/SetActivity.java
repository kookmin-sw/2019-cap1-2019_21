package com.amazonaws.demo.androidpubsub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class SetActivity extends AppCompatActivity {

    TextView temperSet;
    TextView soilMoiSet;
    TextView humiSet;

    //CollecStatic setStat = new CollecStatic();
    /*CollecStatic setStat = (CollecStatic)getApplicationContext();
    int setting_temper = setStat.getSetting_temper();
    int setting_soilMoi = setStat.getSetting_soilMoi();*/
    static int setting_temper = 25;
    static int setting_soilMoi = 200;
    static int setting_humid =  200;

    static int current_temper = 25;
    static int current_soilMoi = 200;
    static int current_humid =  200;

    private SharedPreferences pref;

    static int REQUEST_CODE_ALPHA = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);


        setting_temper = current_temper;
        setting_soilMoi = current_soilMoi;
        setting_humid = current_humid;






        temperSet =findViewById(R.id.temperSet);
        soilMoiSet =findViewById(R.id.soilMoiSet);


        String strTem = String.valueOf(setting_temper);
        temperSet.setText(strTem);
        String strSoilMoi = String.valueOf(setting_soilMoi);
        soilMoiSet.setText(strSoilMoi);
        //humiSet.setText();

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

        /*((PubSubActivity)PubSubActivity.mContext).connectClick(PubSubActivity.btnConnect);
        ((PubSubActivity)PubSubActivity.mContext).subscribeClick(View view);
        ((PubSubActivity)PubSubActivity.mContext).publishClick(View view);*/

        /*LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main, null);
        Button btnConnect = (Button)view.findViewById(R.id.btnConnect);
        Button btnSubscribe = (Button)view.findViewById(R.id.btnSubscribe);
        Button btnPublish = (Button)view.findViewById(R.id.btnPublish);
            PubSubActivity mContext = new PubSubActivity();
         ((PubSubActivity)PubSubActivity.mContext).connectClick(mContext.btnConnect);
        ((PubSubActivity)PubSubActivity.mContext).subscribeClick(mContext.btnSubscribe);
        ((PubSubActivity)PubSubActivity.mContext).publishClick(mContext.btnPublish);
        확인을 누르면 절차들이 진행이 자동으로 될수있도록.

        */

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
