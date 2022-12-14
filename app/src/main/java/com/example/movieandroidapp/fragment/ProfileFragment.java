package com.example.movieandroidapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.Activity.LoginActivity;
import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.model.MessageEvent;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.view.user.ProfileNavigationAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private User mUser;

    TextView profile_role_user,profile_name_user;
    ImageView profile_image_user;

    View mView;
    Spinner profile_navigation;
    ProfileNavigationAdapter profileAdapter;

    RelativeLayout profile_logout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, Extension.GsonUtil().toJson(user));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = Extension.GsonUtil().fromJson(getArguments().getString(ARG_PARAM1),User.class);
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        //default fragment when init is running
//        Profile_information_fragment movieDetailFragment = new Profile_information_fragment();
//        replaceFragment(movieDetailFragment);
        init();
        return mView;
    }

    private void init(){
        renderListItemNavigation();
        renderUser(mUser);

        profile_logout = mView.findViewById(R.id.profile_logout);
        profile_logout.setOnClickListener(t->logout());
    }
    private void renderUser(User user){
        profile_image_user = mView.findViewById(R.id.profile_image_user);
        profile_role_user = mView.findViewById(R.id.profile_role_user);
        profile_name_user = mView.findViewById(R.id.profile_name_user);

        if(mUser.getProfile().getAvatar() != null){
            Picasso.get().load(mUser.getProfile().getAvatar()).into(profile_image_user);
        }
        profile_role_user.setText(mUser.getAuthorization().getAuthorizationName());
        profile_name_user.setText(mUser.getProfile().getFirstName()+" "+mUser.getProfile().getLastName());
    }
    private void renderListItemNavigation(){
        profile_navigation = mView.findViewById(R.id.profile_navigation);
        profileAdapter = new ProfileNavigationAdapter(mView.getContext(), R.layout.dropdown_selected,listNavigation());
        profile_navigation.setAdapter(profileAdapter);
        profile_navigation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                checkNavigation(profileAdapter.getItem(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private List<String> listNavigation(){
        List<String> list = new ArrayList<>();
        list.add("SUBSCRIPTION");
        list.add("SETTINGS");
        return list;
    }
    private void checkNavigation(String item){
        if(item.equals("SUBSCRIPTION")){
            Profile_subscription_fragment fragment = Profile_subscription_fragment.newInstance(mUser.getProfile().getClassification());
            replaceFragment(fragment);
        }
        else{
            Profile_setting_fragment fragment = Profile_setting_fragment.newInstance(mUser);
            replaceFragment(fragment);
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.profile_frame_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Subscribe
    public void refresh(MessageEvent<User> user) {
        mUser = user.getMessage();
        final Handler handler = new Handler();
        final Runnable runnable = () -> renderUser(mUser);
        handler.postDelayed(runnable,1000);
    }

    public void logout(){
        DataLocalManager.setAccessToken("");
        DataLocalManager.setUserId("");
        startActivity(new Intent(mView.getContext(), LoginActivity.class));
    }
}