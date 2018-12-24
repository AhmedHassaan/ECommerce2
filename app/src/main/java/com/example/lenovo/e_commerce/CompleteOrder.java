package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lenovo.e_commerce.Data.Order;
import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.Data.neededThings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CompleteOrder extends AppCompatActivity {

    Order order;
    private ImageButton mOrderAddress;
    private TextView mOrderDate;
    DatabaseReference myRef;
    private Button mCompleteCancel;
    private Button mCompleteDone;
    public static String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);
        mOrderAddress = findViewById(R.id.orderAddress);
        mOrderDate = findViewById(R.id.orderDate);
        mCompleteCancel = findViewById(R.id.completeCancel);
        mCompleteDone = findViewById(R.id.completeDone);
        order = new Order();
        myRef = FirebaseDatabase.getInstance().getReference();
        address = null;
        fillOrder();

        mCompleteDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(address != null){
                    order.setAddress(address);
                    myRef.child("Orders").child(String.valueOf(order.getOid())).setValue(order);
                    neededThings.productsInCart.clear();
                    CartActivity.clear();
                    finish();
                }
                else{
                    neededThings.showToast(getApplicationContext(),"Please choose address first");
                }
            }
        });

        mCompleteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mOrderAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void fillOrder(){
        Map<String,Integer> products = new HashMap<>();
        for(Product p: neededThings.productsInCart)
            products.put(p.getPid(),neededThings.noOfProductInCart.get(p));
        order.setOproducts(products);
        order.setOuid(neededThings.currentUser.getUid());
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        order.setOdate(sdf.format(new Date()));
//        neededThings.showToast(getApplicationContext(),order.getOdate());
        mOrderDate.setText("Date: " + order.getOdate());
        order.setOid(neededThings.orderMaxID+1);



    }

}
