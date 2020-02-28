package onion.homeworkplanner;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class subject extends AppCompatActivity {


    private static final String TAG = "Listy";
    DatabaseHelper mDatabaseHelper;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView subjectList;
    int liczba;
    int checker;/// Maksymalna liczba przedmiotów
    public static subject msubject;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        msubject = this;
        checker = 30;/// Maksymalna liczba przedmiotów

        listItem = new ArrayList<>();




        Button add = findViewById(R.id.button2);
        Button cancel = findViewById(R.id.btncancel2);
        subjectList = findViewById(R.id.list_subjects);
        mDatabaseHelper = new DatabaseHelper(this);


        final EditText customsubject = findViewById(R.id.editText2);
        final TextView maxchars2 = findViewById(R.id.maxchars2);

        maxchars2.setText("0 / 20");

        customsubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                maxchars2.setText(String.valueOf(s.length()) + " / 20");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = customsubject.getText().toString();
                liczba = mDatabaseHelper.CountRows();

                if(customsubject.length() != 0) {
                    if(liczba >= checker){

                        Toasty.normal(getApplicationContext(), "You can't add more than " + checker + " subjects").show();
                    }
                    else{
                        addData(newEntry);
                        customsubject.setText("");
                    }



                    customsubject.setText("");
                } else {

                    Toasty.normal(getApplicationContext(), "This text field can't be empty").show();
                }

            }
        });

        viewData();

    }







    public void viewData() {
        Cursor cursor = mDatabaseHelper.getData();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }


            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem ){


                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView textView = (TextView) super.getView(position, convertView, parent);

                   Typeface typeface;
                    Context context = getContext();

                    typeface = ResourcesCompat.getFont(context, R.font.nslight);
                    textView.setTypeface(typeface);
                    textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);


                    textView.setTextColor(Color.BLACK);
                    return textView;
                }
            };



            subjectList.setAdapter(adapter);

            subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String name = adapterView.getItemAtPosition(i).toString();
                    Log.d(TAG, "On item click; Clicked on: "+ name);

                    Cursor data = mDatabaseHelper.getItemID(name);

                    int itemID = -1;
                    while(data.moveToNext()){
                        itemID = data.getInt(0);
                    }

                    if (itemID > -1){
                        Log.d(TAG, "The id is: "+ itemID);

                        Intent editScreenIntent = new Intent(subject.this, EditDataActivity.class);
                        editScreenIntent.putExtra("id", itemID);
                        editScreenIntent.putExtra("name", name);
                        startActivity(editScreenIntent);
                        finish();




                    }
                    else{
                        Log.d(TAG, "NO ID FOUND" );
                    }


                }
            });



        }    }




    private void addData(String NewEntry){



        boolean insertData = mDatabaseHelper.addData(NewEntry);

        if (insertData) {

            adapter.clear();
            subjectList.setAdapter(adapter);
            viewData();

        } else {

        }
    }



}
