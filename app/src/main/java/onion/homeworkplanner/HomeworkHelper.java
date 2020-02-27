package onion.homeworkplanner;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;import android.content.SharedPreferences;

import org.joda.time.DateTime;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;

import static org.joda.time.Days.daysBetween;

public class HomeworkHelper extends SQLiteOpenHelper {


    int settinghour;
    int settingminute;

    int tet;
    private SQLiteDatabase db;
    private static final String TAG = "HomeworkHelper";
    public static final String DATABASE_NAME = "Homeworks.db";
    public static final String TABLE_NAME = "homework";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SUBJECT";
    public static final String COL_3 = "DESCRIPTION";
    public static final String COL_4 = "DATE";
    public static final String COL_5 = "DAYSLEFT";


    public HomeworkHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql = " CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT, " + COL_3 + " TEXT, " + COL_4 + " TEXT, " + COL_5 + " INTEGER " + ")";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean addData(String subject, String description, String date ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        String [] dateParts = date.split("-");
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


        int daysleftint = (int)noOfDaysBetween;

        contentValues.put(COL_2, subject);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, date);
        contentValues.put(COL_5, daysleftint);





        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
           Log.d(TAG, "NIE DZIAŁA");

            return false;
        } else {
            return true;
        }





    }





    public int CountRows(){

        SQLiteDatabase db = this.getReadableDatabase();
        tet = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return tet;
    }



    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getItemID(String subject, String description, String date, int daysleft) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = '" + subject + "' AND " + COL_3 + " = '" + description + "' AND " + COL_4 + " = '" + date + "' AND " + COL_5 + " = '" + daysleft + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }








    public ArrayList<Homework> getAllData(int outdated)  {
        ArrayList<Homework> arrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_5 + " ASC ";
        SQLiteDatabase dbl = this.getReadableDatabase();


        Cursor cursor = dbl.rawQuery(query, null);
        while(cursor.moveToNext()) {


            int id = cursor.getInt(0);
            String subject = cursor.getString(1);
            String description = cursor.getString(2);
            String date = cursor.getString(3);
            int daysleft = cursor.getInt(4);

            String [] dateParts = date.split("-");
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

            int daysleftint3 = (int)noOfDaysBetween ;


            int newDaysleft = refreshDaysLeft(id, daysleftint3);

            if(newDaysleft  <= outdated){
            deleteHomework(id, subject, description, date );
            }else
                {
            Homework homework = new Homework(id, subject, description, date, newDaysleft);
            arrayList.add(homework);
                }
        }
        return arrayList;
    }


    public ArrayList<String> getAllDates(){
        ArrayList<String> arrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_5 + " ASC ";
        SQLiteDatabase dbl = this.getReadableDatabase();
        Cursor cursor = dbl.rawQuery(query, null);

        while(cursor.moveToNext()) {
            String date = cursor.getString(3);
            arrayList.add(date);
        }
        return arrayList;
    }




    public int refreshDaysLeft(int ID, int newDaysLeft ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " +TABLE_NAME+ " SET " + COL_5 + " = '" + newDaysLeft + "' WHERE " + COL_1 + " = '"+ ID + "'";
        Log.d(TAG, "Updating data: " + newDaysLeft) ;
        db.execSQL(query);
        return newDaysLeft;
    }




    public void updateHomework(int id, String newSubject, String oldSubject, String newDescription, String oldDescription, String newDate, String oldDate ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " +TABLE_NAME+ " SET " + COL_2 + " = '" + newSubject + "' WHERE " + COL_1 + " = '"+ id + "'" + " AND "+ COL_2 + " = '" + oldSubject + "'";
        Log.d(TAG, "Updating data" + newSubject);
        db.execSQL(query);


        String query1 = "UPDATE " +TABLE_NAME+ " SET " + COL_3 + " = '" + newDescription + "' WHERE " + COL_1 + " = '"+ id + "'" + " AND "+ COL_3 + " = '" + oldDescription + "'";
        Log.d(TAG, "Updating data" + newDescription);
        db.execSQL(query1);

        String query2 = "UPDATE " +TABLE_NAME+ " SET " + COL_4 + " = '" + newDate + "' WHERE " + COL_1 + " = '"+ id + "'" + " AND "+ COL_4 + " = '" + oldDate + "'";
        Log.d(TAG, "Updating data" + newDate);
        db.execSQL(query2);
    }








    public void deleteHomework(int id, String subject, String description, String deadline){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_1 + " = '" + id + "'" + " AND " + COL_2 + " = '" + subject + "'" + " AND " + COL_3 + " = '" + description + "'" + " AND " + COL_4 + " = '" + deadline + "'";
        Log.d(TAG, "Deleting from database" + subject + deadline );
        db.execSQL(query);
    }


    public ArrayList<Homework> getDataByDate(String datearg) {
        ArrayList<Homework> arrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_4 + " = '" + datearg + "'";
        SQLiteDatabase dbl = this.getReadableDatabase();
        Cursor cursor = dbl.rawQuery(query, null);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String subject = cursor.getString(1);
            String description = cursor.getString(2);
            String date = cursor.getString(3);
            int daysleft = cursor.getInt(4);
            int newDaysleft = daysleft;



            Homework homework = new Homework(id, subject, description, date, newDaysleft);
            arrayList.add(homework);

        }

        return arrayList;
    }


    public String getAllHomeworksToString(){
        String query = "SELECT * FROM " + TABLE_NAME ;
        SQLiteDatabase dbl = this.getReadableDatabase();
        Cursor cursor = dbl.rawQuery(query, null);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mult~");


        while(cursor.moveToNext()) {

            String subject = cursor.getString(1);
            String description = cursor.getString(2);
            String date = cursor.getString(3);

            stringBuilder.append(subject + "͡°");
            stringBuilder.append(description + "͡°");
            stringBuilder.append(date + "͡°" + "~");

        }

        stringBuilder.append(String.valueOf(cursor.getCount()));
        return  stringBuilder.toString();


    }










    }


