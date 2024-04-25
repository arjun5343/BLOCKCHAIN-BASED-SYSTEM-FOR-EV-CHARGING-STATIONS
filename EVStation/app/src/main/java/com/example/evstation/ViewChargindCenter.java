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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewChargindCenter extends AppCompatActivity implements JsonResponse{
    ListView l1;
    Button b1;
    String[] center_id,name,place,val,phone,email,priceperunit,power;
    //    public static String bank_id;
    SharedPreferences sh;
    public static  String centerid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chargind_center);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvstaff);
        b1=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) ViewChargindCenter.this;
                String q = "/User_view_bunk_review";
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });
        startService(new Intent(getApplicationContext(),LocationService.class));

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                centerid=center_id[position];
                final CharSequence[] items = {"Book Now","Todays Bookings","View Faculties","Cancel"};

                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(ViewChargindCenter.this);
                builder.setTitle("Select Option!");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        // TODO Auto-generated method stub
                        if (items[item].equals("Book Now"))
                        {
                            Intent il=new Intent(getApplicationContext(),BookNow.class);
                            startActivity(il);
                        }
                        else if (items[item].equals("Todays Bookings"))
                        {
                            Intent il=new Intent(getApplicationContext(),TodaysBooking.class);
                            startActivity(il);
                        }
                        else if (items[item].equals("View Faculties"))
                        {
                            Intent il=new Intent(getApplicationContext(),ViewFaculty.class);
                            startActivity(il);
                        }
                        else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }

                    }
                });
                builder.show();
            }
        });


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) ViewChargindCenter.this;
        String q = "/User_view_center?lati="+LocationService.lati+"&longi="+LocationService.logi;
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
                    center_id=new String[ja1.length()];
                    name=new String[ja1.length()];
                    place=new String[ja1.length()];
                    phone=new String[ja1.length()];
                    email=new String[ja1.length()];
                    priceperunit=new String[ja1.length()];
                    power=new String[ja1.length()];
                    val=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        center_id[i]=ja1.getJSONObject(i).getString("center_id");
                        name[i]=ja1.getJSONObject(i).getString("center_name");
                        place[i]=ja1.getJSONObject(i).getString("place");
                        phone[i]=ja1.getJSONObject(i).getString("phone");
                        email[i]=ja1.getJSONObject(i).getString("email");
                        power[i]=ja1.getJSONObject(i).getString("power");
                        priceperunit[i]=ja1.getJSONObject(i).getString("priceperunit");
                        val[i]="Station:  "+name[i]+"\nPlace : "+place[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i]+"\nPrice/unit : "+priceperunit[i]+"\nPower : "+power[i];


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