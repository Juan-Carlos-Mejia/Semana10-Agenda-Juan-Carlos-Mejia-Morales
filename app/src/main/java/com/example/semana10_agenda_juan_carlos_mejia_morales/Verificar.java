package com.example.semana10_agenda_juan_carlos_mejia_morales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.semana10_agenda_juan_carlos_mejia_morales.db.BDContactos;
import com.example.semana10_agenda_juan_carlos_mejia_morales.entidades.Contactos;

public class Verificar extends AppCompatActivity{
    EditText edtNombre, edtTelefono, edtCorreo;
    Button btnActualizar;
    Contactos contacto;
    FloatingActionButton fltEdit, flDelete;
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

        fltEdit = findViewById(R.id.fltIcoEdit);
        flDelete = findViewById(R.id.fltIcoDelete);

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
        BDContactos bdContactos = new BDContactos(Verificar.this);
        contacto = bdContactos.verContactos(id);
        if (contacto != null) {

            edtNombre.setText(contacto.getNombre());
            edtTelefono.setText(contacto.getTelefono());
            edtCorreo.setText(contacto.getCorreo());
            btnActualizar.setVisibility(View.INVISIBLE);
            edtNombre.setInputType(InputType.TYPE_NULL);
            edtTelefono.setInputType(InputType.TYPE_NULL);
            edtCorreo.setInputType(InputType.TYPE_NULL);
        }

        fltEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Verificar.this, Editar.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        flDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Verificar.this);
                builder.setMessage("¿Desea eliminar el registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (bdContactos.eliminarContacto(id)) {
                                    Lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Lista();
                            }
                        }).show();
            }
        });
    }

    public  void Lista() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}