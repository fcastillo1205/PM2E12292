package com.example.pm2e12292;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pm2e12292.configuracion.SQLiteConexion;
import com.example.pm2e12292.configuracion.transaccion;
import com.example.pm2e12292.tablas.contacto;

import java.util.ArrayList;


public class ActivityContactosSalvados extends AppCompatActivity {

    SQLiteConexion cnx;
    ListView lv;
    ArrayList<contacto> lista;
    ArrayList <String> ArregloContactos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_salvados);

        cnx=new SQLiteConexion(this, transaccion.dbName,null,1);
        lv=(ListView) findViewById(R.id.listviewcontact);

        obtener();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloContactos);
        lv.setAdapter(adp);

    }

    private void obtener() {
        SQLiteDatabase db = cnx.getReadableDatabase();
        contacto c;
        lista = new ArrayList<contacto>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + transaccion.tableContacto,null);

        while (cursor.moveToNext()){
            c = new contacto();
            c.setIdContacto(cursor.getInt(0));
            c.setContactoPais(cursor.getString(1));
            c.setContactoNombre(cursor.getString(2));
            c.setContactoNumero(cursor.getString(3));
            c.setContactoNota(cursor.getString(4));
            lista.add(c);

        }

        cursor.close();

        fil();
    }

    private void fil() {
        ArregloContactos = new ArrayList<String>();
        for(int i=0; i < lista.size(); i++){
            ArregloContactos.add(lista.get(i).getIdContacto() + "|" +
                                 lista.get(i).getContactoNombre() + "|" +
                                 lista.get(i).getContactoNumero());

        }
    }

}