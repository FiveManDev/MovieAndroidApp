package com.example.movieandroidapp.fragment.admin;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.movie.DeleteMovieContract;
import com.example.movieandroidapp.contract.movie.GetMoviesContract;
import com.example.movieandroidapp.contract.movie.GetTotalMovieContract;
import com.example.movieandroidapp.contract.movie.ListenerMovie;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.BodyRequest.Filter;
import com.example.movieandroidapp.presenter.movie.DeleteMoviePresenter;
import com.example.movieandroidapp.presenter.movie.GetMoviesPresenter;
import com.example.movieandroidapp.presenter.movie.GetTotalMoviesPresenter;
import com.example.movieandroidapp.Utility.movie.MoviesListAdminAdapter;
import com.example.movieandroidapp.Utility.movie.SortByAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Admin_CatalogFragment extends Fragment implements GetMoviesContract.View, ListenerMovie {

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private View mView;
    private RecyclerView rcv_movies_admin;
    private Spinner catalog_filter_spinner_admin;
    private Filter filterList;
    private EditText search_movie_admin;
    private LinearLayoutManager layoutManager;
    private TextView movie_admin_notFound_txt,movie_admin_total;
    private MoviesListAdminAdapter listAdminAdapter;

    private List<Movie> movieList;
    Button btn_loadMore_movie_admin;
    public Admin_CatalogFragment() {
        // Required empty public constructor
    }

    public static Admin_CatalogFragment newInstance(String param1, String param2) {
        Admin_CatalogFragment fragment = new Admin_CatalogFragment();
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
        mView = inflater.inflate(R.layout.fragment_admin__catalog, container, false);
        init();
        return mView;
    }
    private void init(){
        movieList = new ArrayList<>();

        filterList = new Filter();
        filterList.setPageIndex(1);
        filterList.setPageSize(5);
        filterList.setQuery("");
        filterList.setSortBy("date");
        filterList.setSortType("desc");

        rcv_movies_admin = mView.findViewById(R.id.rcv_movies_admin);
        search_movie_admin = mView.findViewById(R.id.search_movie_admin);
        movie_admin_notFound_txt = mView.findViewById(R.id.movie_admin_notFound_txt);

        btn_loadMore_movie_admin = mView.findViewById(R.id.btn_loadMore_movie_admin);
        movie_admin_total = mView.findViewById(R.id.movie_admin_total);
        renderListSortBy();
        handleSearchText();
        handleLoadMore();
        renderTotalMovies();
    }
    private void renderTotalMovies(){
        GetTotalMovieContract.View view = new GetTotalMovieContract.View() {
            @Override
            public void onResponseFailure(String message) {
                movie_admin_total.setText(0);
            }

            @Override
            public void onResponseSuccess(int total) {
                movie_admin_total.setText(total + " Total");
            }
        };
        GetTotalMoviesPresenter getTotalMoviesPresenter = new GetTotalMoviesPresenter(view);
        getTotalMoviesPresenter.requestGetTotalMovies();
    }

    private void filterGetMovie(){
        layoutManager = new LinearLayoutManager(mView.getContext());
        rcv_movies_admin.setLayoutManager(layoutManager);
        rcv_movies_admin.setHasFixedSize(false);

        GetMoviesPresenter getMoviesPresenter = new GetMoviesPresenter(this);
        getMoviesPresenter.requestGetMovies(filterList);
    }


    private void handleSearchText(){
        search_movie_admin.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filterList.setQuery(search_movie_admin.getText().toString());
                filterGetMovie();
                return true;
            }
            return false;
        });
    }

    private void handleLoadMore(){
        btn_loadMore_movie_admin.setOnClickListener(t->{
            //tang page index len 1 de load more data
            filterList.setPageIndex(filterList.getPageIndex() + 1);
            filterGetMovie();
        });
    }
    private void renderListSortBy(){
        catalog_filter_spinner_admin = mView.findViewById(R.id.catalog_filter_spinner_admin);
        SortByAdapter sortByAdapter = new SortByAdapter(mView.getContext(), R.layout.dropdown_selected,listSortBy());
        catalog_filter_spinner_admin.setAdapter(sortByAdapter);
        catalog_filter_spinner_admin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterList.setSortBy(sortByAdapter.getItem(position));
                filterGetMovie();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List<String> listSortBy(){
        List<String> list = new ArrayList<>();
        list.add("Date");
        list.add("Rating");
        return list;
    }

    @Override
    public void onResponseFailure(String message) {
        movie_admin_notFound_txt.setVisibility(View.VISIBLE);
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseSuccess(ResponseFilter<Movie[]> pagination) {
        if(pagination==null){
            movie_admin_notFound_txt.setVisibility(View.VISIBLE);
            rcv_movies_admin.setAdapter(null);
        }
        else{
            //check co hien thi button load more
            if(pagination.getPagination().getHasNext())
            {
                btn_loadMore_movie_admin.setVisibility(View.VISIBLE);
            }
            else{
                btn_loadMore_movie_admin.setVisibility(View.GONE);
            }

            movie_admin_notFound_txt.setVisibility(View.GONE);

            List<Movie> movieListResponse = Arrays.asList(pagination.getValue());

            movieList.addAll(movieListResponse);
            listAdminAdapter = new MoviesListAdminAdapter(movieList,mView.getContext(),this);
            rcv_movies_admin.setAdapter(listAdminAdapter);
        }
    }

    @Override
    public void ClickedMovie(Movie movie, String type) {
        if(type.equals("delete")){
            deleteMovieById(movie.getMovieID());
        }
    }
    private void deleteMovieById(String idMovie){
        DeleteMovieContract.View view = new DeleteMovieContract.View() {
            @Override
            public void onResponseFailure(String message) {
                Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponseSuccess() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    movieList.removeIf(e -> e.getMovieID().equals(idMovie));
                }
                listAdminAdapter.setMovieList(movieList);
            }
        };
        DeleteMoviePresenter deleteMoviePresenter = new DeleteMoviePresenter(view);
        deleteMoviePresenter.requestDeleteMovies(idMovie);
    }
}