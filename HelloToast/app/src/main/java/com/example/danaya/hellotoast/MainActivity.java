package com.example.danaya.hellotoast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    Button countButton, buttonHello;
//    Button toastButton;
    TextView numView;
    int counter = 0;
    String number;
    public static final String EXTRA_MESSAGE =
            "com.example.android.twoactivities.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonHello = (Button) findViewById(R.id.button_Hello);
        countButton = (Button) findViewById(R.id.button_count);
        numView = (TextView) findViewById(R.id.show_count);
    }

    public void updateCounter(View view){
        counter++;
        numView.setText(counter+"");
    }

    public void showToast(View view){
        //create toast & show it.
        Toast myToast = Toast.makeText(this, "The current # is: " + counter +".", Toast.LENGTH_LONG);
        myToast.show();
    }


    public void sayHello(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        number = numView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, number);
        startActivity(intent);

    }
}