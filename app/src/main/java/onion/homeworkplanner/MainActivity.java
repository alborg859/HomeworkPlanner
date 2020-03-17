package onion.homeworkplanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;

import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    //Zrobić settings i tam godzina dla powiadomień, najlepiej jako !!! hour picker!!!  + creditsy dla ikoniarza i autorzy ofc
    //Zrobić swipe'a
    //Zrobić flagi
    //Zrobić ewentualnie więcej powiadomień !
    //Widgety
    //No już ewentualnie j.Polski, ale komu by sie chciało
    //Ewentualnie w przyszłości bazę danych pod notatki oraz sprawdziany
    //Ewentualnie w przyszłości kalendarz z wydarzeniami zapisanymi na dany dzień.





    HomeworkHelper mHomeworkHelper;
    RecyclerView listupcoming;

    TextView nts;
    public static MainActivity mActivity;




    public String description;


    int outdated;



    ArrayList<Homework> arrayList;
    public MyRecyclerViewAdapter myAdapter;
    CoordinatorLayout coordinatorLayout;


    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //TODO: turn on the ads




        mActivity = this;
        mHomeworkHelper = new HomeworkHelper(this);

        listupcoming = findViewById(R.id.list_up);
        listupcoming.setLayoutManager(new LinearLayoutManager(this));
        nts = findViewById(R.id.txtViewNTS);
        coordinatorLayout = findViewById(R.id.coordinator_Layout_Main);













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

        if(arrayList.size()<= 0) {
            nts.setVisibility(View.VISIBLE);
        } else  {
            nts.setVisibility(View.INVISIBLE);

            myAdapter = new MyRecyclerViewAdapter(this, arrayList);
            listupcoming.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
            myAdapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }

                @Override
                public void onLongItemClick(View view, int position) {

                }
            });


            //swipe controller
            final SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {


                    if ( i == 4){// Swipe to delete

                    final int position = viewHolder.getAdapterPosition();
                    final Homework item = myAdapter.getData().get(position);

                    myAdapter.removeItem(position);
                    mHomeworkHelper.deleteHomework(item.getId(), item.getSubject_homework(), item.getDescription_homework(), item.getDate_homework());


                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            myAdapter.restoreItem(item, position);
                            mHomeworkHelper.addData(item.getSubject_homework(), item.getDescription_homework(), item.getDate_homework());
                            listupcoming.scrollToPosition(position);
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();

                }

                    else if (i == 8) { // Swipe to qr

                        final int position = viewHolder.getAdapterPosition();



                        String subject_str =  myAdapter.getItem(position).getSubject_homework();
                        String deadline_str = myAdapter.getItem(position).getDate_homework();
                        String description_str = myAdapter.getItem(position).getDescription_homework();
                        Intent qrintent = new Intent(MainActivity.this, QRCodeActivity.class);
                        qrintent.putExtra("subject", subject_str);
                        qrintent.putExtra("desc", description_str);
                        qrintent.putExtra("deadline", deadline_str);
                        qrintent.putExtra("alldata", "");
                        startActivity(qrintent);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                myAdapter.notifyDataSetChanged();
                            }
                        }, 1000);




                    }
                }
            };

            ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
            itemTouchhelper.attachToRecyclerView(listupcoming);



        }






    }






    //3 kropki
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


