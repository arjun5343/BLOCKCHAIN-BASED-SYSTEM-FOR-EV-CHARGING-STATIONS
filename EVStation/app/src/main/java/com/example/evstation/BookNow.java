package com.example.evstation;

import static com.example.evstation.Login.logid;
import static com.example.evstation.ViewChargindCenter.centerid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class BookNow extends AppCompatActivity implements JsonResponse{
EditText e1,e2,e3,e4,e5;
TextView t1;
Button b1;
String dt,tm,clevel,dlevel,esttime,bcapacity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
//        e1=(EditText)findViewById(R.id.date);
        e2=(EditText)findViewById(R.id.time);
        e3=(EditText)findViewById(R.id.clevel);
        e4=(EditText)findViewById(R.id.dlevel);
        e5=(EditText)findViewById(R.id.bc);
        t1=(TextView) findViewById(R.id.est);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dt=e1.getText().toString();
                tm=e2.getText().toString();
                clevel=e3.getText().toString();
                dlevel=e4.getText().toString();
                bcapacity=e5.getText().toString();
                if (tm.equalsIgnoreCase("") || !tm.matches("^([01][0-9]|2[0-3]):[0-5][0-9]$"))
                {
                    e2.setError("Enter time in HH:MM format");
                    e2.setFocusable(true);
                }
                else if (clevel.isEmpty() || !clevel.matches("[0-9]+") || Integer.parseInt(clevel) < 1 || Integer.parseInt(clevel) > 100) {
                    e3.setError("Enter a number between 1 and 100");
                    e3.setFocusable(true);
                }

                else if (dlevel.isEmpty() || !dlevel.matches("[0-9]+") || Integer.parseInt(dlevel) < 1 || Integer.parseInt(dlevel) > 100) {
                    e4.setError("Enter a number between 1 and 100");
                    e4.setFocusable(true);
                }

                else {
                    JsonReq jr = new JsonReq();
                    jr.json_response = (JsonResponse) BookNow.this;
                    String q = "/booknow?time=" + tm + "&center=" + centerid + "&logid=" + logid + "&clevel=" + clevel + "&dlevel=" + dlevel + "&bcapacity=" + bcapacity;
                    q.replace(" ", "%20");
                    jr.execute(q);
                }
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
                Toast.makeText(getApplicationContext(), "Booked Successfully", Toast.LENGTH_LONG).show();
                String esttym=jo.getString("estimated_time");
                t1.setText("Estimated Time:"+ esttym +"Hrs");


//                startActivity(new Intent(getApplicationContext(),userhome.class));
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
        startActivity(new Intent(getApplicationContext(),ViewChargindCenter.class));

    }


}