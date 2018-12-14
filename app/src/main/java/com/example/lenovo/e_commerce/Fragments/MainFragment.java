package com.example.lenovo.e_commerce.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lenovo.e_commerce.Adapters.MyFragmentAdapter;
import com.example.lenovo.e_commerce.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    final int PRODUCTS_BTN = 0;
    final int CATEGORY_BTN = 1;
    private Button mGetProList;
    private Button mGetCatList;
    private ViewPager mViewPager;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);
        mGetProList = viewRoot.findViewById(R.id.getProList);
        mGetCatList = viewRoot.findViewById(R.id.getCatList);
        mViewPager = viewRoot.findViewById(R.id.viewPager);

        setViewPager();

        // Inflate the layout for this fragment
        return viewRoot;
    }



    void setViewPager(){
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ProductFragment());
        fragmentList.add(new CategoryFragment());
        mViewPager.setAdapter(new MyFragmentAdapter(getFragmentManager(),fragmentList));
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

}
