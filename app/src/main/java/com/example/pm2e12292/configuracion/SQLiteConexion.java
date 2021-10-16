package com.example.pm2e12292.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteConexion extends SQLiteOpenHelper {

    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version){
        super(context,dbname,factory,version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //crear tablas
        db.execSQL(transaccion.createTablePais);
        db.execSQL(transaccion.createTableContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //eliminar tablas
        db.execSQL(transaccion.dropTablePais);
        db.execSQL(transaccion.dropTableContacto);
    }
}
