package com.example.movieandroidapp.fragment.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.movie.GetGenre;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.presenter.movie.GetGenrePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private View mView;

    //value of dropdown quality
    String[] itemsDropdownQuality = {"HD","FullHD"};
    AutoCompleteTextView autoCompleteTxt_quality;
    ArrayAdapter<String> adapterItemQuality;

    //value of dropdown genre
    List<String> genres;
    ArrayAdapter<String> adapterItemGenre;
    AutoCompleteTextView autoCompleteTxt_genre;


    //value of dropdown country
    String[] itemsDropdownCountry = {"US","UK","Viet Nam","Singapore"};
    AutoCompleteTextView autoCompleteTxt_country;
    ArrayAdapter<String> adapterItemCountry;

    public Admin_AddFragment() {
    }

    public static Admin_AddFragment newInstance(String param1, String param2) {
        Admin_AddFragment fragment = new Admin_AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_admin__add, container, false);
        init();
        return mView;
    }

    private void init(){
        getGenre();
        renderDropdownQuality();
        renderDropdownGenre();
        renderDropdownCountry();
    }

    private void renderDropdownQuality(){
        autoCompleteTxt_quality = mView.findViewById(R.id.auto_complete_quality);

        adapterItemQuality = new ArrayAdapter<>(mView.getContext(), R.layout.dropdown_normal_item, itemsDropdownQuality);

        autoCompleteTxt_quality.setAdapter(adapterItemQuality);

        autoCompleteTxt_quality.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getActivity().getApplicationContext(), item, Toast.LENGTH_SHORT).show();
        });
    }
    private void getGenre(){
        genres = new ArrayList<>();
        GetGenre.View view = new GetGenre.View() {
            @Override
            public void onResponseSuccess(List<Genre> genreList) {
                for (Genre genre : genreList) {
                    genres.add(genre.getGenreName());
                }
            }

            @Override
            public void onResponseFailure(String message) {

            }
        };
        GetGenrePresenter getGenrePresenter = new GetGenrePresenter(view);
        getGenrePresenter.requestGetGenres();
    }
    private void renderDropdownGenre(){
        autoCompleteTxt_genre = mView.findViewById(R.id.auto_complete_genre);

        adapterItemGenre = new ArrayAdapter<>(mView.getContext(), R.layout.dropdown_normal_item, genres);

        autoCompleteTxt_genre.setAdapter(adapterItemGenre);

        autoCompleteTxt_genre.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getActivity().getApplicationContext(), item, Toast.LENGTH_SHORT).show();
        });
    }

    private void renderDropdownCountry(){
        autoCompleteTxt_country = mView.findViewById(R.id.auto_complete_country);

        adapterItemCountry = new ArrayAdapter<>(mView.getContext(), R.layout.dropdown_normal_item, itemsDropdownCountry);

        autoCompleteTxt_country.setAdapter(adapterItemCountry);

        autoCompleteTxt_country.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getActivity().getApplicationContext(), item, Toast.LENGTH_SHORT).show();
        });
    }

}