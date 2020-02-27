package onion.homeworkplanner;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import android.support.v4.app.NotificationCompat; import java.util.Arrays;

import java.util.Arrays;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";

    String formatdaysleft;

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }
    int[] sicons = {R.drawable.def, R.drawable.art, R.drawable.astronomy, R.drawable.biology, R.drawable.businessstudies, R.drawable.chemistry, R.drawable.citizenship, R.drawable.geography, R.drawable.history, R.drawable.it, R.drawable.law, R.drawable.literature, R.drawable.maths, R.drawable.music, R.drawable.physics, R.drawable.religion, R.drawable.poland, R.drawable.english, R.drawable.spanish, R.drawable.italian, R.drawable.norwegian, R.drawable.russian, R.drawable.niemcy};


    public NotificationCompat.Builder getChannelNotification(String subj, String desc, String deadline, int daysleft, int id) {

        if(daysleft > 1) {
            formatdaysleft = String.valueOf(daysleft) + " days left";
        }
        if (daysleft == 1) {
            formatdaysleft = String.valueOf(daysleft) + " day left";
        }

        if (daysleft == 0) {
            formatdaysleft = "Today";
        }

        String content  = formatdaysleft +System.lineSeparator()+ desc;

        mManager.cancel(id);
//wyjebaclem tutaj formatdaysleft bo /n i po desc
        if(subj.equals("Art")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[1])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n"))
                    .setPriority(NotificationCompat.PRIORITY_HIGH);


        }

        if(subj.equals("Astronomy")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[2])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n"));

        }

        if(subj.equals("Biology")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[3])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));

        }
        if(subj.equals("Business studies")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[4])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Chemistry")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[5])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }
        if(subj.equals("Citizenship")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[6])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Geography")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[7])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("History")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[8])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Computer Science")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[9])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Law")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[10])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Literature")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[11])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Maths")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[12])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Music")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[13])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Physics")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[14])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }
        if(subj.equals("Religion")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[15])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }
        if(subj.equals("Polish")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[16])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("English")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[17])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Spanish")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[18])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }


        if(subj.equals("Italian")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[19])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }


        if(subj.equals("Norwegian")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[20])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("Russian")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[21])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }

        if(subj.equals("German")){
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle(subj + " homework")
                    .setContentText(desc + "\n" )
                    .setSmallIcon(sicons[22])
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));
        }


        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(subj + " homework")
                .setContentText(desc + "\n" )
                .setSmallIcon(sicons[0])
                .setStyle(new NotificationCompat.BigTextStyle().bigText(desc + "\n" ));





    }
}
