package com.example.lenovo.e_commerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.R;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    Context context;
    int resources;
    LayoutInflater inflater;
    ArrayList<Product> products;

    public ProductAdapter(Context context, int resource, ArrayList<Product> products){
        super(context,resource,products);
        this.context = context;
        this.resources = resource;
        this.products = products;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final viewHolder holder;

        if(convertView==null){
            convertView = inflater.inflate(resources,null);
            holder = new viewHolder();
            holder.name = convertView.findViewById(R.id.productName);
            holder.stock = convertView.findViewById(R.id.noInStock);
            convertView.setTag(holder);
        }
        else
            holder = (viewHolder) convertView.getTag();

        holder.name.setText(products.get(position).getName());

        if(products.get(position).getQuantity().equalsIgnoreCase("0"))
            holder.stock.setText("Out of Stock");
        else
            holder.stock.setText(products.get(position).getQuantity());






        return convertView;
    }

    static class viewHolder{
        TextView name,stock;
    }

}
