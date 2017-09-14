package com.learnites.javadevelopers;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.learnites.javadevelopers.app.AppConfig;
import com.learnites.javadevelopers.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progres;
    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<Devs> devsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Java Developers In Lagos");

        progres = (ProgressBar) findViewById(R.id.progress);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new RecyclerAdapter(devsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        progres.setVisibility(View.VISIBLE);

        fecthDevs();
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new   RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Devs myDev = devsList.get(position);
                        Intent showPhotoIntent = new Intent(getApplicationContext(), DetailActivity.class);
                        showPhotoIntent.putExtra("username", myDev.getLogin());
                        showPhotoIntent.putExtra("image", myDev.getAvatar());
                        showPhotoIntent.putExtra("url", myDev.getUrl());
                        startActivity(showPhotoIntent);
                    }
                })
        );
    }

    private void fecthDevs(){
        StringRequest inbox = new StringRequest(Request.Method.GET,
                AppConfig.URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progres.setVisibility(View.GONE);
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean success = jObj.getBoolean("incomplete_results");
                    if (!success) {
                        JSONArray ja = jObj.getJSONArray("items");
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject jobj = ja.getJSONObject(i);
                            String login = jobj.getString("login");
                            String avatar_url = jobj.getString("avatar_url");
                            String url = jobj.getString("html_url");
                            int score = jobj.getInt("score");
                            Devs offer = new Devs(login,avatar_url,url,Integer.toString(score));
                            devsList.add(offer);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progres.setVisibility(View.GONE);
                Toast toast = Toast.makeText(getApplicationContext(), "An Error Occurred", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        AppController.getInstance().addToRequestQueue(inbox);

    }
}
