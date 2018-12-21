package com.example.lenovo.e_commerce.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPreferenceCustom {
    private Context context;
    private SharedPreferences.Editor set;
    private SharedPreferences get;
    private String de = "N/A";
    
    public sharedPreferenceCustom(Context context){
        this.context = context;
        get = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        set = context.getSharedPreferences("user",context.MODE_PRIVATE).edit();
    }

    public boolean isRemeberME(){
        return get.getBoolean("logedin",false);
    }

    public void setRemeberMe(){
        set.putBoolean("logedin",true);
        set.commit();
    }

    public void setLogedIn(String username){
        set.putString("username",username);
        set.commit();
    }

    public void setLogOut(){
        set.putBoolean("logedin",false);
        set.remove("username");
        set.commit();
    }

    public User getCurrentUser(){
        User user = new User();
        String username = get.getString("username",de);
        for (User u :neededThings.users) {
            if(u.getUsername().equalsIgnoreCase(username)){
                user = u;
                break;
            }
        }
        return user;
    }
}
