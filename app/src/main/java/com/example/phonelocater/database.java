package com.example.phonelocater;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
private  static  final String dbname="Phone_Locator.db";
    public database(@Nullable Context context) {
        super(context,dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q = "CREATE TABLE contact_name (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, number INTEGER UNIQUE)";
        sqLiteDatabase.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists contact_name");
        onCreate(sqLiteDatabase);
    }
    public boolean insert_data(String name,String number){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("Name",name);
        c.put("Number",number);
        long res=db.insert("contact_name",null,c);
        if(res==-1) return false;
        else
            return true;
    }
    public String searchNameByNumber(String number) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT name FROM contact_name WHERE number = '" + number + "'";
        Cursor cursor = db.rawQuery(query, null);

        String name = "";
        if (cursor.moveToFirst()) {
            name = cursor.getString(0);
        }
        cursor.close();
        return name;
    }
}
