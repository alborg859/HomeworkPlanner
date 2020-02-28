package onion.homeworkplanner;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import com.google.android.gms.ads.MobileAds;

import android.os.AsyncTask;


import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static org.joda.time.Days.daysBetween;


public class PlanActivity extends AppCompatActivity{

    private static final String TAG = "Listy";



    int min;
    CalendarView calender;
    int rok;
    int miesiac;
    int dzien;
    Calendar c;
    Calendar cMinusOneDay;
    Calendar cMinusThreeDays;
    Calendar cMinusWeek;
    Calendar cMinusTwoWeeks;
    
    


    DatabaseHelper mDatabaseHelper;
    HomeworkHelper mHomeworkHelper;
    public static   String  datecontent;
    public static   String descriptiontext;
    public static   String subject;

    int defYear;
    int defMonth;
    int defDay;

    int todYear;
    int todMonth;
    int todDay;
    int tempID;
    int settinghour;
    int settingminute;
    int daysleftint = 1;

    public static boolean notifyFrequencyOneDay = false;
    public static boolean notifyFrequencyThreeDays = false;
    public static boolean notifyFrequencyOneWeek = false;
    public static boolean notifyFrequencyTwoWeeks = false;







    public InterstitialAd mInterstitialAd;

    public static Activity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        //mDatabaseHelper = new DatabaseHelper(this);


        try {
            loadSpinnerData();
        } catch (Exception e) {
            e.printStackTrace();
        }


        fa = this;


         notifyFrequencyOneDay = false;
         notifyFrequencyThreeDays = false;
         notifyFrequencyOneWeek = false;
         notifyFrequencyTwoWeeks = false;



        MobileAds.initialize(this,
                "ca-app-pub-9541882283543234~7663711024");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9541882283543234/8700394869");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());





        ImageButton addfromQRcode = findViewById(R.id.qrcodeAddButton);

        Button save =findViewById(R.id.button);

        Button cancel = findViewById(R.id.btncancel);
        c = Calendar.getInstance();
        cMinusOneDay = Calendar.getInstance();
        cMinusThreeDays =  Calendar.getInstance();
        cMinusWeek =  Calendar.getInstance();
        cMinusTwoWeeks =  Calendar.getInstance();

        Button notificationOptionsBTN = findViewById(R.id.nopt_btn);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        settinghour = sharedPreferences.getInt("Hour", 12);
        settingminute = sharedPreferences.getInt("Minute", 12);

        Calendar cal = Calendar.getInstance();
        todDay = cal.get(Calendar.DAY_OF_MONTH);
        todMonth = cal.get(Calendar.MONTH);
        todYear = cal.get(Calendar.YEAR);

