package com.example.findme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerPositionAdapter extends RecyclerView.Adapter<MyRecyclerPositionAdapter.MyViewHolder> {
    Context context;
    ArrayList<Position> data;


    public MyRecyclerPositionAdapter(Context context, ArrayList<Position> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(context);
        CardView card = (CardView) inf.inflate(R.layout.view_position,null);
        return new MyViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Position p = data.get(position);
        holder.tv_id.setText("" +p.id);
        holder.tv_lon.setText("" +p.longitutde);
        holder.tv_lat.setText("" +p.lattitude);
        holder.tv_nbr.setText("" +p.nbr);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private  TextView tv_id;
        private  TextView tv_lon;
        private  TextView tv_lat;
        private  TextView tv_nbr;
        private  Button btn_call;
        private  Button btn_map;
        private  Button btn_sms;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             tv_id = itemView.findViewById(R.id.tv_id_view);
             tv_lon = itemView.findViewById(R.id.tv_lon_view);
             tv_lat = itemView.findViewById(R.id.tv_lat_view);
             tv_nbr = itemView.findViewById(R.id.tv_num_view);
             btn_call = itemView.findViewById(R.id.btn_call_view);
             btn_map = itemView.findViewById(R.id.btn_map_view);
             btn_sms = itemView.findViewById(R.id.btn_sms_view);

             btn_call.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int i = getAdapterPosition();
                     Toast.makeText(context,"call "+data.get(i).nbr,Toast.LENGTH_LONG).show();
                     //todo: make the call
                     
                 }
             });
        }
    }
}
