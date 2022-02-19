package com.example.pm1e10626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pm1e10626.Contactos.Contactos;

public class ActivityPais extends AppCompatActivity {

    private TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);

        EditText pais = (EditText) findViewById(R.id.txtPaises);
        EditText codigo = (EditText) findViewById(R.id.txtCodigo);

        Button btn = (Button) findViewById(R.id.btnPasar);

        txtInfo = (TextView) findViewById(R.id.txtInfo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantalla = new Intent(getApplicationContext(), ActivityContacto.class);
                Contactos imp = new Contactos(
                        String.valueOf(pais.getText().toString()),
                        Integer.parseInt(codigo.getText().toString())
                );
                String impPais = imp.setNombre(String.valueOf(pais.getText().toString()));
                Integer impCodigo = imp.setCodigo(Integer.parseInt(codigo.getText().toString()));
                txtInfo.setText(impPais + " (" + impCodigo + ")");
                pantalla.putExtra("dato", txtInfo.getText().toString());
                startActivity(pantalla);
            }
        });
    }
}
