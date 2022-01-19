package com.example.loginjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.loginjava.model.Adaptery;
import com.example.loginjava.model.MovieModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity_movie extends AppCompatActivity {
    //themoviedb link on internet: https://api.themoviedb.org/3/movie/popular?api_key=2e5746066608d3e1bf613ab678e736d5
    //1 - JSON Link on internet https://run.mocky.io/v3/3439ecdb-9e1b-4a1f-9687-cf0e85da64c2

    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=2e5746066608d3e1bf613ab678e736d5";
    private ProgressBar prBcargarMovies;
    private ImageView imgCloseMovies;
    List<MovieModelClass> movieList;
    RecyclerView recyclerView;
    ImageView headerImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        headerImage = findViewById(R.id.imgViewHeader);
        prBcargarMovies = findViewById(R.id.prBcargarMovies);
        imgCloseMovies = findViewById(R.id.imgCloseMovies);

        imgCloseMovies.setOnClickListener(view -> {
            onBackPressed();
        });



        GetData getData = new GetData();
        getData.execute();

    }


    public class GetData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    int data = isr.read();
                    while(data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }finally{
                    if(urlConnection!=null){
                        urlConnection.disconnect();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for(int i = 0; i< jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    MovieModelClass model = new MovieModelClass();
                    model.setId(jsonObject1.getString("vote_average"));
                    model.setName(jsonObject1.getString("title"));
                    model.setImg(jsonObject1.getString("poster_path"));
                    movieList.add(model);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            PutDataIntoRecyclerView(movieList);
        }
    }
    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList){
        Adaptery adaptery = new Adaptery(getApplicationContext(),movieList);
        //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        GridLayoutManager gridlayoutManager=new GridLayoutManager(this,3);

        recyclerView.setLayoutManager(gridlayoutManager);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adaptery);
        Glide.with(this)
                .load(R.drawable.nowayhome)
                .into(headerImage);
        prBcargarMovies.setVisibility(View.GONE);
    }

}