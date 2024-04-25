package com.example.evstation;

import static com.example.evstation.FAQ.answerp;
import static com.example.evstation.FAQ.questionp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class user_view_faq_answer extends AppCompatActivity {
TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_faq_answer);
        t1=(TextView) findViewById(R.id.textView4);
        t2=(TextView) findViewById(R.id.textView5);
        t1.setText(questionp);
        t2.setText(answerp);

    }
}