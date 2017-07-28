package org.thunderdot.starplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;


public class Calendar extends AppCompatActivity {

    CalendarView calendarView_event = (CalendarView) findViewById(R.id.calendarView_Event);
    TextView textView_EventTitle = (TextView) findViewById(R.id.textView_EventTitle);
    TextView textView_EventDetail = (TextView) findViewById(R.id.textView_EventDetail);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }
}
