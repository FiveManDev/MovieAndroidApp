package com.example.movieandroidapp.fragment.admin;

import android.os.Bundle;
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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.GetTotalContract;
import com.example.movieandroidapp.contract.review.DeleteReviewContract;
import com.example.movieandroidapp.contract.review.GetReviews;
import com.example.movieandroidapp.contract.review.ListenerReview;
import com.example.movieandroidapp.fragment.custom.DialogReview;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.Review;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.network.BodyRequest.Filter;
import com.example.movieandroidapp.presenter.Review.DeleteReviewPresenter;
import com.example.movieandroidapp.presenter.Review.GetReviewsPresenter;
import com.example.movieandroidapp.presenter.Statistic.GetTotalPresenter;
import com.example.movieandroidapp.service.Statistic.GetTotalReviewRequest;
import com.example.movieandroidapp.view.Review.ListReviewAdminAdapter;
import com.example.movieandroidapp.view.movie.SortByAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_ReviewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_ReviewsFragment extends Fragment implements GetReviews.View, ListenerReview {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private LinearLayoutManager layoutManager;
    private Filter filterList;
    private Spinner review_filter_spinner_admin;
    private EditText search_movie_admin;
    private Button btn_loadMore_review_admin;
    private RecyclerView rcv_review_admin;
    private TextView review_admin_total,review_admin_notFound_txt;
    private ListReviewAdminAdapter listReviewAdminAdapter;


    private List<Review> reviewList;

    private View mView;


    public Admin_ReviewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_ReviewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_ReviewsFragment newInstance(String param1, String param2) {
        Admin_ReviewsFragment fragment = new Admin_ReviewsFragment();
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
        mView =  inflater.inflate(R.layout.fragment_admin__reviews, container, false);
        init();
        return mView;
    }
    private void init() {
        reviewList = new ArrayList<>();

        filterList = new Filter();
        filterList.setPageIndex(1);
        filterList.setPageSize(5);
        filterList.setQuery("");
        filterList.setSortBy("date");
        filterList.setSortType("desc");

        rcv_review_admin = mView.findViewById(R.id.rcv_review_admin);
        search_movie_admin = mView.findViewById(R.id.search_movie_admin);

        review_admin_notFound_txt = mView.findViewById(R.id.review_admin_notFound_txt);

        btn_loadMore_review_admin = mView.findViewById(R.id.btn_loadMore_review_admin);
        review_admin_total = mView.findViewById(R.id.review_admin_total);

        renderTotalReview();
        renderListSortBy();
        handleSearchText();
        handleLoadMore();
    }

    private void renderTotalReview(){
        GetTotalContract.View view = new GetTotalContract.View() {
            @Override
            public void onResponseFailure(String message) {
                review_admin_total.setText(0);
            }

            @Override
            public void onResponseSuccess(int total) {
                review_admin_total.setText(total + " Total");
            }
        };
        GetTotalReviewRequest request = new GetTotalReviewRequest();
        GetTotalPresenter getTotalPresenter = new GetTotalPresenter(view,request);
        getTotalPresenter.requestGetTotal();
    }

    private void filterGetReviews(){
        layoutManager = new LinearLayoutManager(mView.getContext());
        rcv_review_admin.setLayoutManager(layoutManager);
        rcv_review_admin.setHasFixedSize(false);

        GetReviewsPresenter getReviewsPresenter = new GetReviewsPresenter(this);
        getReviewsPresenter.requestGetReviews(filterList);
    }

    private void handleSearchText(){
        search_movie_admin.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filterList.setQuery(search_movie_admin.getText().toString());
                filterGetReviews();
                return true;
            }
            return false;
        });
    }

    private void handleLoadMore(){
        btn_loadMore_review_admin.setOnClickListener(t->{
            //tang page index len 1 de load more data
            filterList.setPageIndex(filterList.getPageIndex() + 1);
            filterGetReviews();
        });
    }
    private void renderListSortBy(){
        review_filter_spinner_admin = mView.findViewById(R.id.review_filter_spinner_admin);
        SortByAdapter sortByAdapter = new SortByAdapter(mView.getContext(), R.layout.dropdown_selected,listSortBy());
        review_filter_spinner_admin.setAdapter(sortByAdapter);
        review_filter_spinner_admin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterList.setSortBy(sortByAdapter.getItem(position));
                filterGetReviews();
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
    public void onResponseSuccess(ResponseFilter<Review[]> response) {
        if(response==null){
            review_admin_notFound_txt.setVisibility(View.VISIBLE);
            rcv_review_admin.setAdapter(null);
            review_admin_notFound_txt.setVisibility(View.GONE);
        }
        else{
            //check co hien thi button load more
            if(response.getPagination().getHasNext())
            {
                btn_loadMore_review_admin.setVisibility(View.VISIBLE);
            }
            else{
                btn_loadMore_review_admin.setVisibility(View.GONE);
            }

            review_admin_notFound_txt.setVisibility(View.GONE);

            List<Review> reviewListResponse = Arrays.asList(response.getValue());

            reviewList.addAll(reviewListResponse);
            listReviewAdminAdapter = new ListReviewAdminAdapter(reviewList,mView.getContext(),"review",this);
            rcv_review_admin.setAdapter(listReviewAdminAdapter);
        }
    }

    @Override
    public void onResponseFailure(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void listenerClicked(Review review,String type) {
        if(type.equals("delete")){
           DeleteReview(review.getReviewID());
        }else{
            openDialogDetail(review);
        }
    }

    private void DeleteReview(String reviewID) {
        DeleteReviewContract.View view = new DeleteReviewContract.View() {
            @Override
            public void onResponseSuccess() {
                for (Review review : reviewList) {
                    if(review.getReviewID().equals(reviewID)){
                        reviewList.remove(review);
                        break;
                    }
                }
                listReviewAdminAdapter.setList(reviewList);
            }

            @Override
            public void onResponseFailure(String message) {
                Toast.makeText(mView.getContext(), message , Toast.LENGTH_SHORT).show();
            }
        };
        DeleteReviewPresenter presenter = new DeleteReviewPresenter(view);
        presenter.requestGetReviews(reviewID);
    }
    private void openDialogDetail(Review review){
        Bundle bundle = new Bundle();
        String reviewJson = Extension.GsonUtil().toJson(review);
        bundle.putString("Review",reviewJson);
        DialogReview dialogReview = new DialogReview();
        dialogReview.setArguments(bundle);
        dialogReview.show(requireActivity().getSupportFragmentManager(), "opend");
    }
}