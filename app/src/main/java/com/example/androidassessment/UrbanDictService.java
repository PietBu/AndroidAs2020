package com.example.androidassessment;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UrbanDictService {
    private static UrbanDictService mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context mCtx;

    private UrbanDictService(Context context)
    {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized UrbanDictService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UrbanDictService(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    private <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void getAllDescriptions(final ManyDescriptionVolleyCallback callback, String name) {
        String url = "http://api.urbandictionary.com/v0/define?term="+name;
        final ArrayList<String> descriptions = new ArrayList<String>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Descriptions", response);
                        //Convert to Pokemon
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonobject = jsonArray.getJSONObject(i);
                                String definition = jsonobject.getString("definition");
                                descriptions.add(definition);
                            }
                            if(descriptions.size() == 0)
                            {
                                descriptions.add("No descriptions found.");
                            }
                            callback.onSuccessResponse(descriptions);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ADebugTag", "Value: " + error.toString());
            }
        });

        UrbanDictService.getInstance(mCtx).addToRequestQueue(stringRequest);
    }

}
