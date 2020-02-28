package onion.homeworkplanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRCodeActivity extends AppCompatActivity {

    String subject;
    String desc;
    String deadline;
    String alldata;

    String qrstring;

    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        ImageView qr = findViewById(R.id.qrimageView);

        Intent receivedIntent = getIntent();

        subject = receivedIntent.getStringExtra("subject");
        desc = receivedIntent.getStringExtra("desc");
        deadline = receivedIntent.getStringExtra("deadline");
        alldata = receivedIntent.getStringExtra("alldata");




        // Tu kurwa usuwanie swipe z maina
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.mActivity.myAdapter.notifyDataSetChanged();
            }
        }, 1000);



        if (alldata.equals("")) {


            qrstring = subject + "͡° ͜ʖ ͡°" + desc + "͡° ͜ʖ ͡°" + deadline;
            QRGEncoder qrgEncoder = new QRGEncoder(qrstring, null, QRGContents.Type.TEXT, 800);


            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                qr.setImageBitmap(bitmap);


            } catch (WriterException e) {
                Log.v("xd", e.toString());
            }

        }


        else{
            QRGEncoder qrgEncoder = new QRGEncoder(alldata, null, QRGContents.Type.TEXT, 800);

            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                qr.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v("xd", e.toString());
            }
        }




    }








}




