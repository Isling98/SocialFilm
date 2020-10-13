package com.example.yndlingsfilm.Data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private SavedStateHandle savedStateHandle = new SavedStateHandle();
    private MutableLiveData<User> users;

    public MutableLiveData<User> getUser(String userName) {
        //userRepo metoden findes ikke endnu;
//    user = userRepo.getUser(String userName);
    if (users == null) {
        users = new MutableLiveData<>();
    }
    return users;
}

}