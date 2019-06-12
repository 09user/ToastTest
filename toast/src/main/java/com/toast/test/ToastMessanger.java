package com.toast.test;

import android.content.Context;
import android.widget.Toast;

public class ToastMessanger {

    public static void s(Context cx,String message){
        Toast.makeText(cx, message, Toast.LENGTH_SHORT).show();
    }
}
