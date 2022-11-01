package com.example.movieandroidapp.presenter.profile;

import com.example.movieandroidapp.contract.profile.UpdateProfileUser;
import com.example.movieandroidapp.service.Profile.updateProfileUserRequest;

public class UpdateProfileUserPresenter implements UpdateProfileUser.Presenter,UpdateProfileUser.Model.OnFinishedListener {
    private UpdateProfileUser.Model model;
    private UpdateProfileUser.View view;

    public UpdateProfileUserPresenter(UpdateProfileUser.View view){
        this.view = view;
        model = new updateProfileUserRequest();
    }

    @Override
    public void onFinished() {
        view.onResponseSuccess();
    }

    @Override
    public void onFailure(String message) {
        view.onResponseFailure(message);
    }

    @Override
    public void requestUpdateProfile(String userId, String firstname, String lastname) {
        if(firstname.isEmpty()){
            view.onResponseFailure("First name can not be empty");
        }
        else if(lastname.isEmpty()){
            view.onResponseFailure("Last name can not be empty");
        }
        else{
            model.updateProfile(this,userId,firstname,lastname);
        }
    }
}
