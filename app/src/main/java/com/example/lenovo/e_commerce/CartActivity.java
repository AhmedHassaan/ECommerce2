package com.example.lenovo.e_commerce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.e_commerce.Adapters.CartAdapter;
import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.Data.neededThings;

public class CartActivity extends AppCompatActivity {

    private ListView mCartList;
    private static TextView mTextView;
    private Button mCartBuyBtn;
    static float  total;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        total = 0;
        mCartList = findViewById(R.id.cartList);
        mTextView = findViewById(R.id.textView);
        mCartBuyBtn = findViewById(R.id.cartBuyBtn);
        adapter = new CartAdapter(getApplicationContext(),R.layout.cartview, neededThings.productsInCart);
        mCartList.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTotal();
    }

    public static void updateTotal(){
        total = 0;
        for (Product t:neededThings.productsInCart) {
            total += (Float.valueOf(t.getPrice()) * neededThings.noOfProductInCart.get(t));
        }
        mTextView.setText(String.valueOf(total));
    }
}
