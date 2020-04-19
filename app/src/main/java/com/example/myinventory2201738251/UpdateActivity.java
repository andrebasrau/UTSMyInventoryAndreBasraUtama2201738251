package com.example.myinventory2201738251;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", -1);

        final EditText et_name = findViewById(R.id.et_nameUpdate);
        final EditText et_desc = findViewById(R.id.et_descUpdate);
        final EditText et_quantity = findViewById(R.id.et_quantityUpdate);
        TextView back = findViewById(R.id.tv_backUpdate);
        Button submit = findViewById(R.id.btn_submitUpdate);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish ();
            }
        });

        et_name.setText(InvMain.IB.InventVector.get(position).getName());
        et_desc.setText(InvMain.IB.InventVector.get(position).getDesc());
        et_quantity.setText(InvMain.IB.InventVector.get(position).getQuantity());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String desc = et_desc.getText().toString();
                String qty = et_quantity.getText().toString();
                InvMain.IB.updateData(name, desc, qty, position);
                finish();

            }
        });


    }
}
