package com.cinemacgp.android;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cinemacgp.models.Movie;
import com.cinemacgp.utils.APIService;
import com.cinemacgp.utils.RecyclerViewAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("CinemaCGP");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.movies = new ArrayList<>();

        new HomeFragment.FetchAPITask().execute();

        recyclerView = getView().findViewById(R.id.movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecyclerViewAdapter(getActivity());
        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), adapter.getItem(position).getTitle(), Toast.LENGTH_SHORT).show();
    }

    private class FetchAPITask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = ProgressDialog.show(getActivity(), "", "Loading...", true);
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