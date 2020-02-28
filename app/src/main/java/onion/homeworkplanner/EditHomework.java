package onion.homeworkplanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.List;

import static org.joda.time.Days.daysBetween;

public class EditHomework extends AppCompatActivity {





    //zmienne z intentów
    public static int selectedID;
    public static String selectedSubject;
    public static String selectedDescription;
    public static String selectedDeadline;
    public static int selectedDaysLeft;

    public static String newSubject;
    public static String newDesc;
    String datecontent2;

    //bazy danych
    public HomeworkHelper mHWDB;
    public DatabaseHelper mSBDB;

    public static boolean notifyFrequencyOneDay = false;
    public static boolean notifyFrequencyThreeDays = false;
    public static boolean notifyFrequencyOneWeek = false;
    public static boolean notifyFrequencyTwoWeeks = false;


    //zmienne do kalendarza.
    CalendarView calender;
    int rok;
    int miesiac;
    int dzien;
    int defYear;
    int defMonth;
    int defDay;
    public static String datecontent;

    Calendar c;

    int daysleftEDIT;
    Spinner spinner;
    EditText editText;
    Button save;
    Button cancel;
    TextView maxcharsedit;

    int settinghour;
    int settingminute;

    int todYear;
    int todMonth;
    int todDay;
    int min;
    Calendar cMinusOneDay;
    Calendar cMinusThreeDays;
    Calendar cMinusWeek;
    Calendar cMinusTwoWeeks;

