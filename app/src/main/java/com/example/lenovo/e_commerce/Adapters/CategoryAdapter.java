package com.example.lenovo.e_commerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.lenovo.e_commerce.Data.Category;
import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.R;

import java.util.ArrayList;
import java.util.Map;

public class CategoryAdapter extends BaseExpandableListAdapter {

    ArrayList<Category> categories;
    Map<Category,ArrayList<Product>> products;
    Context context;
    LayoutInflater inflater;

    public CategoryAdapter(Context context,ArrayList<Category> categories,Map<Category,ArrayList<Product>> products){
        this.categories = categories;
        this.products = products;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return products.get(categories.get(groupPosition)).size();
    }

    @Override
    public Category getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public Product getChild(int groupPosition, int childPosition) {
        return products.get(categories.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String catTitle = getGroup(groupPosition).getcName();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.expmainitem,null);
        }
        TextView mCatName = convertView.findViewById(R.id.catName);
        mCatName.setText(catTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String catProTitle = getChild(groupPosition,childPosition).getName();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.expchilditem,null);
        }
        TextView mCatProName = convertView.findViewById(R.id.catProName);
        mCatProName.setText(catProTitle);
        return convertView;
    }
}
