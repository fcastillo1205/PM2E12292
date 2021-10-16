package com.example.pm2e12292;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pm2e12292.configuracion.SQLiteConexion;
import com.example.pm2e12292.configuracion.transaccion;
import com.example.pm2e12292.tablas.pais;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //variables globales
    SQLiteConexion conexion;
    Spinner spInicioPais;
    ArrayList<String> lstPais;
    ArrayList<pais> lst;
    Boolean campoVacio;
    String codigoPais;

    EditText txtInicioNombre,txtInicioNumero,txtInicioNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexion = new SQLiteConexion(this, transaccion.dbName , null, 1);
        spInicioPais = (Spinner) findViewById(R.id.spInicioPais);

        ObtenerListaPais();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lstPais);
        spInicioPais.setAdapter(adp);



        //botones
        Button btnInicioAgregarPais = (Button) findViewById(R.id.btnInicioAgregarPais);
        Button btnGuardarContacto = (Button) findViewById(R.id.btnGuardarContacto);
        Button btnInicioListaContactos = (Button) findViewById(R.id.btnInicioListaContactos);
        Button btnInicioRefrescar = (Button) findViewById(R.id.btnInicioRefrescar);

        //campos
        txtInicioNombre = (EditText) findViewById(R.id.txtInicioNombre);
        txtInicioNotas = (EditText) findViewById(R.id.txtInicioNotas);
        txtInicioNumero = (EditText) findViewById(R.id.txtInicioNumero);


        btnInicioAgregarPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityPais.class);
                startActivity(intent);
            }
        });

        btnGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarContacto();
            }
        });

        btnInicioRefrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refrescar();
            }
        });

        btnInicioListaContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityContactosSalvados.class);
                startActivity(intent);
            }
        });

        spInicioPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cadena = spInicioPais.getSelectedItem().toString();
                String [] partes = cadena.split("-");

                codigoPais = partes[0];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void agregarContacto() {
        SQLiteConexion conexion = new SQLiteConexion(this, transaccion.dbName, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        //validaciones
        if(txtInicioNombre.getText().toString().isEmpty()){
            mostrarAlerta("Alerta","Debe ingresar nombre","Ok");
            return;
        }else{
            campoVacio=false;
        }

        if(txtInicioNotas.getText().toString().isEmpty()){
            mostrarAlerta("Alerta","Debe ingresar Nota","Ok");
            return;
        }else {
            campoVacio=false;
        }

        //insertar pais
        ContentValues valores = new ContentValues();
        if(!campoVacio){
            valores.put(transaccion.contactoPais, spInicioPais.getSelectedItem().toString());
            valores.put(transaccion.contactoNombre, txtInicioNombre.getText().toString());
            valores.put(transaccion.contactoNumero, codigoPais + txtInicioNumero.getText().toString());
            valores.put(transaccion.contactoNota, txtInicioNotas.getText().toString());

            Long result = db.insert(transaccion.tableContacto,transaccion.idContacto,valores);
            Toast.makeText(getApplicationContext(), "Contacto guardado", Toast.LENGTH_LONG).show();
        }

        db.close();

        limpiarCampos();

    }

    private void mostrarAlerta(String titulo, String mensaje, String nombreBoton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton(nombreBoton, null);

        AlertDialog dialog = builder.create();
        dialog.show();
        campoVacio = true;


    }

    private void refrescar() {
        recreate();
    }

    private void limpiarCampos() {
      txtInicioNombre.setText("");
      txtInicioNumero.setText("");
      txtInicioNotas.setText("");
        refrescar();
    }

    private void ObtenerListaPais(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        pais list_pais = null;
        lst = new ArrayList<pais>();

        //cursor de  bd: nos apoya a recorrer la informacion de la tabla a la cual consltamos
        Cursor cursor = db.rawQuery("SELECT * FROM " + transaccion.tablePais , null);

        //recorrer la informacion del cursor
        while (cursor.moveToNext()){
            list_pais = new pais();
            list_pais.setIdPais(cursor.getInt( 0));
            list_pais.setNombrePais(cursor.getString( 1));
            list_pais.setCodigoPais(cursor.getString( 2));


            lst.add(list_pais);
        }

        cursor.close();
        fillCombo();
    }

    private void fillCombo() {
        lstPais = new ArrayList<String>();
        for (int i =0; i< lst.size(); i++){
            lstPais.add(
                    lst.get(i).getCodigoPais() + "-" +
                    lst.get(i).getNombrePais());
        }
    }
}