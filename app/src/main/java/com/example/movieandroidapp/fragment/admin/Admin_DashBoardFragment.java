package com.example.movieandroidapp.fragment.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.StatisticsContract;
import com.example.movieandroidapp.contract.movie.GetTopLastestReleaseMoviesContract;
import com.example.movieandroidapp.contract.review.GetTopLatestReviewContract;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.model.Statistic;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.presenter.Review.GetTopLatestReviewPresenter;
import com.example.movieandroidapp.presenter.Statistic.GetStatisticsForMonthPresenter;
import com.example.movieandroidapp.presenter.movie.GetTopLastestReleaseMoviesPresenter;
import com.example.movieandroidapp.view.Review.ListReviewLatestAdapter;
import com.example.movieandroidapp.view.movie.MoviesListLatestAdminAdapter;

import java.util.List;

public class Admin_DashBoardFragment extends Fragment  {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View mView;
    private RecyclerView rcv_top_movies_dashboard;
    private RecyclerView rcv_admin_dashboard_review_latest;
    private RecyclerView rcv_admin_dashboard_movie_latest;

    //statistic information
    private TextView admin_dashboard_user,admin_dashboard_movie,admin_dashboard_review,admin_dashboard_money;

    private LinearLayoutManager linearLayoutManager;

    public Admin_DashBoardFragment() {
    }

    public static Admin_DashBoardFragment newInstance(String param1, String param2) {
        Admin_DashBoardFragment fragment = new Admin_DashBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.fragment_admin_dashboard, container, false);
        init();
        return mView;
    }
    private void init(){
        admin_dashboard_user = mView.findViewById(R.id.admin_dashboard_user);
        admin_dashboard_movie = mView.findViewById(R.id.admin_dashboard_movie);
        admin_dashboard_review = mView.findViewById(R.id.admin_dashboard_review);
        admin_dashboard_money = mView.findViewById(R.id.admin_dashboard_money);

        rcv_top_movies_dashboard = mView.findViewById(R.id.rcv_top_movies_dashboard);
        rcv_admin_dashboard_review_latest = mView.findViewById(R.id.rcv_admin_dashboard_review_latest);
        rcv_admin_dashboard_movie_latest = mView.findViewById(R.id.rcv_admin_dashboard_movie_latest);
        getReviewLatest();
        getStatistic();
        getMovieLatest();
    }

    private void getStatistic(){
        StatisticsContract.View view = new StatisticsContract.View() {
            @Override
            public void onResponseSuccess(Statistic statistic) {
                renderStatistic(statistic);
            }

            @Override
            public void onResponseFailure(String message) {
                Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
        GetStatisticsForMonthPresenter presenter = new GetStatisticsForMonthPresenter(view);
        presenter.requestGetGenres("");
    }
    private void renderStatistic(Statistic statistic){
        admin_dashboard_user.setText(statistic.getUserCreatedCount().toString());
        admin_dashboard_movie.setText(statistic.getMovieCreatedCount().toString());
        admin_dashboard_review.setText(statistic.getReviewCreatedCount().toString());
        admin_dashboard_money.setText(statistic.getTotalMoneyGained().toString());
    }

    private void getReviewLatest(){
        GetTopLatestReviewContract.View view = new GetTopLatestReviewContract.View() {
            @Override
            public void setDataToRecyclerview(List<Review> reviewListArray) {
                renderReviewLatest(reviewListArray);
            }

            @Override
            public void onResponseFailure(String message) {

            }
        };

        GetTopLatestReviewPresenter presenter = new GetTopLatestReviewPresenter(view);
        presenter.requestDataFromServer("5");

    }
    private void renderReviewLatest(List<Review> reviewListArray){
        linearLayoutManager = new LinearLayoutManager(mView.getContext());
        ListReviewLatestAdapter reviewLatestAdapter = new ListReviewLatestAdapter(reviewListArray);
        rcv_admin_dashboard_review_latest.setAdapter(reviewLatestAdapter);
        rcv_admin_dashboard_review_latest.setHasFixedSize(true);
        rcv_admin_dashboard_review_latest.setLayoutManager(linearLayoutManager);

    }

    private void getMovieLatest(){
        GetTopLastestReleaseMoviesContract.View view = new GetTopLastestReleaseMoviesContract.View() {
            @Override
            public void setDataToRecyclerview(List<Movie> movieListArray) {
                renderMovieLatest(movieListArray);
            }
            @Override
            public void onResponseFailure(String message) {

            }
        };
        GetTopLastestReleaseMoviesPresenter presenter = new GetTopLastestReleaseMoviesPresenter(view);
        presenter.requestDataFromServer(5);

    }
    private void renderMovieLatest(List<Movie> movieListArray) {
        linearLayoutManager = new LinearLayoutManager(mView.getContext());
        MoviesListLatestAdminAdapter reviewLatestAdapter = new MoviesListLatestAdminAdapter(movieListArray);
        rcv_admin_dashboard_movie_latest.setAdapter(reviewLatestAdapter);
        rcv_admin_dashboard_movie_latest.setHasFixedSize(true);
        rcv_admin_dashboard_movie_latest.setLayoutManager(linearLayoutManager);
    }
}