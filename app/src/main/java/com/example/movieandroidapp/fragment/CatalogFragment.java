package com.example.movieandroidapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.movie.GetGenre;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.presenter.movie.GetGenrePresenter;
import com.example.movieandroidapp.view.movie.GenreAdapter;

import java.util.ArrayList;
import java.util.List;

public class CatalogFragment extends Fragment {

    View mView;
    Spinner spnGenre;

    List<Genre> genreList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_catalog,container,false);
        init();
        return mView;
    }
    private void init(){
        getListGenre();
    }
    private void renderListGenre(){
        spnGenre = mView.findViewById(R.id.catalog_filter_genre);
        GenreAdapter genreAdapter = new GenreAdapter(mView.getContext(), R.layout.dropdown_selected,genreList);
        spnGenre.setAdapter(genreAdapter);
        spnGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
                renderListGenre();
            }

            @Override
            public void onResponseFailure(String message) {
                Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
        GetGenrePresenter getGenrePresenter = new GetGenrePresenter(view);
        getGenrePresenter.requestGetGenres();
    }
}
