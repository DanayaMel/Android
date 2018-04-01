package com.example.danaya.activityplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView_Main1);
    }

    public void sendToSecondActivity(View view) {
        String message = textView_Main1.getText().toString();

        //create an intent .... start the activity
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("My message", message);
        textView_Main1.setText("");
        startActivity(intent);
    }
}
