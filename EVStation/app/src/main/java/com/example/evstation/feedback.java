package com.example.evstation;

import static com.example.evstation.Booking.cenid;
import static com.example.evstation.Login.logid;
import static com.example.evstation.ViewChargindCenter.centerid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class feedback extends AppCompatActivity implements JsonResponse{
    EditText e1;
    Button b1;
    String feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        e1=(EditText)findViewById(R.id.feedback);

        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedback=e1.getText().toString();

                JsonReq jr = new JsonReq();
                jr.json_response = (JsonResponse) feedback.this;
                String q = "/feedback?msg=" + feedback +"&center=" + cenid+"&logid=" + logid ;
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
                Toast.makeText(getApplicationContext(), "Booked Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),userhome.class));
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