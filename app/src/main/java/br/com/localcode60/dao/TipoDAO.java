package br.com.localcode60.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.localcode60.modelo.Tipo;

public class TipoDAO {

    private DAOHelper daoHelper;

    /**
     *
     * @param context
     */
    public TipoDAO(Context context){
        this.daoHelper = new DAOHelper(context);
    }

    /**
     *
     * @param idTipo
     * @return
     */
    public Tipo buscarPorId(Integer idTipo){

        String sql = "SELECT * FROM Modelo WHERE id_modelo = ?";

        String argumentos [] = {idTipo.toString()};

        SQLiteDatabase database = daoHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql,argumentos);

        Tipo tipo = null;

        while (cursor.moveToNext()){

            tipo.setId(cursor.getInt(cursor.getColumnIndex("id_tipo")));
            tipo.setNome(cursor.getString(cursor.getColumnIndex("nm_tipo")));
            tipo.setDescricao(cursor.getString(cursor.getColumnIndex("ds_tipo")));
        }

        return tipo;
    }
}
