package com.example.semana10_agenda_juan_carlos_mejia_morales;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana10_agenda_juan_carlos_mejia_morales.adaptadores.ListaContactosAdapter;
import com.example.semana10_agenda_juan_carlos_mejia_morales.db.BDContactos;
import com.example.semana10_agenda_juan_carlos_mejia_morales.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    FloatingActionButton fltNuevo;
    RecyclerView listContactos;
    ArrayList<Contactos> listArrayContactos;
    ListaContactosAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listContactos = findViewById(R.id.listaContactos);
        listContactos.setLayoutManager(new LinearLayoutManager(this));
        txtBuscar = findViewById(R.id.txtBuscar);
        BDContactos bdcontc = new BDContactos(getApplicationContext());

        listArrayContactos = new ArrayList<>();

        adaptador = new ListaContactosAdapter(bdcontc.mostrarContactos());
        listContactos.setAdapter(adaptador);

        fltNuevo = findViewById(R.id.fltIcoNuevo);


        fltNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NuevoContacto.class);
                startActivity(intent);
            }
        });
        txtBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);
        return true;
    }

    private void nuevoContacto() {
        Intent intent = new Intent(this, NuevoContacto.class);
        startActivity(intent);
    }

    private void Acercade() {
        Intent intent = new Intent(this, Acercade.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuInsertar: nuevoContacto();
                return true;

            case R.id.mnuAcercade: Acercade();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adaptador.filtrado(s);
        return false;
    }
}