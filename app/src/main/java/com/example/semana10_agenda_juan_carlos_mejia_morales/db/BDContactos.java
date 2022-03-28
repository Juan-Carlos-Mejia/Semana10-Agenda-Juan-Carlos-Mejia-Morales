package com.example.semana10_agenda_juan_carlos_mejia_morales.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.example.semana10_agenda_juan_carlos_mejia_morales.entidades.Contactos;

public class BDContactos extends DBHelper{
    Context context;

    public BDContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String telefono, String email) {
        long datos = 0;
        try {
            DBHelper dbhelpe = new DBHelper(context);
            SQLiteDatabase db = dbhelpe.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("NOMBRE", nombre);
            valores.put("TELEFONO", telefono);
            valores.put("EMAIL", email);

            datos = db.insert(TABLE_CONTACTOS, null, valores);
        }
        catch (Exception ex) {
            ex.toString();
        }

        return datos;
    }

    public ArrayList<Contactos> mostrarContactos() {
        DBHelper dbhelpe = new DBHelper(context);
        SQLiteDatabase db = dbhelpe.getWritableDatabase();
        ArrayList<Contactos> listaContactos = new ArrayList<>();

        Cursor cursorContactos = null;
        Contactos contacto = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);
        if (cursorContactos.moveToFirst()) {
            do {
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo(cursorContactos.getString(3));

                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        }
        cursorContactos.close();
        return listaContactos;
    }

    public Contactos verContactos(int id) {
        DBHelper dbhelpe = new DBHelper(context);
        SQLiteDatabase db = dbhelpe.getWritableDatabase();

        Cursor cursorContactos = null;
        Contactos contacto = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1", null);
        if (cursorContactos.moveToFirst()) {

            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo(cursorContactos.getString(3));

        }
        cursorContactos.close();
        return contacto;
    }

    public boolean actualizarContacto(int id, String nombre, String telefono, String email) {

        boolean correcto = false;
        DBHelper dbhelpe = new DBHelper(context);
        SQLiteDatabase db = dbhelpe.getWritableDatabase();

        try {
            String query =  "UPDATE " + TABLE_CONTACTOS + " SET nombre = '" + nombre + "' , telefono = '" + telefono + "' , email = '" + email + "'  WHERE id =" + id;
            db.execSQL(query);
            correcto = true;
        }
        catch (Exception ex) {
            ex.toString();
            correcto = false;
        }
        finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarContacto(int id) {
        boolean correcto = false;
        DBHelper dbhelpe = new DBHelper(context);
        SQLiteDatabase db = dbhelpe.getWritableDatabase();
        try {
            String query = "DELETE FROM " + TABLE_CONTACTOS + " WHERE id =" + id;
            db.execSQL(query);
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
