package onion.homeworkplanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class Display extends AppCompatActivity {

    //zmienne z intentów
    public int selectedID;
    public String selectedSubject;
    public String selectedDescription;
    public String selectedDeadline;
    public String selectedDaysleft;
    public int position;

    //bazy danych
    public HomeworkHelper mHWDB;
    public DatabaseHelper mSBDB;

    TextView subject;
    TextView description;
    TextView date;
    ImageButton editbtn;
    TextView days;
    ImageButton cancel;
    ImageView icon;
    ImageButton removebutton;


    public static Display mdisplay;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mdisplay = this;

        editbtn = findViewById(R.id.editbtn_display);



         ImageButton qrcode2 = findViewById(R.id.qrcodeButton2);



        qrcode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject_str =  subject.getText().toString();
                String deadline_str = date.getText().toString();
                String description_str = description.getText().toString();
                Intent qrintent = new Intent(getApplicationContext(), QRCodeActivity.class);
                qrintent.putExtra("subject", subject_str);
                qrintent.putExtra("desc", description_str);
                qrintent.putExtra("deadline", deadline_str);
                qrintent.putExtra("alldata", "");
                getApplicationContext().startActivity(qrintent);
            }
        });






        //bazy danych
        mHWDB = new HomeworkHelper(this);
        mSBDB = new DatabaseHelper(this);
