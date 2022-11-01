package com.example.movieandroidapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Style.SpacingItemDecorator;
import com.example.movieandroidapp.contract.movie.CatalogHomeContract;
import com.example.movieandroidapp.contract.movie.GetGenre;
import com.example.movieandroidapp.contract.movie.ListenerMovie;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.presenter.movie.GetGenrePresenter;
import com.example.movieandroidapp.presenter.movie.GetMovieBaseOnFilterPresenter;
import com.example.movieandroidapp.presenter.movie.SearchHomePresenter;
import com.example.movieandroidapp.view.movie.GenreAdapter;
import com.example.movieandroidapp.view.movie.MovieListAdapter;
import com.example.movieandroidapp.view.movie.QualityAdapter;
import com.google.android.material.slider.RangeSlider;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CatalogFragment extends Fragment {

    View mView;
    Spinner spnGenre,catalog_filter_quality;
    Button btn_apply_filter;
    RangeSlider movie_detail_imbd,movie_detail_release;
    RecyclerView rcv_movie_catalog;
    TextView not_found_catalog_text;

    ProgressDialog progress;

    private String genreIDFilter, qualityFilter, ratingMin, ratingMax;
    private int releaseTimeMin, releaseTimeMax;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_catalog,container,false);
        init();
        return mView;
    }
    private void init(){
        progress = new ProgressDialog(mView.getContext());
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);


        btn_apply_filter = mView.findViewById(R.id.btn_apply_filter);
        movie_detail_imbd = mView.findViewById(R.id.movie_detail_imbd);
        movie_detail_release = mView.findViewById(R.id.movie_detail_release);
        rcv_movie_catalog = mView.findViewById(R.id.rcv_movie_catalog);
        not_found_catalog_text = mView.findViewById(R.id.not_found_catalog_text);
        renderListQuality();
        handleChangeImbd();
        handleChangeRelease();
        getListGenre();

        handleFilter();
    }
    private void handleChangeImbd(){
        ratingMin = "0";
        ratingMax = "10";
        movie_detail_imbd.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = slider.getValues();
            ratingMin = Collections.min(values).toString();
            ratingMax = Collections.max(values).toString();
        });
    }
    private void handleChangeRelease(){
        releaseTimeMin = 1996;
        releaseTimeMax = Year.now().getValue();

        movie_detail_release.addOnChangeListener((slider, value, fromUser) -> {
            List<Float> values = slider.getValues();
            releaseTimeMin = Math.round(Collections.min(values));
            releaseTimeMax = Math.round(Collections.max(values));
        });
    }

    private void handleFilter(){
        btn_apply_filter.setOnClickListener(t->{
            progress.show();
            resultFilter();
        });
    }

    private void renderListQuality() {
        catalog_filter_quality = mView.findViewById(R.id.catalog_filter_quality);
        QualityAdapter qualityAdapter = new QualityAdapter(mView.getContext(),R.layout.dropdown_selected,getListQuality());
        catalog_filter_quality.setAdapter(qualityAdapter);
        catalog_filter_quality.setSelection(0);
        qualityFilter = qualityAdapter.getItem(0);
        catalog_filter_quality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                qualityFilter = qualityAdapter.getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void renderListGenre(List<Genre> list){
        spnGenre = mView.findViewById(R.id.catalog_filter_genre);
        GenreAdapter genreAdapter = new GenreAdapter(mView.getContext(), R.layout.dropdown_selected,list);
        spnGenre.setAdapter(genreAdapter);
        spnGenre.setSelection(0);
        genreIDFilter = genreAdapter.getItem(0).getGenreID();
        spnGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    genreIDFilter = genreAdapter.getItem(position).getGenreID();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void getListGenre(){
        GetGenre.View view = new GetGenre.View() {
            @Override
            public void onResponseSuccess(List<Genre> list) {
                renderListGenre(list);
                resultFilter();
            }

            @Override
            public void onResponseFailure(String message) {
                Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
        GetGenrePresenter getGenrePresenter = new GetGenrePresenter(view);
        getGenrePresenter.requestGetGenres();
    }
    private List<String> getListQuality(){
        List<String> qualityList = new ArrayList<>();
        qualityList.add("HD");
        qualityList.add("FULL HD");
        return qualityList;
    }

    private void resultFilter(){
        CatalogHomeContract.View view = new CatalogHomeContract.View() {
            @Override
            public void setDataToRecyclerview(List<Movie> movieListArray) {
                progress.dismiss();
                not_found_catalog_text.setVisibility(View.GONE);
                renderMovies(movieListArray);
            }

            @Override
            public void onResponseFailureNew(String message) {
                progress.dismiss();
                rcv_movie_catalog.setAdapter(null);
                not_found_catalog_text.setVisibility(View.VISIBLE);
            }
        };

        GetMovieBaseOnFilterPresenter presenter = new GetMovieBaseOnFilterPresenter(view);
        presenter.requestDataFromServer(genreIDFilter, qualityFilter, ratingMin, ratingMax, releaseTimeMin, releaseTimeMax);
    }

    private void renderMovies(List<Movie> movieList){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mView.getContext(),2);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(20,40);
        rcv_movie_catalog.addItemDecoration(itemDecorator);
        rcv_movie_catalog.setLayoutManager(gridLayoutManager);
        rcv_movie_catalog.setHasFixedSize(true);

        ListenerMovie listenerMovie = (movie, type) -> {
            MovieDetailFragment movieDetailFragment = ((HomeActivity) getActivity()).bundleMovieToDetailFragment(movie);
            ((HomeActivity) getActivity()).replaceFragment(movieDetailFragment);
        };
        MovieListAdapter adapter = new MovieListAdapter(movieList,listenerMovie);
        rcv_movie_catalog.setAdapter(adapter);
    }
}
