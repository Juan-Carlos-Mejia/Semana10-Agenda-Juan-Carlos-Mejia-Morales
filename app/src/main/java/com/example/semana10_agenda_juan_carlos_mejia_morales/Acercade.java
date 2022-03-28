package com.example.semana10_agenda_juan_carlos_mejia_morales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Acercade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
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

    private void listar() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuInsertar: nuevoContacto();
                return true;


            case R.id.mnu_listar: listar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}