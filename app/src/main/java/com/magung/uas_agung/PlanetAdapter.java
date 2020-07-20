package com.magung.uas_agung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.GridViewHolder> {
    private List<Planets> planets;
    private Context context;
    public PlanetAdapter(Context context, List<Planets> planets) {
        this.planets = planets;
        this.context = context;
    }
    @NonNull
    @Override
    public PlanetAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planets_layout, parent, false);
        GridViewHolder gridViewHolder = new GridViewHolder(view);
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetAdapter.GridViewHolder holder, int position) {
        final String
                name = planets.get(position).getName(),
                population = planets.get(position).getPopulation(),
                terrain = planets.get(position).getTerrain(),
                diameter = planets.get(position).getDiameter(),
                rotation_period = planets.get(position).getRotation_period();
        holder.tvName.setText(name);
        holder.tvPopulation.setText(population);
        holder.tvTerrain.setText(terrain);
        holder.tvDiameter.setText(diameter);
        holder.tvRotation_period.setText(rotation_period);
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPopulation, tvTerrain, tvDiameter, tvRotation_period;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_planet);
            tvPopulation = (TextView) itemView.findViewById(R.id.tv_population);
            tvTerrain = (TextView) itemView.findViewById(R.id.tv_terrain);
            tvDiameter = (TextView) itemView.findViewById(R.id.tv_diameter);
            tvRotation_period = (TextView) itemView.findViewById(R.id.tv_rotation_period);

        }
    }
}
