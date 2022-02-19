package com.example.pm1e10626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pm1e10626.Contactos.Contactos;
import com.example.pm1e10626.Transacciones.Transacciones;

import java.util.ArrayList;

public class ActivityContacto extends AppCompatActivity {

    SQLiteConexion conexion;
    ArrayList<String> ListaContactos;
    ArrayList<Contactos> lista;
    EditText pais;
    TextView txtPais;
    EditText nombre;
    EditText telefono;
    EditText nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        pais = (EditText) findViewById(R.id.edtPais);
        txtPais = (EditText) findViewById(R.id.txtPaises);
        nombre = (EditText) findViewById(R.id.txtNombre);
        telefono = (EditText) findViewById(R.id.txtTelefono);
        nota = (EditText) findViewById(R.id.txtNota);

        String dato = getIntent().getStringExtra("dato");
        pais.setText("" + dato);

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        Button btnContactos = (Button) findViewById(R.id.btnContactos);

        btnSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AgregarContacto();
            }
        });

        btnContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityVerContactos.class);
                startActivity(intent);
            }
        });

    }


    private void AgregarContacto() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.pais, pais.toString());
        valores.put(Transacciones.nombre, nombre.getText().toString());
        valores.put(Transacciones.telefono, telefono.getText().toString());
        valores.put(Transacciones.nota, nota.getText().toString());


        Long registro = db.insert(Transacciones.tablacontactos, Transacciones.id, valores);
        //toast
        Toast.makeText(getApplicationContext(), " INSERCCION EXITOSA " + registro.toString(), Toast.LENGTH_LONG).show();
        //cerrando base de datos
        db.close();

        ClearScreen();
    }

    private void ClearScreen() {
        pais.setText("");
        nombre.setText("");
        telefono.setText("");
        nota.setText("");
    }

    public void clickNew(View view) {
        Intent intent = new Intent(this, ActivityPais.class);
        startActivity(intent);
    }
}