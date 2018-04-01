package com.example.danaya.asynctaskloader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.Random;

/**
 * Created by Danaya on 3/26/18.
 */

public class MyAsyncTaskLoader  extends AsyncTaskLoader{
    public static final String TAG = "MyAsyncTaskLoader";
    private String taskText;
    private String taskID;

    public MyAsyncTaskLoader(Context context, String text) {
        super(context);
        taskID = text;
        taskText = taskID;
        Log.d(TAG, "ID" + taskText);
        Log.d(TAG, "TEXT" + taskText);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        //make sure we load the data regardless.
        forceLoad();
    }

    @Override
    public Object loadInBackground() {
        Random rand = new Random();
        int x = rand.nextInt(10);
        for(int i=0; i<=x; i++){
            try{
                taskText = taskID + " num " + x;
                Thread.sleep(250);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Log.d(TAG, taskText + "i= " +i);
        }
        return taskText + " done!";
//        return null;
    }
}
