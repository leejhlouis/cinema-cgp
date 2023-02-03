package com.cinemacgp.android;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinemacgp.models.Movie;
import com.cinemacgp.utils.APIService;
import com.cinemacgp.utils.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.movies = new ArrayList<>();

        new FetchAPITask().execute();

        recyclerView = findViewById(R.id.movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerViewAdapter(this);
        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, adapter.getItem(position).getTitle(), Toast.LENGTH_SHORT).show();
    }

    private class FetchAPITask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading...", true);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            movies = APIService.getMoviesNowPlaying();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            this.progressDialog.dismiss();

            adapter.setMovies(movies);
            recyclerView.setAdapter(adapter);
        }
    }
}