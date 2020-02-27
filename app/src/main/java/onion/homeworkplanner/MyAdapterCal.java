package onion.homeworkplanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.Image;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class MyAdapterCal extends BaseAdapter {


    Context context;
    HomeworkHelper hmh;
    MainActivity ma = new MainActivity();

    String deadline_str;

    public  MyAdapterCal (Context context) {
        this.context = context;
    }







    ArrayList<Homework> arrayList;
    public MyAdapterCal(Context context, ArrayList<Homework> arrayList) {



        this.context=context;
        this.arrayList=arrayList;



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



        /* ikonka */



        //taką metodą można multilingual //
        String[] art= {"Art", "Plastyka", "plastyka", "art"};





        icon.setImageResource(sicons[0]);

        if ( Arrays.asList(art).contains(homework.getSubject_homework()) == true) {
            icon.setImageResource(sicons[1]);
        }

        if (homework.getSubject_homework().equals("Astronomy")) {
            icon.setImageResource(sicons[2]);
        }

        if (homework.getSubject_homework().equals("Biology")) {
            icon.setImageResource(sicons[3]);
        }

        if (homework.getSubject_homework().equals("Business studies")) {
            icon.setImageResource(sicons[4]);
        }

        if (homework.getSubject_homework().equals("Chemistry")) {
            icon.setImageResource(sicons[5]);
        }

        if (homework.getSubject_homework().equals("Citizenship")) {
            icon.setImageResource(sicons[6]);
        }

        if (homework.getSubject_homework().equals("Geography")) {
            icon.setImageResource(sicons[7]);
        }

        if (homework.getSubject_homework().equals("History")) {
            icon.setImageResource(sicons[8]);
        }

        if (homework.getSubject_homework().equals("Computer Science")) {
            icon.setImageResource(sicons[9]);
        }

        if (homework.getSubject_homework().equals("Law")) {
            icon.setImageResource(sicons[10]);
        }

        if (homework.getSubject_homework().equals("Literature")) {
            icon.setImageResource(sicons[11]);
        }

        if (homework.getSubject_homework().equals("Maths")) {
            icon.setImageResource(sicons[12]);
        }

        if (homework.getSubject_homework().equals("Music")) {
            icon.setImageResource(sicons[13]);
        }

        if (homework.getSubject_homework().equals("Physics")) {
            icon.setImageResource(sicons[14]);
        }

        if (homework.getSubject_homework().equals("Religion")) {
            icon.setImageResource(sicons[15]);
        }

        if (homework.getSubject_homework().equals("Polish")) {
            icon.setImageResource(sicons[16]);
        }

        if (homework.getSubject_homework().equals("English")) {
            icon.setImageResource(sicons[17]);
        }

        if (homework.getSubject_homework().equals("Spanish")) {
            icon.setImageResource(sicons[18]);
        }

        if (homework.getSubject_homework().equals("Italian")) {
            icon.setImageResource(sicons[19]);
        }

        if (homework.getSubject_homework().equals("Norwegian")) {
            icon.setImageResource(sicons[20]);
        }

        if (homework.getSubject_homework().equals("Russian")) {
            icon.setImageResource(sicons[21]);
        }

        if (homework.getSubject_homework().equals("German")) {
            icon.setImageResource(sicons[22]);
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
            daysleft.setTextColor(Color.RED);
        }




























        return convertView;
    }


    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}

