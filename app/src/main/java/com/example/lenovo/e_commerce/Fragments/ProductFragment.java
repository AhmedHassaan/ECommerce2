package com.example.lenovo.e_commerce.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.R;
import com.example.lenovo.e_commerce.ViewDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    private ListView mProductList;
    ArrayAdapter<String> arrayAdapter;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_product, container, false);
        mProductList = viewRoot.findViewById(R.id.productList);
        arrayAdapter = new ArrayAdapter<>(getActivity(),R.layout.itemviewtest);
        mProductList.setAdapter(arrayAdapter);
        fillAdapter();


        mProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ViewDetails.class);
                intent.putExtra("pid",neededThings.products.get(position).getPid());
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return viewRoot;
    }


    private void fillAdapter(){
        for(int i = 0; i<neededThings.products.size(); ++i)
            arrayAdapter.add(neededThings.products.get(i).getName());
        arrayAdapter.notifyDataSetChanged();
    }

}
