package com.example.movieandroidapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.user.GetUserInformationContract;
import com.example.movieandroidapp.fragment.CatalogFragment;
import com.example.movieandroidapp.fragment.HomeFragment;
import com.example.movieandroidapp.fragment.MovieDetailFragment;
import com.example.movieandroidapp.fragment.PricingFragment;
import com.example.movieandroidapp.fragment.ProfileFragment;
import com.example.movieandroidapp.fragment.SearchHomeFragment;
import com.example.movieandroidapp.model.MessageEvent;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.model.movie.Movie;
import com.example.movieandroidapp.presenter.user.GetUserInformationPresenter;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_CATEGORY = 1;
    public static final int FRAGMENT_SEARCH_HOME = 2;
    public static final int FRAGMENT_PROFILE_HOME = 3;
    public static final int FRAGMENT_PRICING_HOME = 4;
    public static final int FRAGMENT_ADMIN = 5;

    public static int mCurrentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;
    private ImageView avatar;
    private TextView role,name;
    private RelativeLayout btn_logout_home;
    View header_nav;
    User mUser;
    NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //get information of a user
        getUser(DataLocalManager.getUserId());

    }
    private void init(){
        //tool bar top
        Toolbar toolbar = findViewById(R.id.toolbar_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,mDrawerLayout,toolbar,
                R.string.navigation_drawer_opend,
                R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView = findViewById(R.id.navigation_view_user);
        navigationView.setNavigationItemSelectedListener(this);
        //create context to find id of list item in header navigation
        header_nav = navigationView.getHeaderView(0);


        navigationView.getMenu().findItem(R.id.nav_home_user).setChecked(true);

        if(mUser.getAuthorization().getAuthorizationLevel() == 1){
            navigationView.getMenu().findItem(R.id.nav_admin).setVisible(false);
        }

        replaceFragment(HomeFragment.newInstance(mUser));
        renderUser(mUser);
        logout();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar,menu);

        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        };
        menu.findItem(R.id.search_toolbar).setOnActionExpandListener(onActionExpandListener);
        //search
        SearchView searchView =(SearchView) menu.findItem(R.id.search_toolbar).getActionView();
        //set placeholder
        searchView.setQueryHint("I'm looking for...");
        //handle event search when click icon search on keyboard
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //bundle data to search fragment
                Bundle bundle = new Bundle();
                bundle.putString("search_query",query);
                bundle.putString("user", Extension.GsonUtil().toJson(mUser));
                SearchHomeFragment searchHomeFragment = new SearchHomeFragment();
                searchHomeFragment.setArguments(bundle);
                //replace current fragment to search fragment
                replaceFragment(searchHomeFragment);

                mCurrentFragment = FRAGMENT_SEARCH_HOME;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //handle event when click close search in toolbar
        MenuItem searchMenuItem = menu.findItem(R.id.search_toolbar);
        searchMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                replaceFragment(HomeFragment.newInstance(mUser));
                mCurrentFragment=FRAGMENT_HOME;
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home_user){
            if(mCurrentFragment != FRAGMENT_HOME){
                replaceFragment(HomeFragment.newInstance(mUser));
                mCurrentFragment=FRAGMENT_HOME;
            }
        }
        else if(id == R.id.nav_category_user){
            if(mCurrentFragment != FRAGMENT_CATEGORY){
                replaceFragment(new CatalogFragment());
                mCurrentFragment=FRAGMENT_CATEGORY;
            }
        }
        else if(id == R.id.nav_profile_user){
            if(mCurrentFragment != FRAGMENT_PROFILE_HOME){
                replaceFragment(ProfileFragment.newInstance(mUser));
                mCurrentFragment=FRAGMENT_PROFILE_HOME;
            }
        }
        else if(id == R.id.nav_pricing_user){
            if(mCurrentFragment != FRAGMENT_PRICING_HOME){
                replaceFragment(new PricingFragment());
                mCurrentFragment=FRAGMENT_PRICING_HOME;
            }
        }
        else if(id == R.id.nav_admin){
            startActivity(new Intent(HomeActivity.this, AdminActivity.class));
            mCurrentFragment=FRAGMENT_ADMIN;
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
        transaction.replace(R.id.content_frame_user,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void renderUser(User user){
        avatar =header_nav.findViewById(R.id.header_navigation_avatar);
        role = header_nav.findViewById(R.id.header_navigation_role);
        name = header_nav.findViewById(R.id.header_navigation_name);


        if(user != null){
            if(user.getProfile().getAvatar() != null){
                Picasso.get().load(user.getProfile().getAvatar()).into(avatar);
            }else {
                Picasso.get().load("@drawable/not_available").into(avatar);
            }
            name.setText(user.getProfile().getFirstName() + " " + user.getProfile().getLastName());
            role.setText(user.getAuthorization().getAuthorizationName());
        }
    }

    public MovieDetailFragment bundleMovieToDetailFragment(Movie movie){
        Gson gson = new Gson();
        Bundle bundle = new Bundle();
        bundle.putString("movie",gson.toJson(movie));
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(bundle);
        return movieDetailFragment;
    }

    public void getUser(String userId) {
        GetUserInformationContract.View userInformationContract = new GetUserInformationContract.View() {
            @Override
            public void onResponseSuccess(User user) {
                mUser = user;
                init();
            }

            @Override
            public void onResponseFailure(String message) {
            }
        };
        GetUserInformationPresenter presenter = new GetUserInformationPresenter(userInformationContract);
        presenter.requestGetUserToServer(userId);
    }

    public void logout(){
        //set onclick for icon logout to logout
        btn_logout_home =(header_nav).findViewById(R.id.btn_logout_home);
        btn_logout_home.setOnClickListener(t ->{
            DataLocalManager.setAccessToken("");
            DataLocalManager.setUserId("");
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        });
    }

    @Override
    public boolean isDestroyed() {
        navigationView.getMenu().findItem(R.id.nav_home_user).setChecked(true);
        mCurrentFragment = FRAGMENT_HOME;
        return super.isDestroyed();
    }
}