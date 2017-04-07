package br.com.fiap.trabalho.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import br.com.fiap.trabalho.model.Clube;

/**
 * Created by nicebadu on 18/03/2017.
 */

public class ClubeDAO {

    private DBOpenHelper banco;
    public static final String TABELA_CLUBES = "clube";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";

    public ClubeDAO(Context ctx) {
        this.banco = new DBOpenHelper(ctx);
    }

    public List<Clube> getAll() {
        List<Clube> clubes = new LinkedList<>();
        String query = "SELECT * FROM " + TABELA_CLUBES;
        SQLiteDatabase db = banco.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Clube clube = null;
        if (cursor.moveToFirst()) {
            do {
                clube = new Clube();
                clube.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                clube.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
                clubes.add(clube);
            } while (cursor.moveToNext());
        }
        return clubes;
    }

    public Clube getBy(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_NOME};
        String where = "id = " + id;
        Cursor cursor = db.query(true, TABELA_CLUBES, colunas, where, null, null,
                null, null, null);
        Clube clube = null;
        if(cursor != null)
        {
            cursor.moveToFirst();
            clube = new Clube();
            clube.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
            clube.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
        }
        return clube;
    }
}
