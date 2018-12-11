package com.example.lenovo.e_commerce;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        t = getIntent().getExtras().getString("pid","1");
//        temp = neededThings.products.get(t);
        temp = neededThings.getProduct(t);


        mDetailsName = findViewById(R.id.detailsName);
        mDetailsStock = findViewById(R.id.detailsStock);
        mDetailsPrice = findViewById(R.id.detailsPrice);
        mDetailsCart = findViewById(R.id.detailsCart);

        mDetailsName.setText("Name: " + temp.getName());
        mDetailsStock.setText("Available in Stock: " + temp.getQuantity());
        mDetailsPrice.setText("Price: " + temp.getPrice());

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        neededThings.productsInCart.add(temp);
                        neededThings.showToast(getApplicationContext(),"Added to cart");
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        if(neededThings.productsInCart.contains(temp))
            builder.setMessage("Already in cart do you want to add one more ?");
        else
            builder.setMessage("Do you want to buy this ?");

        mDetailsCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });
    }
}
