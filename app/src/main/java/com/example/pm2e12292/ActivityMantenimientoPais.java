package com.example.pm2e12292;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pm2e12292.configuracion.SQLiteConexion;
import com.example.pm2e12292.configuracion.transaccion;
import com.example.pm2e12292.tablas.pais;

import java.util.ArrayList;

public class ActivityMantenimientoPais extends AppCompatActivity {
    //varioables globales
    SQLiteConexion conexion;
    ListView lstPaises;
    ArrayList<pais> lst;
    ArrayList arregloPais;

    EditText id,nombre,codigo;
    boolean campoVacio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento_pais);

        conexion = new SQLiteConexion(this, transaccion.dbName,null,1);
        lstPaises = (ListView) findViewById(R.id.listaPaises);

        getListaPaises();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arregloPais);
        lstPaises.setAdapter(adp);

        Button btnMantenimientoBuscar = (Button) findViewById(R.id.btnMantenimientoBuscar);
        Button btnMantenimientoEliminar = (Button) findViewById(R.id.btnMantenimientoEliminar);
        Button btnMantenimientoModifi = (Button) findViewById(R.id.btnMantenimientoModifi);

        id = (EditText) findViewById(R.id.txtMantenimientoIdPais);
        nombre = (EditText) findViewById(R.id.txtMantenimientoNombrePais);
        codigo = (EditText) findViewById(R.id.txtMantenimientoCodigoPais);

        btnMantenimientoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarPais();
            }
        });

        btnMantenimientoEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarPais();
            }
        });

        btnMantenimientoModifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarPais();
            }
        });


    }

    private void buscarPais() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String [] parametros = {id.getText().toString()};
        String [] campos = {transaccion.nombrePais,transaccion.codigoPais};
        String where = transaccion.idPais + "=?";

        if(id.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo de id de pais vacio", Toast.LENGTH_LONG).show();
            return;
        }else{
            try {
                Cursor data = db.query(transaccion.tablePais,campos,where,parametros,null,null,null);
                data.moveToFirst();
                nombre.setText(data.getString(0));
                codigo.setText(data.getString(1));

                Toast.makeText(getApplicationContext(), "Registro encontrado con exito",Toast.LENGTH_LONG).show();
            }catch (Exception ex){
                ClearScreen();
                Toast.makeText(getApplicationContext(), "Registro no encontrado",Toast.LENGTH_LONG).show();
            }
        }

    }

    private void ClearScreen() {
        id.setText("");
        nombre.setText("");
        codigo.setText("");
    }

    private void modificarPais() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};

        ContentValues valores = new ContentValues();

        if(id.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo de id de pais vacio", Toast.LENGTH_LONG).show();
            return;
        }else{
            valores.put(transaccion.nombrePais, nombre.getText().toString());
            valores.put(transaccion.codigoPais, codigo.getText().toString());

            db.update(transaccion.tablePais, valores, transaccion.idPais + "=?", params);
            Toast.makeText(getApplicationContext(), "Registro actualizado", Toast.LENGTH_LONG).show();
            ClearScreen();
            finish();
            refrescar();
        }

    }

    private void eliminarPais() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};
        String wherecond = transaccion.idPais + "=?";
        if(id.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo de id de pais vacio", Toast.LENGTH_LONG).show();
            return;
        }else{
            db.delete(transaccion.tablePais, wherecond, params);
            Toast.makeText(getApplicationContext(), "Registro eliminado", Toast.LENGTH_LONG).show();
            ClearScreen();
            refrescar();
        }
    }

    private void refrescar() {
        recreate();
    }

    private void getListaPaises() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        pais list_pais = null;
        lst = new ArrayList<pais>();

        //cursor de  bd: nos apoya a recorrer la informacion de la tabla a la cual consltamos
        Cursor cursor = db.rawQuery("SELECT * FROM " + transaccion.tablePais , null);

        //recorrer la informacion del cursor
        while (cursor.moveToNext()) {
            list_pais = new pais();
            //list_pais.setIdPais(cursor.getInt(0));
            list_pais.setNombrePais(cursor.getString(1));
            list_pais.setCodigoPais(cursor.getString(2));

            lst.add(list_pais);
        }
        
        cursor.close();
        
        fillList();
    }

    private void fillList() {
        arregloPais = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++){

            arregloPais.add(lst.get(i).getIdPais() + " | "
                    +lst.get(i).getNombrePais() + " | "
                    +lst.get(i).getCodigoPais());
        }
    }
}