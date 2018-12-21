package com.example.lenovo.e_commerce;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

import com.example.lenovo.e_commerce.Data.User;
import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.Data.sharedPreferenceCustom;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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

    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;
    sharedPreferenceCustom shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //region Deceleration
        myRef = FirebaseDatabase.getInstance().getReference();
        shared = new sharedPreferenceCustom(getApplicationContext());
        allTexts = new ArrayList<>();
        mSignupFullname = findViewById(R.id.signupFullname);
        mSignupEmail = findViewById(R.id.signupEmail);
        mSignupUsername = findViewById(R.id.signupUsername);
        mSignupPassword = findViewById(R.id.signupPassword);
        mSignupRePassword = findViewById(R.id.signupRePassword);
        mSignupJob = findViewById(R.id.signupJob);
        mSignupBday = findViewById(R.id.signupBday);
        mSignupGender = findViewById(R.id.signupGender);
        mSignupBday.setFocusable(false);
        calendar = Calendar.getInstance();

        date  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                mSignupBday.setText(sdf.format(calendar.getTime()));
            }
        };
        mSignupBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SignUpActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
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
                        user.setEmail(mSignupEmail.getText().toString());
                        user.setFullName(mSignupFullname.getText().toString());
                        user.setPassword(mSignupPassword.getText().toString());
                        user.setUsername(mSignupUsername.getText().toString());
                        user.setBDay(mSignupBday.getText().toString());
                        user.setGender(mSignupGender.getText().toString());
                        user.setJob(mSignupJob.getText().toString());
                        myRef.child("Users").child(String.valueOf(user.getUid())).setValue(user);
                        shared.setLogedIn(user.getUsername());
                        neededThings.currentUser = user;
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
