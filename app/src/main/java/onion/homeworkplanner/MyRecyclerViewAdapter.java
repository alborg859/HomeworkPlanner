package onion.homeworkplanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Homework> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public Context context;


    MyRecyclerViewAdapter(Context context, List<Homework> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String subjectString = mData.get(position).getSubject_homework();
        String descString = mData.get(position).getDescription_homework();
        String deadlineString = mData.get(position).getDate_homework();
        String daysleftString = String.valueOf(mData.get(position).getDaysleft_homework());



        holder.subject.setText(subjectString);
        holder.description.setText(descString);
        holder.deadline.setText(deadlineString);
        holder.daysleft.setText(daysleftString + " days left");



        String[] art= {"Art", "Plastyka", "plastyka", "art"};





        Glide
                .with(context)

                .load("https://drive.google.com/uc?id=149j_7DIBEOCBMaGDuqyILmi4-A-u6gSo" )
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.palitko)
                .into(holder.icon);

        if ( Arrays.asList(art).contains(subjectString) == true) {


            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1wgRIjXhWxpDfZh0B65aIZaN7vSpEb7Sq" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);

        }

        if (subjectString.equals("Astronomy")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1Rg1CGgzbfprpYzWQFzY1SMfqDGAUoxM6" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Biology")) {
            Glide
                    .with(context)

                    .load("https://drive.google.com/uc?id=1CsbaNhNmePdSlDxWCATf21Z8I5yW_m17" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Business studies")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1WPjxy09hjDANRZZXIrCBpj1D7NJfbxKr" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Chemistry")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1OcXA7MSlYqaH38SclCLevM2I6Qo9-cGI" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Citizenship")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1I7HEXVGC83R1KBXtq5ecz9gmRWdadHNQ" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Geography")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=18dySJbkIcL_aZTIIofy6Nxbu4Ns7MquT" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("History")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1pSOZUpgLCeZp9PmEI7zyxkZ8RGQrZk3T" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Computer Science")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1xreYBoa3q774hldTV9dgHLfifNgL3277" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Law")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1XQqNbX4Kl9RqS0Mou2QS9RdrWghdgteE" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Literature")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=10DtdjymWEV32Q1-iratUdsxg6X-IF4Yb" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Maths")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1xC4E-utzFqsm32j4QlfqN6Ptlt4FM8U0" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Music")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1OpYGp31FWCuW7aTPbJG9d5IQH8WL_ZIu" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Physics")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=13aW88nY7ngLuErN2azGKAgWJkX-vnVEA" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Religion")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1KEKx1LZAc1y5KQvB0zTd_glONydafR3W" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Polish")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1PiY3w73Y5QJJZWC6mXRiHPX3OR6bSafZ" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("English")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1qkgp-8WL70RRK4ucGCLgLYRaSzCJxx3S" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Spanish")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1pg84RvqJnVImHp1rfq1VFVRAAAQSYYtO" )

                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Italian")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=183pXtdd3MI3JOAm4YAACBsczSqRU0fOP" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Norwegian")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1Pnl2dmFK1ITDZ-v7FdfPH3V7mu8_SJNO" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("Russian")) {
            Glide
                    .with(context)
                    .load("https://drive.google.com/uc?id=1f15aY30wjU_9Coc3ErfHzGc2gLfm04nL" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }

        if (subjectString.equals("German")) {
            Glide

                    .with(context)
                    .load("https://drive.google.com/uc?id=1Y7cItjKfwAZJaxoQt7sZ3K1knbyTZlX3" )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.palitko)
                    .into(holder.icon);
        }




    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {




         ImageView icon;


         TextView subject;
         TextView description;
         TextView deadline;
         TextView daysleft;


         ImageView cover;
         Button remove;
         Button cancel;
         ImageButton qrcode;

        ViewHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.subject_icon);
            subject = itemView.findViewById(R.id.hm_subject);
            description = itemView.findViewById(R.id.hm_description);
            deadline = itemView.findViewById(R.id.hm_deadline);
            daysleft = itemView.findViewById(R.id.hm_daysleft);

            cover = itemView.findViewById(R.id.hm_cover);
            remove = itemView.findViewById(R.id.hm_remove);
            cancel = itemView.findViewById(R.id.hm_cancel);
            qrcode = itemView.findViewById(R.id.qrcodeButton);



            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);



        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

            Intent i = new Intent(context, Display.class);
            i.putExtra("id", mData.get(getAdapterPosition()).getId());
            i.putExtra("subject", mData.get(getAdapterPosition()).getSubject_homework());
            i.putExtra("description", mData.get(getAdapterPosition()).getDescription_homework());
            i.putExtra("deadline", mData.get(getAdapterPosition()).getDate_homework());
            i.putExtra("daysleft", Integer.toString(mData.get(getAdapterPosition()).getDaysleft_homework()));
            context.startActivity(i);


        }

        @Override
        public boolean onLongClick(View view) {
            if (mClickListener != null) mClickListener.onLongItemClick(view, getAdapterPosition());


            cover.setVisibility(View.VISIBLE);
            remove.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
            qrcode.setVisibility(View.VISIBLE);

            YoYo.with(Techniques.FadeIn)
                    .duration(200)
                    .repeat(0)
                    .playOn(cover);
            YoYo.with(Techniques.FadeInUp).duration(220).repeat(0).playOn(qrcode);

            YoYo.with(Techniques.FadeInLeft)
                    .duration(220)
                    .repeat(0)
                    .playOn(remove);
            YoYo.with(Techniques.FadeInRight)
                    .duration(220)
                    .repeat(0)
                    .playOn(cancel);
            return true;

        }






    }

    // convenience method for getting data at click position
    Homework getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onLongItemClick(View view, int position);
    }
}