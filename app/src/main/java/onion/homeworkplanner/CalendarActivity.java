package onion.homeworkplanner;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    int defYear;
    int defMonth;
    int defDay;

    int rok;
    int miesiac;
    int dzien;

    DatabaseHelper mDatabaseHelper;
    HomeworkHelper mHomeworkHelper;
    public static   String  datecontent;
    public static   String descriptiontext;
    public static   String subject;
    ListView lista;
    String DEFDATE;
    String DATAKUTAFON;

    ArrayList<Homework> arrayList;
    MyAdapterCal myAdaptertet;
    TextView nts;
    ArrayList<String> myStringList;


    

    public static CalendarActivity calendarActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarActivity = new CalendarActivity();

        Date date = new Date();
        TextView palitko = findViewById(R.id.textView13);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        String[] months= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        palitko.setText(months[month] + " " + String.valueOf(year));

        lista = findViewById(R.id.listuphej);

        nts = findViewById(R.id.txtViewNTS2);

        mDatabaseHelper = new DatabaseHelper(this);
        mHomeworkHelper = new HomeworkHelper(this);
        Date dt = new Date();
        DateTime dtOrg = new DateTime(dt);

        defYear = dtOrg.getYear();
        defMonth = dtOrg.getMonthOfYear();
        defDay = dtOrg.getDayOfMonth();




        myStringList = mHomeworkHelper.getAllDates();
        int sizeOfList = myStringList.size() - 1;
        Log.d("size", "soze to je:  " + sizeOfList);
        int i = 0;
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        compactCalendarView.shouldScrollMonth(true);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        while (i <= sizeOfList){
            String nazwa = myStringList.get(i);


            String [] dateParts = nazwa.split("-");
            String day = dateParts[2];
            String month1 = dateParts[1];
            String year1 = dateParts[0];
            int dayint = Integer.parseInt(day);
            int monthint = Integer.parseInt(month1);
            int yearint = Integer.parseInt(year1);

            Calendar data = Calendar.getInstance();

            data.set(Calendar.YEAR, yearint);
            data.set(Calendar.MONTH, monthint - 1);
            data.set(Calendar.DAY_OF_MONTH, dayint);

            long sarmatyzm = data.getTimeInMillis();
            Log.d("KURWA", " KURWA MAC TO JEST TO: " + sarmatyzm);


            Event ev1 = new Event(Color.parseColor("#ef8354"),  data.getTimeInMillis());
            compactCalendarView.addEvent(ev1);


            i++;
        }






        DEFDATE = String.valueOf(defYear) + "-" + String.valueOf(defMonth) + "-" + String.valueOf(defDay);



        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {


                Calendar cal = Calendar.getInstance();
                cal.setTime(dateClicked);

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);


                rok = year;
                miesiac = month;
                dzien = dayOfMonth;



                DATAKUTAFON = String.valueOf(rok) + "-" + String.valueOf(miesiac + 1) + "-" + String.valueOf(dzien);
                Log.d("Data w cal", "Data onchange: " + DATAKUTAFON);
                loadListView(DATAKUTAFON);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            TextView kutasiki = findViewById(R.id.textView13);
                Calendar cal = Calendar.getInstance();
                cal.setTime(firstDayOfNewMonth);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                String[] months= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

                kutasiki.setText(months[month] + " " + String.valueOf(year));
            }
        });





        loadListView(DEFDATE);
        Log.d("Data w cal", "Data default: " + DATAKUTAFON);



    }

    public void loadListView(String dateKUTAS){
    arrayList = mHomeworkHelper.getDataByDate(dateKUTAS);
    myAdaptertet = new MyAdapterCal(this, arrayList);
    lista.setAdapter(myAdaptertet);
    myAdaptertet.notifyDataSetChanged();

    //// Sprawdzić czy nie ladży :)
    MainActivity.mActivity.loadDataInListView();

        if(arrayList.size()<= 0) {
            nts.setVisibility(View.VISIBLE);
        } else  {
            nts.setVisibility(View.INVISIBLE);
        }

    }

}
