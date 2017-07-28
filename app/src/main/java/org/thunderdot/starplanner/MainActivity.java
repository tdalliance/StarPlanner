package org.thunderdot.starplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView_CurrentWeather = (ImageView) findViewById(R.id.imageView_CurrentWeather);
    TextView textView_DateTime = (TextView) findViewById(R.id.textView_DateTime);
    TextView textView_CityName = (TextView) findViewById(R.id.textView_CityName);
    TextView textView_Msg = (TextView) findViewById(R.id.textView_Msg);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
