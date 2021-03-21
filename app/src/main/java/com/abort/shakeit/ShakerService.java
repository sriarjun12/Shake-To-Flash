package com.abort.shakeit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.squareup.seismic.ShakeDetector;

import java.util.Timer;
import java.util.TimerTask;

public class ShakerService extends Service implements ShakeDetector.Listener {
    public int counter=0;
    @Override
    public void onCreate() {
        super.onCreate();
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

//register your sensor manager listener here
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void hearShake() {
        // or you can replace **'this'** with your **ActivityName.this**
        Context ctx =this;
        try {
            Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.abort.shakeit");
            ctx.startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
        }
        Toast.makeText(this, "ShackFromService", Toast.LENGTH_SHORT).show();
    }
}
