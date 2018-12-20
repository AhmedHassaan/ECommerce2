package com.example.lenovo.e_commerce;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.Data.neededThings;

public class ViewDetails extends AppCompatActivity {

    Product temp;
    String t;
    private TextView mDetailsName;
    private TextView mDetailsStock;
    private TextView mDetailsPrice;
    private Button mDetailsCart;
    int inCart;
    private Button mDetailsMinus;
    private TextView mDetailsInCart;
    private Button mDetailsPlus;
    private LinearLayout mDetailsBuyOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        t = getIntent().getExtras().getString("pid","1");
        temp = neededThings.getProduct(t);
        inCart = 1;


        mDetailsName = findViewById(R.id.detailsName);
        mDetailsStock = findViewById(R.id.detailsStock);
        mDetailsPrice = findViewById(R.id.detailsPrice);
        mDetailsCart = findViewById(R.id.detailsCart);
        mDetailsMinus = findViewById(R.id.detailsMinus);
        mDetailsInCart = findViewById(R.id.detailsInCart);
        mDetailsPlus = findViewById(R.id.detailsPlus);
        mDetailsBuyOptions = findViewById(R.id.detailsBuyOptions);
        mDetailsInCart.setText(String.valueOf(temp.getQuantity()));
        mDetailsCart.setText("Add to cart");
        mDetailsName.setText("Name: " + temp.getName());
        mDetailsStock.setText("Available in Stock: " + temp.getQuantity());
        mDetailsPrice.setText("Price: " + temp.getPrice());
        mDetailsInCart.setText("1");
        mDetailsMinus.setVisibility(View.INVISIBLE);
        if(temp.getQuantity().equalsIgnoreCase("1"))
            mDetailsPlus.setVisibility(View.INVISIBLE);


        if(neededThings.productsInCart.contains(temp)) {
            mDetailsCart.setText("Already in Cart");
            mDetailsMinus.setVisibility(View.INVISIBLE);
            mDetailsPlus.setVisibility(View.INVISIBLE);
            mDetailsInCart.setVisibility(View.INVISIBLE);
        }
        else if(temp.getQuantity().equalsIgnoreCase("0"))
            mDetailsBuyOptions.setVisibility(View.INVISIBLE);

        startListeners();

    }

    private void startListeners(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        neededThings.productsInCart.add(temp);
                        int num = Integer.parseInt(mDetailsInCart.getText().toString());
                        neededThings.noOfProductInCart.put(temp,num);
                        neededThings.showToast(getApplicationContext(),"Added to cart");
                        mDetailsMinus.setVisibility(View.INVISIBLE);
                        mDetailsPlus.setVisibility(View.INVISIBLE);
                        mDetailsInCart.setVisibility(View.INVISIBLE);
                        mDetailsCart.setText("Already In Cart");
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.setMessage("Do you want to buy this ?");

        mDetailsCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!neededThings.productsInCart.contains(temp))
                    builder.show();
            }
        });

        mDetailsPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inCart = Integer.parseInt(mDetailsInCart.getText().toString()) +1;
                mDetailsInCart.setText(String.valueOf(inCart));
                mDetailsMinus.setVisibility(View.VISIBLE);
                if(inCart==Integer.valueOf(temp.getQuantity()))
                    mDetailsPlus.setVisibility(View.INVISIBLE);
            }
        });
        mDetailsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inCart = Integer.parseInt(mDetailsInCart.getText().toString()) -1;
                mDetailsInCart.setText(String.valueOf(inCart));
                mDetailsPlus.setVisibility(View.VISIBLE);
                if(inCart==1)
                    mDetailsMinus.setVisibility(View.INVISIBLE);
            }
        });
    }
}
