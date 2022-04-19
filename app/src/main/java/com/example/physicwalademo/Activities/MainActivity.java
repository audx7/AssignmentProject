package com.example.physicwalademo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.physicwalademo.Adapters.ClientAdapter;
import com.example.physicwalademo.Fragment.HomeFragment;
import com.example.physicwalademo.Pojo.ClientModel;
import com.example.physicwalademo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backBtn;
    private TextView fragmentBtn;
    private int orientationDisplay = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backBtn = findViewById(R.id.backBtn);
        fragmentBtn = findViewById(R.id.titlebtn);
        backBtn.setOnClickListener(this);
        fragmentBtn.setOnClickListener(this);
        fetchingPrinterData();
    }


    private void fetchingPrinterData(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewData);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        String url="https://my-json-server.typicode.com/easygautam/data/users";
        ArrayList<ClientModel> clientModels =new ArrayList<>();
        RequestQueue queue= Volley.newRequestQueue(this);
       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {

               for (int i=0;i<=response.length();i++){
                   try {
                       JSONObject jsonObject=response.getJSONObject(i);
                       ClientModel clientModel = new ClientModel();
                       clientModel.setName(jsonObject.getString("name"));
                       clientModel.setId(jsonObject.getInt("id"));
                       clientModel.setProfileImage(jsonObject.getString("profileImage"));
                       clientModel.setQualification(Collections.singletonList(jsonObject.getString("qualification")));
                       clientModel.setSubjects(Collections.singletonList(jsonObject.getString("subjects")));
                       clientModels.add(clientModel);
                   }catch (Exception e){
                       e.printStackTrace();
                   }
               }
               ClientAdapter clientAdapter = new ClientAdapter(getApplicationContext(), clientModels);
               recyclerView.setAdapter(clientAdapter);
               clientAdapter.OnItemClick(new ClientAdapter.ClientListner() {
                   @Override
                   public void onClick(int position, ClientModel clientModel) {
                       switch (position){
                           case 0:
                               Toast.makeText(MainActivity.this, "Click On Robby For Fragment", Toast.LENGTH_LONG).show();
                               //Toast.makeText(MainActivity.this, "Position 0", Toast.LENGTH_SHORT).show();
                               break;
                           case 1:
                               Toast.makeText(MainActivity.this, "Click On Robby For Fragment", Toast.LENGTH_LONG).show();
                              // Toast.makeText(MainActivity.this, "Position 1", Toast.LENGTH_SHORT).show();
                               break;
                           case 2:
                               Toast.makeText(MainActivity.this, "Click On Robby For Fragment", Toast.LENGTH_LONG).show();
                              // Toast.makeText(MainActivity.this, "Position 2", Toast.LENGTH_SHORT).show();
                               break;
                           case 3:
                               Toast.makeText(MainActivity.this, "Position 3", Toast.LENGTH_SHORT).show();
                               break;
                           case 4:
                               Toast.makeText(MainActivity.this, "Position 4", Toast.LENGTH_SHORT).show();
                               break;
                           case 5:
                               Toast.makeText(MainActivity.this, "Position 5", Toast.LENGTH_SHORT).show();
                               break;
                           case 6:
                               Toast.makeText(MainActivity.this, "Position 6", Toast.LENGTH_SHORT).show();
                               break;
                           case 7:
                               Toast.makeText(MainActivity.this, "Position 7", Toast.LENGTH_SHORT).show();
                               break;
                       }
                   }
               });
           }
       }, new com.android.volley.Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backBtn:
                if (orientationDisplay==0){
                    orientationDisplay=1;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }else {
                    orientationDisplay=0;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
            case R.id.titlebtn:
                FragmentManager mgr = getSupportFragmentManager();
                FragmentTransaction trans = mgr.beginTransaction();
                HomeFragment memberDepositReceiv1 = new HomeFragment();
                trans.add(R.id.container, memberDepositReceiv1);
                trans.addToBackStack(null);
                trans.commit();
                break;
        }

    }
}