package br.com.localcode60.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.localcode60.modelo.MarcaTipo;



public class MarcaTipoDAO {
    private DAOHelper daoHelper;

    public MarcaTipoDAO (Context context)
    {
        this.daoHelper = new DAOHelper(context);
    }

    public MarcaTipo BuscaporID (Integer id_marcaTipo){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT * ");
        stringBuilder.append("FROM Tipo t ");
        stringBuilder.append("INNER JOIN MarcaTipo ON MarcaTipo.id_marca_tipo = t.id_tipo ");
        stringBuilder.append("JOIN Marca m");
        stringBuilder.append("ON MarcaTipo.cd_tipo_marca = m.cd_marca");
        stringBuilder.append("WHERE = ?");

        String argumento [] = {id_marcaTipo.toString()};

        SQLiteDatabase database = daoHelper.getReadableDatabase();

        MarcaTipo marcaTipo = null;

        return marcaTipo;
    }



}
