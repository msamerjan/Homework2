package edu.lclark.homework2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by maiaphoebedylansamerjan on 2/18/16.
 */
public class MainActivity extends AppCompatActivity {
    PokemonArrayAdapter Adapter;
    public static final String TAG = MainActivity.class.getSimpleName();
    PokemonAsyncTask AsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pokedex pokedex = new Pokedex();

        Adapter = new PokemonArrayAdapter(this, R.layout.row_pokemon, pokedex.getPokemons());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (AsyncTask != null && !AsyncTask.isCancelled()) {
            AsyncTask.cancel(true);
        }
    }


}
