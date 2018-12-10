package com.example.lenovo.e_commerce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.Data.neededThings;

public class ViewDetails extends AppCompatActivity {

    Product temp;
    int t;
    private TextView mDetailsName;
    private TextView mDetailsStock;
    private TextView mDetailsPrice;
    private Button mDetailsCart;
    boolean added;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        t = getIntent().getExtras().getInt("p",0);
        temp = neededThings.products.get(t);


        mDetailsName = findViewById(R.id.detailsName);
        mDetailsStock = findViewById(R.id.detailsStock);
        mDetailsPrice = findViewById(R.id.detailsPrice);
        mDetailsCart = findViewById(R.id.detailsCart);

        mDetailsName.setText("Name: " + temp.getName());
        mDetailsStock.setText("Available in Stock: " + temp.getQuantity());
        mDetailsPrice.setText("Price: " + temp.getPrice());
        if(!temp.isInCart())
            mDetailsCart.setText(R.string.addToCartTxt);
        else
            mDetailsCart.setText("Added");

        mDetailsCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!temp.isInCart()){
                    added = true;
                    mDetailsCart.setText("Added");
                    temp.setInCart(true);
                    neededThings.productsInCart.add(temp);
                    neededThings.showToast(getApplicationContext(),"Added");
                }
                else{
                    added = false;
                    temp.setInCart(true);
                    mDetailsCart.setText(R.string.addToCartTxt);
                    neededThings.productsInCart.remove(temp);
                    neededThings.showToast(getApplicationContext(),"Removed");
                }
            }
        });
    }
}
