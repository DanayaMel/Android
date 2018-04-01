package com.example.danaya.inputcontrol;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    public static final String TAG = "MainActivity";
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Button button_call;
    EditText editTextPhone;
    String [] PERMISSIONS_REQUESTED = {Manifest.permission.CALL_PHONE};
    public static final int REQUEST_PERMISSION_PHONE_CALL=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.label_phones_array,
                android.R.layout.simple_spinner_item);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);

        editTextPhone = (EditText) findViewById(R.id.edit_text_phone);
        button_call = (Button) findViewById(R.id.button_main_call);
        button_call.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, "Inside onitemSelected method.");
        //get the item selected by parameter i
        String spinnerText = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, "You chose "+spinnerText, Toast.LENGTH_LONG).show();
        Log.d(TAG, "You chose "+spinnerText);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_main_call:
                Log.d(TAG, "Button main call clicked.");
                confirmCall();
                break;
            default:
                Log.d(TAG, "Dunno what was clicked.");
                break;
        }
    }

    private void confirmCall() {
        Log.d(TAG, "Inside confirmCall method.");
        //use constructor to make alert dialog
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        //set a title for it
        alertBuilder.setTitle("You want to call? Yes or No");
        //set two button options
        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "yes want to call.. will go ahead.");
                Toast.makeText(getApplicationContext(), "YES, CALL!", Toast.LENGTH_LONG).show();
                callUsingImplicitIntent();
            }
        });

        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "no, don't want to call..");
                Toast.makeText(getApplicationContext(), "YOU CHOSE NOT TO CALL!", Toast.LENGTH_LONG).show();
            }
        });
        alertBuilder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "Inside onRequestPermissionsResult");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSION_PHONE_CALL:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG, "Permission Granted");
                    callUsingImplicitIntent();//call the method again
                }else{
                    Log.d(TAG, "PERMISSION DENIEDS");
                }
                return;

        }
    }

    private void callUsingImplicitIntent() {
        Log.d(TAG, "Inside callUsingImplicitIntent");
        //get the number
        String telNum = editTextPhone.getText().toString();
        //create an intent
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:"+telNum);
        getIntent().setData(uri);
        if(intent.resolveActivity(getPackageManager()) != null){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, PERMISSIONS_REQUESTED, REQUEST_PERMISSION_PHONE_CALL);
            }else{
                startActivity(intent);
            }
        }
        //fun stuff.. because call is considered dangerous ..so we have more work.
    }
}
