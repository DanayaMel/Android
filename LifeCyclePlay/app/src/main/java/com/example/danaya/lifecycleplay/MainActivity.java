package com.example.danaya.lifecycleplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    //always include name of activity
    private int counter;
    public static final String TAG = "MainActivity";
    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Started onCreate method.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView = findViewById(R.id.textViewMain);
        if(savedInstanceState != null){
            //get the value
            counter = savedInstanceState.getInt("myCounter");
            Log.d(TAG, "Got counter value" + counter);
            myTextView.setText(counter+"");

        }

        Log.d(TAG, "Ended onCreate method.");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Started onStart method.");
        super.onStart();
        Log.d(TAG, "Ended onStart method.");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "Started onRestart method.");
        super.onRestart();
        Log.d(TAG, "Ended onRestart method.");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Started onResume method.");
        super.onResume();
        Log.d(TAG, "Ended onResume method.");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Started onPause method.");
        super.onPause();
        Log.d(TAG, "Ended onPause method.");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "Start of saveinstantstate method.");
        super.onSaveInstanceState(outState);
        outState.putInt("myCounter", counter); //saves the value
        Log.d(TAG, "End of saveinstantstate method saved.");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt("myCounter");
        Log.d(TAG, "End of onRestoreInstanceState method retrieved " + counter);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Started onStop method.");
        super.onStop();
        Log.d(TAG, "Ended onStop method.");
    }

    @Override
    protected void onDestroy() {
        //happens everytime you change layout
        Log.d(TAG, "Started onDestroy method.");
        super.onDestroy();
        Log.d(TAG, "Ended onDestroy method.");
    }

    public void incrementCounter(View view){
        Log.d(TAG, "Inside increment method.");
        counter++;
        myTextView.setText(counter+"");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }

}
