package com.example.evstation;

import static com.example.evstation.Login.logid;

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

public class Booking extends AppCompatActivity implements JsonResponse{
    ListView l1;
    String[] center_id,name,place,val,date,time,stat,amnt,booking_id;
    //    public static String bank_id;
    SharedPreferences sh;
    public static String cenid,st,amount,bookid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvstaff);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cenid=center_id[position];
                bookid=booking_id[position];
                st=stat[position];
                amount=amnt[position];
                final CharSequence[] items = {"Pay Now","Cancel Request","Feedback","Cancel"};

                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(Booking.this);
                builder.setTitle("Select Option!");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        // TODO Auto-generated method stub
                        if (items[item].equals("Feedback"))
                        {
                            Intent il=new Intent(getApplicationContext(),feedback.class);
                            startActivity(il);
                        }
                        else if (items[item].equals("Cancel Request")) {
                            JsonReq JR=new JsonReq();
                            JR.json_response=(JsonResponse) Booking.this;
                            String q = "/User_cancel_booking?logid="+logid+"&bookid="+bookid;
                            q=q.replace(" ","%20");
                            JR.execute(q);

                            Intent il=new Intent(getApplicationContext(),Booking.class);
                            startActivity(il);

                        }
                        else if (items[item].equals("Pay Now"))
                        {
                            if (st.equalsIgnoreCase("booked")){
                                Intent il=new Intent(getApplicationContext(),user_pay_amount.class);
                                startActivity(il);

                            }
                            else if(st.equalsIgnoreCase("paid")){
                                Toast.makeText(Booking.this, "Already paid", Toast.LENGTH_SHORT).show();
                            }
                            else if(st.equalsIgnoreCase("rejected")){
                                Toast.makeText(Booking.this, "Your Request rejected", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Booking.this, "Your Request is not accepted yet", Toast.LENGTH_SHORT).show();
                            }

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
        JR.json_response=(JsonResponse) Booking.this;
        String q = "/User_view_booking?logid="+logid;
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
                    booking_id=new String[ja1.length()];
                    name=new String[ja1.length()];
                    place=new String[ja1.length()];
                    date=new String[ja1.length()];
                    time=new String[ja1.length()];
                    stat=new String[ja1.length()];
                    amnt=new String[ja1.length()];
                    val=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        center_id[i]=ja1.getJSONObject(i).getString("center_id");
                        booking_id[i]=ja1.getJSONObject(i).getString("booking_id");
                        name[i]=ja1.getJSONObject(i).getString("center_name");
                        place[i]=ja1.getJSONObject(i).getString("place");
                        date[i]=ja1.getJSONObject(i).getString("date");
                        time[i]=ja1.getJSONObject(i).getString("time");
                        stat[i]=ja1.getJSONObject(i).getString("status");
                        amnt[i]=ja1.getJSONObject(i).getString("amount");
                        val[i]="Station:  "+name[i]+"\nPlace : "+place[i]+"\nDate : "+date[i]+"\nTime : "+time[i]+"\nStatus : "+stat[i];


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