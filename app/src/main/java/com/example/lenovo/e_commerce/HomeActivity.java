package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.Data.sharedPreferenceCustom;
import com.example.lenovo.e_commerce.Fragments.MainFragment;
import com.example.lenovo.e_commerce.Fragments.SearchFragment;

public class HomeActivity extends AppCompatActivity {



    sharedPreferenceCustom shared;
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction(),
            ft2 = getSupportFragmentManager().beginTransaction();
    boolean up = false;
    SearchFragment searchFragment;
    MainFragment mainFragment;
    private FrameLayout mSearchFragment;
    private EditText mSearchEditTxt;
    private FrameLayout mMainFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        shared = new sharedPreferenceCustom(getApplicationContext());
        mSearchFragment = findViewById(R.id.searchFragment);
        mSearchEditTxt = findViewById(R.id.searchEditTxt);
        searchFragment = new SearchFragment();
        mainFragment = new MainFragment();
        mMainFragment = findViewById(R.id.mainFragment);
        mSearchFragment.setY(2000);
        mSearchEditTxt.setFocusable(false);
        ft.replace(R.id.mainFragment, mainFragment);
        ft2.replace(R.id.searchFragment,searchFragment);
        ft2.commit();
        ft.commit();

        mSearchEditTxt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mSearchEditTxt.setFocusableInTouchMode(true);
                if(!up)
                {
                    mSearchFragment.animate().yBy(-2000).setDuration(300).start();
                    mMainFragment.animate().yBy(2000).setDuration(300).start();
                    up = true;
                }
                return false;
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

    @Override
    public void onBackPressed() {
        if(!up){
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            finish();
        }
        up = false;
        mSearchEditTxt.setFocusable(false);
        mSearchFragment.animate().yBy(2000).setDuration(300).start();
        mMainFragment.animate().yBy(-2000).setDuration(300).start();
    }

}
