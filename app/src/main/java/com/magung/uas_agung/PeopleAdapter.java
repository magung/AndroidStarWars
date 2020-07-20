package com.magung.uas_agung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.GridViewHolder> {
    private List<People> people;
    private Context context;
    public PeopleAdapter (Context context, List<People> people) {
        this.people = people;
        this.context = context;
    }
    @NonNull
    @Override
    public PeopleAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_layout, parent, false);
        GridViewHolder gridViewHolder = new GridViewHolder(view);
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleAdapter.GridViewHolder holder, int position) {
        final String
                name = people.get(position).getName(),
                height = people.get(position).getHeight(),
                mass = people.get(position).getMass(),
                birth_day = people.get(position).getBirth_year(),
                gender = people.get(position).getGender();

        holder.tv_name.setText(name);
        holder.tv_height.setText(height);
        holder.tv_mass.setText(mass);
        holder.tv_birth_year.setText(birth_day);
        holder.tv_gender.setText(gender);

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_height, tv_mass, tv_birth_year, tv_gender;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_height = (TextView) itemView.findViewById(R.id.tv_height);
            tv_mass = (TextView) itemView.findViewById(R.id.tv_mass);
            tv_birth_year = (TextView) itemView.findViewById(R.id.tv_birth_year);
            tv_gender = (TextView) itemView.findViewById(R.id.tv_gender);
        }
    }
}
