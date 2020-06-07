package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    Button btn_islem;
    Boolean servis_durum=false;
    TextView text_deger;
    ProgressBar seviye_cubuk;

    private BroadcastReceiver yayin_alici = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //
            int seviye = intent.getIntExtra("level",10);
            seviye_cubuk.setProgress(seviye);
            text_deger.setText("Pil Seviyesi: " + Integer.toString(seviye)+ "%");
            int sarj_durumu = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
            if(sarj_durumu == BatteryManager.BATTERY_PLUGGED_AC || sarj_durumu == BatteryManager.BATTERY_PLUGGED_USB){
                text_deger.append("\nŞarj Oluyor...");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seviye_cubuk = findViewById(R.id.seviye_cubuk);
        text_deger = findViewById(R.id.text_deger);
        registerReceiver(yayin_alici,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        btn_islem = findViewById(R.id.btn_islem);

        btn_islem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!servis_durum){
                    Intent intent_baslat=new Intent(getApplicationContext(),Servisim.class);
                    startService(intent_baslat);
                    btn_islem.setText("Servisi Durdur");
                    servis_durum = true;
                }else{
                    Intent inten_durdur = new Intent(getApplicationContext(),Servisim.class);
                    stopService(inten_durdur);
                    btn_islem.setText("Servisi Baslat");
                    servis_durum = false;
                }
            }
        });

    }


}