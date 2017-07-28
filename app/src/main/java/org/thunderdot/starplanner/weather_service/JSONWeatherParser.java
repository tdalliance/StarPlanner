package org.thunderdot.starplanner.weather_service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.thunderdot.starplanner.model.Location;
import org.thunderdot.starplanner.model.Weather;

public class JSONWeatherParser {

    public static Weather getweather(String data) throws JSONException {

        Weather weather = new Weather();
        Location location = new Location();

        JSONObject jsonObject = new JSONObject(data);
        JSONObject coordObject = getObject("coord", jsonObject);
        JSONObject systemObject = getObject("sys", jsonObject);

        JSONArray jsonArray = jsonObject.getJSONArray("weather");
        JSONObject weatherObject = jsonArray.getJSONObject(0);

        JSONObject mainObject = getObject("main", jsonObject);
        JSONObject windObject = getObject("wind", jsonObject);
        JSONObject cloudsObject = getObject("clouds", jsonObject);

        location.setLongitude(getFloat("lon", coordObject));
        location.setLatitude(getFloat("lat", coordObject));

        location.setCountry(getString("country", systemObject));
        location.setCity(getString("city", systemObject));
        location.setSunrise(getInt("sunrise", systemObject));
        location.setSunset(getInt("sunset", systemObject));

        weather.location = location;

        weather.currentCondition.setWeatherId(getInt("id", weatherObject));
        weather.currentCondition.setCondition(getString("main", weatherObject));
        weather.currentCondition.setDescription(getString("description", weatherObject));
        weather.currentCondition.setIcon(getString("icon", weatherObject));
        weather.currentCondition.setPressure(getFloat("pressure", mainObject));
        weather.currentCondition.setHumidity(getFloat("humidity", mainObject));

        weather.temperature.setTemp(getFloat("temp", mainObject));
        weather.temperature.setTemp_min(getFloat("temp_min", mainObject));
        weather.temperature.setTemp_max(getFloat("temp_max", mainObject));

        weather.wind.setSpeed(getFloat("speed", windObject));
        weather.wind.setDeg(getFloat("deg", windObject));

        weather.clouds.setPerc(getInt("all", cloudsObject));


        return weather;

    }

    private static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException{
        JSONObject subObject = jsonObject.getJSONObject(tagName);
        return subObject;
    }

    private static String getString(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jsonObject) throws JSONException {
        return (float)jsonObject.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getInt(tagName);
    }

}
