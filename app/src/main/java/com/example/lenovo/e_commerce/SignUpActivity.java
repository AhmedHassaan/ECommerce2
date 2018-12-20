package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lenovo.e_commerce.Data.User;
import com.example.lenovo.e_commerce.Data.neededThings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText mSignupFullname;
    private TextInputEditText mSignupEmail;
    private TextInputEditText mSignupUsername;
    private TextInputEditText mSignupPassword;
    private TextInputEditText mSignupRePassword;
    private ArrayList<TextInputEditText> allTexts;
    private DatabaseReference myRef;
    private TextInputEditText mSignupJob;
    private TextInputEditText mSignupBday;
    private TextInputEditText mSignupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //region Deceleration
        myRef = FirebaseDatabase.getInstance().getReference();
        allTexts = new ArrayList<>();
        mSignupFullname = findViewById(R.id.signupFullname);
        mSignupEmail = findViewById(R.id.signupEmail);
        mSignupUsername = findViewById(R.id.signupUsername);
        mSignupPassword = findViewById(R.id.signupPassword);
        mSignupRePassword = findViewById(R.id.signupRePassword);
        mSignupJob = findViewById(R.id.signupJob);
        mSignupBday = findViewById(R.id.signupBday);
        mSignupGender = findViewById(R.id.signupGender);
        //endregion

        allTexts.add(mSignupFullname);
        allTexts.add(mSignupEmail);
        allTexts.add(mSignupUsername);
        allTexts.add(mSignupPassword);
        allTexts.add(mSignupRePassword);
        allTexts.add(mSignupJob);
        allTexts.add(mSignupBday);
        allTexts.add(mSignupGender);
    }

    public void signup(View view){

        //region adding to database
        if(!neededThings.isEmpty(allTexts)){
            if(mSignupPassword.getText().toString().equalsIgnoreCase(mSignupRePassword.getText().toString())){
                if(!searchUsername(mSignupUsername.getText().toString())){
                    if(!searchEmail(mSignupEmail.getText().toString())){
                        User user = new User();
                        user.setUid(neededThings.maximumID+1);
                        neededThings.maximumID++;
                        user.setEmail(mSignupEmail.getText().toString());
                        user.setFullName(mSignupFullname.getText().toString());
                        user.setPassword(mSignupPassword.getText().toString());
                        user.setUsername(mSignupUsername.getText().toString());
                        user.setBDay(mSignupBday.getText().toString());
                        user.setGender(mSignupGender.getText().toString());
                        user.setJob(mSignupJob.getText().toString());
                        myRef.child("Users").child(String.valueOf(user.getUid())).setValue(user);
                        neededThings.showToast(getApplicationContext(),"Registered Successfully");
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        neededThings.showToast(getApplicationContext(),"This Email is already registered");
                }
                else
                    neededThings.showToast(getApplicationContext(),"This Username is already registered");
            }
            else
                neededThings.showToast(getApplicationContext(),"Passwords are different");
        }
        //endregion

        //region show registered usernames
        /*
        String temp;
        temp="";
        for(User user: neededThings.users)
            temp+=user.getUsername()+" ";
        Toast.makeText(getApplicationContext(),temp,Toast.LENGTH_LONG).show();
        */
        //endregion

    }

    private boolean searchUsername(String username){
        for(User user: neededThings.users)
            if(user.getUsername().equalsIgnoreCase(username))
                return true;
        return false;
    }
    private boolean searchEmail(String email){
        for(User user: neededThings.users)
            if(user.getEmail().equalsIgnoreCase(email))
                return true;
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
        finish();
    }
}
