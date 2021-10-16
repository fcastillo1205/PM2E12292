package com.example.pm2e12292;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm2e12292.configuracion.SQLiteConexion;
import com.example.pm2e12292.configuracion.transaccion;

public class ActivityPais extends AppCompatActivity {
    //txt
    EditText nombrePais,codigoPais;

    boolean campoVacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);

        //EditText
        nombrePais = (EditText) findViewById(R.id.txtPaisNombre);
        codigoPais = (EditText) findViewById(R.id.txtPaisCodigo);

        //Botones
        Button btnGuardarPais = (Button) findViewById(R.id.btnGuardarPais);
        Button btnPaisListar = (Button) findViewById(R.id.btnPaisListar);

        btnGuardarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarPais();
            }
        });

        btnPaisListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityMantenimientoPais.class);
                startActivity(intent);
            }
        });
    }

    private void agregarPais() {
        SQLiteConexion conexion = new SQLiteConexion(this, transaccion.dbName, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        //validaciones
        if(nombrePais.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo de nombre de pais vacio", Toast.LENGTH_LONG).show();
            campoVacio=true;
            refrescar();
        }

        if(codigoPais.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo de codigo de pais vacio", Toast.LENGTH_LONG).show();
            campoVacio=true;
            refrescar();
        }

        //insertar pais
        ContentValues valores = new ContentValues();
        if(!campoVacio){
            valores.put(transaccion.nombrePais, nombrePais.getText().toString());
            valores.put(transaccion.codigoPais, "+" + codigoPais.getText().toString());

            Long result = db.insert(transaccion.tablePais,transaccion.idPais,valores);
            Toast.makeText(getApplicationContext(), "Pais guardado", Toast.LENGTH_LONG).show();
        }

        db.close();

        limpiarCampos();

    }
    private void refrescar() {
        recreate();
    }

    private void limpiarCampos() {
        nombrePais.setText("");
        codigoPais.setText("");
    }
}