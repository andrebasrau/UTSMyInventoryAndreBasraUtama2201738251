package com.example.myinventory2201738251;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements InvMain{
    DBHelper dbHelper;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        TextView back = findViewById(R.id.tv_back);
        final EditText name = findViewById(R.id.et_name);
        final EditText desc = findViewById(R.id.et_desc);
        final EditText quantity = findViewById(R.id.et_quantity);
        submit = findViewById(R.id.btn_submit);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameString = name.getText().toString();
                String descString = desc.getText().toString();
                String qtyString = quantity.getText().toString ();
                nameString.trim();
                descString.trim();
                qtyString.trim();
                if (nameString.length() <= 0){
                    Toast.makeText(AddActivity.this, "name must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (descString.length()<=0){
                    Toast.makeText(AddActivity.this, "desc must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (qtyString.length() <= 0){
                    Toast.makeText(AddActivity.this, "quantity must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
//                Inventory inventory = new Inventory(nameString, descString, qtyString);
                InvMain.IB.insertInventory(nameString, descString, qtyString);

//                dbHelper = new DBHelper(MainActivity.ctx);


                finish();

            }
        });

    }






}
