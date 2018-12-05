package com.example.lenovo.e_commerce.Data;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class neededThings {
    public static ArrayList<User> users;

    public neededThings(){
        users = new ArrayList<>();
    }

    public static void showToast(Context context,String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}
