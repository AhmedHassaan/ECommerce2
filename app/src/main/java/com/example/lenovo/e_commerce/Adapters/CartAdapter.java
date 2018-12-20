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
    String priceTxt;
    int total,num;
    public CartAdapter(Context context, int resource, ArrayList<Product> products){
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
            holder.removeFromCart = convertView.findViewById(R.id.removeFromCartBtn);
            holder.name = convertView.findViewById(R.id.cartProductName);
            holder.price = convertView.findViewById(R.id.cartProductPrice);
            holder.plus = convertView.findViewById(R.id.cartPlus);
            holder.minus = convertView.findViewById(R.id.cartMinus);
            convertView.setTag(holder);
        }
        else
            holder = (viewHolder) convertView.getTag();

        total = Integer.parseInt(products.get(position).getQuantity());
        num = neededThings.noOfProductInCart.get(products.get(position));
        if(total == num)
            holder.plus.setVisibility(View.INVISIBLE);
        if(num == 1)
            holder.minus.setVisibility(View.INVISIBLE);

        holder.name.setText(products.get(position).getName());
        priceTxt = String.valueOf(neededThings.noOfProductInCart.get(products.get(position))) + "X "
                +String.valueOf(products.get(position).getPrice());
        holder.price.setText(priceTxt);
        holder.removeFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neededThings.showToast(context,products.get(position).getName());
                removeInCart(position);
                notifyDataSetChanged();
                CartActivity.updateTotal();

            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = neededThings.noOfProductInCart.get(products.get(position)) + 1;
                neededThings.noOfProductInCart.put(products.get(position),num);
                neededThings.showToast(getContext(),String.valueOf(num));
                priceTxt = String.valueOf(neededThings.noOfProductInCart.get(products.get(position))) + "X "
                        +String.valueOf(products.get(position).getPrice());
                holder.price.setText(priceTxt);
                CartActivity.updateTotal();
                total = Integer.parseInt(products.get(position).getQuantity());
                holder.minus.setVisibility(View.VISIBLE);
                if(total == num)
                    holder.plus.setVisibility(View.INVISIBLE);
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = neededThings.noOfProductInCart.get(products.get(position)) - 1;
                neededThings.noOfProductInCart.put(products.get(position),num);
                neededThings.showToast(getContext(),String.valueOf(num));
                priceTxt = String.valueOf(neededThings.noOfProductInCart.get(products.get(position))) + "X "
                        +String.valueOf(products.get(position).getPrice());
                holder.price.setText(priceTxt);
                CartActivity.updateTotal();
                total = Integer.parseInt(products.get(position).getQuantity());
                holder.plus.setVisibility(View.VISIBLE);
                if(num == 1)
                    holder.minus.setVisibility(View.INVISIBLE);
            }
        });
        return convertView;
    }

    static class viewHolder{
        TextView name,price,plus,minus;
        Button removeFromCart;
    }

    public void removeInCart(int position){
        neededThings.productsInCart.remove(position);
    }
}
