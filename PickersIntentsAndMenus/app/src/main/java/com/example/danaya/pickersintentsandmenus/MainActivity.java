package com.example.danaya.pickersintentsandmenus;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private TextView textViewDisplayDate;
    private TextView textViewDisplayTime;
    private Button buttonNextActivity;
    private String timesAsString, dateAsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewDisplayDate = (TextView) findViewById(R.id.textView_DisplayDate);
        textViewDisplayTime = (TextView) findViewById(R.id.textView_DisplayTime);
        buttonNextActivity = (Button) findViewById(R.id.button_next_activity);
    }


    public void showTimePickerDialog(View view) {
        Log.d(TAG, "Inside showTimePickerDialog");
        TimePickerFragment timepickerFragment = new TimePickerFragment();
        timepickerFragment.show(getSupportFragmentManager(), "Time Picker");
        //create an instance of the time picker
    }

    public void showDatePickerDialog(View view) {
        Log.d(TAG, "Inside showDatePickerDialog");
        //create an instance of the date picker
        DatePickerFragment datepickerFragment = new DatePickerFragment();
        datepickerFragment.show(getSupportFragmentManager(), "Date Picker");
    }

    public void processDatePicker(int year, int month, int day){
        Log.d(TAG, "Inside processDatePicker");
        //format our date
        String formattedDate = month+"/"+day+"/"+year;
            //display it on the textviews
        Toast.makeText(this, "time = " +formattedDate, Toast.LENGTH_LONG).show();
        textViewDisplayDate.setText(formattedDate);
        textViewDisplayDate.setVisibility(View.VISIBLE);
        enableButton();
    }

    private void enableButton() {
        buttonNextActivity.setVisibility(View.VISIBLE);
        buttonNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderActivity(view);
            }
        });
    }

    private void goToOrderActivity(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("Date", dateAsString);
        intent.putExtra("Time", timesAsString);
        startActivity(intent);
    }

    public void processTimePicker(int hour, int minute){
        Log.d(TAG, "Inside processTimePicker");
        //format our time
        String formattedTime = hour+":"+minute;
            //display it on the textviews
        Toast.makeText(this, "time = " +formattedTime, Toast.LENGTH_LONG).show();
        textViewDisplayTime.setText(formattedTime);
        textViewDisplayTime.setVisibility(View.VISIBLE);
        enableButton();
    }
}
