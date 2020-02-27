package onion.homeworkplanner;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.util.List;

public class Async extends AsyncTask<Void, Void, ArrayAdapter<String>> {
    DatabaseHelper mDatabaseHelper ;
    PlanActivity pa;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayAdapter<String> doInBackground(Void... params) {


        mDatabaseHelper = new DatabaseHelper(pa);

        List<String> lables = mDatabaseHelper.getAllSetcardCategory();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(pa,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);


        return null;

    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayAdapter<String> result) {


        super.onPostExecute(result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

}
