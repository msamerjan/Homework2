package edu.lclark.homework2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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
        ListView listView = (ListView) findViewById(R.id.homework_main_listview);

        Pokedex pokedex = new Pokedex();

        Adapter = new PokemonArrayAdapter(this, R.id.homework_main_listview, pokedex.getPokemons());

        listView.setAdapter(Adapter);
    }


}
