package com.example.myinventory2201738251;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "InventoryDB";
    private static final int DB_VERSION = 1;
    public static final String TABLE_INVENTORY = "Inventory";
    public static final String FIELD_INVENTORY_ID = "id";
    public static final String FIELD_INVENTORY_NAME = "name";
    public static final String FIELD_INVENTORY_DESC = "description" ;
    public static final String FIELD_INVENTORY_QUANTITY = "quantity";
    public static final String FIELD_INVENTORY_TIMESTAMP = "timestamp";
    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE IF NOT EXISTS " + TABLE_INVENTORY + "(" +
            FIELD_INVENTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_INVENTORY_NAME + " TEXT, " +
            FIELD_INVENTORY_DESC + " TEXT, " +
            FIELD_INVENTORY_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            FIELD_INVENTORY_QUANTITY + " TEXT);";

    private static final String DROP_TABLE_INVENTORY = "DROP TABLE IF EXISTS " + TABLE_INVENTORY + ";";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_PRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP THE EXISTING TABLES AND RECREATE AGAIN
        db.execSQL(DROP_TABLE_INVENTORY);
        onCreate(db);
    }


    public void insertInventory (String name, String desc, String qty){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put (FIELD_INVENTORY_NAME, name);
        contentValues.put(FIELD_INVENTORY_DESC,  desc);
        contentValues.put(FIELD_INVENTORY_QUANTITY, qty);
        sqLiteDatabase.insert(TABLE_INVENTORY, null, contentValues);

        sqLiteDatabase.close();


    }
    public Cursor getAllData (){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor mcursor = sqLiteDatabase.query(
                DBHelper.TABLE_INVENTORY, null, null, null, null, null, DBHelper.FIELD_INVENTORY_TIMESTAMP + " DESC");
//        Cursor mcursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_INVENTORY, null);
        return mcursor;
    }

}
