package com.example.lenovo.e_commerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.e_commerce.CartActivity;
import com.example.lenovo.e_commerce.Data.Product;
import com.example.lenovo.e_commerce.Data.neededThings;
import com.example.lenovo.e_commerce.R;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Product> {

    Context context;
    int resources;
    LayoutInflater inflater;
    ArrayList<Product> products;
    public CartAdapter(Context context, int resource, ArrayList<Product> products){
        super(context,resource,products);
        this.context = context;
        this.resources = resource;
        this.products = products;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder holder;

        if(convertView==null){
            convertView = inflater.inflate(resources,null);
            holder = new viewHolder();
            holder.removeFromCart = convertView.findViewById(R.id.removeFromCartBtn);
            holder.name = convertView.findViewById(R.id.cartProductName);
            holder.price = convertView.findViewById(R.id.cartProductPrice);
            convertView.setTag(holder);
        }
        else
            holder = (viewHolder) convertView.getTag();

        holder.name.setText(products.get(position).getName());
        holder.price.setText(products.get(position).getPrice());
        holder.removeFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neededThings.showToast(context,products.get(position).getName());
                removeInCart(position);
                products.remove(position);
                notifyDataSetChanged();
                CartActivity.updateTotal();

            }
        });
        return convertView;
    }

    static class viewHolder{
        TextView name,price;
        Button removeFromCart;
    }

    public void removeInCart(int position){
        int temp = neededThings.products.indexOf(products.get(position));
        Product p = neededThings.products.get(temp);
        p.setInCart(false);
        neededThings.products.set(temp,p);
    }
}