    int olddaysleft;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_homework);

        notifyFrequencyOneDay = false;
        notifyFrequencyThreeDays = false;
        notifyFrequencyOneWeek = false;
        notifyFrequencyTwoWeeks = false;

        // otrzymywanie intentów
        final Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id", 1);
        selectedSubject = receivedIntent.getStringExtra("subject");
        selectedDescription = receivedIntent.getStringExtra("description");
        selectedDeadline = receivedIntent.getStringExtra("deadline");
        selectedDaysLeft = receivedIntent.getIntExtra("daysleft", 1);

        c = Calendar.getInstance();



        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        settinghour = sharedPreferences.getInt("Hour", 12);
        settingminute = sharedPreferences.getInt("Minute", 12);

        Calendar cal = Calendar.getInstance();
        todDay = cal.get(Calendar.DAY_OF_MONTH);
        todMonth = cal.get(Calendar.MONTH);
        todYear = cal.get(Calendar.YEAR);

        c.set(Calendar.YEAR, todYear);
        c.set(Calendar.MONTH, todMonth);
        c.set(Calendar.DAY_OF_MONTH, todDay);
        c.set(Calendar.HOUR_OF_DAY, settinghour);
        c.set(Calendar.MINUTE, settingminute);
        c.set(Calendar.SECOND, 0);



        final String DATE = String.valueOf(todYear) + "-" + String.valueOf(todMonth + 1) + "-" + String.valueOf(todDay);

        Log.d("Data  dzisiejsza", "to:: " + DATE);


        String [] dateParts = selectedDeadline.split("-");
        String day = dateParts[2];
        String month = dateParts[1];
        String year = dateParts[0];
        int dayint = Integer.parseInt(day);
        int monthint = Integer.parseInt(month);
        int yearint = Integer.parseInt(year);


        DateTime startDate = DateTime.now();
        DateTime startDateTest = new DateTime(startDate.getYear(), startDate.getMonthOfYear(), startDate.getDayOfMonth(), 12, 0 );
        DateTime dateAfterTest = new DateTime(yearint, monthint, dayint, 12, 0);
        int noOfDaysBetween = daysBetween(startDateTest.toLocalDate(), dateAfterTest.toLocalDate()).getDays();
        int daysleftint = (int)noOfDaysBetween ;

        olddaysleft = daysleftint;
        daysleftEDIT = daysleftint;






        //bazy danych
        mHWDB = new HomeworkHelper(this);
        mSBDB = new DatabaseHelper(this);


        save = findViewById(R.id.button_ededh);
        editText = findViewById(R.id.editTextedh);
        editText.setText(selectedDescription);
        cancel = findViewById(R.id.btncanceledh);
        maxcharsedit = findViewById(R.id.maxchar3edh);


        //ustawianie kalendarza
        calender = (CalendarView)findViewById(R.id.calendarViewedh);

        String [] dateParts1 = selectedDeadline.split("-");
        String dayt = dateParts1[2];
        String montht = dateParts1[1];
        String yeart = dateParts1[0];


        String DEFDATE = String.valueOf(yeart) + "-" + String.valueOf(montht) + "-" + String.valueOf(dayt);

        calender.setMinDate(System.currentTimeMillis() + 86400000);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, Integer.parseInt(yeart));
        calendar.set(Calendar.MONTH, Integer.parseInt(montht) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayt));


        long milliTime = calendar.getTimeInMillis();

        calender.setDate (milliTime, true, true);








        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                rok = year;
                miesiac = month;
                dzien = dayOfMonth;



                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth); // usunąć -1 // TU BYŁO -1
                c.set(Calendar.HOUR_OF_DAY, settinghour); // ustawić o której ma przychodzić np w settings
                c.set(Calendar.MINUTE, settingminute);
                c.set(Calendar.SECOND, 0);





                String DATE = String.valueOf(rok) + "-" + String.valueOf(miesiac + 1) + "-" + String.valueOf(dzien);
                datecontent2 = DATE;


                String [] dateParts = DATE.split("-");
                String day = dateParts[2];
                String month1 = dateParts[1];
                String year1 = dateParts[0];
                int dayint = Integer.parseInt(day);
                int monthint = Integer.parseInt(month1);
                int yearint = Integer.parseInt(year1);


                DateTime startDate = DateTime.now();
                DateTime startDateTest = new DateTime(startDate.getYear(), startDate.getMonthOfYear(), startDate.getDayOfMonth(), 12, 0 );
                DateTime dateAfterTest = new DateTime(yearint, monthint, dayint, 12, 0);
                int noOfDaysBetween = daysBetween(startDateTest.toLocalDate(), dateAfterTest.toLocalDate()).getDays();

                int daysleftint = (int)noOfDaysBetween ;


                daysleftEDIT = daysleftint;
                selectedDaysLeft = daysleftEDIT;
                Log.d("Dyas", "Dyas;leftint = " + String.valueOf(daysleftEDIT));
            }
        });

        datecontent2 = DEFDATE;

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newSubject = spinner.getSelectedItem().toString();
                newDesc = editText.getText().toString();
                Display.mdisplay.finish();



                mHWDB.updateHomework(selectedID, newSubject, selectedSubject, newDesc, selectedDescription, datecontent2, selectedDeadline );



                ///Cancelowanie Alarmów i ustawianie na nowo
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






                MainActivity.mActivity.myAdapter.updateItem(receivedIntent.getIntExtra("position", 0),new Homework(selectedID, newSubject, newDesc, datecontent2, selectedDaysLeft ) );


                finish();



            }
        });



        maxcharsedit.setText( String.valueOf(selectedDescription.length()) + " / 50");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                maxcharsedit.setText(String.valueOf(s.length()) + " / 50");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        //ustawianie spinnera
        spinner = findViewById(R.id.spinneredh);
        spinner.setSelected(Boolean.parseBoolean(selectedSubject));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });






        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loadSpinnerData();

    }//Koniec onCreate








    // załadowanie spinnera danymi z bazy danych.
    private void loadSpinnerData() {

        Spinner spinner = findViewById(R.id.spinneredh);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<String> lables = db.getAllSetcardCategory();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
        if (selectedSubject != null) {
            int spinnerPosition = dataAdapter.getPosition(selectedSubject);
            spinner.setSelection(spinnerPosition);
        }


    }




}
