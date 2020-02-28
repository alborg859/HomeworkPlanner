package onion.homeworkplanner;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EditDataActivity extends AppCompatActivity {
    private static final String TAG = "Listy";
    DatabaseHelper mDatabaseHelper;

    private Button btnClose, btnDelete;
    private EditText nazwa;
    private ImageView obrazek;



    private String selectedName;
    private int selectedID;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        btnClose = findViewById(R.id.close_btn);
        btnDelete = findViewById(R.id.rmv_btn);
        nazwa = findViewById(R.id.textView5);
        obrazek = findViewById(R.id.imageView);
        mDatabaseHelper = new DatabaseHelper(this);
        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id", -1);

        selectedName = receivedIntent.getStringExtra("name");
        nazwa.setText(selectedName);


        final TextView maxchars = findViewById(R.id.maxchars);
        maxchars.setText(selectedName.length() + " / 20");




        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = nazwa.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateSubject(item, selectedName, selectedID);
                    finish();
                    startActivity(new Intent(EditDataActivity.this, subject.class));


                }else{
                    Log.d(TAG, "Nie może być pusto");
                    nazwa.setText(selectedName);
                }



            }
        });

        nazwa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                maxchars.setText(String.valueOf(s.length()) + " / 20");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteSubject(selectedID, selectedName);
                //animka może na delete
                EditDataActivity.this.finish();
                Intent editScreenIntent = new Intent(EditDataActivity.this, subject.class);
                startActivity(editScreenIntent);

            }



        });




        int[] sicons = {R.drawable.def, R.drawable.art, R.drawable.astronomy, R.drawable.biology, R.drawable.businessstudies, R.drawable.chemistry, R.drawable.citizenship, R.drawable.geography, R.drawable.history, R.drawable.it, R.drawable.law, R.drawable.literature, R.drawable.maths, R.drawable.music, R.drawable.physics, R.drawable.religion, R.drawable.poland, R.drawable.english, R.drawable.spanish, R.drawable.italian, R.drawable.norwegian, R.drawable.russian, R.drawable.niemcy};







        obrazek.setImageResource(sicons[0]);

        if (selectedName.equals("Art")) {
            obrazek.setImageResource(sicons[1]);
        }

        if (selectedName.equals("Astronomy")) {
            obrazek.setImageResource(sicons[2]);
        }

        if (selectedName.equals("Biology")) {
            obrazek.setImageResource(sicons[3]);
        }

        if (selectedName.equals("Business Studies")) {
            obrazek.setImageResource(sicons[4]);
        }

        if (selectedName.equals("Chemistry")) {
            obrazek.setImageResource(sicons[5]);
        }

        if (selectedName.equals("Citizenship")) {
            obrazek.setImageResource(sicons[6]);
        }

        if (selectedName.equals("Geography")) {
            obrazek.setImageResource(sicons[7]);
        }

        if (selectedName.equals("History")) {
            obrazek.setImageResource(sicons[8]);
        }

        if (selectedName.equals("Computer Science")) {
            obrazek.setImageResource(sicons[9]);
        }

        if (selectedName.equals("Law")) {
            obrazek.setImageResource(sicons[10]);
        }

        if (selectedName.equals("Literature")) {
            obrazek.setImageResource(sicons[11]);
        }

        if (selectedName.equals("Maths")) {
            obrazek.setImageResource(sicons[12]);
        }

        if (selectedName.equals("Music")) {
            obrazek.setImageResource(sicons[13]);
        }

        if (selectedName.equals("Physics")) {
            obrazek.setImageResource(sicons[14]);
        }



        if (selectedName.equals("Religion")) {
            obrazek.setImageResource(sicons[15]);
        }

        if (selectedName.equals("Polish")) {
            obrazek.setImageResource(sicons[16]);
        }

        if (selectedName.equals("English")) {
            obrazek.setImageResource(sicons[17]);
        }

        if (selectedName.equals("Spanish")) {
            obrazek.setImageResource(sicons[18]);
        }

        if (selectedName.equals("Italian")) {
            obrazek.setImageResource(sicons[19]);
        }

        if (selectedName.equals("Norwegian")) {
            obrazek.setImageResource(sicons[20]);
        }

        if (selectedName.equals("Russian")) {
            obrazek.setImageResource(sicons[21]);
        }

        if (selectedName.equals("German")) {
            obrazek.setImageResource(sicons[22]);
        }

























    }


}
