package com.example.mytoken;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Doctor_List extends AppCompatActivity {
    public static String[] s;

     JSONObject my,obj;
   Runnable runnable;
   ListView docl;
    Gson gson;
    JSONArray jsonArray;
    String a;
    TextView textView;
    int idn[],curr_to[],expected[],max[];
    String speacial[],degree[],expri[],location[];

  static   ArrayAdapter<String> arrayAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__list);
        docl=findViewById(R.id.list);
        getdata();

        textView=findViewById(R.id.text);


//        try {
//            jsonArray=my.getJSONArray("");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        for(int i=0;i<jsonArray.length();i++)
//        {
//            try {
//                obj=jsonArray.getJSONObject(i);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            try {
//                a=" "+obj.getString("name");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//        textView.setText(a);

    }
    private void getdata()
    {
        String url="http://192.168.29.32:3000/list";
        JSONObject a=null;
      JsonObjectRequest  stringRequest=new JsonObjectRequest(Request.Method.POST, url,a, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray=response.getJSONArray("qu");
                    s=new String[jsonArray.length()];
                    idn=new int[jsonArray.length()];
//                    degree=new String[jsonArray.length()];
//                    speacial=new String[jsonArray.length()];
//                    expri=new String[jsonArray.length()];
//                    location=new String[jsonArray.length()];
//                    curr_to= new int[jsonArray.length()];
//                    expected= new int[jsonArray.length()];
//                    max= new int[jsonArray.length()];

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        obj=jsonArray.getJSONObject(i);
                        idn[i]=obj.getInt("id_no");
                        s[i]=obj.getString("doc_name");
//                        degree[i]=Doctor_List.obj.getString("doc_degree");
//                        expri[i]=Doctor_List.obj.getString("experience");
//                        location[i]=Doctor_List.obj.getString("location");
//                        curr_to[i]=Doctor_List.obj.getInt("curr_token");
//                        expected[i]=Doctor_List.obj.getInt("expected");
//                        max[i]=Doctor_List.obj.getInt("max_token");

                    }
                } catch (JSONException e) {}


             arrayAdapter  =new ArrayAdapter<String>(Doctor_List.this,android.R.layout.simple_list_item_1,s);
                arrayAdapter.notifyDataSetChanged();
                docl.setAdapter(arrayAdapter);
                docl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent doctor=new Intent(Doctor_List.this,doc_profile.class);
                doctor.putExtra("id",idn[position]);
                doctor.putExtra("name",s[position]);

                startActivity(doctor);


                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

        refresh(30000);

    }

    public void refresh(int mill)
    {
      final   Handler handler=new Handler();
      final   Runnable runnable=new Runnable() {
          @Override
          public void run() {

              getdata();
          }

      };
            handler.postDelayed(runnable,mill);

    }
    public  void makelist(final String[] listdata)
    {


    }
}
