package com.example.cse492.gettingtext;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


//public class BatteryInformation extends Activity {
public class BatteryInfo extends Activity {
    private TextView batteryInfo;
    private ImageView imageBatteryState;
    //Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battery_main);
        batteryInfo=(TextView)findViewById(R.id.textViewBatteryInfo);
        imageBatteryState=(ImageView)findViewById(R.id.imageViewBatteryState);

        this.registerReceiver(this.batteryInfoReceiver,	new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int  health= intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
            int  icon_small= intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL,0);
            int  level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            int  plugged= intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
            boolean  present= intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
            int  scale= intent.getIntExtra(BatteryManager.EXTRA_SCALE,0);
            int  status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);
            String  technology= intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            int  temperature= intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
            int  voltage= intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);


            float temperature1 = ((float)temperature)/10;
            //Retrieving the temperature value, which is set by the user
            String temp_value = null;
            Intent iin= getIntent();
            Bundle bundle = iin.getExtras();
            if(bundle !=null)
            {
                temp_value =(String) bundle.get("value");
            }


            batteryInfo.setText(
                    "Health: "+health+"\n"+
                    "Icon Small:"+icon_small+"\n"+
                    "Level: "+level+"\n"+
                    "Plugged: "+plugged+"\n"+
                    "Present: "+present+"\n"+
                    "Scale: "+scale+"\n"+
                    "Status: "+status+"\n"+
                    "Technology: "+technology+"\n"+
                    "Temperature: "+temperature1+"\n"+
                    "Voltage: "+voltage+"\n \n \n"+
                    "And the temperature threshehold is: "+ temp_value+"\n" );
            imageBatteryState.setImageResource(icon_small);

            int client_value = Integer.parseInt(temp_value);
/*
            if (temperature == client_value )
            {
                //Tell the user about the notification
                String notif =" WARNING: CPU is throttled!";
                Toast msg = Toast.makeText(getBaseContext(),notif, Toast.LENGTH_LONG);
                msg.show();
                msg.show();

                // notification is selected
                Intent intent2 = new Intent(this, NotificationReceiverActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

                // Build notification
                // Actions are just fake
                Notification noti = new Notification.Builder(this)
                        .setContentTitle("This is working Chada! Awesome ;)")
                        .setContentText("Subject").setSmallIcon(R.drawable.icon)
                        .setContentIntent(pIntent)
                                //.setLargeIcon(R.drawable.Mario_icon)
                        .addAction(R.drawable.icon, "Call", pIntent)
                        .addAction(R.drawable.icon, "More", pIntent)
                        .addAction(R.drawable.icon, "And more", pIntent).build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // hide the notification after its selected
                noti.flags |= Notification.FLAG_AUTO_CANCEL;

                notificationManager.notify(0, noti);

            }
            */
        }
    };

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void createNotification(View view) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("This is working Chada! Awesome ;)")
                .setContentText("Subject").setSmallIcon(R.drawable.icon)
                .setContentIntent(pIntent)
                        //.setLargeIcon(R.drawable.Mario_icon)
                .addAction(R.drawable.icon, "Call", pIntent)
                .addAction(R.drawable.icon, "More", pIntent)
                .addAction(R.drawable.icon, "And more", pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

        //Tell the user about the notification
        String notif =" WARNING: CPU is throttled!";
        Toast msg = Toast.makeText(getBaseContext(),notif, Toast.LENGTH_LONG);
        msg.show();
        msg.show();

    }


}
