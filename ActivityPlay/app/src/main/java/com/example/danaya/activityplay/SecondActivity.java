package com.example.danaya.activityplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class SecondActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView) findViewById(R.id.textView_Main1);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("My message");
        textView.setText(msg);
    }

    public void sendToFirstActivity(View view) {
    }
}
