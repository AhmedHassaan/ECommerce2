package com.example.lenovo.e_commerce;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText mSignupFullname;
    private TextInputEditText mSignupEmail;
    private TextInputEditText mSignupUsername;
    private TextInputEditText mSignupPassword;
    private TextInputEditText mSignupRePassword;
    private ArrayList<TextInputEditText> allTexts;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        database = FirebaseDatabase.getInstance();
        allTexts = new ArrayList<>();
        mSignupFullname = findViewById(R.id.signupFullname);
        mSignupEmail = findViewById(R.id.signupEmail);
        mSignupUsername = findViewById(R.id.signupUsername);
        mSignupPassword = findViewById(R.id.signupPassword);
        mSignupRePassword = findViewById(R.id.signupRePassword);
        allTexts.add(mSignupFullname);
        allTexts.add(mSignupEmail);
        allTexts.add(mSignupUsername);
        allTexts.add(mSignupPassword);
        allTexts.add(mSignupRePassword);
    }

    public void signup(View view){
        if(!isEmpty(allTexts)){

        }
    }

    public boolean isEmpty(ArrayList<TextInputEditText> texts){
        boolean isEmpty = false;
        for (TextInputEditText editTxt:texts) {
            if(TextUtils.isEmpty(editTxt.getText().toString())) {
                editTxt.setError("Can't be Empty");
                isEmpty = true;
            }
        }
        return isEmpty;
    }
}
