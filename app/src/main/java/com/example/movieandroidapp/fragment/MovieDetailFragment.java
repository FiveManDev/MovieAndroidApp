package com.example.movieandroidapp.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.review.CreateReviewContract;
import com.example.movieandroidapp.contract.review.getListReviewContract;
import com.example.movieandroidapp.contract.user.GetUserInformationContract;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.network.ApiClient;
import com.example.movieandroidapp.network.BodyRequest.ReviewBody;
import com.example.movieandroidapp.presenter.Review.CreateReviewMoviePresenter;
import com.example.movieandroidapp.presenter.Review.GetListReviewMoviePresenter;
import com.example.movieandroidapp.presenter.user.GetUserInformationPresenter;
import com.example.movieandroidapp.view.Review.ListReviewAdapter;
import com.google.android.material.slider.Slider;
import com.google.gson.Gson;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.util.List;

public class MovieDetailFragment extends Fragment implements getListReviewContract.View {
    Button watchBtn, btn_send_review;
    TextView movie_title_detail,
            movie_rating_detail,
            movie_quality_detail,
            movie_age_detail,
            movie_genre_detail,
            movie_time_detail,
            movie_release_detail,
            movie_country_detail,
            movie_desc_detail,
            error_message_review
    ;
    //rating
    Slider movie_review_rating_review;
    int rating;

    ImageView movie_image_detail;
    Movie movie;
    View mView;
    EditText movie_detail_title_review, movie_detail_content_review;
    RecyclerView rcv_movie_detail_review_item;

    HubConnection hubConnection;

    ListReviewAdapter listReviewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        Gson gson = new Gson();
        movie = gson.fromJson(this.getArguments().getString("movie"), Movie.class);
        init();

        return mView;
    }

    private void init() {
        renderMovieDetail();
        renderListReview();
        clickButtonWatch();
        formReview();
    }

    private void renderMovieDetail() {
        movie_image_detail = mView.findViewById(R.id.movie_image_detail);

        error_message_review = mView.findViewById(R.id.error_message_review);
        movie_title_detail = mView.findViewById(R.id.movie_title_detail);
        movie_rating_detail = mView.findViewById(R.id.movie_rating_detail);
        movie_quality_detail = mView.findViewById(R.id.movie_quality_detail);
        movie_age_detail = mView.findViewById(R.id.movie_age_detail);
        movie_genre_detail = mView.findViewById(R.id.movie_genre_detail);
        movie_time_detail = mView.findViewById(R.id.movie_time_detail);
        movie_release_detail = mView.findViewById(R.id.movie_release_detail);
        movie_country_detail = mView.findViewById(R.id.movie_country_detail);
        movie_desc_detail = mView.findViewById(R.id.movie_desc_detail);

        Picasso.get().load(movie.getThumbnail()).into(movie_image_detail);

        movie_title_detail.setText(movie.getMovieName());
        movie_rating_detail.setText(movie.getRating().toString());
        movie_quality_detail.setText(movie.getQuality());
        movie_age_detail.setText(movie.getAge());
        movie_genre_detail.setText(String.join(", ", movie.getGenres()));
        movie_time_detail.setText(movie.getRunningTime().toString());
        movie_release_detail.setText(Extension.formatDate(movie.getReleaseTime()));
        movie_country_detail.setText(movie.getCountry());
        movie_desc_detail.setText(movie.getDescription());

        rcv_movie_detail_review_item = mView.findViewById(R.id.rcv_movie_detail_review_item);
        movie_review_rating_review = mView.findViewById(R.id.movie_review_rating_review);
        movie_review_rating_review.addOnChangeListener((slider, value, fromUser) -> {rating =(int)value;});
    }

    private void clickButtonWatch() {
        watchBtn = mView.findViewById(R.id.btn_watch_movie);
        watchBtn.setOnClickListener(t -> {
            ((HomeActivity) getActivity()).replaceFragment(WatchMovieFragment.newInstance(movie.getMovieURL()));
        });
    }

    private void formReview() {
        movie_detail_title_review = mView.findViewById(R.id.movie_detail_title_review);
        movie_detail_content_review = mView.findViewById(R.id.movie_detail_content_review);
        movie_review_rating_review = mView.findViewById(R.id.movie_review_rating_review);
        btn_send_review = mView.findViewById(R.id.btn_send_review);

        ReviewBody review = new ReviewBody();
        btn_send_review.setOnClickListener(t -> {
            review.setTitle(movie_detail_title_review.getText().toString());
            review.setReviewContent(movie_detail_content_review.getText().toString());
            review.setRating(rating);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                review.setReviewTime(LocalDate.now().toString());
            }
            review.setUserID(DataLocalManager.getUserId());
            review.setMovieID(movie.getMovieID());

            CreateReviewContract.View view = new CreateReviewContract.View() {
                @Override
                public void onResponseSuccess() {
                    error_message_review.setVisibility(View.GONE);
                    Toast.makeText(mView.getContext(), "Created review successfully!", Toast.LENGTH_SHORT).show();
                    movie_detail_title_review.setText("");
                    movie_detail_content_review.setText("");
                    movie_review_rating_review.setValue(0);
                }

                @Override
                public void onResponseFailure(String message) {
                    error_message_review.setText(message);
                    error_message_review.setVisibility(View.VISIBLE);
                }
            };

            CreateReviewMoviePresenter createReviewMoviePresenter  = new CreateReviewMoviePresenter(view);
            createReviewMoviePresenter.requestCreateReview(review);
        });
    }

    private void renderListReview() {
        handleReviewWithSignalR();
        GetListReviewMoviePresenter getListReviewMoviePre = new GetListReviewMoviePresenter(this);
        getListReviewMoviePre.requestDataFromServer(movie.getMovieID());
    }
    private void handleReviewWithSignalR(){
        hubConnection = HubConnectionBuilder.create(
                        ApiClient.address+"/review")
                .build();
        try {
            if(hubConnection.getConnectionState() == HubConnectionState.DISCONNECTED){
                hubConnection.start().blockingAwait();
                hubConnection.invoke("JoinGroup", movie.getMovieID());
            }
        } catch (Exception e) {
            Toast.makeText(mView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        hubConnection.on("SendReview",(review)-> {
            Activity activity = (Activity) mView.getContext();
            activity.runOnUiThread(() ->listReviewAdapter.addItems(review));
        }, Review.class);
    }

    @Override
    public void onDestroy() {
        if(hubConnection.getConnectionState() == HubConnectionState.CONNECTED){
            hubConnection.stop();
        }
        super.onDestroy();
    }

    @Override
    public void setDataToRecyclerview(List<Review> reviewListArray) {
        listReviewAdapter = new ListReviewAdapter(reviewListArray);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mView.getContext());
        rcv_movie_detail_review_item.setHasFixedSize(false);
        rcv_movie_detail_review_item.setLayoutManager(layoutManager);
        rcv_movie_detail_review_item.setAdapter(listReviewAdapter);
    }

    @Override
    public void onResponseFailure(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
