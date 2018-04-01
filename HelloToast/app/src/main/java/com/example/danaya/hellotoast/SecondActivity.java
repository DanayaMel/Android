package com.example.danaya.hellotoast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //get the intent from parent.
        Intent intent = getIntent();
        //get the message from the putExtra in main.
        String message =
                intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //set the text to be the found message.
        TextView textView = (TextView) findViewById(R.id.numResult);
        textView.setText(message);
    }
}
