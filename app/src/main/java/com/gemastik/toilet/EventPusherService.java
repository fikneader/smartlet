package com.gemastik.toilet;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.json.JSONObject;

/**
 * Created by anjas on 01/04/17.
 */

public class EventPusherService extends Service {
    public EventPusherService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PusherOptions opt = new PusherOptions();
        opt.setCluster("ap1");

        Pusher pusher = new Pusher("d09e3b7b2ebd5914e5fd", opt);
        // subscribe to our "messages" channel
        Channel channel = pusher.subscribe("scantour_event_channel");

        // listen for the "new_message" event
        channel.bind("data_event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {

                String newsTitle = "";
                try{
                    JSONObject object = new JSONObject(data);
                    newsTitle = object.getString("title");
                }catch (Exception e){

                }

                //Toast.makeText(getApplicationContext(), "New Event come for you, "+eName, Toast.LENGTH_SHORT).show();

                Intent targetIntent = new Intent(getApplicationContext(), InfoActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
                Notification notification = new Notification.Builder(getApplicationContext())
                        .setContentIntent(pendingIntent)
                        .setContentTitle("SmartLet")
                        .setContentText("Keadaan "+newsTitle)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setSmallIcon(R.drawable.a).build();
                notificationManager.notify(0, notification);
            }
        });

        // connect to the Pusher API
        pusher.connect();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
