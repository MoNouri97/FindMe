package com.example.findme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    private Button btnqte,btnval;
    private TextView ednbr,edlong,edlat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            String body = bundle.getString("body");
        }

        btnval = findViewById(R.id.btnval_ajout);
        btnqte = findViewById(R.id.btnqte_ajout);

        ednbr = findViewById(R.id.ednum_ajout);
        edlong = findViewById(R.id.edlong_ajout);
        edlat = findViewById(R.id.edlat_ajout);

        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddActivity.this.finish();
            }
        });

        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nbr = ednbr.getText().toString();
                String lon = edlong.getText().toString();
                String lat = edlat.getText().toString();

                PositionManager manager = new PositionManager(AddActivity.this,PositionHelper.FILE,1);
                manager.insert(nbr,lon,lat);
            }
        });
    }
}