package br.com.localcode60.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.localcode60.modelo.Marca;

public class MarcaDAO {

    private DAOHelper daoHelper;

    /**
     *
     * @param context
     */
    public MarcaDAO(Context context) {
        this.daoHelper = new DAOHelper(context);
    }

    /**
     *
     * @param idMarca
     * @return
     */
    public Marca buscaPorId(Integer idMarca){

        String sql = "SELECT * FROM Marca WHERE id_marca = ?";

        String argumentos [] = {idMarca.toString()};

        SQLiteDatabase database = daoHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, argumentos);

        Marca marca = null;
        while (cursor.moveToNext()){
            marca = new Marca();
            marca.setId(cursor.getInt(cursor.getColumnIndex("id_marca")));
            marca.setNome(cursor.getString(cursor.getColumnIndex("nm_marca")));
        }

        return marca;
    }

}
