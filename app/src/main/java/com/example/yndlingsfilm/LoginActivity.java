package com.example.yndlingsfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yndlingsfilm.Data.UserViewModel;
import com.example.yndlingsfilm.Data.User;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {
    UserViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        model = new ViewModelProvider(this).get(UserViewModel.class);
//        model.getUsers();
        Button loginButton = (Button) findViewById(R.id.button_login);
        EditText mail = (EditText) findViewById(R.id.mail);
        EditText password = (EditText) findViewById(R.id.password);
        TextView forgotPassword = (TextView) findViewById(R.id.forgot_password);
        loginButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


//        for(int i =0; i< model.getUsers().getValue().size(); i++){
//
//            User u = model.getUsers().getValue().get(i);
//            if(u.getUsername().equals( mail.getText().toString())){
//                model.getUser().setValue(u);
//                System.out.println("Hej jeg har nu sat model til " + u.getUsername());
//        }}
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }
}