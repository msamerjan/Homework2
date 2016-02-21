package edu.lclark.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by maiaphoebedylansamerjan on 2/18/16.
 */
public class PokemonDetailView extends AppCompatActivity {
    public static final String ARG_POKEMON = "ArgPokemon";
    private Pokemon Pokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail_view);
        Pokemon = getIntent().getParcelableExtra(ARG_POKEMON);

        TextView nameTextView = (TextView) findViewById(R.id.activity_detail_pokemon_name);
        TextView heightTextView = (TextView) findViewById(R.id.activity_height_number_detail);
        TextView weightTextView = (TextView) findViewById(R.id.activity_weight_number_detail);
        ImageView pokemonImageView = (ImageView) findViewById(R.id.activity_detail_imageview);

        Picasso.with(this).load(Pokemon.getImageUrl()).fit().centerInside().into(pokemonImageView);

        nameTextView.setText(Pokemon.getName());
        heightTextView.setText(getString(R.string.height_label, Pokemon.getHeight()));
        weightTextView.setText(getString(R.string.weight_label, Pokemon.getWeight()));
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra(ARG_POKEMON, Pokemon);
        setResult(RESULT_OK, intent);
        finish();

    }
}
