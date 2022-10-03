package com.example.movieandroidapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.movie.GetTopLastestReleaseMoviesContract;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.presenter.movie.GetTopLastestReleaseMoviesPresenter;
import com.example.movieandroidapp.presenter.user.GetUserInformationPresenter;
import com.example.movieandroidapp.view.movie.GetTopLastestReleaseMoviesAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements GetTopLastestReleaseMoviesContract.View {

    private RecyclerView rcv_movie_home;
    View mView;
    List<Movie> movieList;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home,container,false);
        initUI();
        return mView;
    }
    private void initUI(){
        rcv_movie_home = mView.findViewById(R.id.rcv_movie_release_home);
        GetTopLastestReleaseMoviesPresenter presenter = new GetTopLastestReleaseMoviesPresenter(this);

        layoutManager = new LinearLayoutManager(mView.getContext(),LinearLayoutManager.HORIZONTAL,false);

        rcv_movie_home.setLayoutManager(layoutManager);
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
    public void onResponseFailure(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
