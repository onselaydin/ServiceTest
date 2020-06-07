package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;



public class Servisim extends Service {

    timer t;
    int i;

    @Override
    public void onCreate() {
        super.onCreate();
        t=new timer(60000,10000); //(ne kadar sürecek,hızı ne olacak)
        i=0;
        t.start(); //ile timer çalışacak
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class timer extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Toast.makeText(getApplicationContext(),"servis başlatıldı",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinish() {

        }
    }

}
