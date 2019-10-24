package com.dpridoy.foodrecipe;

import android.app.AlertDialog;
import android.content.Context;

import com.dpridoy.foodrecipe.api.MenuApi;
import com.dpridoy.foodrecipe.api.MenuClient;

public class Utils {

    public static MenuApi getApi(){
        return MenuClient.getMenuClient().create(MenuApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}
