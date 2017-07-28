package org.thunderdot.starplanner.weather_service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {

    private static String CURRENT_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    private static String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String API_KEY = "&appid=562c735d2c5689311bae0b82d082f84e";

    public String getWeatherData(String location) {

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
                connection = (HttpURLConnection) (new URL(CURRENT_BASE_URL + location + API_KEY)).openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                StringBuffer stringBuffer = new StringBuffer();
                inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                    stringBuffer.append(line + "\r\n");

                inputStream.close();
                connection.disconnect();

                return stringBuffer.toString();
            }
                catch(Throwable throwable) {
                    throwable.printStackTrace();
                }
                finally {
                    try {
                        inputStream.close();
                    } catch (Throwable throwable) {}
                    try {
                        connection.disconnect();
                    } catch (Throwable throwable) {}
        }

        return null;

    }

    public byte[] getImage(String code) {

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) (new URL(IMG_URL + code)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            inputStream = connection.getInputStream();

            byte[] bytes = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while (inputStream.read(bytes) != -1)
                byteArrayOutputStream.write(bytes);

            return byteArrayOutputStream.toByteArray();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (Throwable throwable) {}
            try {
                connection.disconnect();
            } catch (Throwable throwable) {}
        }

        return null;

    }



}
