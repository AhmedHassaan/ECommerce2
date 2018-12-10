package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.Data.User;
import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.Data.sharedPreferenceCustom;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    sharedPreferenceCustom shared;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        mProgressBar = findViewById(R.id.progressBar);
        myRef = database.getReference("Users");
        shared = new sharedPreferenceCustom(getApplicationContext());

        //region Write a message to the database
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello");
        */
        //endregion

        //region Read from the database
        /*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("a", "Value is: " + value);
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("b", "Failed to read value.", error.toException());

                Toast.makeText(getApplicationContext(),error.toException().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        */
        //endregion






        neededThings.maximumID = 0;
        neededThings.users = new ArrayList<>();
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                neededThings.users.add(user);
                neededThings.maximumID = user.getCID();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                neededThings.showToast(getApplicationContext(),"There is a connection error pls check your connection then try again");
            }
        };
        myRef.addChildEventListener(childEventListener);



        neededThings.productsInCart = new ArrayList<>();
        neededThings.products = new ArrayList<>();

        //region temp product add
        {
            Product p1 = new Product();
            p1.setName("aa");
            p1.setQuantity("2");
            p1.setPrice("20");
            neededThings.products.add(p1);

            p1 = new Product();
            p1.setName("dd");
            p1.setQuantity("5");
            p1.setPrice("10");
            neededThings.products.add(p1);

            p1 = new Product();
            p1.setName("bb");
            p1.setQuantity("1");
            p1.setPrice("50");
            neededThings.products.add(p1);

            p1 = new Product();
            p1.setName("cc");
            p1.setQuantity("0");
            p1.setPrice("30");
            neededThings.products.add(p1);

        }
        //endregion


        mProgressBar.setVisibility(ProgressBar.VISIBLE);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try { Thread.sleep(1500); }
                catch (InterruptedException e) {}

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                        Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                        startActivity(intent);
                    }
                });
            }
        };
        thread.start();

    }





}
