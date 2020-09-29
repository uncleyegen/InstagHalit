package com.halit.instaghalit;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by android on 9/29/20.
 */

public class VolleyHandler {

    private static VolleyHandler mVolleyHandler;
    private static Context mContext;
    private RequestQueue mRequestQueue;


    private VolleyHandler(Context mContext) {
        this.mContext = mContext;
        this.mRequestQueue = getRequestQueue();
    }


    public static synchronized VolleyHandler getInstance(Context context){

        if(mVolleyHandler == null){
            mVolleyHandler = new VolleyHandler(context);
        }

        return mVolleyHandler;
    }

    public RequestQueue getRequestQueue(){

        if(mRequestQueue == null){

            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;

    }


    public <T> void addRequetToQueue(Request<T> req){
        getRequestQueue().add(req);

    }


}
