package com.example.mytoken;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String AUTHORITY = "com.example.android.datasync.provider";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button click=findViewById(R.id.login);
//      ContentResolver  contentResolver=getContentResolver();
//        contentResolver.addPeriodicSync(new Account("mytoken","example.com"),AUTHORITY,new Bundle(),3000);3000
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Doctor_List.class);
                startActivity(intent);
            }
        });
        Button doc=findViewById(R.id.docenter);
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,doc_enter.class);
                startActivity(intent);
            }
        });
    }
}
