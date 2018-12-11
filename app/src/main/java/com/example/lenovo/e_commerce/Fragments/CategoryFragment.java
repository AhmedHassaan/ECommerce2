package com.example.lenovo.e_commerce.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.lenovo.e_commerce.Adapters.CategoryAdapter;
import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.R;
import com.example.lenovo.e_commerce.ViewDetails;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    CategoryAdapter adapter;
    ExpandableListView listView;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_category, container, false);
        adapter = new CategoryAdapter(getActivity(),neededThings.categories,neededThings.catwithProduct);
        listView = viewRoot.findViewById(R.id.catList);
        listView.setAdapter(adapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getActivity(),ViewDetails.class);
                intent.putExtra("pid",adapter.getChild(groupPosition,childPosition).getPID());
                startActivity(intent);
                return false;
            }
        });
        // Inflate the layout for this fragment
        return viewRoot;
    }

}
