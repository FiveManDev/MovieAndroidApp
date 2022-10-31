package com.example.movieandroidapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.Utility.Style.SpacingItemDecorator;
import com.example.movieandroidapp.contract.movie.GetGenre;
import com.example.movieandroidapp.contract.movie.GetMoviesBasedOnGenreContract;
import com.example.movieandroidapp.contract.movie.GetTopLastestReleaseMoviesContract;
import com.example.movieandroidapp.contract.movie.ListenerMovie;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.presenter.movie.GetGenrePresenter;
import com.example.movieandroidapp.presenter.movie.GetTopLastestPublicationMoviesPresenter;
import com.example.movieandroidapp.presenter.movie.GetTopLastestReleaseMoviesPresenter;
import com.example.movieandroidapp.view.movie.GenreAdapter;
import com.example.movieandroidapp.view.movie.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment
        implements GetTopLastestReleaseMoviesContract.View,
        GetMoviesBasedOnGenreContract.View {

    private static final String ARG_PARAM1 = "param1";


    private RecyclerView rcv_movie_home, rcv_movie_new_home;
    View mView;
    List<Movie> movieList;
    private LinearLayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;

    TextView new_movie_not_found;
    private Spinner spnGenre;
    private GenreAdapter genreAdapter;

    List<Genre> genreList;

    private User mUser;
    public static HomeFragment newInstance(User user) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, Extension.GsonUtil().toJson(user));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = Extension.GsonUtil().fromJson(getArguments().getString(ARG_PARAM1),User.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();
        return mView;
    }

    private void initUI() {
        new_movie_not_found = mView.findViewById(R.id.new_movie_not_found);


        setUpMoviesRelease();
        setUpMoviesNew("8782bbd0-2f56-4fd4-89d6-081396549bfb",6);
        getListGenre();
    }

    private void setUpMoviesNew(String genreID,int top) {
        rcv_movie_new_home = mView.findViewById(R.id.rcv_movie_new_home);
        GetTopLastestPublicationMoviesPresenter presenter = new GetTopLastestPublicationMoviesPresenter(this);
        gridLayoutManager = new GridLayoutManager(mView.getContext(), 2);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(20, 40);
        rcv_movie_new_home.addItemDecoration(itemDecorator);
        rcv_movie_new_home.setLayoutManager(gridLayoutManager);
        rcv_movie_new_home.setHasFixedSize(true);
        presenter.requestDataFromServerNew(genreID, top);
    }

    private void setUpMoviesRelease() {
        rcv_movie_home = mView.findViewById(R.id.rcv_movie_release_home);
        GetTopLastestReleaseMoviesPresenter presenter = new GetTopLastestReleaseMoviesPresenter(this);

        layoutManager = new LinearLayoutManager(mView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        rcv_movie_home.setLayoutManager(layoutManager);

        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(40, 0);
        rcv_movie_home.addItemDecoration(itemDecorator);
        rcv_movie_home.setHasFixedSize(false);
        movieList = new ArrayList<>();
        presenter.requestDataFromServer(6);
    }

    private void renderGenreFilter() {
        spnGenre = mView.findViewById(R.id.home_filter_genre);
        genreAdapter = new GenreAdapter(mView.getContext(), R.layout.dropdown_selected,genreList);
        spnGenre.setAdapter(genreAdapter);
        spnGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterMovieByGenre((genreAdapter.getItem(position).getGenreID()));

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void getListGenre(){
        genreList = new ArrayList<>();
        GetGenre.View view = new GetGenre.View() {
            @Override
            public void onResponseSuccess(List<Genre> list) {
                genreList.addAll(list);
                renderGenreFilter();
            }

            @Override
            public void onResponseFailure(String message) {
                Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
        GetGenrePresenter getGenrePresenter = new GetGenrePresenter(view);
        getGenrePresenter.requestGetGenres();
    }

    private void filterMovieByGenre(String genreID){
        setUpMoviesNew(genreID,10);
    }
    @Override
    public void setDataToRecyclerview(List<Movie> movieListArray) {
        ListenerMovie listenerMovie = (movie,type) -> {
            checkUserIsBasic(mUser,movie);
        };
        movieList.addAll(movieListArray);
        MovieListAdapter adapter = new MovieListAdapter(movieList, listenerMovie);
        rcv_movie_home.setAdapter(adapter);
    }

    @Override
    public void setDataToRecyclerviewNew(List<Movie> movieListArray) {
        new_movie_not_found.setVisibility(View.GONE);
        ListenerMovie listenerMovie = (movie,type) -> {
            checkUserIsBasic(mUser,movie);
        };
        MovieListAdapter adapter = new MovieListAdapter(movieListArray, listenerMovie);
        rcv_movie_new_home.setAdapter(adapter);
    }

    @Override
    public void onResponseFailureNew(String message) {
        new_movie_not_found.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResponseFailure(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
    public void checkUserIsBasic(User user,Movie movie) {
        String classNameUser = user.getProfile().getClassification().getClassName().toLowerCase();
        String classNameMovie = movie.getClassName().toLowerCase();

        if(user.getProfile().getClassification().getClassLevel() == 2){
            MovieDetailFragment movieDetailFragment = ((HomeActivity) getActivity()).bundleMovieToDetailFragment(movie);
            ((HomeActivity) getActivity()).replaceFragment(movieDetailFragment);
        }
        else if(classNameUser.equals("basic")){
            if(classNameMovie.equals("basic")){
                MovieDetailFragment movieDetailFragment = ((HomeActivity) getActivity()).bundleMovieToDetailFragment(movie);
                ((HomeActivity) getActivity()).replaceFragment(movieDetailFragment);
            }
            else{
                ((HomeActivity) getActivity()).replaceFragment(new PricingFragment());
                HomeActivity.mCurrentFragment = HomeActivity.FRAGMENT_PRICING_HOME;
            }
        }
    }
    
}
