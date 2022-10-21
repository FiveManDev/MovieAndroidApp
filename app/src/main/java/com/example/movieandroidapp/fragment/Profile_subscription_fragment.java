package com.example.movieandroidapp.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.model.Classification;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_subscription_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_subscription_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Classificaiton = "param1";

    View mView;
    CardView profile_basic_plan,profile_pre_plan;
    Classification classification;
    public Profile_subscription_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param classification Parameter 1.
     * @return A new instance of fragment Profile_subscription_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile_subscription_fragment newInstance(Classification classification) {
        Profile_subscription_fragment fragment = new Profile_subscription_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_Classificaiton, Extension.GsonUtil().toJson(classification));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            classification = Extension.GsonUtil().fromJson(getArguments().getString(ARG_Classificaiton),Classification.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile_subscription_fragment, container, false);
        init();
        return mView;
    }
    private void init(){
        profile_basic_plan = mView.findViewById(R.id.profile_basic_plan);
        profile_pre_plan = mView.findViewById(R.id.profile_pre_plan);

        if(classification.getClassLevel() == 1){
            profile_basic_plan.setVisibility(View.VISIBLE);
            profile_pre_plan.setVisibility(View.GONE);
        }
        else{
            profile_basic_plan.setVisibility(View.GONE);
            profile_pre_plan.setVisibility(View.VISIBLE);

        }
    }
}