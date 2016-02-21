package edu.lclark.homework2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.support.design.widget.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by maiaphoebedylansamerjan on 2/19/16.
 */
public class PokemonRecyclerView extends AppCompatActivity{
    public static final int ID_POKEMON=0;
    private static final String TAG="Pokemon";
    private PokemonRecyclerView RecyclerView;
    private Pokedex Pokedex;
    private PokemonRecyclerViewAdapter Adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedPokemonState) {
        super.onCreate(savedPokemonState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView=(RecyclerView) findViewById(R.id.activity_pokemon_recyclerview);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Pokedex=new Pokedex();
        Adapter= new PokemonRecyclerViewAdapter(Pokedex.getPokemon(),this);
        RecyclerView.setAdapter(Adapter);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);


        final String url="http://pokeapi.co/api/v2/pokemon/ID_NUMBER/ ";
        new AsyncHttpTask().execute(url);

    }

    public class AsyncHttpTask extends AsyncTask<String,Integer,JSONArray>{
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }
        @Override
        protected JSONArray doInBackground(String... params) {
            StringBuilder responseBuilder = new StringBuilder();
            JSONArray jsonArray=null;
            if (params.length == 0) {
                return null;
            }
            String userId = params[0];
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(new URL("https://api.github.com/users/" + userId + "/subscriptions"););
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

                        Log.d(TAG, owner.getString("login") + " owns " + subscription.getString("name"));
                    } catch (JSONException e) {
                        Log.e(TAG, e.getLocalizedMessage());
                    }
                }
                Log.d(TAG, jsonArray.toString());
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ID_POKEMON && resultCode == RESULT_OK) {
            Pokemon pokemon = data.getParcelableExtra(PokemonDetailView.ARG_POKEMON);
            Snackbar.make(RecyclerView, "I saw " + pokemon.getName(), Snackbar.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
