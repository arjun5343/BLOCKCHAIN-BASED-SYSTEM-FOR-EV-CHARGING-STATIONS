package com.example.evstation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class userhome extends AppCompatActivity {

    Button b1,b2,b3,b5,b6,b7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
//        b1=(Button) findViewById(R.id.btnbank);
        b2=(Button) findViewById(R.id.btnloan);
        b3=(Button) findViewById(R.id.btnloanreq);
//        b4=(Button) findViewById(R.id.feedback);
        b5=(Button) findViewById(R.id.complaint);
        b6=(Button) findViewById(R.id.logout);
        b7=(Button) findViewById(R.id.faq);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),ViewFaculty.class));
//            }
//        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),ViewChargindCenter.class));

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Booking.class));
            }
        });
//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),feedback.class));
//            }
//        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Complaint.class));
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FAQ.class));
            }
        });
    }

    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        startActivity(new Intent(getApplicationContext(),Login.class));

    }
}