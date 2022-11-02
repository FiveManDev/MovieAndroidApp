package com.example.movieandroidapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.contract.user.GetUserInformationContract;
import com.example.movieandroidapp.fragment.admin.Admin_MovieFragment;
import com.example.movieandroidapp.fragment.admin.Admin_CatalogFragment;
import com.example.movieandroidapp.fragment.admin.Admin_DashBoardFragment;
import com.example.movieandroidapp.fragment.admin.Admin_ReviewsFragment;
import com.example.movieandroidapp.fragment.admin.Admin_UserFragment;
import com.example.movieandroidapp.model.MessageEvent;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.presenter.user.GetUserInformationPresenter;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final int FRAGMENT_DASHBOARD = 0;
    public static final int FRAGMENT_CATALOG = 1;
    public static final int FRAGMENT_USER = 2;
    public static final int FRAGMENT_REVIEWS = 3;
    public static final int FRAGMENT_ADD_MOVIE = 4;

    public static int mCurrentFragment = FRAGMENT_DASHBOARD;

    private DrawerLayout mDrawerLayout;
    private ImageView avatar;
    private TextView role,name;
    private RelativeLayout btn_logout_home;
    View header_nav;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //tool bar top
        Toolbar toolbar = findViewById(R.id.toolbar_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mDrawerLayout = findViewById(R.id.drawer_layout_admin);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,
                R.string.navigation_drawer_opend,
                R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        NavigationView navigationView = findViewById(R.id.navigation_view_admin);
        navigationView.setNavigationItemSelectedListener(this);
        //create context to find id of list item in header navigation
        header_nav = navigationView.getHeaderView(0);

        replaceFragment(new Admin_DashBoardFragment());

        navigationView.getMenu().findItem(R.id.nav_dashboard_admin).setChecked(true);

        //get information of a user
        getUser(DataLocalManager.getUserId());
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void refresh(MessageEvent<User> user) {
        mUser = user.getMessage();
        final Handler handler = new Handler();
        final Runnable runnable = () -> renderUser(mUser);
        handler.postDelayed(runnable,1000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_dashboard_admin){
            if(mCurrentFragment != FRAGMENT_DASHBOARD){
                replaceFragment(new Admin_DashBoardFragment());
                mCurrentFragment=FRAGMENT_DASHBOARD;
            }
        }
        else if(id == R.id.nav_catalog_admin){
            if(mCurrentFragment != FRAGMENT_CATALOG){
                replaceFragment(new Admin_CatalogFragment());
                mCurrentFragment=FRAGMENT_CATALOG;
            }
        }
        else if(id == R.id.nav_user_admin){
            if(mCurrentFragment != FRAGMENT_USER){
                replaceFragment(new Admin_UserFragment());
                mCurrentFragment=FRAGMENT_USER;
            }
        }
        else if(id == R.id.nav_review_admin){
            if(mCurrentFragment != FRAGMENT_REVIEWS){
                replaceFragment(new Admin_ReviewsFragment());
                mCurrentFragment=FRAGMENT_REVIEWS;
            }
        }
        else if(id == R.id.nav_add_admin){
                replaceFragment(new Admin_MovieFragment());
                mCurrentFragment=FRAGMENT_ADD_MOVIE;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_admin,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void renderUser(User user){
        avatar =header_nav.findViewById(R.id.header_navigation_avatar);
        role = header_nav.findViewById(R.id.header_navigation_role);
        name = header_nav.findViewById(R.id.header_navigation_name);

        //set onclick for icon logout to logout
        btn_logout_home =(header_nav).findViewById(R.id.btn_logout_home);
        btn_logout_home.setOnClickListener(t ->{
            logout();
        });
        if(user != null){
            if(!user.getProfile().getAvatar().isEmpty()){
                Picasso.get().load(user.getProfile().getAvatar()).into(avatar);
            }else {
                Picasso.get().load("@drawable/not_available").into(avatar);
            }
            name.setText(user.getProfile().getFirstName() + " " + user.getProfile().getLastName());
            role.setText(user.getAuthorization().getAuthorizationName());
        }
    }

    public void getUser(String userId) {
        GetUserInformationContract.View userInformationContract = new GetUserInformationContract.View() {
            @Override
            public void onResponseSuccess(User user) {
                mUser = user;
                renderUser(user);
            }

            @Override
            public void onResponseFailure(String message) {
            }
        };
        GetUserInformationPresenter presenter = new GetUserInformationPresenter(userInformationContract);
        presenter.requestGetUserToServer(userId);
    }

    public void logout(){
        DataLocalManager.setAccessToken("");
        DataLocalManager.setUserId("");
        startActivity(new Intent(AdminActivity.this, LoginActivity.class));
    }
}