package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.lenovo.e_commerce.Data.Category;
import com.example.lenovo.e_commerce.Data.Order;
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
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DatabaseReference refUsers,refCat,refPro,refOrder;
    sharedPreferenceCustom shared;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progressBar);
        refUsers = FirebaseDatabase.getInstance().getReference("Users");
        refCat = FirebaseDatabase.getInstance().getReference("Categories");
        refPro = FirebaseDatabase.getInstance().getReference("Products");
        refOrder = FirebaseDatabase.getInstance().getReference("Orders");
        shared = new sharedPreferenceCustom(getApplicationContext());

        //region Write a message to the database
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refUsers = database.getReference("message");
        refUsers.setValue("Hello");
        */
        //endregion

        //region Read from the database
        /*
        refUsers.addValueEventListener(new ValueEventListener() {
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
        neededThings.productsInCart = new ArrayList<>();
        neededThings.products = new ArrayList<>();
        neededThings.catwithProduct = new HashMap<>();
        neededThings.categories = new ArrayList<>();
        neededThings.searchResult = new ArrayList<>();
        neededThings.noOfProductInCart = new HashMap<>();
        neededThings.orderMaxID = 0;


        startListeners();

        Category c1;
        Product p1 = new Product();

//        Order o = new Order();
//        o.setAddress("a");
//        o.setOdate("1");
//        o.setOid("1");
//        o.setOuid("1");
//        o.addProduct("1","1");
//        myRef.child("Orders").push().setValue(o);


        //region add in firebase
        /*
        {


            c1 = new Category();
            c1.setCid("1");
            c1.setCname("Music");
            myRef.child("Categories").child(String.valueOf(c1.getCid())).setValue(c1);

            c1 = new Category();
            c1.setCid("2");
            c1.setCname("Games");
            myRef.child("Categories").child(String.valueOf(c1.getCid())).setValue(c1);

            c1 = new Category();
            c1.setCid("3");
            c1.setCname("Clothes");
            myRef.child("Categories").child(String.valueOf(c1.getCid())).setValue(c1);

            c1 = new Category();
            c1.setCid("4");
            c1.setCname("Films");
            myRef.child("Categories").child(String.valueOf(c1.getCid())).setValue(c1);


            p1 = new Product();
            p1.setPid("1");
            p1.setPcid("1");
            p1.setName("Demi Lovato - Unbroken");
            p1.setQuantity("0");
            p1.setPrice("20");
            myRef.child("Products").child(String.valueOf(p1.getPid())).setValue(p1);


            p1 = new Product();
            p1.setPid("2");
            p1.setPcid("1");
            p1.setName("Demi Lovato - Confident");
            p1.setQuantity("1");
            p1.setPrice("20");
            myRef.child("Products").child(String.valueOf(p1.getPid())).setValue(p1);


            p1 = new Product();
            p1.setPid("3");
            p1.setPcid("2");
            p1.setName("Diablo III");
            p1.setQuantity("5");
            p1.setPrice("15");
            myRef.child("Products").child(String.valueOf(p1.getPid())).setValue(p1);


            p1 = new Product();
            p1.setPid("4");
            p1.setPcid("2");
            p1.setName("Overwatch");
            p1.setQuantity("2");
            p1.setPrice("30");
            myRef.child("Products").child(String.valueOf(p1.getPid())).setValue(p1);

            p1 = new Product();
            p1.setPid("5");
            p1.setPcid("2");
            p1.setName("Call Of duty VI");
            p1.setQuantity("1");
            p1.setPrice("60");
            myRef.child("Products").child(String.valueOf(p1.getPid())).setValue(p1);


            p1 = new Product();
            p1.setPid("6");
            p1.setPcid("3");
            p1.setName("white T-Shirt");
            p1.setQuantity("4");
            p1.setPrice("10");
            myRef.child("Products").child(String.valueOf(p1.getPid())).setValue(p1);


            p1 = new Product();
            p1.setPid("7");
            p1.setPcid("4");
            p1.setName("AquaMan Bluray");
            p1.setQuantity("10");
            p1.setPrice("50");
            myRef.child("Products").child(String.valueOf(p1.getPid())).setValue(p1);


            p1 = new Product();
            p1.setPid("8");
            p1.setPcid("4");
            p1.setName("Avengers 3");
            p1.setQuantity("0");
            p1.setPrice("40");
            myRef.child("Products").child(String.valueOf(p1.getPid())).setValue(p1);


            p1 = new Product();
            p1.setPid("9");
            p1.setPcid("4");
            p1.setName("Black Panther");
            p1.setQuantity("4");
            p1.setPrice("30");
            myRef.child("Products").child(String.valueOf(p1.getPid())).setValue(p1);




            neededThings.prepareCatWithPro();

        }
        */
        //endregion


        mProgressBar.setVisibility(ProgressBar.VISIBLE);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try { Thread.sleep(2000); }
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


    private void startListeners(){
        ChildEventListener childEventListenerUser = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                neededThings.users.add(user);
                neededThings.maximumID = user.getUid();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                int index = neededThings.getUser(user.getUsername(),user.getEmail());
                neededThings.users.remove(index);
                neededThings.users.add(index,user);
                Log.d("change",user.getPassword());
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
        refUsers.addChildEventListener(childEventListenerUser);

        ChildEventListener childEventListenerProduct = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Product product = dataSnapshot.getValue(Product.class);
                neededThings.products.add(product);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Product product = dataSnapshot.getValue(Product.class);
                int index = neededThings.getProductIndex(product.getPid());
                neededThings.products.remove(index);
                neededThings.products.add(index,product);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refPro.addChildEventListener(childEventListenerProduct);

        ChildEventListener childEventListenerCategory = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Category category = dataSnapshot.getValue(Category.class);
                neededThings.categories.add(category);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Category category = dataSnapshot.getValue(Category.class);
                int index = neededThings.getCategoryIndex(category.getCid());
                neededThings.categories.remove(index);
                neededThings.categories.add(index,category);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refCat.addChildEventListener(childEventListenerCategory);

        ChildEventListener childEventListenerOrder = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Order o = dataSnapshot.getValue(Order.class);
                neededThings.orderMaxID = o.getOid();
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

            }
        };
        refOrder.addChildEventListener(childEventListenerOrder);

//        refOrder.orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Order o = dataSnapshot.getValue(Order.class);
//                if(o != null)
//                    neededThings.orderMaxID = o.getOid();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }





}
