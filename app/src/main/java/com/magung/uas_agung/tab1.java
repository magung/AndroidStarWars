package com.magung.uas_agung;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class tab1 extends Fragment {
    ArrayList<People> people;
    RecyclerView rv;
    ProgressBar pb;
    SwipeRefreshLayout srl;
    public tab1() {
        // Required empty public constructor
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        pb = view.findViewById(R.id.progress_horizontal);
        srl = view.findViewById(R.id.swipeRefreshLayout);
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


        return view;
    }

    public void load(final View view) {
        pb.setVisibility(ProgressBar.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String url = "https://swapi.dev/api/people/";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String name, height, mass, birth_year, gender;
                        people = new ArrayList<>();
                        Log.e("test",  response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            people.clear();
                            if (jsonArray.length() != 0) {
                                for (int i = 0; 1 < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    name = data.getString("name").toString().trim();
                                    height = data.getString("height").toString().trim();
                                    mass = data.getString("mass").toString().trim();
                                    gender = data.getString("gender").toString().trim();
                                    birth_year = data.getString("birth_year").toString().trim();

                                    people.add(new People(name, height, mass, birth_year, gender));
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
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        PeopleAdapter peopleAdapter = new PeopleAdapter(getContext(), people);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        }
        recyclerView.setAdapter(peopleAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}