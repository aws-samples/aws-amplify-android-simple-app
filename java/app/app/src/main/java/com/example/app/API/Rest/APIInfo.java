package com.example.app.API.Rest;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.app.interfaces.SubjectListRunInterface;
import com.example.app.models.Subject;
import com.example.app.models.User;
import com.example.app.interfaces.UserInfoRunInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * *** APIInfo ***
 * Makes requests to the API using Volley and formating the response
 * with an interface passed by parameter. This interface should implement
 * a method called run.
 */
public class APIInfo {

    public APIInfo() {

    }

    /**
     * This method call a GET request from the API. Also a user token is needed.
     * This token is sent in the Authorization header. When the response is OK, you
     * get a JSONObject response and then parsed in a User object.
     *
     * @param appContext
     * @param userToken
     * @param userInfoRunInterface
     */
    public void getPersonalInfo(Context appContext, String userToken, UserInfoRunInterface userInfoRunInterface) {
        RequestQueue queue = Volley.newRequestQueue(appContext);
        String url = APIConstants.getAPIPersonalInfo();

        JSONObject obj = new JSONObject();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, obj,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        User u = User.parseUser(response);

                        // We call method run from the interface.
                        // This function is then implemented in the fragment when this method is called.
                        userInfoRunInterface.run(u);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ExampleAPP", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", userToken);

                return params;
            }
        };

        queue.add(jsObjRequest);
    }

    public void postPersonalInfo(Context appContext, String userToken, User u, UserInfoRunInterface userInfoRunInterface) {
        RequestQueue queue = Volley.newRequestQueue(appContext);
        String url = APIConstants.getAPIPersonalInfo();

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", u.getName());
        params.put("surname", u.getSurname());

        JSONObject obj = new JSONObject(params);

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                userInfoRunInterface.run(u);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", userToken);
                return params;
            }
        };

        queue.add(jsonObjRequest);

    }

    /**
     * Retrieves a list of Subjects. This is going to be called in the main fragment.
     */
    public void getSubjectList(Context appContext, SubjectListRunInterface subjectListRunInterface) {
        RequestQueue queue = Volley.newRequestQueue(appContext);
        String url = APIConstants.getAPISubjects();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<Subject> list = new ArrayList<>();
                        int total = response.length();

                        for( int i = 0 ; i < total ; i++ ){

                            JSONObject object = null;

                            try {
                                object = response.getJSONObject(i);
                                String subjectName = object.get("subject_name").toString();
                                String subjectID = object.get("subject_id").toString();
                                long subjectIDLong = Long.parseLong(subjectID);
                                list.add(new Subject(subjectName, subjectIDLong));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        subjectListRunInterface.run(list);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ExampleAPP", error.toString());
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }

}
