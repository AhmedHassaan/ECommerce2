package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.Data.sharedPreferenceCustom;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {

    private TextInputEditText mSigninUsername;
    private TextInputEditText mSigninPassword;
    ArrayList<TextInputEditText> allTexts;
    sharedPreferenceCustom shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mSigninUsername = findViewById(R.id.signinUsername);
        mSigninPassword = findViewById(R.id.signinPassword);
        shared = new sharedPreferenceCustom(getApplicationContext());
        allTexts = new ArrayList<>();
        allTexts.add(mSigninPassword);
        allTexts.add(mSigninUsername);
    }

    public void signin(View view){
        if(!neededThings.isEmpty(allTexts)){
            String username = mSigninUsername.getText().toString();
            String password = mSigninPassword.getText().toString();
            if(neededThings.isFound(username,password)){
                shared.setLogedIn(username);
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
            else
                neededThings.showToast(getApplicationContext(),"Wrong Username or Password");
        }

    }

    public void goToSignUp(View view){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
