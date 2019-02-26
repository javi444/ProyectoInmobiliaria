package com.example.inmobiliaria.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilToken {

    public static void setToken(Context mContext, String token){
        SharedPreferences sharedPreferences =
                mContext.getSharedPreferences(
                        "login",
                        Context.MODE_PRIVATE
                );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("JWT_KEY", token);
        editor.commit();
    }

    public static String getToken(Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                "login",
                Context.MODE_PRIVATE
        );

        String jwt = sharedPreferences
                .getString("JWT_KEY", null);
        return jwt;
    }

    public static String getEmailUser(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        return prefs.getString("emailUser", null);

    }

    public static String getNombreUser(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        return prefs.getString("nombreUser",null);
    }

    public static String getPhotoUser(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        return prefs.getString("fotoUser", null);
    }
}
