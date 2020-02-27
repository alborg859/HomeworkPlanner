package onion.homeworkplanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.Image;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class MyAdapter extends BaseAdapter {





    Context context;
    HomeworkHelper hmh;
    MainActivity ma = new MainActivity();
    NotificationHelper notificationHelper;
    String deadline_str;

    public  MyAdapter (Context context) {
        this.context = context;
    }







    ArrayList<Homework> arrayList;
    public MyAdapter(Context context, ArrayList<Homework> arrayList) {

        this.context=context;
        this.arrayList=arrayList;

        notificationHelper = new NotificationHelper(context);

    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Glide.get(context).setMemoryCategory(MemoryCategory.HIGH);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_item, null);

        final ImageView icon = convertView.findViewById(R.id.subject_icon);

        int[] sicons = {R.drawable.def, R.drawable.art, R.drawable.astronomy, R.drawable.biology, R.drawable.businessstudies, R.drawable.chemistry, R.drawable.citizenship, R.drawable.geography, R.drawable.history, R.drawable.it, R.drawable.law, R.drawable.literature, R.drawable.maths, R.drawable.music, R.drawable.physics, R.drawable.religion, R.drawable.poland, R.drawable.english, R.drawable.spanish, R.drawable.italian, R.drawable.norwegian, R.drawable.russian, R.drawable.niemcy};


        final TextView subject = (TextView)convertView.findViewById(R.id.hm_subject);
        final TextView description = (TextView)convertView.findViewById(R.id.hm_description);
        final  TextView deadline = (TextView)convertView.findViewById(R.id.hm_deadline);
        final TextView daysleft = (TextView)convertView.findViewById(R.id.hm_daysleft);


        final ImageView cover = convertView.findViewById(R.id.hm_cover);
        final Button remove = convertView.findViewById(R.id.hm_remove);
        final Button cancel = convertView.findViewById(R.id.hm_cancel);
        final Homework homework = arrayList.get(position);
        final ImageButton qrcode = convertView.findViewById(R.id.qrcodeButton);





        /* ikonka */



        //taką metodą można multilingual //
        String[] art= {"Art", "Plastyka", "plastyka", "art"};





        Glide
                .with(context)

                .load("https://drive.google.com/uc?id=149j_7DIBEOCBMaGDuqyILmi4-A-u6gSo" )
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.palitko)
                .into(icon);

        if ( Arrays.asList(art).contains(homework.getSubject_homework()) == true) {


            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1wgRIjXhWxpDfZh0B65aIZaN7vSpEb7Sq" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);

        }

        if (homework.getSubject_homework().equals("Astronomy")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1Rg1CGgzbfprpYzWQFzY1SMfqDGAUoxM6" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Biology")) {
            Glide
                    .with(context)

                    .load("https://drive.google.com/uc?id=1CsbaNhNmePdSlDxWCATf21Z8I5yW_m17" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Business studies")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1WPjxy09hjDANRZZXIrCBpj1D7NJfbxKr" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Chemistry")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1OcXA7MSlYqaH38SclCLevM2I6Qo9-cGI" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Citizenship")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1I7HEXVGC83R1KBXtq5ecz9gmRWdadHNQ" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Geography")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=18dySJbkIcL_aZTIIofy6Nxbu4Ns7MquT" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("History")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1pSOZUpgLCeZp9PmEI7zyxkZ8RGQrZk3T" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Computer Science")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1xreYBoa3q774hldTV9dgHLfifNgL3277" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Law")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1XQqNbX4Kl9RqS0Mou2QS9RdrWghdgteE" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Literature")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=10DtdjymWEV32Q1-iratUdsxg6X-IF4Yb" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Maths")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1xC4E-utzFqsm32j4QlfqN6Ptlt4FM8U0" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Music")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1OpYGp31FWCuW7aTPbJG9d5IQH8WL_ZIu" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)

                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Physics")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=13aW88nY7ngLuErN2azGKAgWJkX-vnVEA" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)

                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Religion")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1KEKx1LZAc1y5KQvB0zTd_glONydafR3W" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)

                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Polish")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1PiY3w73Y5QJJZWC6mXRiHPX3OR6bSafZ" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)

                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("English")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1qkgp-8WL70RRK4ucGCLgLYRaSzCJxx3S" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)

                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Spanish")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1pg84RvqJnVImHp1rfq1VFVRAAAQSYYtO" )

                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Italian")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=183pXtdd3MI3JOAm4YAACBsczSqRU0fOP" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Norwegian")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1Pnl2dmFK1ITDZ-v7FdfPH3V7mu8_SJNO" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("Russian")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1f15aY30wjU_9Coc3ErfHzGc2gLfm04nL" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }

        if (homework.getSubject_homework().equals("German")) {
            Glide

                    .with(context)
                    .load("https://drive.google.com/uc?id=1Y7cItjKfwAZJaxoQt7sZ3K1knbyTZlX3" )
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.palitko)
                    .into(icon);
        }













        int localdays;
        localdays = homework.getDaysleft_homework();
        final String localdaysstring = Integer.toString(localdays);

        subject.setText(homework.getSubject_homework());
        description.setText(homework.getDescription_homework());
        deadline.setText(homework.getDate_homework());

        daysleft.setText(localdaysstring+ " days left");

        if (localdays == 1 ){
            daysleft.setText(localdaysstring + " day left");
        }
        if(localdays == 0){
            daysleft.setText("Today!");
            daysleft.setTextColor(Color.parseColor("#ef8354"));
        }
        if(localdays < 0) {
            convertView.setBackgroundColor(Color.parseColor("#bbbbbb"));
        }

        if(localdays == -1) {
            daysleft.setText(Integer.toString(localdays * -1) + " day ago");

        }
        if(localdays < -1) {
            daysleft.setText(Integer.toString(localdays * -1) + " days ago");
        }






        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                String subject_str =  homework.getSubject_homework();
                 deadline_str = homework.getDate_homework();
                String description_str = homework.getDescription_homework();

                int hm_days = homework.getDaysleft_homework();
                String hm_daysstr = Integer.toString(hm_days);


                int hm_id = homework.getId();






                Intent i = new Intent(context, Display.class);
                i.putExtra("id", hm_id);
                i.putExtra("subject", subject_str);
                i.putExtra("description", description_str);
                i.putExtra("deadline", deadline_str);
                i.putExtra("daysleft", hm_daysstr);
                context.startActivity(i);

            }
        });



        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {



                cover.setVisibility(View.VISIBLE);
                remove.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                qrcode.setVisibility(View.VISIBLE);
                        
                YoYo.with(Techniques.FadeIn)
                        .duration(200)
                        .repeat(0)
                        .playOn(cover);
                YoYo.with(Techniques.FadeInUp).duration(220).repeat(0).playOn(qrcode);

                YoYo.with(Techniques.FadeInLeft)
                        .duration(220)
                        .repeat(0)
                        .playOn(remove);
                YoYo.with(Techniques.FadeInRight)
                        .duration(220)
                        .repeat(0)
                        .playOn(cancel);
                return true;

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cancel.getVisibility() == View.VISIBLE){

                YoYo.with(Techniques.FadeOut)
                        .duration(200)
                        .repeat(0)
                        .playOn(cover);

                YoYo.with(Techniques.FadeOutLeft)
                        .duration(220)
                        .repeat(0)
                        .playOn(remove);
                YoYo.with(Techniques.FadeOutRight)
                        .duration(220)
                        .repeat(0)
                        .playOn(cancel);
                YoYo.with(Techniques.FadeOutDown).duration(220).repeat(0).playOn(qrcode);





                CountDownTimer timer = new CountDownTimer(220, 1000) {
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        cover.setVisibility(View.INVISIBLE);
                        remove.setVisibility(View.INVISIBLE);
                        cancel.setVisibility(View.INVISIBLE);
                        qrcode.setVisibility(View.INVISIBLE);



                    }
                };

            timer.start();





            }
