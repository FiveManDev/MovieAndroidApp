package com.example.movieandroidapp.fragment.admin;

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
import com.example.movieandroidapp.contract.GetTotalContract;
import com.example.movieandroidapp.contract.user.ChangeUserStatus;
import com.example.movieandroidapp.contract.user.DeleteUserContract;
import com.example.movieandroidapp.contract.user.GetUsersContract;
import com.example.movieandroidapp.contract.user.ListenerActionUser;
import com.example.movieandroidapp.model.ResponseFilter;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.network.BodyRequest.Filter;
import com.example.movieandroidapp.presenter.Statistic.GetTotalPresenter;
import com.example.movieandroidapp.presenter.user.ChangeUserStatusPresenter;
import com.example.movieandroidapp.presenter.user.DeleteUserPresenter;
import com.example.movieandroidapp.presenter.user.GetUsersPresenter;
import com.example.movieandroidapp.service.Statistic.GetTotalUserRequest;
import com.example.movieandroidapp.view.movie.SortByAdapter;
import com.example.movieandroidapp.view.user.UserListAdminAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Admin_UserFragment extends Fragment implements GetUsersContract.View,ListenerActionUser {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private LinearLayoutManager layoutManager;
    private Filter filterList;
    private Spinner user_filter_spinner_admin;
    private EditText search_user_admin;
    private Button btn_loadMore_user_admin;
    private RecyclerView rcv_user_admin;
    private TextView user_admin_total,user_admin_notFound_txt;
    private UserListAdminAdapter listAdminAdapter;

    private boolean isSearch;


    private List<User> userList;

    private View mView;
    public Admin_UserFragment() {
        // Required empty public constructor
    }


    public static Admin_UserFragment newInstance(String param1, String param2) {
        Admin_UserFragment fragment = new Admin_UserFragment();
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

        mView  = inflater.inflate(R.layout.fragment_admin__user, container, false);
        init();
        return mView;
    }

    private void init() {
        userList = new ArrayList<>();

        filterList = new Filter();
        filterList.setPageIndex(1);
        filterList.setPageSize(5);
        filterList.setQuery("");
        filterList.setSortBy("date");
        filterList.setSortType("desc");

        rcv_user_admin = mView.findViewById(R.id.rcv_user_admin);
        search_user_admin = mView.findViewById(R.id.search_user_admin);
        user_admin_notFound_txt = mView.findViewById(R.id.user_admin_notFound_txt);

        btn_loadMore_user_admin = mView.findViewById(R.id.btn_loadMore_user_admin);
        user_admin_total = mView.findViewById(R.id.user_admin_total);

        renderTotalUsers();
        renderListSortBy();
        handleSearchText();
        handleLoadMore();
    }

    private void renderTotalUsers(){
        GetTotalContract.View view = new GetTotalContract.View() {
            @Override
            public void onResponseFailure(String message) {
                user_admin_total.setText(0);
            }

            @Override
            public void onResponseSuccess(int total) {
                user_admin_total.setText(total + " Total");
            }
        };
        GetTotalUserRequest request = new GetTotalUserRequest();
        GetTotalPresenter getTotalPresenter = new GetTotalPresenter(view,request);
        getTotalPresenter.requestGetTotal();
    }

    private void filterGetUser(){
        layoutManager = new LinearLayoutManager(mView.getContext());
        rcv_user_admin.setLayoutManager(layoutManager);
        rcv_user_admin.setHasFixedSize(false);

        GetUsersPresenter getUsersPresenter = new GetUsersPresenter(this);
        getUsersPresenter.requestGetUserToServer(filterList);
    }

    private void handleSearchText(){
        search_user_admin.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filterList.setQuery(search_user_admin.getText().toString());
                isSearch=true;
                filterGetUser();
                return true;
            }
            return false;
        });
    }

    private void handleLoadMore(){
        btn_loadMore_user_admin.setOnClickListener(t->{
            //tang page index len 1 de load more data
            filterList.setPageIndex(filterList.getPageIndex() + 1);
            filterGetUser();
        });
    }
    private void renderListSortBy(){
        user_filter_spinner_admin = mView.findViewById(R.id.user_filter_spinner_admin);
        SortByAdapter sortByAdapter = new SortByAdapter(mView.getContext(), R.layout.dropdown_selected,listSortBy());
        user_filter_spinner_admin.setAdapter(sortByAdapter);
        user_filter_spinner_admin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterList.setSortBy(sortByAdapter.getItem(position));
                filterGetUser();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List<String> listSortBy(){
        List<String> list = new ArrayList<>();
        list.add("Date");
        list.add("Status");
        list.add("Pricing Plan");
        return list;
    }

    @Override
    public void onResponseSuccess(ResponseFilter<User[]> pagination) {
        if(pagination==null){
            user_admin_notFound_txt.setVisibility(View.VISIBLE);
            rcv_user_admin.setAdapter(null);
            btn_loadMore_user_admin.setVisibility(View.GONE);
        }
        else{
            //check co hien thi button load more
            if(pagination.getPagination().getHasNext())
            {
                btn_loadMore_user_admin.setVisibility(View.VISIBLE);
            }
            else{
                btn_loadMore_user_admin.setVisibility(View.GONE);
            }

            user_admin_notFound_txt.setVisibility(View.GONE);

            List<User> userListResponse = Arrays.asList(pagination.getValue());

            userList.addAll(userListResponse);
            listAdminAdapter = new UserListAdminAdapter(userList,mView.getContext(),"user",this);
            rcv_user_admin.setAdapter(listAdminAdapter);
        }
    }


    private void ChangeStatus(User user){
        ChangeUserStatus.View view = new ChangeUserStatus.View() {
            @Override
            public void onResponseSuccess(String userID) {
                for (User user1 : userList) {
                    if(user1.getUserID().equals(userID)){
                        user1.setStatus(!user1.getStatus());
                        break;
                    }
                }
                listAdminAdapter.setMovieList(userList);
            }

            @Override
            public void onResponseFailure(String message) {
                Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
        ChangeUserStatusPresenter presenter = new ChangeUserStatusPresenter(view);
        presenter.requestChangeStatusUser(user.getUserID(),!user.getStatus());
    }

    private void DeleteUserById(String userId){

        DeleteUserContract.View view= new DeleteUserContract.View() {
            @Override
            public void onResponseSuccess() {
                for (User user1 : userList) {
                    if(user1.getUserID().equals(userId)){
                        userList.remove(user1);
                        break;
                    }
                }
                listAdminAdapter.setMovieList(userList);
                Toast.makeText(mView.getContext(), "Delete user successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponseFailure(String message) {
                Toast.makeText(mView.getContext(), "Delete user fail!", Toast.LENGTH_SHORT).show();
            }
        };
        DeleteUserPresenter presenter = new DeleteUserPresenter(view);
        presenter.requestDeleteUser(userId);
    }
    @Override
    public void onResponseFailure(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ListenerClicked(String action_type, User user) {
        if(action_type.equals("block")){
            ChangeStatus(user);
        }
        else{
            DeleteUserById(user.getUserID());
        }
    }
}