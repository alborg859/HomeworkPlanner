package onion.homeworkplanner;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;


import es.dmoral.toasty.Toasty;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    HomeworkHelper mHomeworkHelper;
    private ZXingScannerView mScannerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scanner);

        // Programmatically initialize the scanner view
        mScannerView = new ZXingScannerView(this);
        // Set the scanner view as the content view
        setContentView(mScannerView);

        mHomeworkHelper = new HomeworkHelper(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }


    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }



    @Override
    public void handleResult(Result rawResult) {

        String scanned = rawResult.getText();
        Log.d("QRCodeScanner", rawResult.getText());


        String firstfour = scanned.substring(0, 4);
        int replace = scanned.length() - scanned.replace("͡° ͜ʖ ͡°", "").length();





        if (firstfour.equals("mult")){
            PlanActivity.fa.finish();



            String[] QRPartsmult = scanned.split("~");
            int numberofrows = QRPartsmult.length;
            int indexbig = 1;





            while (indexbig < numberofrows) {
                String smallest = QRPartsmult[indexbig];

                String[] QRParts = smallest.split("͡°");
                String date = QRParts[2];
                String desc = QRParts[1];
                String subj = QRParts[0];
                mHomeworkHelper.addData(subj, desc, date);


                indexbig = indexbig + 1;

            }
            MainActivity.mActivity.finish();
            this.finish();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);








        }


           if (replace == 16){
            String[] QRParts = scanned.split("͡° ͜ʖ ͡°");
            String date = QRParts[2];
            String desc = QRParts[1];
            String subj = QRParts[0];
            Toasty.success(getApplicationContext(), "Homework added", Toast.LENGTH_SHORT, true).show();
            mHomeworkHelper.addData(subj, desc, date);
            MainActivity.mActivity.loadDataInListView();
            PlanActivity.fa.finish();
            this.finish();
        }


        if (replace != 16 && firstfour.equals("mult ") == false){
            Toasty.error(getApplicationContext(), "Invalid QR", Toast.LENGTH_SHORT, true).show();
               mScannerView.resumeCameraPreview(this);
        }


    }
}
