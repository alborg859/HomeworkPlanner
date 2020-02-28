package onion.homeworkplanner;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;


public class Options extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Button timePick;
    public int hour_alarm;
    public int minute_alarm;
    static final int DIALOG_ID = 0;
    TextView datepicked;
    Spinner spinner;
    int intdlakacpraf;
    Button save;

    HomeworkHelper mHomeworkHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        final SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        int settinghour = sharedPreferences.getInt("Hour", 12);
        int settingminute = sharedPreferences.getInt("Minute", 12);
        int daysoutdated = sharedPreferences.getInt("daysoutdated", -7);

        datepicked = findViewById(R.id.Pickeddate);
        mHomeworkHelper = new HomeworkHelper(this);
        save = findViewById(R.id.buttonsave);


        spinner = findViewById(R.id.spinner2);
        ArrayList<String> arrayList = new ArrayList<>();

        Button exporttoqr = findViewById(R.id.buttonqr);

        exporttoqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = mHomeworkHelper.getAllHomeworksToString();
                Intent qrintent = new Intent(getApplicationContext(), QRCodeActivity.class);
                qrintent.putExtra("alldata", test);
                getApplicationContext().startActivity(qrintent);

            }
        });

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("Never");






        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayList);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
        if(daysoutdated ==  -999999999){
            spinner.setSelection(7);
        }else
            {
            daysoutdated = daysoutdated * -1;
            spinner.setSelection(daysoutdated - 1);}






        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.BLACK);

                String outdated = spinner.getSelectedItem().toString();

                int dni;
                if (outdated.equals("Never")){
                    dni = -999999999;
                }
                else{
                    dni = Integer.parseInt(outdated);
                dni = dni * -1;
                }



                SharedPreferences.Editor editortrue = sharedPreferences.edit();
                editortrue.putInt("daysoutdated", dni);
                editortrue.apply();
                Log.d("tersadf", "asdasasgfd ga: " + dni);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });












        String minutetext = String.valueOf(settingminute);
        String hourtext = String.valueOf(settinghour);

        if(String.valueOf(settinghour).length() == 1){
            String hourtextmodify = "0" + hourtext;
            hourtext = hourtextmodify;
        }

        if(String.valueOf(settingminute).length() == 1){
            String minutetextmodify = "0" + minutetext;
            minutetext = minutetextmodify;
        }

        datepicked.setText("Picked: " + hourtext + ":"+ minutetext);


        timePick = findViewById(R.id.buttontimepick);
        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();

                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }





        @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_alarm = hourOfDay;
        minute_alarm = minute;

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editortrue = sharedPreferences.edit();
        editortrue.putInt("Hour", hourOfDay);
        editortrue.putInt("Minute", minute);
        editortrue.apply();

        String minutetext = String.valueOf(minute);
        String hourtext = String.valueOf(hourOfDay);


        if(hourtext.length() == 1){
            String hourtextmodify = "0" + hourtext;
            hourtext = hourtextmodify;
        }

        if(minutetext.length() == 1){
                String minutetextmodify = "0" + minutetext;
                minutetext = minutetextmodify;
        }






        datepicked.setText("Picked: " + hourtext + ":"+ minutetext);

    }
}







