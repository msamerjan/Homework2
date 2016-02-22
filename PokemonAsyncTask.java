package edu.lclark.homework2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by maiaphoebedylansamerjan on 2/21/16.
 */
public class PokemonAsyncTask extends AsyncTask<String,Integer,JSONArray> {

    public static final String TAG = PokemonAsyncTask.class.getSimpleName();
Pokemon currentPokemon;


@Override
protected void onPreExecute() {
    super.onPreExecute();
    Log.d(TAG, "Started AsyncTask");
}


@Override
protected JSONArray doInBackground(String... params) {
        StringBuilder responseBuilder = new StringBuilder();
        JSONArray jsonArray=null;
        if (params.length == 0) {
        return null;
        }
        HttpURLConnection urlConnection;
        try {
        URL url = new URL("http://pokeapi.co/api/v2/pokemon/"+currentPokemon.getID());
        urlConnection = (HttpURLConnection) url.openConnection();
        InputStreamReader inputStream = new InputStreamReader(urlConnection.getInputStream());
        BufferedReader reader = new BufferedReader(inputStream);
        String line;

        if (isCancelled()) {
        return null;
        }
        while ((line = reader.readLine()) != null) {
        responseBuilder.append(line);

        if (isCancelled()) {
        return null;
        }
        }

        jsonArray = new JSONArray(responseBuilder.toString());

        if (isCancelled()) {
        return null;
        }
        } catch (IOException | JSONException e) {
        Log.e(TAG, e.getLocalizedMessage());
        }

        return jsonArray;

        }
@Override
protected void onCancelled(JSONArray jsonArray) {
        super.onCancelled(jsonArray);
        Log.d(TAG, "AsyncTask cancelled");
        }

@Override
protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        if (jsonArray == null) {
        Log.e(TAG, "Resulting JSON is null");
        } else {
        for (int i = 0; i < jsonArray.length(); i++) {
        try {
        JSONObject subscription = jsonArray.getJSONObject(i);
        JSONObject owner = subscription.getJSONObject("owner");

        Log.d(TAG, owner.getString("Pokemon") + " has " + subscription.getString("number"));
        } catch (JSONException e) {
        Log.e(TAG, e.getLocalizedMessage());
        }
        }
        Log.d(TAG, jsonArray.toString());
        }
        }
}
