package com.example.mytoken;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class doc_profile extends AppCompatActivity {
    JSONObject obj;
    JsonObjectRequest jsonObjectRequest;
    TextView dname,degree,speacial,curr,expected,maxt,feesd;
   // String ddegree,expri, location,spl,fees;int curr_to,texpected=0, max=0;
    Button add;
    RequestQueue requestQueue;
    int s,f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);
        f=1;
        s= getIntent().getIntExtra("id",-1);
        String name= getIntent().getStringExtra("name");
        add=findViewById(R.id.addtoken);
         dname=findViewById(R.id.doc_name);
        dname.setText(""+name);
        degree=findViewById(R.id.degree);
        speacial=findViewById(R.id.doc_special);
        curr=findViewById(R.id.curr_token);
        expected=findViewById(R.id.expect_token);
        maxt=findViewById(R.id.max);
        feesd=findViewById(R.id.fees);
        getdata(s);
    }
    public void getdata(int id)
    {

        HashMap<String,Integer> parmas=new HashMap<>();
        parmas.put("id",id);
        String url="http://192.168.29.32:3001/one";
          jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url,new JSONObject(parmas),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String ddegree = null,expri=null, location=null,spl=null,fees=null;int curr_to=0,texpected=0, max=0;
                try {
                    JSONArray jsonArray=response.getJSONArray("qu");
                    obj=jsonArray.getJSONObject(0);
                     ddegree=obj.getString("doc_degree");
                     expri=obj.getString("experience");
                       location=obj.getString("location");
                       spl=location=obj.getString("doc_speciality");
                     curr_to=obj.getInt("curr_token");
                       texpected=obj.getInt("expected");
                      max=obj.getInt("max_token");
                    fees=obj.getString("fees");
                } catch (JSONException e) {

                }
                degree.setText("Has a "+ddegree+" degree"+" &  "+expri+" years experience ");
                speacial.setText("Speciality in : "+spl);
                curr.setText(""+curr_to);
                expected.setText(""+texpected);
                maxt.setText(""+max);
                feesd.setText("Consultation fees is "+fees+" Rs");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
          requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
        if(f==1)
        {
            f=0;
       refresh(100,true);

        }
        else
        {}
    }
    public void refresh(int mill,boolean flag)
    {
        final Handler handler=new Handler();
        final   Runnable runnable=new Runnable() {
            @Override
            public void run() {

                getdata(s);
            }

        };
        handler.postDelayed(runnable,mill);

    }
}
