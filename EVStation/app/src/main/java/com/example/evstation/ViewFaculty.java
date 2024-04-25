package com.example.evstation;

import static com.example.evstation.ViewChargindCenter.centerid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewFaculty extends AppCompatActivity implements JsonResponse{
    ListView l1;
    String[] faculty_id,name,desc,val;
    //    public static String bank_id;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvstaff);


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) ViewFaculty.this;
        String q = "/User_view_faculty?center_id="+centerid;
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
                    faculty_id=new String[ja1.length()];
                    name=new String[ja1.length()];
                    desc=new String[ja1.length()];
                    val=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        faculty_id[i]=ja1.getJSONObject(i).getString("faculty_id");
                        name[i]=ja1.getJSONObject(i).getString("fac_name");
                        desc[i]=ja1.getJSONObject(i).getString("description");
                        val[i]="Name:  "+name[i]+"\nDescription : "+desc[i];


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