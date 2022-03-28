package com.example.semana10_agenda_juan_carlos_mejia_morales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semana10_agenda_juan_carlos_mejia_morales.db.BDContactos;
import com.example.semana10_agenda_juan_carlos_mejia_morales.entidades.Contactos;

public class Editar  extends AppCompatActivity {

    EditText edtNombre, edtTelefono, edtCorreo;
    Button btnActualizar;
    Contactos contacto;
    int id = 0;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);

        edtNombre = findViewById(R.id.edtxNombre);
        edtTelefono = findViewById(R.id.edtxTelefono);
        edtCorreo = findViewById(R.id.edtxCorreo);
        btnActualizar = findViewById(R.id.btnActualizar);

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                id = Integer.parseInt(null);
            } else {
                id = extra.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        BDContactos bdContactos = new BDContactos(Editar.this);
        contacto = bdContactos.verContactos(id);
        if (contacto != null) {

            edtNombre.setText(contacto.getNombre());
            edtTelefono.setText(contacto.getTelefono());
            edtCorreo.setText(contacto.getCorreo());

        }
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtNombre.getText().toString().equals("") && !edtTelefono.getText().toString().equals("") && !edtCorreo.getText().toString().equals("")) {
                    correcto = bdContactos.actualizarContacto(id, edtNombre.getText().toString(), edtTelefono.getText().toString(), edtCorreo.getText().toString());
                    if (correcto) {
                        Toast.makeText(Editar.this, "Contacto actualizado", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(Editar.this, "Contacto no actualizado", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Editar.this, "Los campos son necesarios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro() {
        Intent intent = new Intent(this, Verificar.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
