package com.example.hack_it.ui.Profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mText.setValue(user.getDisplayName());
    }

    public LiveData<String> getText() {
        return mText;
    }
}