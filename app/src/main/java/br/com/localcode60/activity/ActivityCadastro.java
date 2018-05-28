package br.com.localcode60.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.localcode60.dao.LocalDAO;
import br.com.localcode60.dao.ModeloDAO;
import br.com.localcode60.dao.ProdutoDAO;
import br.com.localcode60.localcode60.R;
import br.com.localcode60.modelo.Local;
import br.com.localcode60.modelo.Modelo;
import br.com.localcode60.modelo.Produto;

public class ActivityCadastro extends AppCompatActivity {

    private Local local;
    private LocalDAO localDAO = new LocalDAO(this);

    private AlertDialog alerta;
    private AlertDialog.Builder builder;
    private Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setHomeButtonEnabled(true);

        builder = new AlertDialog.Builder(this);
        intent2 = new Intent(this, ActivityHome.class);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int conteudo = Integer.parseInt(bundle.getString("conteudo"));

        TextView txtNome = (TextView) findViewById(R.id.textResultNome);
        TextView txtMarca = (TextView) findViewById(R.id.textResultMarca);
        TextView txtCor = (TextView) findViewById(R.id.textResultCor);
        TextView txtTamanho = (TextView) findViewById(R.id.textResultTamanho);
        TextView txtModelo = (TextView) findViewById(R.id.textResultModelo);

        ProdutoDAO daoProduto = new ProdutoDAO(this);
        ModeloDAO daoModelo = new ModeloDAO(this);

        Produto produto = daoProduto.buscarPorID(conteudo);
        String buscaModelo = produto.getMarca().getNome();
        Modelo modelo = daoModelo.buscarPorId(conteudo);

        txtNome.setText(produto.getNome());
        txtMarca.setText(produto.getMarca().getNome());
        txtCor.setText(produto.getNomeCor());
        txtTamanho.setText(Integer.toString(produto.getVlTamanhoProduto()));
        txtModelo.setText(modelo.getNome());

        final Spinner spinnerEstante = (Spinner) findViewById(R.id.spinEstante);

        String[] opcoesEstante = {"A","B","C","D","E"};

        ArrayAdapter<String> listaDeOpcoesEstante = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoesEstante);
        spinnerEstante.setAdapter(listaDeOpcoesEstante);

        final Spinner spinnerPrateleira = (Spinner) findViewById(R.id.spinPrateleira);

        String[] opcoesPrateleira = {"1","2","3","4","5"};

        ArrayAdapter<String> listaDeOpcoesPrateleira = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoesPrateleira);
        spinnerPrateleira.setAdapter(listaDeOpcoesPrateleira);

        final Spinner spinnerColuna = (Spinner) findViewById(R.id.spinColuna);

        String[] opcoesColuna = {"1","2","3","4","5","6","7","8","9","10"};

        ArrayAdapter<String> listaDeOpcoesColuna = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoesColuna);
        spinnerColuna.setAdapter(listaDeOpcoesColuna);

        Button botaoCadastro = (Button) findViewById(R.id.btnCadastrar);

        botaoCadastro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                final String estante = spinnerEstante.getSelectedItem().toString();
                final Integer prateleira = Integer.parseInt(spinnerPrateleira.getSelectedItem().toString());
                final Integer coluna = Integer.parseInt(spinnerColuna.getSelectedItem().toString());

                if(!estante.isEmpty() && prateleira != null && coluna != null ) {

                    local = new Local();

                    local.setEstante(estante);
                    local.setPrateleira(prateleira);
                    local.setColuna(coluna);
                    local.setLocalProduto(conteudo);

                    localDAO.inserir(local);

                    builder.setTitle("Cadastrado!");
                    builder.setMessage("O produto foi cadastrado em um local com sucesso.");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(intent2);
                        }

                    });

                    alerta = builder.create();

                    alerta.show();

                }
            }
        });

    }
}