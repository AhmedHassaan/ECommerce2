package com.example.lenovo.e_commerce.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    private ListView mSearchList;
    ArrayAdapter<String> adapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_search, container, false);
        mSearchList = viewRoot.findViewById(R.id.searchList);
        adapter = new ArrayAdapter<>(getActivity(),R.layout.itemviewtest);
        mSearchList.setAdapter(adapter);


        // Inflate the layout for this fragment
        return viewRoot;
    }


    public void searchByName(String name){
        adapter.clear();
        neededThings.searchResult.clear();
        for(Product p:neededThings.products) {
            if (p.getName().contains(name)) {
                neededThings.searchResult.add(p);
                adapter.add(p.getName());
            }
        }
        adapter.notifyDataSetChanged();
    }


    public void searchByID(String id){
        adapter.clear();
        neededThings.searchResult.clear();
        for(Product p:neededThings.products) {
            if (p.getPID().equalsIgnoreCase(id)) {
                adapter.add(p.getName());
                neededThings.searchResult.add(p);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void clearLists(){
        adapter.clear();
        neededThings.searchResult.clear();
        adapter.notifyDataSetChanged();
    }



}
