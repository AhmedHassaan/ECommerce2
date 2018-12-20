package com.example.lenovo.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    final int VOICE_ACTION = 120;
    final int CAMERA_ACTION = 150;

    sharedPreferenceCustom shared;
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction(),
            ft2 = getSupportFragmentManager().beginTransaction();
    boolean up = false;
    SearchFragment searchFragment;
    MainFragment mainFragment;
    private FrameLayout mSearchFragment;
    private EditText mSearchEditTxt;
    private FrameLayout mMainFragment;

    boolean nameFlage = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        neededThings.prepareCatWithPro();
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
                nameFlage = true;
                if(!up)
                {
                    mSearchFragment.animate().yBy(-2000).setDuration(300).start();
                    mMainFragment.animate().yBy(2000).setDuration(300).start();
                    up = true;
                }
                return false;
            }
        });

        mSearchEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(nameFlage){
                    if(!TextUtils.isEmpty(mSearchEditTxt.getText().toString()))
                        searchFragment.searchByName(mSearchEditTxt.getText().toString());
                    else
                        searchFragment.clearLists();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        mSearchEditTxt.setText("");
        mSearchEditTxt.setFocusable(false);
        mSearchFragment.animate().yBy(2000).setDuration(300).start();
        mMainFragment.animate().yBy(-2000).setDuration(300).start();
    }

    public void voiceSearch(View view) {
        if(!up)
        {
            mSearchFragment.animate().yBy(-2000).setDuration(300).start();
            mMainFragment.animate().yBy(2000).setDuration(300).start();
            up = true;
        }
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        startActivityForResult(intent,VOICE_ACTION);
    }

    public void barcodeSearch(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == VOICE_ACTION && resultCode == RESULT_OK){
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            searchFragment.searchByName(result.get(0));
            nameFlage = false;
            mSearchEditTxt.setText(result.get(0));
        }
        else if(requestCode == CAMERA_ACTION && resultCode == RESULT_OK){
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            searchFragment.searchByID(result.get(0));
            nameFlage = false;
            mSearchEditTxt.setText(result.get(0));
        }
    }
}
