package com.example.physicwalademo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.physicwalademo.Fragment.HomeFragment;
import com.example.physicwalademo.Pojo.ClientModel;
import com.example.physicwalademo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {
    Context context;
    ArrayList<ClientModel> clientModels;
    Map<Integer, Boolean> checked = new HashMap<>();
    ClientListner listner;

    public ClientAdapter(Context context, ArrayList<ClientModel> clientModels) {
        this.clientModels=clientModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_personinfo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClientModel item=clientModels.get(position);
        holder.name.setText(item.getName());
        Glide.with(context).load(item.getProfileImage()).into(holder.image);

        for (int i=0; i<item.getSubjects().size();i++){
            String data= item.getSubjects().get(i).replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\\"","");
            holder.subject.setText(data);
        }
        for (int i=0; i<item.getQualification().size();i++){
            String data= item.getQualification().get(i).replaceAll("\\[","").replaceAll("\\]","").replaceAll("\\\"","");
            holder.qualification.setText(data);
        }
        
        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked.clear();
                checked.put(position, true);
                notifyDataSetChanged();
                Toast.makeText(context, "View More", Toast.LENGTH_SHORT).show();
                if (listner!=null){
                    ClientAdapter.this.listner.onClick(position,item);
                }
            }
        });


            holder.image.setBackgroundResource(R.drawable.selector_grocery_image);


    }

    public void OnItemClick(ClientListner listner){
        this.listner=listner;
    }

    @Override
    public int getItemCount() {
        return clientModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView name,subject,qualification;
         Button viewMore;
         ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.clientName);
            subject = itemView.findViewById(R.id.subjectName);
            qualification = itemView.findViewById(R.id.collegeName);
            viewMore = itemView.findViewById(R.id.button);
            image = itemView.findViewById(R.id.clientImage);


        }
    }

    public interface ClientListner{
        void onClick(int position, ClientModel clientModel);
    }
}
