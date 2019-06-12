package com.toast.test;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;

public class Utils {

    public static  Context  mcontext=null;

    public  static  void creatInstance(Context ctx){
        mcontext=ctx;
    }
    public static void ShowToast(Context cx,String message){
        Toast.makeText(cx, message, Toast.LENGTH_SHORT).show();
    }

    public static  HashMap getAllSms(){
        ContentResolver cr =mcontext.getContentResolver();
        HashMap map=new HashMap<String,String>();
        Cursor c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
        }
        int totalSMS = 0;
        if (c != null) {
            totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int j = 0; j < totalSMS; j++) {
                    String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.Sent.DATE));
                    String address = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.Sent.ADDRESS));
                    String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.Sent.BODY));
                    Date dateFormat= new Date(Long.valueOf(smsDate));

                    map.put(address,body);

                    c.moveToNext();
                }
            }

            c.close();

            return map;

        } else {
          return null;
        }
    }
}
