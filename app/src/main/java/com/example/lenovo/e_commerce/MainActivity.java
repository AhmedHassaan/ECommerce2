package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        Intent intent = new Intent(this,LogInActivity.class);
        startActivity(intent);
    }
}