else {

                }

            }
        });

        ///QR CODE.
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject_str =  homework.getSubject_homework();
                deadline_str = homework.getDate_homework();
                String description_str = homework.getDescription_homework();
                Intent qrintent = new Intent(context, QRCodeActivity.class);
                qrintent.putExtra("subject", subject_str);
                qrintent.putExtra("desc", description_str);
                qrintent.putExtra("deadline", deadline_str);
                qrintent.putExtra("alldata", "");
                context.startActivity(qrintent);
            }
        });
        
        
        
        //usuwanie zad dom



        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                YoYo.with(Techniques.FadeOut)
                        .duration(200)
                        .repeat(0)
                        .playOn(cover);

                YoYo.with(Techniques.FadeOutLeft)
                        .duration(220)
                        .repeat(0)
                        .playOn(remove);
                YoYo.with(Techniques.FadeOutRight)
                        .duration(220)
                        .repeat(0)

                        .playOn(cancel);

                YoYo.with(Techniques.FadeOutDown).duration(220).repeat(0).playOn(qrcode);
                // animacja częsci convertView

                YoYo.with(Techniques.FadeOutLeft)
                        .duration(220)
                        .repeat(0)
                        .playOn(subject);

                YoYo.with(Techniques.FadeOutLeft)
                        .duration(220)
                        .repeat(0)
                        .playOn(icon);

                YoYo.with(Techniques.FadeOutRight)
                        .duration(220)
                        .repeat(0)

                        .playOn(description);
                YoYo.with(Techniques.FadeOutRight)
                        .duration(220)
                        .repeat(0)

                        .playOn(deadline);
                YoYo.with(Techniques.FadeOutRight)
                        .duration(220)
                        .repeat(0)

                        .playOn(daysleft);

                hmh = new HomeworkHelper(context);




                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {



                        Looper.prepare();

                        hmh.deleteHomework(homework.getId(),homework.subject_homework, homework.description_homework, homework.date_homework);


                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);



                        ////////////////////////////////////////////
                        Intent intent = new Intent(context, AlertReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, homework.getId() , intent, PendingIntent.FLAG_ONE_SHOT);
                        alarmManager.cancel(pendingIntent);

                        Intent intent1 = new Intent(context, AlertReceiver.class);
                        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, homework.getId() + 30000000 , intent1, PendingIntent.FLAG_ONE_SHOT);
                        alarmManager.cancel(pendingIntent1);

                        Intent intent3 = new Intent(context, AlertReceiver.class);
                        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(context,homework.getId() + 60000000 , intent3, PendingIntent.FLAG_ONE_SHOT);
                        alarmManager.cancel(pendingIntent3);

                        Intent intent4 = new Intent(context, AlertReceiver.class);
                        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, homework.getId()+ 90000000, intent4, PendingIntent.FLAG_ONE_SHOT);
                        alarmManager.cancel(pendingIntent4);





                        ma.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                cover.setVisibility(View.INVISIBLE);
                                remove.setVisibility(View.INVISIBLE);
                                cancel.setVisibility(View.INVISIBLE);
                                qrcode.setVisibility(View.INVISIBLE);



                                MainActivity.mActivity.loadDataInListView();
                            }
                        });


                    }
                };
                timer.schedule(task,220);



            }
        });



        return convertView;
    }


    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}

