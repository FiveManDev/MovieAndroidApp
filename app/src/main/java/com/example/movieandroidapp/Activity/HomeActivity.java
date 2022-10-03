package com.example.movieandroidapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.contract.user.GetUserInformationContract;
import com.example.movieandroidapp.fragment.CategoryFragment;
import com.example.movieandroidapp.fragment.HomeFragment;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.presenter.user.GetUserInformationPresenter;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GetUserInformationContract.View {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_CATEGORY = 1;

    private int mCurrentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;
    private ImageView avatar;
    private TextView role,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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


        NavigationView navigationView = findViewById(R.id.navigation_view_user);
        navigationView.setNavigationItemSelectedListener(this);


        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home_user).setChecked(true);

        avatar = findViewById(R.id.header_navigation_avatar);
        role = findViewById(R.id.header_navigation_role);
        name = findViewById(R.id.header_navigation_name);

        GetUserInformationPresenter presenter = new GetUserInformationPresenter(this);
        presenter.requestGetUserToServer();

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
        SearchView searchView =(SearchView) menu.findItem(R.id.search_toolbar).getActionView();
        searchView.setQueryHint("I'm looking for...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(HomeActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home_user){
            if(mCurrentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurrentFragment=FRAGMENT_HOME;
            }
        }
        else if(id == R.id.nav_category_user){
            if(mCurrentFragment != FRAGMENT_CATEGORY){
                replaceFragment(new CategoryFragment());
                mCurrentFragment=FRAGMENT_CATEGORY;
            }
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

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_user,fragment);
        transaction.commit();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onResponseSuccess(User user) {
        if(user != null){
            Picasso.get().load(user.getProfile().getAvatar()).into(avatar);
            name.setText(user.getProfile().getFirstName() + " " + user.getProfile().getLastName());
            role.setText(user.getAuthorization().getAuthorizationName());
        }
    }

    @Override
    public void onResponseFailure(String message) {
        Toast.makeText(this, "Something wrong with your internet", Toast.LENGTH_SHORT).show();
    }
}