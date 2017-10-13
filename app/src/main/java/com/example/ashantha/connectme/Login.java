package com.example.ashantha.connectme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText pNumber;
    private Button save;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = this.getSharedPreferences("appdata", Context.MODE_PRIVATE);

        if(prefs.getString ("pnum",null) != null){
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }

            save = (Button) findViewById(R.id.btnSave);
            pNumber = (EditText) findViewById(R.id.pNumber);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                saveNumber();
            }
        });
    }

    public void saveNumber()
    {
        String phone = pNumber.getText().toString();
        prefs.edit().putString("pnum", phone).apply();
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
