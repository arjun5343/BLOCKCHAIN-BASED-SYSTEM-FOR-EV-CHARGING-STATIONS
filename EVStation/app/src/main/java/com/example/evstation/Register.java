package com.example.evstation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;


public class Register extends AppCompatActivity implements JsonResponse{

    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    Button b1;
    String pwd,fname,lname,phone,email,gender,place,post,pin;
    RadioButton r1,r2,r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=(EditText) findViewById(R.id.place);
        e2=(EditText) findViewById(R.id.pswd);
        e3=(EditText) findViewById(R.id.fname);
        e4=(EditText) findViewById(R.id.lname);
        e5=(EditText) findViewById(R.id.post);
        e6=(EditText) findViewById(R.id.phone);
        e7=(EditText) findViewById(R.id.email);
        e8=(EditText) findViewById(R.id.pin);
        r1=(RadioButton) findViewById(R.id.radio_button_1);
        r2=(RadioButton) findViewById(R.id.radio_button_2);
        r3=(RadioButton) findViewById(R.id.radio_button_3);


        b1=(Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                place=e1.getText().toString();
                pwd=e2.getText().toString();
                fname=e3.getText().toString();
                lname=e4.getText().toString();
                post=e5.getText().toString();
                phone=e6.getText().toString();
                email=e7.getText().toString();
                pin=e8.getText().toString();
                if(r1.isChecked()){
                    gender="male";
                }
                else if(r2.isChecked()){
                    gender="female";
                }
                else{
                    gender="others";
                }
//                place=e8.getText().toString();
//                dist=e9.getText().toString();


                JsonReq jr = new JsonReq();
                jr.json_response = (JsonResponse) Register.this;
                String q = "/register?pwd=" + pwd +"&fname=" + fname + "&lname=" + lname + "&place=" + place +"&phone=" + phone + "&email=" + email + "&gender=" + gender+ "&post=" + post+ "&pin=" + pin;
                q.replace(" ", "%20");
                jr.execute(q);
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        // TODO Auto-generated method stub

        try
        {
            String status=jo.getString("status");
            Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
            if(status.equalsIgnoreCase("success"))
            {
                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }

            else
            {
                Toast.makeText(getApplicationContext(), "Not Successfull", Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Hai"+e, Toast.LENGTH_LONG).show();
        }

    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
}


