package com.example.sqldemo2.sql;

import com.example.sqldemo2.schema.StudentSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLHelper extends SQLiteOpenHelper
{
    public MySQLHelper(Context context)
    {
        super(context, "student", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)     //to create database for the very first time
    {
        db.execSQL("CREATE TABLE "+ StudentSchema.Student._tableName +" ("+StudentSchema.Student._id+" INTEGER PRIMARY KEY AUTOINCREMENT , "+StudentSchema.Student._firstName+" VARCHAR(50), "+StudentSchema.Student._lastName+" VARCHAR(50), "+StudentSchema.Student._marks+" INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}

