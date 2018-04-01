package com.example.danaya.implicitintentplay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText editTextWeb, editTextLocation, editTextText;
    private Button buttonWeb, buttonLoc, buttonText, buttonPic, buttonDialJoey;
    public static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "inside on create.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReferencesToWidgets();//prevent code bloat...get the references to widgets

        setOnClickListenersToButtons();//make sure to set the lsiteners in that method

        Log.d(TAG, "End of on create.");
    }
    private void getReferencesToWidgets(){
        Log.d(TAG, "inside on getReferencesToWidgets.");
        buttonWeb = (Button) findViewById(R.id.button_Web);
        editTextWeb = (EditText) findViewById(R.id.editText_web);
        editTextLocation = (EditText) findViewById(R.id.editText_Location);
        buttonLoc = (Button) findViewById(R.id.button_Location);
        editTextText = (EditText) findViewById(R.id.editText_Text);
        buttonText = (Button) findViewById(R.id.button_Text);
        buttonPic = (Button) findViewById(R.id.button_Take_Pic);
        buttonDialJoey  = (Button) findViewById(R.id.button_DialJoey);

    }

    private void setOnClickListenersToButtons(){
        Log.d(TAG, "inside OnClickListenersToButtons.");
        buttonWeb.setOnClickListener(this);
        buttonLoc.setOnClickListener(this);
        buttonText.setOnClickListener(this);
        buttonPic.setOnClickListener(this);
        buttonDialJoey.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "inside onclick.");
        switch (view.getId()){
            case R.id.button_Web:
                Log.d(TAG, "button web was clicked.");
                openWebsite();
                break;
            case R.id.button_Location:
                Log.d(TAG, "button loc was clicked.");
                openLocation();
                break;
            case R.id.button_Text:
                Log.d(TAG, "button text was clicked.");
                sendText();
                break;
            case R.id.button_Take_Pic:
                Log.d(TAG, "button tpic was clicked.");
                takePic();
                break;
            case R.id.button_DialJoey:
                Log.d(TAG, "button dial joey was clicked.");
                dialJoey();
            default:
                Log.d(TAG, "I have no idea what was clicked.");
        }
    }

    private void takePic() {
        Log.d(TAG, "inside Take pic method");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }else{
            Toast.makeText(this, "Cannot take a pic.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "inside onactivityresult method");
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Log.d(TAG, "Successfully took a pic.");
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
//            intent.putExtra("bmp_Image", data);
            intent.putExtra("imagePath", data);
            startActivity(intent);
            //create an explicit intent to antoher activity in your app to display the image.
            //or... create an implicit intent to allow an activity to pick up on it and display
        }else{
            Log.d(TAG, "Did not take a pic.");
            Toast.makeText(this, "Could not take a pic", Toast.LENGTH_LONG).show();
        }
    }

    private void dialJoey(){
        Log.d(TAG, "inside dial Joey");
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:5555555555"));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d(TAG, "Did not dial Joey.");
            Toast.makeText(this, "Could not dial Joey", Toast.LENGTH_LONG).show();
        }
    }

    private void sendText() {
        Log.d(TAG, "inside sendText method");
        String textToSend = editTextText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle("Pick an app").setText(textToSend).startChooser();
    }

    private void openLocation() {
        Log.d(TAG, "inside openLocation method");
        String loc = editTextLocation.getText().toString();
        Uri uri = Uri.parse("geo:0,0?q="+loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //checks if there is activity to handle intent.
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d(TAG, "So sorry, cannot handle opening web page. "+loc);
            Toast.makeText(this, "So sorry, cannot handle opening web page. "+loc, Toast.LENGTH_LONG).show();
        }
    }

    private void openWebsite() {
        Log.d(TAG, "inside openWebsite method");
        String url = editTextWeb.getText().toString();
        Uri webPage = Uri.parse("http://"+url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        //checks if there is activity to handle intent.
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d(TAG, "So sorry, cannot handle opening web page. "+url);
            Toast.makeText(this, "So sorry, cannot handle opening web page. "+url, Toast.LENGTH_LONG).show();
        }
    }
}
