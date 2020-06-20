package com.example.androidassessment;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

class UrbanDictService {
    private Context context;
    private RequestQueue queue;
    private static UrbanDictService instance;

    // Singleton methods
    private UrbanDictService(Context c) {
        context = c;
        queue = getRequestQueue();
    }
    static synchronized UrbanDictService getInstance(Context c) {
        if (instance == null) {
            instance = new UrbanDictService(c);
        }
        return instance;
    }
    private RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }
    private <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    void getDescriptionsByName(final DescriptionCallback callback, String name) {
        String url = "https://api.urbandictionary.com/v0/define?term="+name;
        final ArrayList<String> descriptions = new ArrayList<String>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("Descriptions", response);
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray("list");
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
                Log.d("Error", "Value: " + error.toString());
            }
        });

        UrbanDictService.getInstance(context).addToRequestQueue(stringRequest);
    }
}