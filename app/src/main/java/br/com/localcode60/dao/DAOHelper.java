package br.com.localcode60.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAOHelper extends SQLiteOpenHelper {

    private static final String NOME_DB = "LocalCode";
    private static final int VERSAO_DB = 1;

    public DAOHelper(Context context){
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder builderQuery1 = new StringBuilder();

        // MARCA
        builderQuery1.append(" CREATE TABLE Marca (");
        builderQuery1.append(" id_marca INTEGER NOT NULL,");
        builderQuery1.append(" nm_marca VARCHAR (50) NOT NULL,");
        builderQuery1.append(" CONSTRAINT pk_marca");
        builderQuery1.append(" PRIMARY KEY (id_marca)");
        builderQuery1.append(" );");

        db.execSQL(builderQuery1.toString());

        StringBuilder builderQuery2 = new StringBuilder();
        // Modelo
        builderQuery2.append(" CREATE TABLE Modelo(");
        builderQuery2.append(" id_modelo INTEGER NOT NULL,");
        builderQuery2.append(" nm_modelo VARCHAR (50) NOT NULL,");
        builderQuery2.append(" id_modelo_marca INTEGER,");
        builderQuery2.append(" CONSTRAINT pk_modelo");
        builderQuery2.append(" PRIMARY KEY (id_modelo),");
        builderQuery2.append(" CONSTRAINT fk_modelo_marca");
        builderQuery2.append(" FOREIGN KEY (id_modelo_marca)");
        builderQuery2.append(" references Marca (id_marca)");
        builderQuery2.append(" );");

        db.execSQL(builderQuery2.toString());

        StringBuilder builderQuery3 = new StringBuilder();
        // TIPO
        builderQuery3.append(" CREATE TABLE Tipo(");
        builderQuery3.append(" id_tipo INTEGER NOT NULL,");
        builderQuery3.append(" nm_tipo VARCHAR (30) NOT NULL,");
        builderQuery3.append(" ds_tipo VARCHAR (70), ");
        builderQuery3.append(" CONSTRAINT pk_tipo");
        builderQuery3.append(" PRIMARY KEY (id_tipo)");
        builderQuery3.append(" );");

        db.execSQL(builderQuery3.toString());

        StringBuilder builderQuery4 = new StringBuilder();
        //MarcaTipo
        builderQuery4.append(" CREATE TABLE MarcaTipo(");
        builderQuery4.append(" id_marca_tipo INTEGER,");
        builderQuery4.append(" id_tipo_marca int,");
        builderQuery4.append(" CONSTRAINT fk_marca_tipo");
        builderQuery4.append(" FOREIGN KEY (id_marca_tipo)");
        builderQuery4.append(" REFERENCES Marca (id_marca),");
        builderQuery4.append(" CONSTRAINT fk_tipo_marca");
        builderQuery4.append(" FOREIGN KEY (id_tipo_marca)");
        builderQuery4.append(" REFERENCES Tipo (id_tipo)");
        builderQuery4.append(" );");

        db.execSQL(builderQuery4.toString());

        StringBuilder builderQuery5 = new StringBuilder();
        // TABELA PRODUTO
        builderQuery5.append(" CREATE TABLE Produto( ");
        builderQuery5.append(" id_produto INTEGER NOT NULL,");
        builderQuery5.append(" nm_produto VARCHAR(75) NOT NULL, ");
        builderQuery5.append(" vl_tamanho_produto INTEGER(2) NOT NULL, ");
        builderQuery5.append(" nm_cor VARCHAR(25) NOT NULL, ");
        builderQuery5.append(" nm_tipo VARCHAR(50) NOT NULL,");
        builderQuery5.append(" qt_produto INTEGER NOT NULL,");
        builderQuery5.append(" id_produto_marca INTEGER, ");
        builderQuery5.append(" CONSTRAINT pk_produto");
        builderQuery5.append(" PRIMARY KEY (id_produto),");
        builderQuery5.append(" CONSTRAINT fk_produto_marca");
        builderQuery5.append(" FOREIGN KEY (id_produto_marca)");
        builderQuery5.append(" REFERENCES Marca (id_marca)");
        builderQuery5.append(" );");

        db.execSQL(builderQuery5.toString());

        StringBuilder builderQuery = new StringBuilder();
        // TABELA LOCAL
        builderQuery.append(" CREATE TABLE Local(");
        builderQuery.append(" id_local INTEGER AUTO INCREMENT,");
        builderQuery.append(" vl_prateleira INT NOT NULL,");
        builderQuery.append(" vl_coluna INTEGER NOT NULL,");
        builderQuery.append(" nm_estante VARCHAR(3) NOT NULL,");
        builderQuery.append(" id_local_produto INTEGER,");
        builderQuery.append(" CONSTRAINT pk_local");
        builderQuery.append(" PRIMARY KEY (id_local),");
        builderQuery.append(" CONSTRAINT fk_local_produto");
        builderQuery.append(" FOREIGN KEY (id_local_produto)");
        builderQuery.append(" REFERENCES Produto(id_produto)");
        builderQuery.append(" );");

        db.execSQL(builderQuery.toString());

        //Tabela Local não há insert's manuais
        //Insert teste
        ContentValues valuesLocal1 = new ContentValues();
        valuesLocal1.put("vl_prateleira", 2);
        valuesLocal1.put("vl_coluna", 3);
        valuesLocal1.put("nm_estante", "A");
        valuesLocal1.put("id_local_produto", 1);
        db.insert("Local", null, valuesLocal1);

        //TABELA MARCA
        //PRIMEIRO INSERT
        ContentValues valuesMarca1 = new ContentValues();
        valuesMarca1.put("id_marca",1);
        valuesMarca1.put("nm_marca","Nike");
        db.insert("Marca",null,valuesMarca1);

        //SEGUNDO INSERT
        ContentValues valuesMarca2= new ContentValues();
        valuesMarca2.put("id_marca",2);
        valuesMarca2.put("nm_marca","Converse");
        db.insert("Marca",null,valuesMarca2);

        //TERCEIRO INSERT
        ContentValues valuesMarca3 = new ContentValues();
        valuesMarca3.put("id_marca",3);
        valuesMarca3.put("nm_marca","Adidas");
        db.insert("Marca",null,valuesMarca3);

        // QUARTO INSERT
        ContentValues valuesMarca4 = new ContentValues();
        valuesMarca4.put("id_marca",4);
        valuesMarca4.put("nm_marca","Asics");
        db.insert("Marca",null,valuesMarca4);

        //QUINTO INSERT
        ContentValues valuesMarca5 = new ContentValues();
        valuesMarca5.put("id_marca",5);
        valuesMarca5.put("nm_marca","Puma");
        db.insert("Marca",null,valuesMarca5);

        //TABELA MODELO

        //PRIMEIRO INSERT
        ContentValues valuesModelo1 = new ContentValues();
        valuesModelo1.put("id_modelo",1);
        valuesModelo1.put("nm_modelo","Mercurial ViperX");
        valuesModelo1.put("id_modelo_marca",1);
        db.insert("Modelo",null,valuesModelo1);

        //SEGUNDO INSERT
        ContentValues valuesModelo2 = new ContentValues();
        valuesModelo2.put("id_modelo",2);
        valuesModelo2.put("nm_modelo","All Star Chuck Taylor");
        valuesModelo2.put("id_modelo_marca",2);
        db.insert("Modelo",null,valuesModelo2);

        //TERCEIRO INSERT
        ContentValues valuesModelo3 = new ContentValues();
        valuesModelo3.put("id_modelo",3);
        valuesModelo3.put("nm_modelo","Air Shock");
        valuesModelo3.put("id_modelo_marca",3);
        db.insert("Modelo",null,valuesModelo3);

        //QUARTO INSERT
        ContentValues valuesModelo4 = new ContentValues();
        valuesModelo4.put("id_modelo",4);
        valuesModelo4.put("nm_modelo","S street");
        valuesModelo4.put("id_modelo_marca",4);
        db.insert("Modelo",null,valuesModelo4);

        //QUINTO INSERT
        ContentValues valuesModelo5 = new ContentValues();
        valuesModelo5.put("id_modelo",5);
        valuesModelo5.put("nm_modelo","Gun Lap Knockout");
        valuesModelo5.put("id_modelo_marca",5);
        db.insert("Modelo",null,valuesModelo5);

        //TABELA TIPO
        //PRIMEIRO INSERT
        ContentValues valuesTipo1 = new ContentValues();
        valuesTipo1.put("id_tipo",1);
        valuesTipo1.put("nm_tipo","Chuteira");
        valuesTipo1.put("ds_tipo", "");
        db.insert("Tipo",null,valuesTipo1);

        //Segundo Insert
        ContentValues valuesTipo2 = new ContentValues();
        valuesTipo2.put("id_tipo",2);
        valuesTipo2.put("nm_tipo","Sapato Social");
        valuesTipo2.put("ds_tipo","indicado para eventos formais");
        db.insert("Tipo",null,valuesTipo2);

        //Terceiro Insert
        ContentValues valuesTipo3 = new ContentValues();
        valuesTipo3.put("id_tipo",3);
        valuesTipo3.put("nm_tipo","Salto");
        valuesTipo3.put("ds_tipo","");
        db.insert("Tipo",null,valuesTipo3);

        //Quarto Insert
        ContentValues valuesTipo4 = new ContentValues();
        valuesTipo4.put("id_tipo",4);
        valuesTipo4.put("nm_tipo","Bota");
        valuesTipo4.put("ds_tipo","");
        db.insert("Tipo",null,valuesTipo4);

        //Quinto Insert
        ContentValues valuesTipo5 = new ContentValues();
        valuesTipo5.put("id_tipo",5);
        valuesTipo5.put("nm_tipo","Tênis");
        valuesTipo5.put("ds_tipo","");
        db.insert("Tipo",null,valuesTipo5);

        //TABELA MarcaTipo
        //PRIMEIRO INSERT
        ContentValues valuesMarcaTipo1 = new ContentValues();
        valuesMarcaTipo1.put("id_marca_tipo",1);
        valuesMarcaTipo1.put("id_tipo_marca",1);
        db.insert("MarcaTipo",null,valuesMarcaTipo1);

        //SEGUNDO INSERT
        ContentValues valuesMarcaTipo2 = new ContentValues();
        valuesMarcaTipo2.put("id_marca_tipo",2);
        valuesMarcaTipo2.put("id_tipo_marca",2);
        db.insert("MarcaTipo",null,valuesMarcaTipo2);

        //TERCEIRO INSERT
        ContentValues valuesMarcaTipo3 = new ContentValues();
        valuesMarcaTipo3.put("id_marca_tipo",3);
        valuesMarcaTipo3.put("id_tipo_marca",3);
        db.insert("MarcaTipo",null,valuesMarcaTipo3);

        //QUARTO INSERT
        ContentValues valuesMarcaTipo4 = new ContentValues();
        valuesMarcaTipo4.put("id_marca_tipo",4);
        valuesMarcaTipo4.put("id_tipo_marca",4);
        db.insert("MarcaTipo",null,valuesMarcaTipo4);

        //QUINTO INSERT
        ContentValues valuesMarcaTipo5 = new ContentValues();
        valuesMarcaTipo5.put("id_marca_tipo",5);
        valuesMarcaTipo5.put("id_tipo_marca",5);
        db.insert("MarcaTipo",null,valuesMarcaTipo5);

        //TABELA PRODUTO

        //PRIMEIRO INSERT
        ContentValues valuesProduto1 = new ContentValues();
        valuesProduto1.put("id_produto",1);
        valuesProduto1.put("nm_produto","Mercurial");
        valuesProduto1.put("vl_tamanho_produto",37);
        valuesProduto1.put("nm_cor","Vermelho");
        valuesProduto1.put("nm_tipo","Chuteira");
        valuesProduto1.put("qt_produto",10);
        valuesProduto1.put("id_produto_marca",1);
        db.insert("Produto",null,valuesProduto1);

        //SEGUNDO INSERT
        ContentValues valuesProduto2 = new ContentValues();
        valuesProduto2.put("id_produto",2);
        valuesProduto2.put("nm_produto","All Star");
        valuesProduto2.put("vl_tamanho_produto",41);
        valuesProduto2.put("nm_cor","Preto");
        valuesProduto2.put("nm_tipo", "Tênis");
        valuesProduto2.put("qt_produto",12);
        valuesProduto2.put("id_produto_marca",2);
        db.insert("Produto",null,valuesProduto2);

        //TERCEIRO INSERT
        ContentValues valuesProduto3 = new ContentValues();
        valuesProduto3.put("id_produto",3);
        valuesProduto3.put("nm_produto","Shock");
        valuesProduto3.put("vl_tamanho_produto",40);
        valuesProduto3.put("nm_cor","Cinza");
        valuesProduto3.put("nm_tipo", "Tênis");
        valuesProduto3.put("qt_produto",0);
        valuesProduto3.put("id_produto_marca",1);
        db.insert("Produto",null,valuesProduto3);

        //QUARTO INSERT
        ContentValues valuesProduto4 = new ContentValues();
        valuesProduto4.put("id_produto",4);
        valuesProduto4.put("nm_produto","Predator");
        valuesProduto4.put("vl_tamanho_produto",38);
        valuesProduto4.put("nm_cor","Azul");
        valuesProduto4.put("nm_tipo", "Chuteira");
        valuesProduto4.put("qt_produto",20);
        valuesProduto4.put("id_produto_marca",3);
        db.insert("Produto",null,valuesProduto4);

        //QUINTO INSERT
        ContentValues valuesProduto5 = new ContentValues();
        valuesProduto5.put("id_produto",5);
        valuesProduto5.put("nm_produto","Air Shock");
        valuesProduto5.put("vl_tamanho_produto",39);
        valuesProduto5.put("nm_cor","Rosa");
        valuesProduto5.put("nm_tipo", "Sapatênis");
        valuesProduto5.put("qt_produto",5);
        valuesProduto5.put("id_produto_marca",1);
        db.insert("Produto",null,valuesProduto5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
