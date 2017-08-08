package com.blovvme.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;

/**
 * Created by Blovvme on 8/7/17.
 */



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database";
    private static final String TABLE_NAME = "Information";
    private static final String COL1 = "ID";
    private static final String COL2 = "NAME";
    private static final String COL3 = "SURNAME";
    private static final String COL4 = "ADDRESS";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = (
                "CREATE TABLE " + TABLE_NAME + "(" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL2  + " TEXT," + COL3  + " TEXT,"
                        + COL4 + " TEXT)"
        );
//                "CREATE TABLE " + TABLE_NAME + "(" + COL2 + " TEXT," +
//                COL3  + " TEXT," + COL4  + " TEXT,"
//                + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT)"
//        );
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //To insert the data in the Database
    public boolean insertData(String name, String surname, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,address);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //Method to retrieve and show the data
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME,null);
        return cursor;
    }

    //Method to Update data in the Database
    public boolean updateData(String id,String name,String surname,String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,address);
        db.update(TABLE_NAME,contentValues, "ID = ?", new String[] { id } );
        return true;
    }

    //Method to Delete data from the Database
    public Integer dataDelete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?", new String[] { id });
    }

}//last key
