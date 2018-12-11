package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.e_commerce.Adapters.MyFragmentAdapter;
import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.Data.sharedPreferenceCustom;
import com.example.lenovo.e_commerce.Fragments.CategoryFragment;
import com.example.lenovo.e_commerce.Fragments.ProductFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    final int PRODUCTS_BTN = 0;
    final int CATEGORY_BTN = 1;

    private Button mGetProList;
    private Button mGetCatList;
    private ViewPager mViewPager;
    sharedPreferenceCustom shared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mGetProList = findViewById(R.id.getProList);
        mGetCatList = findViewById(R.id.getCatList);
        mViewPager = findViewById(R.id.viewPager);
        shared = new sharedPreferenceCustom(getApplicationContext());
        setViewPager();
    }


    void setViewPager(){
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ProductFragment());
        fragmentList.add(new CategoryFragment());
        mViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),fragmentList));
        btnOnClick();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i==0){
                    mGetCatList.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    mGetProList.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                else if(i==1){
                    mGetProList.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    mGetCatList.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    void btnOnClick(){
        mGetProList.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        mGetCatList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetProList.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mGetCatList.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mViewPager.setCurrentItem(CATEGORY_BTN);
            }
        });

        mGetProList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetCatList.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mGetProList.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mViewPager.setCurrentItem(PRODUCTS_BTN);
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
                shared.setLogOut();
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

}
