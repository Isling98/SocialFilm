package com.example.yndlingsfilm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yndlingsfilm.Model.User;

public class CreateAccountActivity extends AppCompatActivity {

    EditText mail;
    EditText password;
    EditText repeatedPassword;
    Button createAccount;
    EditText username;

    Boolean usernameIsTaken = false;
    Boolean mailIsInUse = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        repeatedPassword = findViewById(R.id.repeated_password);
        createAccount = findViewById(R.id.create_account);
        username = findViewById(R.id.username);


        createAccount.setOnClickListener(view -> {
            //kontrol af kodeord og om mail/username er brugt
            if(!password.getText().toString().equals(repeatedPassword.getText().toString())) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_LONG).show();
                addRedBorder(repeatedPassword);
            } else{
                repeatedPassword.setBackground(null);

                if (usernameIsTaken) {
                    Toast.makeText(this, "Username already taken", Toast.LENGTH_LONG).show();
                    addRedBorder(username);
                } else if(mailIsInUse){
                    Toast.makeText(this, "Mail is already in use", Toast.LENGTH_LONG).show();
                    addRedBorder(mail);
                } else{
                    User user = new User(1, username.getText().toString(),
                            password.getText().toString(), mail.getText().toString(), null
                            );
                }
            }
        });
    }

    public void addRedBorder(EditText editText){
        editText.setTextColor(Color.RED);
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.RED);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(3);
        editText.setBackground(shape);
    }
}