package com.example.toastframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.toast.test.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private final String TAG="MAIN_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.creatInstance(this);

        HashMap map;
        map=Utils.getAllSms();
        retrieveValuesFromListMethod(map);

    }
    public void retrieveValuesFromListMethod(Map map)
    {
        Set keys = map.keySet();
        Iterator itr = keys.iterator();

        String key;
        String value;
        while(itr.hasNext())
        {
            key = (String)itr.next();
            value = (String)map.get(key);
            Log.d(TAG,"sms data : "+key +" : "+value);
        }
    }

}
