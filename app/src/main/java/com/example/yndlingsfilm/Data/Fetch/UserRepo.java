package com.example.yndlingsfilm.Data.Fetch;

import androidx.lifecycle.MutableLiveData;

import com.example.yndlingsfilm.Data.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepo{ // implements Webservice. hardcoder det i første omgang mens jeg venter på databasen. bruger stadig singleton

    private static UserRepo instance;
    private ArrayList<User> dataSet = new ArrayList<>();


    public static UserRepo getInstance(){
        if(instance == null){
            instance = new UserRepo();
        }
        return instance;
    }

    public MutableLiveData<List<User>> getUsers(){
        //tilgå database her. er bare faket her
        setUsers();

        MutableLiveData<List<User>> data = new MutableLiveData<>();
        data.setValue(dataSet);

        return data;
    }


    private void setUsers(){
        dataSet.add(new User(1,"Asger Åkanden", "ikkepassher", "asger@head.com"));
        dataSet.add(new User(1,"Markus head", "ikkepassher", "markus@head.com"));
    }

}
