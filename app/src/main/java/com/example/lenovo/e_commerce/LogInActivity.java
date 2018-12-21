package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.Data.sharedPreferenceCustom;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {

    private TextInputEditText mSigninUsername;
    private TextInputEditText mSigninPassword;
    ArrayList<TextInputEditText> allTexts;
    sharedPreferenceCustom shared;
    private CheckBox mCheckBox;

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
        mCheckBox = findViewById(R.id.checkBox);
        if(shared.isRemeberME()){
            if(neededThings.users.size()==0) {
                neededThings.showToast(getApplicationContext(), "Something went error pls log in again");
                shared.setLogOut();
            }
            else{
                neededThings.currentUser = shared.getCurrentUser();
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    public void signin(View view){
        if(!neededThings.isEmpty(allTexts)){
            String username = mSigninUsername.getText().toString();
            String password = mSigninPassword.getText().toString();
            if(neededThings.isFound(username,password)){
                if(mCheckBox.isChecked())
                    shared.setRemeberMe();
                shared.setLogedIn(username);
                neededThings.currentUser = shared.getCurrentUser();
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

    public void goToForgetPassword(View view) {
        Intent intent = new Intent(this,ForgetPassword.class);
        startActivity(intent);
        finish();
    }
}
