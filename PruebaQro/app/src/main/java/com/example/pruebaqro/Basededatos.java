package com.example.pruebaqro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Basededatos extends SQLiteOpenHelper {

    final String Creartablaalumno="CREATE TABLE alumnos(id_alumno int primary key,nombreAlumno Text,idPadres int, idMadre int)";
    final String Creartablaescuela="CREATE TABLE escuela(id_esc int primary key, nombre_esc Text)";
    final String Creartablapadres="CREATE TABLE padres(id_padres int primary key, nombrePadre Text)";
    final String Creartablanoescuela="CREATE TABLE noescuela(id_no int primary key, id_alum int, id_escuela int)";
    final String Creartablamadres="CREATE TABLE madres(id_madres int primary key, nombreMadre Text)";

    public Basededatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Creartablaescuela);
        sqLiteDatabase.execSQL(Creartablaalumno);
        sqLiteDatabase.execSQL(Creartablapadres);
        sqLiteDatabase.execSQL(Creartablanoescuela);
        sqLiteDatabase.execSQL(Creartablamadres);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS escuela");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS padres");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS alumnos");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS noescuela");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS madres");
    onCreate(sqLiteDatabase);
    }



}
