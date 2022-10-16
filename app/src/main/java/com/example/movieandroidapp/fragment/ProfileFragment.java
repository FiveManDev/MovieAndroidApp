package com.example.movieandroidapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.R;
import com.example.movieandroidapp.view.movie.GenreAdapter;
import com.example.movieandroidapp.view.user.ProfileNavigationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    View mView;
    Spinner profile_navigation;
    ProfileNavigationAdapter profileAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        //default fragment when init is running
        Profile_information_fragment movieDetailFragment = new Profile_information_fragment();
        replaceFragment(movieDetailFragment);
        init();
        return mView;
    }

    private void init(){
        renderListItemNavigation();
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
        list.add(new String("PROFILE"));
        list.add(new String("SUBSCRIPTION"));
        list.add(new String("SETTINGS"));
        return list;
    }
    private void checkNavigation(String item){
        if(item.equals("PROFILE")){
            Profile_information_fragment movieDetailFragment = new Profile_information_fragment();
            replaceFragment(movieDetailFragment);
        }
        else if(item.equals("SUBSCRIPTION")){
            Profile_subscription_fragment fragment = new Profile_subscription_fragment();
            replaceFragment(fragment);
        }
        else{
            Profile_setting_fragment fragment = new Profile_setting_fragment();
            replaceFragment(fragment);
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.profile_frame_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}