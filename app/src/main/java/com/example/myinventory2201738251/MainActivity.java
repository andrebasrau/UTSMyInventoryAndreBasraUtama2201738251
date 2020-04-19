package com.example.myinventory2201738251;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements InvMain{
    static Context ctx;
    SQLiteDatabase db;
    static DBHelper dbHelper;
    RecyclerView rv_invent;
    static Vector<Inventory> InventVector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;

        TextView add = findViewById(R.id.tv_addBut);
        rv_invent = findViewById(R.id.rv_inventory);
//        dbHelper = new DBHelper(this);

//        InventoryAdapter mAdapter = new InventoryAdapter(this, dbHelper.getAllData());

        InvMain.IB.getInventory ();
//        showInventory();
//        if(!IB.InventVector.isEmpty()){
            loadInventory();
//        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                loadInventory();
//                showInventory();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadInventory();
    }

    public void loadInventory (){

        Log.d("siapa lu", "loadInventory: siapa lu");
        InventoryAdapter mAdapter = new InventoryAdapter();
        rv_invent.setLayoutManager(new LinearLayoutManager(this));
        rv_invent.setAdapter(mAdapter);
        mAdapter.setOnClickItemClickListener(new InventoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent (MainActivity.this, UpdateActivity.class);
                intent.putExtra("position", position);
                startActivity (intent);
            }
        });

    }





}
