package com.example.movieandroidapp.fragment.admin;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.movie.GetGenre;
import com.example.movieandroidapp.model.Genre;
import com.example.movieandroidapp.model.movie.PostMovie;
import com.example.movieandroidapp.presenter.movie.GetGenrePresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Admin_AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final int MY_REQUEST_CODE = 10;

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

    private PostMovie postMovie;
    private ImageView admin_movie_add_thumbnail;

    private DatePickerDialog datePickerDialog;
    private EditText admin_movie_add_releaseTime,admin_movie_add_age,admin_movie_add_running_time;
    public Admin_AddFragment() {
    }

    public static Admin_AddFragment newInstance(String param1) {
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

        postMovie = new PostMovie();

        admin_movie_add_releaseTime = mView.findViewById(R.id.admin_movie_add_releaseTime);
        admin_movie_add_releaseTime.setOnClickListener(t->{
            openDatePicker(mView);
        });
        getGenre();
        renderDropdownQuality();
        renderDropdownGenre();
        renderDropdownCountry();
        initDatePicker();
    }

    private void renderDropdownQuality(){
        autoCompleteTxt_quality = mView.findViewById(R.id.auto_complete_quality);

        adapterItemQuality = new ArrayAdapter<>(mView.getContext(), R.layout.dropdown_normal_item, itemsDropdownQuality);

        autoCompleteTxt_quality.setAdapter(adapterItemQuality);

        autoCompleteTxt_quality.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            postMovie.setQuality(item);
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
            List<String> list = new ArrayList<>();
            list.add(item);
            postMovie.setGenreNames(list);
        });
    }

    private void renderDropdownCountry(){
        autoCompleteTxt_country = mView.findViewById(R.id.auto_complete_country);

        adapterItemCountry = new ArrayAdapter<>(mView.getContext(), R.layout.dropdown_normal_item, itemsDropdownCountry);

        autoCompleteTxt_country.setAdapter(adapterItemCountry);

        autoCompleteTxt_country.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            postMovie.setCountry(item);
        });
    }

    private void getName(){
        EditText admin_movie_add_title = mView.findViewById(R.id.admin_movie_add_title);
        postMovie.setMovieName(admin_movie_add_title.getText().toString());
    }
    private void getDesc(){
        EditText admin_movie_add_content = mView.findViewById(R.id.admin_movie_add_content);
        postMovie.setDescription(admin_movie_add_content.getText().toString());
    }
    private void getReleaseDate(){
        admin_movie_add_releaseTime = mView.findViewById(R.id.admin_movie_add_releaseTime);
        admin_movie_add_releaseTime.setOnClickListener(t->{
            openDatePicker(mView);
        });
    }
    private void getAge(){
        admin_movie_add_age = mView.findViewById(R.id.admin_movie_add_age);
        postMovie.setAge(admin_movie_add_age.getText().toString());
    }
    private void getRunningTime(){
        admin_movie_add_running_time = mView.findViewById(R.id.admin_movie_add_running_time);
        postMovie.setAge(admin_movie_add_running_time.getText().toString());
    }
    private void getThumbnail(){
        admin_movie_add_thumbnail = mView.findViewById(R.id.admin_movie_add_thumbnail);
        admin_movie_add_thumbnail.setOnClickListener(t->{
            onClickRequestPermission();
        });
    }

    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }
        if(mView.getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        }
        else{
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission,MY_REQUEST_CODE);
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

    }


    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            postMovie.setPublicationTime(date);
            admin_movie_add_releaseTime.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(mView.getContext(), style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year)
    {
        return year + "-"+month+"-"+day ;
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}