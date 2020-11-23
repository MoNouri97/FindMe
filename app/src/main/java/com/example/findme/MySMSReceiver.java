package com.example.findme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MySMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "sms received", Toast.LENGTH_SHORT).show();

        String messageBody, phoneNumber;
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    messageBody = messages[0].getMessageBody();
                    phoneNumber = messages[0].getDisplayOriginatingAddress();

                    if (messageBody.contains("FindMe")) {
                        // send notification

                        NotificationCompat.Builder notif = new NotificationCompat.Builder(context
                                , "CHANNEL_FINDME");
                        notif.setSmallIcon(android.R.drawable.ic_dialog_alert);
                        notif.setContentTitle("sms received");
                        notif.setContentText(messageBody);
                        notif.setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        notif.setVibrate(new long[]{1000, 5000, 2000});
                        Uri sound = RingtoneManager.getActualDefaultRingtoneUri(context,
                                RingtoneManager.TYPE_ALARM);

                        Intent i = new Intent(context, AddActivity.class);
                        i.putExtra("body", messageBody);
                        i.putExtra("number", phoneNumber);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                                i,
                                PendingIntent.FLAG_ONE_SHOT);
                        notif.setContentIntent(pendingIntent);

                        NotificationManagerCompat notificationManager =
                                NotificationManagerCompat.from(context);
                        // android oreo
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new
                                    NotificationChannel("CHANNEL_FINDME",
                                    "MY_APP_CHANNEL",
                                    NotificationManager.IMPORTANCE_DEFAULT);
                            channel.setDescription("description");
                            notificationManager.createNotificationChannel(channel);
                        }
                        notificationManager.notify(1,notif.build());


                    }
                }
            }
        }
    }
}
