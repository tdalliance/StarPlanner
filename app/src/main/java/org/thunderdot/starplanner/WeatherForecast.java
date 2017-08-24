package org.thunderdot.starplanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.thunderdot.starplanner.model.Weather;
import org.thunderdot.starplanner.weather_service.JSONWeatherParser;
import org.thunderdot.starplanner.weather_service.WeatherHttpClient;

public class WeatherForecast extends AppCompatActivity {

    private ImageView iconimageView;
    private TextView condition;
    private TextView description;
    private TextView temperature;
    private TextView humidity;
    private TextView wind_speed;
    private TextView wind_degree;
    private TextView city_name;
    private TextView sunset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        String city = "Chinju, KR";

        iconimageView = (ImageView) findViewById(R.id.iconimageView);
        condition = (TextView) findViewById(R.id.textView_condition);
        description = (TextView) findViewById(R.id.textView_desrcription);
        temperature = (TextView) findViewById(R.id.textView_temperature);
        humidity = (TextView) findViewById(R.id.textView_humidity);
        wind_speed = (TextView) findViewById(R.id.textView_wind_speed);
        wind_degree = (TextView) findViewById(R.id.textView_wind_degree);
        city_name = (TextView) findViewById(R.id.textView_city);
        sunset = (TextView) findViewById(R.id.textView_sunset);

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});

    }

        class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

            @Override
            protected Weather doInBackground(String... params) {
                Weather weather  = new Weather();
                String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

                try {
                    weather = JSONWeatherParser.getweather(data);

                    weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));
                } catch(JSONException e) {
                    e.printStackTrace();
                }
                return weather;
            }

            @Override
            protected void onPostExecute(Weather weather) {
                super.onPostExecute(weather);

                if(weather.iconData != null && weather.iconData.length > 0) {
                    Bitmap img  = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                    iconimageView.setImageBitmap(img);
                }

                condition.setText("Condition : " + weather.currentCondition.getCondition());
                description.setText("Description : " + weather.currentCondition.getDescription());
                temperature.setText("Temperature : " + weather.temperature.getTemp());
                humidity.setText("Humidity : " + weather.currentCondition.getHumidity() + "%");
                wind_speed.setText("Wind Speed : " + weather.wind.getSpeed());
                wind_degree.setText("Wind Degree : " + weather.wind.getDeg());
                city_name.setText("City Name : " + weather.location.getCity() + "/" + weather.location.getCountry());
                sunset.setText("Sunset : " + weather.location.getSunset());

            }

        }


}


