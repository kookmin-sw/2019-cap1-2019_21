package com.amazonaws.demo.androidpubsub;

import java.io.Serializable;

public class Data implements Serializable {
    public int setting_temper ;
    public int setting_soilMoi ;

    public Data(int setting_temper, int setting_soilMoi) {
        this.setting_temper = setting_temper;
        this.setting_soilMoi = setting_soilMoi;
    }
}
