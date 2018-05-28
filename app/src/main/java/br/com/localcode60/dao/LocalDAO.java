package br.com.localcode60.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.localcode60.modelo.Local;
import br.com.localcode60.modelo.Produto;

public class LocalDAO {

    private DAOHelper daohelper;

    /**
     *
     * @param context
     */
    public LocalDAO(Context context) {
        this.daohelper = new DAOHelper(context);
    }

    /**
     *
     * @param idLocal
     * @return
     */
    public Local buscarPorId(Integer idLocal) {

        String sql = "SELECT * FROM Local WHERE id_local = ?";

        String argumentos[] = {idLocal.toString()};

        SQLiteDatabase database = daohelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, argumentos);

        Local local = null;

        while (cursor.moveToNext()) {

            local = new Local();

            local.setId(cursor.getInt(cursor.getColumnIndex("id_local")));
            local.setPrateleira(cursor.getInt(cursor.getColumnIndex("vl_prateleira")));
            local.setColuna(cursor.getInt(cursor.getColumnIndex("vl_coluna")));
            local.setEstante(cursor.getString(cursor.getColumnIndex("nm_estante")));
            local.setLocalProduto(cursor.getInt(cursor.getColumnIndex("id_local_produto")));
        }

        return local;
    }

    /**
     *
     * @param idLocalProduto
     * @return
     */
    public Local buscarPorIdProduto(Integer idLocalProduto) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT * ");
        stringBuilder.append("FROM Local l ");
        stringBuilder.append("INNER JOIN Produto p ON p.id_produto = l.id_local_produto ");
        stringBuilder.append("WHERE l.id_local_produto = ?");

        String argumentos[] = {idLocalProduto.toString()};

        SQLiteDatabase database = daohelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(stringBuilder.toString(), argumentos);

        Local local = null;

        while (cursor.moveToNext()) {
            local = new Local();

            local.setId(cursor.getInt(cursor.getColumnIndex("id_local")));
            local.setPrateleira(cursor.getInt(cursor.getColumnIndex("vl_prateleira")));
            local.setColuna(cursor.getInt(cursor.getColumnIndex("vl_coluna")));
            local.setEstante(cursor.getString(cursor.getColumnIndex("nm_estante")));
            local.setLocalProduto(cursor.getInt(cursor.getColumnIndex("id_local_produto")));

            Produto produto = new Produto();
            produto.setId(cursor.getInt(cursor.getColumnIndex("id_produto")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nm_produto")));
            produto.setVlTamanhoProduto(cursor.getInt(cursor.getColumnIndex("vl_tamanho_produto")));
            produto.setNomeCor(cursor.getString(cursor.getColumnIndex("nm_cor")));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("qt_produto")));
        }
        return local;
    }

    /**
     *
     * @param local
     */
    public void inserir(Local local){

        ContentValues valuesLocal = new ContentValues();
        valuesLocal.put("vl_prateleira", local.getPrateleira());
        valuesLocal.put("vl_coluna", local.getColuna());
        valuesLocal.put("nm_estante", local.getEstante());
        valuesLocal.put("id_local_produto", local.getLocalProduto());

        SQLiteDatabase database = daohelper.getWritableDatabase();
        database.insert("Local", null, valuesLocal);

    }

}