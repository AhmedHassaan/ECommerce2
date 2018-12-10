package com.example.lenovo.e_commerce.Data;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class neededThings {
    public static ArrayList<User> users;

    public static ArrayList<Product> products;
    public static ArrayList<Product> productsInCart;
    public static Map<String,Product> categories;
    public static int maximumID;
    public static User currentUser;

    public neededThings(){
        users = new ArrayList<>();
    }

    public static boolean isEmpty(ArrayList<TextInputEditText> texts){
        boolean isEmpty = false;
        for (TextInputEditText editTxt:texts) {
            if(TextUtils.isEmpty(editTxt.getText().toString())) {
                editTxt.setError("Can't be Empty");
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    public static void showToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }

    public static boolean isFound(String username,String password){
        for (User u :users) {
            if(u.getUsername().equalsIgnoreCase(username) && u.getPassword().equalsIgnoreCase(password))
                return true;
        }
        return false;
    }

    public static ArrayList<String> getArrayListString(){
        ArrayList<String> temp = new ArrayList<>();
        for (Product p :
                products) {
            temp.add(p.getName());
        }

        return temp;
    }


}
