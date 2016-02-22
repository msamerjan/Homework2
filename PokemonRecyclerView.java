package edu.lclark.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


/**
 * Created by maiaphoebedylansamerjan on 2/19/16.
 */
public class PokemonRecyclerView extends AppCompatActivity implements PokemonRecyclerViewAdapter.OnPokemonRowClickListener{
    public static final int ID_POKEMON=0;
    private static final String TAG="Pokemon";
    private RecyclerView RecyclerView;
    private Pokedex Pokedex;
    private PokemonRecyclerViewAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedPokemonState) {
        super.onCreate(savedPokemonState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView=(RecyclerView) findViewById(R.id.activity_pokemon_recyclerview);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Pokedex=new Pokedex();
        Adapter= new PokemonRecyclerViewAdapter(Pokedex.getPokemon(),this);
        RecyclerView.setAdapter(Adapter);



        final String url="http://pokeapi.co/api/v2/pokemon/"+ID_POKEMON;
        new PokemonAsyncTask().execute(url);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ID_POKEMON && resultCode == RESULT_OK) {
            Pokemon pokemon = data.getParcelableExtra(PokemonDetailView.ARG_POKEMON);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPokemonRowClick(Pokemon pokemon) {
        Intent intent = new Intent(PokemonRecyclerView.this, PokemonDetailView.class);
        intent.putExtra(PokemonDetailView.ARG_POKEMON, pokemon);
        startActivityForResult(intent, ID_POKEMON);
    }

}
