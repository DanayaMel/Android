package com.example.danaya.pickersintentsandmenus;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.text.format.DateFormat;
//import java.text.DateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        MainActivity activity = (MainActivity) getActivity();
        activity.processTimePicker(hour, minute);

    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity()
                , this, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }



}
