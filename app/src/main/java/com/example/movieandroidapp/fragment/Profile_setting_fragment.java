package com.example.movieandroidapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieandroidapp.Activity.HomeActivity;
import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.profile.UpdateProfileUser;
import com.example.movieandroidapp.contract.user.ChangePasswordContract;
import com.example.movieandroidapp.model.MessageEvent;
import com.example.movieandroidapp.model.User;
import com.example.movieandroidapp.presenter.profile.UpdateProfileUserPresenter;
import com.example.movieandroidapp.presenter.user.ChangePasswordPresenter;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_setting_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_setting_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private User mUser;
    private View mView;
    private TextView error_message_profile;

    private EditText username_profile_setting,
            email_profile_setting,
            firstName_profile_setting,
            lastName_profile_setting,
            old_password_profile_setting,
            new_password_profile_setting,
            confirm_password_profile_setting;

    private Button btn_save_profile, btn_change_password_profile;

    public Profile_setting_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @return A new instance of fragment Profile_setting_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile_setting_fragment newInstance(User user) {
        Profile_setting_fragment fragment = new Profile_setting_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, Extension.GsonUtil().toJson(user));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = Extension.GsonUtil().fromJson(getArguments().getString(ARG_PARAM1), User.class);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile_setting_fragment, container, false);
        init();
        return mView;
    }

    private void init() {
        renderProfile();
        handleChangeProfile();
        handleChangePassword();
    }

    private void renderProfile() {
        username_profile_setting = mView.findViewById(R.id.username_profile_setting);
        email_profile_setting = mView.findViewById(R.id.email_profile_setting);
        firstName_profile_setting = mView.findViewById(R.id.firstName_profile_setting);
        lastName_profile_setting = mView.findViewById(R.id.lastName_profile_setting);

        username_profile_setting.setText(mUser.getUserName());
        email_profile_setting.setText(mUser.getProfile().getEmail());
        firstName_profile_setting.setText(mUser.getProfile().getFirstName());
        lastName_profile_setting.setText(mUser.getProfile().getLastName());
    }

    private void handleChangeProfile() {
        btn_save_profile = mView.findViewById(R.id.btn_save_profile);
        btn_save_profile.setOnClickListener(t -> {
            UpdateProfileUser.View view = new UpdateProfileUser.View() {
                @Override
                public void onResponseSuccess() {
                    mUser.getProfile().setFirstName(firstName_profile_setting.getText().toString());
                    mUser.getProfile().setLastName(lastName_profile_setting.getText().toString());
                    EventBus.getDefault().post(new MessageEvent<User>(mUser));
                    Toast.makeText(mView.getContext(),"Update profile success" , Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponseFailure(String message) {
                    Toast.makeText(mView.getContext(),"Failed" , Toast.LENGTH_SHORT).show();
                }
            };
            UpdateProfileUserPresenter presenter = new UpdateProfileUserPresenter(view);
            presenter.requestUpdateProfile(mUser.getUserID(),firstName_profile_setting.getText().toString(),
                    lastName_profile_setting.getText().toString()
                    );
        });
    }

    private void handleChangePassword() {
        error_message_profile = mView.findViewById(R.id.error_message_profile);

        old_password_profile_setting = mView.findViewById(R.id.old_password_profile_setting);
        new_password_profile_setting = mView.findViewById(R.id.new_password_profile_setting);
        confirm_password_profile_setting = mView.findViewById(R.id.confirm_password_profile_setting);

        btn_change_password_profile = mView.findViewById(R.id.btn_change_password_profile);

        btn_change_password_profile.setOnClickListener(t -> {
            ChangePasswordContract.View view = new ChangePasswordContract.View() {
                @Override
                public void onResponseSuccess() {
                    error_message_profile.setText("");
                    error_message_profile.setVisibility(View.GONE);
                    Toast.makeText(mView.getContext(), "Change password successfully", Toast.LENGTH_SHORT).show();
                    old_password_profile_setting.setText("");
                    new_password_profile_setting.setText("");
                    confirm_password_profile_setting.setText("");
                }

                @Override
                public void onResponseFailure(String message) {
                    error_message_profile.setText(message);
                    error_message_profile.setVisibility(View.VISIBLE);
                }
            };

            ChangePasswordPresenter presenter = new ChangePasswordPresenter(view);
            presenter.requestChangePassword(old_password_profile_setting.getText().toString(),
                    new_password_profile_setting.getText().toString(),
                    confirm_password_profile_setting.getText().toString()
            );
        });
    }
}