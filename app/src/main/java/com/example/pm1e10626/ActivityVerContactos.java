package com.example.pm1e10626;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.pm1e10626.Contactos.Contactos;
import com.example.pm1e10626.Transacciones.Transacciones;

import java.util.ArrayList;

public class ActivityVerContactos extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listacontactos;
    ArrayList<Contactos> lista;
    ArrayList<String> ArregloContactos;
    EditText buscar, nombre, telefono, nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contactos);

        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        listacontactos = (ListView) findViewById(R.id.ListaContactos);

        ObtenerListaContactos();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloContactos);
        listacontactos.setAdapter(adp);

        listacontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String informacion = "ID: " + lista.get(position).getId() + "\n";
                informacion += "Nombre: " + lista.get(position).getNombre() + "\n";
                informacion += "Telefono: " + lista.get(position).getTelefono();

                Toast.makeText(getApplicationContext(), informacion,Toast.LENGTH_LONG).show();

                Intent Compartir = new Intent();
                Compartir.setAction(Intent.ACTION_SEND);
                Compartir.putExtra(Intent.EXTRA_TEXT, informacion);
                Compartir.setType("text/plain");

                Intent share = Intent.createChooser(Compartir, null);
                startActivity(share);

            }
        });
        nombre = (EditText)findViewById(R.id.txtnombre);
        telefono = (EditText)findViewById(R.id.txttelefono);
        nota = (EditText)findViewById(R.id.txtnota);
        buscar = (EditText) findViewById(R.id.txtBuscar);


        Button btnEliminar = (Button) findViewById(R.id.btnEliminar);
        Button btnActualizar = (Button) findViewById(R.id.btnActualizar);
        Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
        Button btnAtras = (Button) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityContacto.class);
                startActivity(intent);
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
            }
        });
    }

    /*public void onClick(View view){
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:99641468"));
        if(ActivityCompat.checkSelfPermission(ActivityVerContactos.this, Manifest.permission.CALL_PHONE)!=
                PackageManager.PERMISSION_GRANTED)
            return;
            startActivity(i);


    }*/
    private void ObtenerListaContactos(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Contactos listContactos = null;
        lista = new ArrayList<Contactos>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablacontactos, null);

        while (cursor.moveToNext()){
            listContactos = new Contactos();
            listContactos.setId(cursor.getInt(0));
            listContactos.setPais(cursor.getString(1));
            listContactos.setNombre(cursor.getString(2));
            listContactos.setTelefono(cursor.getString(3));
            listContactos.setNota(cursor.getString(4));


            lista.add(listContactos);
            fillList();
        }
    }//obtenerListaPersonas

    private void fillList() {
        ArregloContactos = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++){
            ArregloContactos.add(lista.get(i).getId() + "  "
                    +lista.get(i).getNombre() + " | "
                    +lista.get(i).getTelefono());
        }
    }//fillList

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {buscar.getText().toString()};
        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.tablacontactos, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato eliminado",Toast.LENGTH_LONG).show();
        ClearScreen();
    }//Eliminar

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {buscar.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, nombre.getText().toString());
        valores.put(Transacciones.telefono, telefono.getText().toString());
        valores.put(Transacciones.nota, nota.getText().toString());

        db.update(Transacciones.tablacontactos, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato actualizado",Toast.LENGTH_LONG).show();
        ClearScreen();

    }//Actualizar

    private void Buscar() {
        /*Parametros de configuracion de sentencia SELECT*/
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {buscar.getText().toString()};//parametro de la busquedad o el id
        String [] fields = {Transacciones.nombre,
                Transacciones.pais,
                Transacciones.telefono,
                Transacciones.nota
        };
        String wherecond = Transacciones.id + "=?";

        try {
            Cursor cdata = db.query(Transacciones.tablacontactos, fields, wherecond, params, null, null, null);
            cdata.moveToFirst();

            //buscar.setText(cdata.getString(0));
            nombre.setText(cdata.getString(0));
            telefono.setText(cdata.getString(2));
            nota.setText(cdata.getString(3));



            Toast.makeText(getApplicationContext(), "Consulta con exito",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado",Toast.LENGTH_LONG).show();
        }
    }//Buscar

    private void ClearScreen() {
        buscar.setText("");
        nombre.setText("");
        telefono.setText("");
        nota.setText("");
    }
}
