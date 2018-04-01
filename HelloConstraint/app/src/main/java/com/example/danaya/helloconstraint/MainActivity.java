package com.example.danaya.helloconstraint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    Button countButton;
    Button toastButton;
    TextView textView;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countButton = (Button) findViewById(R.id.button_count);
        textView = (TextView) findViewById(R.id.show_count);
    }

    public void updateCounter(View view){
        counter++;
        textView.setText(counter+"");
    }

    public void showToast(View view){
        //create toast & show it.
        Toast myToast = Toast.makeText(this, "The current # is: " + counter +".", Toast.LENGTH_LONG);
        myToast.show();
    }


}