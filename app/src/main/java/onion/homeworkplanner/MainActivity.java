package onion.homeworkplanner;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.Element;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Zrobić settings i tam godzina dla powiadomień, najlepiej jako !!! hour picker!!!  + creditsy dla ikoniarza i autorzy ofc
    //Zrobić swipe'a
    //Zrobić flagi
    //Zrobić ewentualnie więcej powiadomień !
    //Widgety
    //No już ewentualnie j.Polski, ale komu by sie chciało
    //Ewentualnie w przyszłości bazę danych pod notatki oraz sprawdziany
    //Ewentualnie w przyszłości kalendarz z wydarzeniami zapisanymi na dany dzień.





    HomeworkHelper mHomeworkHelper;
    ArrayList<String> listItem;
    ListView listupcoming;

    TextView nts;
    public static MainActivity mActivity;


    private static final String TAG = "DOBERA";

    public String subjectstr;
    public String description;
    public String date;

    int outdated;



    ArrayList<Homework> arrayList;
    MyAdapter myAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        mHomeworkHelper = new HomeworkHelper(this);

        listupcoming = findViewById(R.id.list_up);

        nts = findViewById(R.id.txtViewNTS);


        listItem = new ArrayList<>();










        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editortrue = sharedPreferences.edit();
            editortrue.putInt("Hour", 12);
            editortrue.putInt("Minute", 0);
            editortrue.putInt("daysoutdated", -7);
            editortrue.apply();

            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Snackbar.make(view, "Odpalanie dodaj_ocenę activity", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
               

                startActivity(new Intent(MainActivity.this, PlanActivity.class));


            }
        });

        FloatingActionButton fab_calendar = findViewById(R.id.fab_calendar);
        fab_calendar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        outdated = sharedPreferences.getInt("daysoutdated", -7);
        arrayList = new ArrayList<>();
        loadDataInListView();








    } //koniec kurcza onkirejta



    public void loadDataInListView(){
        arrayList = mHomeworkHelper.getAllData(outdated);
        myAdapter = new MyAdapter(this, arrayList);
        listupcoming.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        if(arrayList.size()<= 0) {
            nts.setVisibility(View.VISIBLE);
        } else  {
            nts.setVisibility(View.INVISIBLE);
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_subjects) {

            startActivity(new Intent(MainActivity.this, subject.class));


            //



        }
        if (id == R.id.action_options) {
            startActivity(new Intent(MainActivity.this, Options.class));

        }
        return super.onOptionsItemSelected(item);
    }




}


