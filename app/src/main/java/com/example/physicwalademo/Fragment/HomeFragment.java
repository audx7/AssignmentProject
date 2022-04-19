package com.example.physicwalademo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.physicwalademo.Activities.MainActivity;
import com.example.physicwalademo.Adapters.ClientAdapter;
import com.example.physicwalademo.Pojo.ClientModel;
import com.example.physicwalademo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


public class HomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        fetchingPrinterData(view);
        return view;
    }
    private void fetchingPrinterData(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewData);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        String url="https://my-json-server.typicode.com/easygautam/data/users";
        ArrayList<ClientModel> clientModels =new ArrayList<>();
        RequestQueue queue= Volley.newRequestQueue(getContext());
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
                ClientAdapter clientAdapter = new ClientAdapter(getContext(), clientModels);
                recyclerView.setAdapter(clientAdapter);
                clientAdapter.OnItemClick(new ClientAdapter.ClientListner() {
                    @Override
                    public void onClick(int position, ClientModel clientModel) {
                        switch (position){
                            case 0:
                                Toast.makeText(getContext(), "Position 0", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(getContext(), "Position 1", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(getContext(), "Position 2", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(getContext(), "Position 3", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Toast.makeText(getContext(), "Position 4", Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                Toast.makeText(getContext(), "Position 5", Toast.LENGTH_SHORT).show();
                                break;
                            case 6:
                                Toast.makeText(getContext(), "Position 6", Toast.LENGTH_SHORT).show();
                                break;
                            case 7:
                                Toast.makeText(getContext(), "Position 7", Toast.LENGTH_SHORT).show();
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
}