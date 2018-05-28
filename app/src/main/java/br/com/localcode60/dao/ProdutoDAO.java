package br.com.localcode60.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.localcode60.modelo.Marca;
import br.com.localcode60.modelo.Produto;

public class ProdutoDAO {

    private DAOHelper daohelper;

    /**
     *
     * @param context
     */
    public ProdutoDAO (Context context){
        this.daohelper = new DAOHelper(context);
    }

    /**
     *
     * @param idProduto
     * @return
     */
    public Produto buscarPorID(Integer idProduto) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT * ");
        stringBuilder.append("FROM Produto p ");
        stringBuilder.append("INNER JOIN Marca m ON m.id_marca = p.id_produto_marca ");
        stringBuilder.append("WHERE p.id_produto = ?");

        String argumentos[] = {idProduto.toString()};

        SQLiteDatabase database = daohelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(stringBuilder.toString(), argumentos);

        Produto produto = null;

        while (cursor.moveToNext()) {

            produto = new Produto();

            produto.setId(cursor.getInt(cursor.getColumnIndex("id_produto")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nm_produto")));
            produto.setVlTamanhoProduto(cursor.getInt(cursor.getColumnIndex("vl_tamanho_produto")));
            produto.setNomeCor(cursor.getString(cursor.getColumnIndex("nm_cor")));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("qt_produto")));
            produto.setProdutoMarca(cursor.getInt(cursor.getColumnIndex("id_produto_marca")));
            produto.setTipo(cursor.getString(cursor.getColumnIndex("nm_tipo")));
            Marca marca = new Marca();
            marca.setId(cursor.getInt(cursor.getColumnIndex("id_marca")));
            marca.setNome(cursor.getString(cursor.getColumnIndex("nm_marca")));
            produto.setMarca(marca);

        }

        return produto;

    }

}
