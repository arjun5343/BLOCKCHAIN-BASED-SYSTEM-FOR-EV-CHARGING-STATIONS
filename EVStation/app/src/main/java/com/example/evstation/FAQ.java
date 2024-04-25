package com.example.evstation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class FAQ extends AppCompatActivity implements JsonResponse{
    ListView l1;
    String[] faq_id,question,answer,val;
        public static String questionp,answerp;
    SharedPreferences sh;
    public static  String faqid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.ff);


        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                faqid=faq_id[position];
                questionp=question[position];
                answerp=answer[position];
                startActivity(new Intent(getApplicationContext(),user_view_faq_answer.class));
//                final CharSequence[] items = {"Ask Now","Cancel"};
//
//                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(FAQ.this);
//                builder.setTitle("Select Option!");
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int item) {
//                        // TODO Auto-generated method stub
//                        if (items[item].equals("Ask Now"))
//                        {
//                            Intent il=new Intent(getApplicationContext(),BookNow.class);
//                            startActivity(il);
//                        }
//                        else if (items[item].equals("View Faculties"))
//                        {
//                            Intent il=new Intent(getApplicationContext(),ViewFaculty.class);
//                            startActivity(il);
//                        }
//                        else if (items[item].equals("Cancel")) {
//                            dialog.dismiss();
//                        }
//
//                    }
//                });
//                builder.show();
            }
        });


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) FAQ.this;
        String q = "/User_view_Question";
        q=q.replace(" ","%20");
        JR.execute(q);

    }

    @Override
    public void response(JSONObject jo) {
        // TODO Auto-generated method stub
        try{
            String method=jo.getString("method");

            if(method.equalsIgnoreCase("User_view_bank")){
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("check");
                    faq_id=new String[ja1.length()];
                    question=new String[ja1.length()];
                    answer=new String[ja1.length()];

                    val=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        faq_id[i]=ja1.getJSONObject(i).getString("faq_id");
                        question[i]=ja1.getJSONObject(i).getString("question");
                        answer[i]=ja1.getJSONObject(i).getString("answer");

                        val[i]="Question:  "+question[i];


                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,val);
                    l1.setAdapter(ar);
                    //startActivity(new Intent(getApplicationContext(),User_Post_Disease.class));
                }

                else

                {
                    Toast.makeText(getApplicationContext(), "No Data!!", Toast.LENGTH_LONG).show();

                }
            }

        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


    }





    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), userhome.class);
        startActivity(b);
    }

}