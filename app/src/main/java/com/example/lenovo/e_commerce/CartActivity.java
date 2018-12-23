package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.e_commerce.Adapters.CartAdapter;
import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.Data.neededThings;

public class CartActivity extends AppCompatActivity {

    private ListView mCartList;
    private static TextView mTextView;
    static float  total;
    static CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        total = 0;
        mCartList = findViewById(R.id.cartList);
        mTextView = findViewById(R.id.textView);
        adapter = new CartAdapter(getApplicationContext(),R.layout.cartview, neededThings.productsInCart);
        mCartList.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTotal();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cartmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        neededThings.productsInCart.clear();
        adapter.clear();
        adapter.notifyDataSetChanged();
        mTextView.setText("");
        return super.onOptionsItemSelected(item);
    }

    public static void updateTotal(){
        total = 0;
        for (Product t:neededThings.productsInCart) {
            total += (Float.valueOf(t.getPrice()) * neededThings.noOfProductInCart.get(t));
        }
        if(total == 0)
            mTextView.setText("");
        else
            mTextView.setText(String.valueOf(total));
    }


    public static void clear(){
        adapter.notifyDataSetChanged();
        updateTotal();
    }

    public void completeBuy(View view) {
        Intent intent = new Intent(this,CompleteOrder.class);
        startActivity(intent);
    }
}
