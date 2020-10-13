package com.example.yndlingsfilm.Data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {

    private SavedStateHandle savedStateHandle = new SavedStateHandle();
    private MutableLiveData<List<User>> users;

    public MutableLiveData<List<User>> getUsers() {
        //userRepo metoden findes ikke endnu;
//    user = userRepo.getUser(String userName);
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    public void loadUsers() {
        List<User> usersExample = new ArrayList<>();
        User user1 = new User(1, "Asger", "ikkekodeher","AA@rs.com");
        User user2 = new User(2, "Markus", "ikkekodeher","MA@rs.com");
        usersExample.add(user1);
        usersExample.add(user2);

        users.setValue(usersExample);

    }

}