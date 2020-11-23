package com.example.findme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyPositionAdapter extends BaseAdapter {
    Context context;
    ArrayList<Position> data;

    public MyPositionAdapter(Context context, ArrayList<Position> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Position getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // create view
        LayoutInflater inf = LayoutInflater.from(context);
        CardView card = (CardView) inf.inflate(R.layout.view_position,null);

        // text
        TextView tv_id = card.findViewById(R.id.tv_id_view);
        TextView tv_lon = card.findViewById(R.id.tv_lon_view);
        TextView tv_lat = card.findViewById(R.id.tv_lat_view);
        TextView tv_nbr = card.findViewById(R.id.tv_num_view);
        // btn
        Button btn_call = card.findViewById(R.id.btn_call_view);
        Button btn_map = card.findViewById(R.id.btn_map_view);
        Button btn_sms = card.findViewById(R.id.btn_sms_view);

        Position p = getItem(position);
        tv_id.setText("" +p.id);
        tv_lon.setText("" +p.longitutde);
        tv_lat.setText("" +p.lattitude);
        tv_nbr.setText("" +p.nbr);

        return card;
    }
}
