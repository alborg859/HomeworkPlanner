package onion.homeworkplanner;


import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import android.util.Log;


public class AlertReceiverEdit extends BroadcastReceiver {

    int min;



    private NotificationManager mManager;

    @Override

    public void onReceive(Context context, Intent intent) {
        HomeworkHelper mHomeworkHelper = new HomeworkHelper(context);
        Homework hm = new Homework();
        EditHomework editHomework = new EditHomework();


        Bundle bundle = intent.getExtras();
        int ids = bundle.getInt("code");
        int ids1 = bundle.getInt("code1");
        String subject = bundle.getString("subj");
        String description = bundle.getString("desc");
        String date = bundle.getString("date");




        Log.d("Receiver", "Brextras received: Subject " + subject);
        Log.d("Receiver", "Brextras received: Description " + description);
        Log.d("Receiver", "Brextras received: Data "  + date);
        Log.d("Receiver", "Brextras received: ID  " + ids);
        Log.d("Receiver", "Brextras received: Days;eft  " + ids1);













        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(subject, description,date, ids1, ids);
        notificationHelper.getManager().notify(ids  , nb.build());


    }


}
