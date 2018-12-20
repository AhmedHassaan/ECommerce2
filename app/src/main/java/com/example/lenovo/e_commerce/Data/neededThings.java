package com.example.lenovo.e_commerce.Data;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class neededThings {
    public static ArrayList<User> users;

    public static ArrayList<Product> products;
    public static ArrayList<Product> productsInCart;
    public static ArrayList<Category> categories;
    public static Map<Category,ArrayList<Product>> catwithProduct;
    public static Map<Product,Integer> noOfProductInCart;
    public static int maximumID;
    public static User currentUser;
    public static ArrayList<Product> searchResult;



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

    public static void prepareCatWithPro(){
        ArrayList<Product> tempP;
        Category tempC;
        for(int i=0;i<categories.size();i++){
            tempC = categories.get(i);
            tempP = new ArrayList<>();
            for(int j=0;j<products.size();j++)
                if(tempC.getCID().equalsIgnoreCase(products.get(j).getCID()))
                    tempP.add(products.get(j));
            catwithProduct.put(tempC,tempP);
            Log.v("categories",tempC.getcName() + " " + tempP.size());
        }
    }

    public static Product getProduct(String id){
        Product p = null;
        for(Product temp:products)
            if(temp.getPID().equalsIgnoreCase(id)){
                p = temp;
                break;
            }
        return p;
    }

    public static void searchByName(String name){
        searchResult.clear();
        for(Product p:products)
            if(p.getName().contains(name))
                searchResult.add(p);
    }

    public static void searchByID(String id){
        searchResult.clear();
        for(Product p:products)
            if(p.getPID().equalsIgnoreCase(id))
                searchResult.add(p);
    }

    public static int getUser(String username, String email){
        for(int i=0;i<users.size();i++)
            if(users.get(i).getUsername().equalsIgnoreCase(username) && users.get(i).getEmail().equalsIgnoreCase(email))
                return i;
        return -1;
    }

    public static int getProductIndex(String id){
        for(int i=0;i<products.size();i++)
            if(products.get(i).getPID().equalsIgnoreCase(id)){
                return i;
            }
        return -1;
    }

    public static int getCategoryIndex(String id){
        for(int i=0;i<categories.size();i++)
            if(categories.get(i).getCID().equalsIgnoreCase(id)){
                return i;
            }
        return -1;
    }


}
