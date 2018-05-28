package br.com.localcode60.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.localcode60.modelo.Modelo;

public class ModeloDAO {

    private DAOHelper daoHelper;

    /**
     *
     * @param context
     */
    public ModeloDAO(Context context){
        this.daoHelper = new DAOHelper(context);
    }

    /**
     *
     * @param idModelo
     * @return
     */
    public Modelo buscarPorId(Integer idModelo){

        String sql = "SELECT * FROM Modelo WHERE id_modelo = ?";

        String argumento[] = {idModelo.toString()};

        SQLiteDatabase database = daoHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, argumento);

        Modelo modelo = null;

        while(cursor.moveToNext()){
            modelo = new Modelo();
            modelo.setId(cursor.getInt(cursor.getColumnIndex("id_modelo")));
            modelo.setNome(cursor.getString(cursor.getColumnIndex("nm_modelo")));
            modelo.setModeloMarca(cursor.getInt(cursor.getColumnIndex("id_modelo_marca")));

        }
        return modelo;
    }

}

