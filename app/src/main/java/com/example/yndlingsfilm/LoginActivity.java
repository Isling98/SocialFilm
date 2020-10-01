package com.example.yndlingsfilm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.button_login);
        EditText mail = (EditText) findViewById(R.id.mail);
        EditText password = (EditText) findViewById(R.id.password);
        TextView forgotPassword = (TextView) findViewById(R.id.forgot_password);

        loginButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        // kontrol af mail og password
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}