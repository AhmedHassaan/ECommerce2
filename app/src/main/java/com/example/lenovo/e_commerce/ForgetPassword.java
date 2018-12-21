package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.lenovo.e_commerce.Data.neededThings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ForgetPassword extends AppCompatActivity {

    private TextInputEditText mForgetEmail;
    private TextInputEditText mForgetUsername;
    private TextInputEditText mForgetPassword;
    private TextInputEditText mForgetRePassword;
    ArrayList<TextInputEditText> allTexts;

    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        myRef = FirebaseDatabase.getInstance().getReference();
        mForgetEmail = findViewById(R.id.forgetEmail);
        mForgetUsername = findViewById(R.id.forgetUsername);
        mForgetPassword = findViewById(R.id.forgetPassword);
        mForgetRePassword = findViewById(R.id.forgetRePassword);
        allTexts = new ArrayList<>();
        allTexts.add(mForgetUsername);
        allTexts.add(mForgetEmail);
        allTexts.add(mForgetPassword);
        allTexts.add(mForgetRePassword);
    }


    public void changePassword(View view) {
        if(!neededThings.isEmpty(allTexts)){
            String newPassword = mForgetPassword.getText().toString();
            String newRePassword = mForgetRePassword.getText().toString();
            if(newPassword.equalsIgnoreCase(newRePassword)){
                int userIndex = neededThings.getUser(mForgetUsername.getText().toString()
                                                        ,mForgetEmail.getText().toString());
                if(userIndex!=-1){
                    Log.d("test","in update");
                    myRef.child("Users").child(String.valueOf(neededThings.users.get(userIndex).getUid())).
                            child("password").setValue(newPassword);
                    neededThings.users.get(userIndex).setPassword(newPassword);
                    neededThings.showToast(getApplicationContext(),"Updated Successfully");
                    Intent intent = new Intent(this,LogInActivity.class);
                    startActivity(intent);
                    finish();
                }else
                    neededThings.showToast(getApplicationContext(),"User Can't be found");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
        finish();
    }
}
