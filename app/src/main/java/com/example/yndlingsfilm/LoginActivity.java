package com.example.yndlingsfilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yndlingsfilm.viewModels.UserViewModel;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {
    UserViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        model = new ViewModelProvider(this).get(UserViewModel.class);
//        model.getUsers();
        Button loginButton = findViewById(R.id.button_login);
        EditText mail = findViewById(R.id.mail);
        EditText password = findViewById(R.id.password);
        TextView forgotPassword = findViewById(R.id.forgot_password);
        TextView newAccount = findViewById(R.id.new_account);

        loginButton.setOnClickListener(this);
        newAccount.setOnClickListener(this);
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
        if(view.getId()== R.id.button_login) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        } else if(view.getId() == R.id.new_account) {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}