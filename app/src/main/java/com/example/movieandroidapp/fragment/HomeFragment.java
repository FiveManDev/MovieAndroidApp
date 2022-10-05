package com.example.movieandroidapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Style.SpacingItemDecorator;
import com.example.movieandroidapp.contract.movie.GetTopLastestPublicationMoviesContract;
import com.example.movieandroidapp.contract.movie.GetTopLastestReleaseMoviesContract;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.presenter.movie.GetTopLastestPublicationMoviesPresenter;
import com.example.movieandroidapp.presenter.movie.GetTopLastestReleaseMoviesPresenter;
import com.example.movieandroidapp.view.movie.GetTopLastestPublicationMoviesAdapter;
import com.example.movieandroidapp.view.movie.GetTopLastestReleaseMoviesAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements GetTopLastestReleaseMoviesContract.View, GetTopLastestPublicationMoviesContract.View {

    private RecyclerView rcv_movie_home, rcv_movie_new_home;
    View mView;
    List<Movie> movieList;
    private LinearLayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home,container,false);
        initUI();
        return mView;
    }
    private void initUI(){
        setUpMoviesRelease();
        setUpMoviesNew();
    }

    private void setUpMoviesNew(){
        rcv_movie_new_home = mView.findViewById(R.id.rcv_movie_new_home);
        GetTopLastestPublicationMoviesPresenter presenter = new GetTopLastestPublicationMoviesPresenter(this);
        gridLayoutManager = new GridLayoutManager(mView.getContext(),2);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(20,40);
        rcv_movie_new_home.addItemDecoration(itemDecorator);
        rcv_movie_new_home.setLayoutManager(gridLayoutManager);
        rcv_movie_new_home.setHasFixedSize(true);
        presenter.requestDataFromServerNew(6);
    }

    private void setUpMoviesRelease(){
        rcv_movie_home = mView.findViewById(R.id.rcv_movie_release_home);
        GetTopLastestReleaseMoviesPresenter presenter = new GetTopLastestReleaseMoviesPresenter(this);

        layoutManager = new LinearLayoutManager(mView.getContext(),LinearLayoutManager.HORIZONTAL,false);

        rcv_movie_home.setLayoutManager(layoutManager);

        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(40,0);
        rcv_movie_home.addItemDecoration(itemDecorator);
        rcv_movie_home.setHasFixedSize(false);
        movieList = new ArrayList<>();
        presenter.requestDataFromServer(6);
    }

    @Override
    public void setDataToRecyclerview(List<Movie> movieListArray) {
        movieList.addAll(movieListArray);
        GetTopLastestReleaseMoviesAdapter adapter = new GetTopLastestReleaseMoviesAdapter(movieList);
        rcv_movie_home.setAdapter(adapter);
    }

    @Override
    public void setDataToRecyclerviewNew(List<Movie> movieListArray) {
        GetTopLastestPublicationMoviesAdapter adapter = new GetTopLastestPublicationMoviesAdapter(movieListArray);
        rcv_movie_new_home.setAdapter(adapter);
    }

    @Override
    public void onResponseFailureNew(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseFailure(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