        c.set(Calendar.YEAR, todYear);
        c.set(Calendar.MONTH, todMonth);
        c.set(Calendar.DAY_OF_MONTH, todDay); // usunąć -1
        c.set(Calendar.HOUR_OF_DAY, settinghour); // ustawić o której ma przychodzić np. w settings
        c.set(Calendar.MINUTE, settingminute);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.DATE, 1);

        Log.d("Calendar", c.toString());





        calender = (CalendarView)findViewById(R.id.calendarView);





        Date dt = new Date();
        DateTime dtOrg = new DateTime(dt);
        DateTime dtPlusOne = dtOrg.plusDays(1);
        defYear = dtPlusOne.getYear();
        defMonth = dtPlusOne.getMonthOfYear();
        defDay = dtPlusOne.getDayOfMonth();
        String DEFDATE = String.valueOf(defYear) + "-" + String.valueOf(defMonth) + "-" + String.valueOf(defDay);




        final Dialog dialog;
        final String[] items = {" Day before", " Three days before", " One week before", " Two weeks before"};
        final ArrayList itemsSelected = new ArrayList();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select desired notifications:  ");
        builder.setMultiChoiceItems(items, null,

                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedItemId,
                                        boolean isSelected) {
                        if (isSelected) {


                            itemsSelected.add(selectedItemId);
                            Log.d("Items", "Testy: " + itemsSelected);


                        } else if (itemsSelected.contains(selectedItemId)) {


                            itemsSelected.remove(Integer.valueOf(selectedItemId));


                        }
                    }
                })



                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Your logic when OK button is clicked



                        if (itemsSelected.contains(0 )  ) {
                            notifyFrequencyOneDay = true;
                            Log.d("Dialog", "1d");
                        }

                        if(((AlertDialog) dialog).getListView().getChildAt(1).isEnabled() == true) {
                            if (itemsSelected.contains(1)) {
                                notifyFrequencyThreeDays = true;
                                Log.d("Dialog", "3d");
                            }}


                        if (((AlertDialog) dialog).getListView().getChildAt(2).isEnabled() == true) {

                            if (itemsSelected.contains(2)) {
                                notifyFrequencyOneWeek = true;
                                Log.d("Dialog", "1w");
                            } }


                        if(((AlertDialog) dialog).getListView().getChildAt(3).isEnabled() == true) {

                            if (itemsSelected.contains(3)) {
                                notifyFrequencyTwoWeeks = true;
                                Log.d("Dialog", "2w");
                            } }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        itemsSelected.removeAll(itemsSelected);
                    }
                })

        ;






        dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener(){
            @Override
            public void onShow(DialogInterface dialog) {

                if(daysleftint <= 3) {



                    ((AlertDialog) dialog).getListView().getChildAt(1).setEnabled(false);



                } else {
                    ((AlertDialog) dialog).getListView().getChildAt(1).setEnabled(true);
                }




                if(daysleftint <= 7) {
                    ((AlertDialog) dialog).getListView().getChildAt(2).setEnabled(false);
                } else {
                    ((AlertDialog) dialog).getListView().getChildAt(2).setEnabled(true);
                }

                if(daysleftint <= 14) {
                    ((AlertDialog) dialog).getListView().getChildAt(3).setEnabled(false);

                }
                else
                {
                    ((AlertDialog) dialog).getListView().getChildAt(3).setEnabled(true);
                }



            }


        });



        notificationOptionsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        calender.setMinDate(System.currentTimeMillis() + 86400000);

        calender.setDate(System.currentTimeMillis() + 86400000);

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                rok = year;
                miesiac = month;
                dzien = dayOfMonth;

                Log.d("Godzina", "godzina powiadomienia to: " + settinghour);
                Log.d("Godzina", "minuta powiadomienia to: " + settingminute);


                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth ); // usunąć -1
                c.set(Calendar.HOUR_OF_DAY, settinghour); // ustawić o której ma przychodzić np. w settings
                c.set(Calendar.MINUTE, settingminute);
                c.set(Calendar.SECOND, 0);

                String DATE = String.valueOf(rok) + "-" + String.valueOf(miesiac + 1) + "-" + String.valueOf(dzien);


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



                daysleftint = (int)noOfDaysBetween ;

                datecontent = DATE;


            }
        });
        datecontent = DEFDATE;



        EditText description = findViewById(R.id.editText);
        final TextView maxchars3 = findViewById(R.id.maxchar3);

        maxchars3.setText("0/50");
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                maxchars3.setText(String.valueOf(s.length()) + " / 50");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {






            saveData();


                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }


                //adsy masne fest




            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                PlanActivity.this.finish();
            }
        });




        addfromQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 int MY_CAMERA_REQUEST_CODE = 100;

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {

                    ActivityCompat.requestPermissions(PlanActivity.this, new String[]{Manifest.permission.CAMERA}, 100);


                }

                else{
                Intent qrScanner = new Intent(PlanActivity.this, QRCodeScanner.class);
                startActivity(qrScanner);}
            }







        });



    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toasty.success(getApplicationContext(), "Camera permission granted!", Toast.LENGTH_SHORT, true).show();
                Intent qrScanner = new Intent(PlanActivity.this, QRCodeScanner.class);
                startActivity(qrScanner);

            } else {


                Toasty.error(getApplicationContext(), "Camera permission denied", Toast.LENGTH_SHORT, true).show();
            }

        }}//end onRequestPermissionsResult




    public void saveData(){
        mHomeworkHelper = new HomeworkHelper(this);

        Log.d("Godzina", "godzina powiadomienia to: " + settinghour);
        Log.d("Godzina", "minuta powiadomienia to: " + settingminute);





        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        subject = spinner.getSelectedItem().toString();
        EditText description = findViewById(R.id.editText);
        descriptiontext = description.getText().toString();
        mHomeworkHelper.addData(subject, descriptiontext, datecontent );
        MainActivity.mActivity.loadDataInListView();




        String [] dateParts = datecontent.split("-");
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


        Cursor id = mHomeworkHelper.getItemID(subject, descriptiontext, datecontent, daysleftint);
        Log.d("Receiver", "Brextras received: " + id);
        id.moveToNext();
        min = id.getInt(0);


        if(notifyFrequencyOneDay == true) {
            OpenOneDay(min, daysleftint, subject, descriptiontext, datecontent);
        }

        if(notifyFrequencyThreeDays == true && daysleftint > 3) {
            OpenThreeDays(min, daysleftint, subject, descriptiontext, datecontent);
        }

        if(notifyFrequencyOneWeek == true && daysleftint > 7) {
            OpenOneWeek(min, daysleftint, subject, descriptiontext, datecontent);
        }

        if(notifyFrequencyTwoWeeks == true && daysleftint > 14) {
            OpenTwoWeeks(min, daysleftint, subject, descriptiontext, datecontent);
        }









        finish();

        mHomeworkHelper.close();



    }




    private void OpenOneDay(int id, int daysleft, String subj, String desc, String date){
        cMinusOneDay = (Calendar)c.clone();
        cMinusOneDay.add(Calendar.DATE, -1);
        Log.d("ID","Test kalendara Dla one DAY" +  cMinusOneDay);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
        intent.putExtra("code", id);
        intent.putExtra("code1", daysleft);
        intent.putExtra("subj", subj);
        intent.putExtra("desc", desc);
        intent.putExtra("date", date);
        Log.d("ID", "Id dla 1 dnia: " + id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id , intent, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cMinusOneDay.getTimeInMillis(), pendingIntent);
    }

    private void OpenThreeDays(int id, int daysleft, String subj, String desc, String date){
        cMinusThreeDays = (Calendar)c.clone();
        cMinusThreeDays.add(Calendar.DATE, -3);
        Log.d("ID","Test kalendara DLA 3 DAYS" +  cMinusThreeDays);
        id = id + 30000000; // 30 milionów
        Log.d("ID", "Id dla 3: " + id);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent2 = new Intent(getApplicationContext(), AlertReceiver.class);
        intent2.putExtra("code", id);
        intent2.putExtra("code1", daysleft);
        intent2.putExtra("subj", subj);
        intent2.putExtra("desc", desc);
        intent2.putExtra("date", date);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, intent2, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cMinusThreeDays.getTimeInMillis(), pendingIntent);
    }

    private void OpenOneWeek(int id, int daysleft, String subj, String desc, String date) {
        cMinusWeek = (Calendar)c.clone();
        cMinusWeek.add(Calendar.DATE, -7);
        Log.d("ID","Test kalendara" +  cMinusWeek);
        id = id + 60000000;//60 milionów
        Log.d("ID", "Id dla 1 tgdonia: " + id);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent3 = new Intent(getApplicationContext(), AlertReceiver.class);

        intent3.putExtra("code", id);
        intent3.putExtra("code1", daysleft);
        intent3.putExtra("subj", subj);
        intent3.putExtra("desc", desc);
        intent3.putExtra("date", date);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, intent3, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cMinusWeek.getTimeInMillis(), pendingIntent);
    }

    private void OpenTwoWeeks(int id, int daysleft, String subj, String desc, String date){
        cMinusTwoWeeks = (Calendar)c.clone();
        cMinusTwoWeeks.add(Calendar.DATE, -14);
        Log.d("ID","Test kalendara" +  cMinusTwoWeeks);
        id = id + 90000000;//90 milionów
        Log.d("ID", "Id dla 2 tyg: " + id);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent4 = new Intent(getApplicationContext(), AlertReceiver.class);
        intent4.putExtra("code", id);
        intent4.putExtra("code1", daysleft);
        intent4.putExtra("subj", subj);
        intent4.putExtra("desc", desc);
        intent4.putExtra("date", date);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, intent4, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cMinusTwoWeeks.getTimeInMillis(), pendingIntent);
    }











    public void loadSpinnerData() throws Exception {

try {
    Spinner spinner = findViewById(R.id.spinner);
    ArrayAdapter<String> dataAdapter = new Async().execute().get();
    spinner.setAdapter(dataAdapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            ((TextView) parentView.getChildAt(0)).setTextColor(Color.BLACK);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) { }
    });



} catch (Exception e){Log.d("CHUJ KURWA", "JA KURWIE BŁĄD: " + e);}







    }




    public class Async extends AsyncTask<Void, Void, ArrayAdapter<String>> {
        DatabaseHelper mDatabaseHelper ;
        ArrayAdapter<String> dataAdapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayAdapter<String> doInBackground(Void... params) {



            mDatabaseHelper = new DatabaseHelper(getApplicationContext());

            List<String> lables = mDatabaseHelper.getAllSetcardCategory();
            Log.d("test", lables.toString());
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getBaseContext(),
                    android.R.layout.simple_spinner_item, lables);
            dataAdapter.setDropDownViewResource(R.layout.spinner_item);
            Log.d("testyyyytyyyyyyyyyyyy", dataAdapter.toString());



            return dataAdapter;


        }



        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ArrayAdapter<String> result) {






        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }



}


