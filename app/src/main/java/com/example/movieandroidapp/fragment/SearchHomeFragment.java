package com.example.movieandroidapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.Utility.Style.SpacingItemDecorator;
import com.example.movieandroidapp.contract.movie.ListenerMovie;
import com.example.movieandroidapp.contract.movie.SearchMovieHome;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.presenter.movie.SearchHomePresenter;
import com.example.movieandroidapp.view.movie.MovieListAdapter;

import java.util.List;

public class SearchHomeFragment extends Fragment implements SearchMovieHome.View {
    private RecyclerView  rcv_movie_search_home;
    private View mView;
    private String query;
    private GridLayoutManager gridLayoutManager;

    private User user;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search_home,container,false);
        initUI();
        return mView;
    }

    private void initUI(){
        query = this.getArguments().getString("search_query");
        user = Extension.GsonUtil().fromJson(this.getArguments().getString("user"),User.class);
        setUpMoviesSearch();
    }

    private void setUpMoviesSearch(){
        rcv_movie_search_home = mView.findViewById(R.id.rcv_movie_search_home);
        SearchHomePresenter presenter = new SearchHomePresenter(this);
        gridLayoutManager = new GridLayoutManager(mView.getContext(),2);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(20,40);
        rcv_movie_search_home.addItemDecoration(itemDecorator);
        rcv_movie_search_home.setLayoutManager(gridLayoutManager);
        rcv_movie_search_home.setHasFixedSize(true);

        presenter.requestSearchMovieHome(query);
    }

    @Override
    public void setDataToRecyclerview(List<Movie> movieListArray) {
        ListenerMovie listenerMovie = (movie,type) -> {

            checkUserIsBasic(user,movie);

        };

        MovieListAdapter adapter = new MovieListAdapter(movieListArray,listenerMovie);
        rcv_movie_search_home.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void checkUserIsBasic(User user, Movie movie) {

        if(user != null){
            int classNameUser = user.getProfile().getClassification().getClassLevel();
            String classNameMovie = movie.getClassName().toLowerCase();

            if (classNameUser == 2) {
                MovieDetailFragment movieDetailFragment = ((HomeActivity) getActivity()).bundleMovieToDetailFragment(movie);
                ((HomeActivity) getActivity()).replaceFragment(movieDetailFragment);

            } else if (classNameUser == 1) {

                if (classNameMovie.equals("basic")) {
                    MovieDetailFragment movieDetailFragment = ((HomeActivity) getActivity()).bundleMovieToDetailFragment(movie);
                    ((HomeActivity) getActivity()).replaceFragment(movieDetailFragment);
                }

                else {
                    ((HomeActivity) getActivity()).replaceFragment(new PricingFragment());
                    HomeActivity.mCurrentFragment = HomeActivity.FRAGMENT_PRICING_HOME;
                }
            }
        }
    }
}
