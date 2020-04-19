package com.example.myinventory2201738251;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Vector;

import static com.example.myinventory2201738251.DBHelper.*;
//import static com.example.myinventory2201738251.InvMain.InventVector;

public class InventoryDB {
    private DBHelper dbHelper;
    private Context context;
    Vector <Inventory> InventVector = new Vector<>();
    public InventoryDB (Context context){
        this.context = context;
        dbHelper =new DBHelper(context);

    }

    public void getInventory(){
        Vector <Inventory> contoh = InventVector;
        InventVector.removeAll(contoh);
        SQLiteDatabase db =dbHelper.getReadableDatabase();

        Cursor mcursor = db.rawQuery("SELECT * FROM " + TABLE_INVENTORY, null);
//        Vector<Inventory> inventoryTemp = new Vector<>();
        mcursor.moveToFirst();
        Inventory invent;
        while (mcursor.moveToNext()){
            String id = mcursor.getString(mcursor.getColumnIndex(FIELD_INVENTORY_ID));
            String name= mcursor.getString(mcursor.getColumnIndex(FIELD_INVENTORY_NAME));
            String desc = mcursor.getString(mcursor.getColumnIndex(FIELD_INVENTORY_DESC));
            String quantity = mcursor.getString(mcursor.getColumnIndex(FIELD_INVENTORY_QUANTITY));
            invent = new Inventory(name, desc , quantity, id);
            InventVector.add(invent);
        }
        if(!InventVector.isEmpty()){
            Log.d("siapa lu", "getInventory: "+InventVector.get(0).getName());
        }
    }

    public void insertInventory (String name, String desc, String quantity){
        ContentValues contentValues = new ContentValues();
        contentValues.put (FIELD_INVENTORY_NAME, name);
        contentValues.put(FIELD_INVENTORY_DESC,  desc);
        contentValues.put(FIELD_INVENTORY_QUANTITY, quantity);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        sqLiteDatabase.insert(TABLE_INVENTORY, null, contentValues);
        sqLiteDatabase.close();
        getInventory();


    }

    public void updateData (String name, String desc, String quantity , int position){
        ContentValues contentValues = new ContentValues();
        contentValues.put (FIELD_INVENTORY_NAME, name);
        contentValues.put(FIELD_INVENTORY_DESC,  desc);
        contentValues.put(FIELD_INVENTORY_QUANTITY, quantity);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.update(TABLE_INVENTORY, contentValues, "id = ?", new String [] {InventVector.get(position).id});
        sqLiteDatabase.close();
        getInventory();
    }

    public Cursor getDataAll (){
        SQLiteDatabase db =dbHelper.getReadableDatabase();
        Cursor mcursor = db.rawQuery("SELECT * FROM " + TABLE_INVENTORY, null);
        return mcursor;
    }

}
