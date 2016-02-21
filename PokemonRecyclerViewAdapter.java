package edu.lclark.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import android.support.v7.widget.RecyclerView;

/**
 * Created by maiaphoebedylansamerjan on 2/19/16.
 */
public class PokemonRecyclerViewAdapter extends RecyclerView.Adapter<PokemonRecyclerViewAdapter.PokemonViewHolder> {
    private final OnPokemonRowClickListener Listener;
    private final ArrayList<Pokemon> Pokemons;

    public interface OnPokemonRowClickListener {
        void onPokemonRowClick(Pokemon pokemon);
    }

    public PokemonRecyclerViewAdapter(Pokemon pokemon, OnPokemonRowClickListener listener) {
        Pokemons = new ArrayList<>();
        Pokemons.add(pokemon);
        Listener = listener;
    }


    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.row_pokemon, parent, false);
        return new PokemonViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final PokemonViewHolder holder, int position) {
        Pokemon pokemon = Pokemons.get(position);
        holder.name.setText(pokemon.getName());
        holder.id.setText(pokemon.getId());


        Context context = holder.name.getContext();
        String weight = context.getString(R.string.weight_label, pokemon.getWeight());

        holder.weight.setText(weight);

        String height = context.getString(R.string.height_label, pokemon.getHeight());
        holder.height.setText(height);

        holder.fullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Listener != null) {
                    Listener.onPokemonRowClick(Pokemons.get(holder.getAdapterPosition()));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return Pokemons.size();
    }


    static class PokemonViewHolder extends RecyclerView.ViewHolder {

        TextView name, id, weight, height;
        View fullView;

        public PokemonViewHolder(View itemView) {
            super(itemView);
            fullView = itemView;
            name = (TextView) itemView.findViewById(R.id.row_pokemon_name_textview);
            id = (TextView) itemView.findViewById(R.id.row_pokemon_id_textview);
            weight = (TextView) itemView.findViewById(R.id.row_pokemon_weight_textview);
            height = (TextView) itemView.findViewById(R.id.row_pokemon_height_textview);
        }
    }
}