// otrzymywanie intentów

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedSubject = receivedIntent.getStringExtra("subject");
        selectedDescription = receivedIntent.getStringExtra("description");
        selectedDeadline = receivedIntent.getStringExtra("deadline");
        selectedDaysleft = receivedIntent.getStringExtra("daysleft");
        position = receivedIntent.getIntExtra("position", 0);





        cancel = findViewById(R.id.buttoncancel);
        subject = findViewById(R.id.textView7);
        description = findViewById(R.id.textView8);
        date = findViewById(R.id.textView9);
        days = findViewById(R.id.txtviewdays);

        subject.setText(selectedSubject);
        description.setText(selectedDescription);

        String[] d = selectedDeadline.split("-");
        if(d[1].length() == 1) d[1] = "0"+d[1];
        if(d[2].length() == 1) d[2] = "0"+d[2];
        String formated = d[0] + "-" + d[1] + "-" + d[2];
        date.setText(formated);


        int daysint = Integer.parseInt(selectedDaysleft);
        if (daysint == 1){
            days.setText(selectedDaysleft+ " day left.");}
        if (daysint > 1 ){
            days.setText(selectedDaysleft + " days left.");
        }
        if(daysint == 0){
            days.setText("Today!");
        }

        if (daysint == -1) {
            days.setText(Integer.toString(daysint *-1)+ " day ago.");}

        if (daysint < -1) {
            days.setText(Integer.toString(daysint *-1) + " days ago.");
        }


        removebutton = findViewById(R.id.button_removedispla);


        String [] dateParts = selectedDeadline.split("-");
        String day = dateParts[2];
        String month = dateParts[1];
        String year = dateParts[0];

        int dayint = Integer.parseInt(day);
        int monthint = Integer.parseInt(month);
        int yearint = Integer.parseInt(year);

        DateTime startDate = DateTime.now();
        DateTime endDate = new DateTime(yearint, monthint, dayint, 23, 59);

        Period period = new Period(startDate, endDate, PeriodType.dayTime());
        PeriodFormatter formatter = new PeriodFormatterBuilder().appendDays().appendSuffix(" day ", " days ").toFormatter();

        final String daysleft = formatter.print(period);
        Log.d("DATA", "Zostało tyle dni:" + daysleft);





        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Display.this, EditHomework.class);
                i.putExtra("id", selectedID);
                i.putExtra("subject", selectedSubject);
                i.putExtra("description", selectedDescription);
                i.putExtra("deadline", selectedDeadline);
                i.putExtra("daysleft", daysleft);
                i.putExtra("position", position);
                startActivity(i);
                finish();


            }

        });





        removebutton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                mHWDB.deleteHomework(selectedID, selectedSubject, selectedDescription, selectedDeadline);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                ////////////////////////////////////////////
                Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), selectedID , intent, PendingIntent.FLAG_ONE_SHOT);
                alarmManager.cancel(pendingIntent);

                Intent intent1 = new Intent(getApplicationContext(), AlertReceiver.class);
                PendingIntent pendingIntent1 = PendingIntent.getBroadcast(getApplicationContext(), selectedID + 30000000 , intent1, PendingIntent.FLAG_ONE_SHOT);
                alarmManager.cancel(pendingIntent1);

                Intent intent3 = new Intent(getApplicationContext(), AlertReceiver.class);
                PendingIntent pendingIntent3 = PendingIntent.getBroadcast(getApplicationContext(), selectedID + 60000000 , intent3, PendingIntent.FLAG_ONE_SHOT);
                alarmManager.cancel(pendingIntent3);

                Intent intent4 = new Intent(getApplicationContext(), AlertReceiver.class);
                PendingIntent pendingIntent4 = PendingIntent.getBroadcast(getApplicationContext(), selectedID + 90000000, intent4, PendingIntent.FLAG_ONE_SHOT);
                alarmManager.cancel(pendingIntent4);


                MainActivity.mActivity.myAdapter.removeItem(position);
                finish();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        icon = findViewById(R.id.imageView2);


        int[] sicons = {R.drawable.def, R.drawable.art, R.drawable.astronomy, R.drawable.biology, R.drawable.businessstudies, R.drawable.chemistry, R.drawable.citizenship, R.drawable.geography, R.drawable.history, R.drawable.it, R.drawable.law, R.drawable.literature, R.drawable.maths, R.drawable.music, R.drawable.physics, R.drawable.religion, R.drawable.poland, R.drawable.english, R.drawable.spanish, R.drawable.italian, R.drawable.norwegian, R.drawable.russian, R.drawable.niemcy};







        /* ikonka */




        icon.setImageResource(sicons[0]);

        if (subject.getText().toString().equals("Art")) {
            icon.setImageResource(sicons[1]);
        }

        if (subject.getText().toString().equals("Astronomy")) {
            icon.setImageResource(sicons[2]);
        }

        if (subject.getText().toString().equals("Biology")) {
            icon.setImageResource(sicons[3]);
        }

        if (subject.getText().toString().equals("Business studies")) {
            icon.setImageResource(sicons[4]);
        }

        if (subject.getText().toString().equals("Chemistry")) {
            icon.setImageResource(sicons[5]);
        }

        if (subject.getText().toString().equals("Citizenship")) {
            icon.setImageResource(sicons[6]);
        }

        if (subject.getText().toString().equals("Geography")) {
            icon.setImageResource(sicons[7]);
        }

        if (subject.getText().toString().equals("History")) {
            icon.setImageResource(sicons[8]);
        }

        if (subject.getText().toString().equals("Computer Science")) {
            icon.setImageResource(sicons[9]);
        }

        if (subject.getText().toString().equals("Law")) {
            icon.setImageResource(sicons[10]);
        }

        if (subject.getText().toString().equals("Literature")) {
            icon.setImageResource(sicons[11]);
        }

        if (subject.getText().toString().equals("Maths")) {
            icon.setImageResource(sicons[12]);
        }

        if (subject.getText().toString().equals("Music")) {
            icon.setImageResource(sicons[13]);
        }

        if (subject.getText().toString().equals("Physics")) {
            icon.setImageResource(sicons[14]);
        }


        if (subject.getText().toString().equals("Religion")) {
            icon.setImageResource(sicons[15]);
        }

        if (subject.getText().toString().equals("Polish")) {
            icon.setImageResource(sicons[16]);
        }

        if (subject.getText().toString().equals("English")) {
            icon.setImageResource(sicons[17]);
        }

        if (subject.getText().toString().equals("Spanish")) {
            icon.setImageResource(sicons[18]);
        }

        if (subject.getText().toString().equals("Italian")) {
            icon.setImageResource(sicons[19]);
        }

        if (subject.getText().toString().equals("Norwegian")) {
            icon.setImageResource(sicons[20]);
        }

        if (subject.getText().toString().equals("Russian")) {
            icon.setImageResource(sicons[21]);
        }

        if (subject.getText().toString().equals("German")) {
            icon.setImageResource(sicons[22]);
        }











    }
}
