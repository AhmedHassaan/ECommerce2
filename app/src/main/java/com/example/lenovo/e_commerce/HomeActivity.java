package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.e_commerce.Data.neededThings;

public class HomeActivity extends AppCompatActivity {

    private ListView mProductList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mProductList = findViewById(R.id.productList);
        arrayAdapter = new ArrayAdapter<>(getApplication(),R.layout.itemviewtest);
        mProductList.setAdapter(arrayAdapter);
        fillAdapter();


        mProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ViewDetails.class);
                intent.putExtra("p",position);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.cartBtn:
                intent = new Intent(this,CartActivity.class);
                break;
            case R.id.logoutBtn:
                intent = new Intent(this,LogInActivity.class);
                neededThings.productsInCart.clear();
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    private void fillAdapter(){
        for(int i=0;i<neededThings.products.size();++i)
            arrayAdapter.add(neededThings.products.get(i).getName());
        arrayAdapter.notifyDataSetChanged();
    }
}
