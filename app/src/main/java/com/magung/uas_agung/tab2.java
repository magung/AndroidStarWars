package com.magung.uas_agung;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tab2 extends Fragment {
    ArrayList<Planets> planets;
    ProgressBar pb;
    SwipeRefreshLayout srl;

    public tab2() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        pb = view.findViewById(R.id.progress_horizontal2);
        srl = view.findViewById(R.id.swipeRefreshLayout2);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load(view);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl.setRefreshing(false);
                    }
                }, 1000);
            }


        });

        srl.setColorSchemeColors(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        load(view);

        return  view;
    }

    public void load(final View view) {
        pb.setVisibility(ProgressBar.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String url = "https://swapi.dev/api/planets/";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String name, population, terrain, diameter, rotation_period;
                        planets = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            planets.clear();
                            if (jsonArray.length() != 0) {
                                for (int i = 0; 1 < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    name = data.getString("name").toString().trim();
                                    population = data.getString("population").toString().trim();
                                    terrain = data.getString("terrain").toString().trim();
                                    diameter = data.getString("diameter").toString().trim();
                                    rotation_period = data.getString("rotation_period").toString().trim();

                                    planets.add(new Planets(name,population,terrain, diameter, rotation_period));
                                    showRecyclerGrid(view);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pb.setVisibility(ProgressBar.GONE);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("test",  error.toString());
                        pb.setVisibility(ProgressBar.GONE);
                        Toast.makeText(getContext(), "Connection problem!", Toast.LENGTH_SHORT);
                    }
                }
        );
        queue.add(jsonObjectRequest);
    }

    public void showRecyclerGrid(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv2);
        PlanetAdapter planetAdapter = new PlanetAdapter(getContext(), planets);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        }
        recyclerView.setAdapter(planetAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}