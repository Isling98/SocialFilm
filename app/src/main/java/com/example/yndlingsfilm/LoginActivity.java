package com.example.yndlingsfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yndlingsfilm.viewModels.UserViewModel;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {
    private UserViewModel userViewModel;
    Button loginButton;
    EditText mail;
    EditText password;
    TextView forgotPassword;
    TextView newAccount;
     ProgressBar simpleProgressBar;

    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        loginButton = findViewById(R.id.button_login);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgot_password);
        newAccount = findViewById(R.id.new_account);
        simpleProgressBar = findViewById(R.id.progressBar);

        loginButton.setOnClickListener(this);
        newAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.button_login) {
            simpleProgressBar.setVisibility(View.VISIBLE);
            Log.d(TAG, "@@ LOADING PROGRESS BAR SAT IGANG");

            if(userViewModel.login(mail.getText().toString(), password.getText().toString())){
                userViewModel.getLoggedInUser().setValue(userViewModel.getUser(mail.getText().toString(), "token"));
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();

            } else {
                simpleProgressBar.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), "wrong pass", Toast.LENGTH_LONG);
                toast.show();

            }

        } else if(view.getId() == R.id.new_account) {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}