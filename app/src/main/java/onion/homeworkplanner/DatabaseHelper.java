package onion.homeworkplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    int tet;
    private SQLiteDatabase db;
    private static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "Subjects.db";
    public static final String TABLE_NAME = "tabelka";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String test = "CREATE TABLE tabelka (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT ) ";
        String sql = " CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT )";
        db.execSQL(sql);


        ContentValues contentValues = new ContentValues();

        // Tu dodawać przedmioty difoltowe
        contentValues.put(COL_2, "Maths");db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Literature"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "English"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Norwegian"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Italian"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Spanish"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "German"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Russian"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Polish"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Art"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "History"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Computer Science"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Music"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Citizenship"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Law"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Business studies"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Biology"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Physics"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Geography"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Chemistry"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Religion"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Drama"); db.insert(TABLE_NAME, null, contentValues);
        contentValues.put(COL_2, "Astronomy"); db.insert(TABLE_NAME, null, contentValues);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }






    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, item);

        Log.d(TAG, "adding: " + item + "to" + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY name COLLATE NOCASE ASC";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public List<String> getAllSetcardCategory() {
        List<String> setcardCategories = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY name COLLATE NOCASE ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                // adding to tags list
                setcardCategories.add(c.getString(c.getColumnIndex("NAME")));
            } while (c.moveToNext());
        }
        return setcardCategories;
    }


    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemName(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_2 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public void deleteSubject(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_1 + " = '" + id + "'" + " AND " + COL_2 + " = '" + name + "'";
        Log.d(TAG, "Deleting from database" + name );
        db.execSQL(query);
    }


    public void updateSubject(String newName, String oldName, int id ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " +TABLE_NAME+ " SET " + COL_2 + " = '" + newName + "' WHERE " + COL_1 + " = '"+ id + "'" + " AND "+ COL_2 + " = '" + oldName + "'";
        Log.d(TAG, "Updating data" + newName);
        db.execSQL(query);

    }

    public void restoreToDefault() {
        String clearDBQuery = "DELETE FROM "+ TABLE_NAME;
        db.execSQL(clearDBQuery);

    }



    public int CountRows(){

    SQLiteDatabase db = this.getReadableDatabase();
    tet = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return tet;
    }
}










