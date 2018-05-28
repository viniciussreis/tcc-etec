package br.com.localcode60.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.localcode60.dao.LocalDAO;
import br.com.localcode60.dao.ProdutoDAO;
import com.example.eduardo.localcode60.R;
import br.com.localcode60.modelo.Local;
import br.com.localcode60.modelo.Produto;

public class ActivityResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int conteudo = Integer.parseInt(bundle.getString("conteudo"));

        TextView txtNome = (TextView) findViewById(R.id.txtNome);
        TextView txtMarca = (TextView) findViewById(R.id.txtMarca);
        TextView txtCor = (TextView) findViewById(R.id.txtCor);
        TextView txtTamanho = (TextView) findViewById(R.id.txtTamanho);
        TextView txtTipo = (TextView) findViewById(R.id.txtTipo);
        TextView txtEstante = (TextView) findViewById(R.id.txtEstante);
        TextView txtPrateleira = (TextView) findViewById(R.id.txtPrateleira);
        TextView txtColuna = (TextView) findViewById(R.id.txtColuna);
        TextView txtDisponivel = (TextView) findViewById(R.id.txtDisponivel);

        ProdutoDAO daoProduto = new ProdutoDAO(this);
        LocalDAO daoLocal = new LocalDAO(this);

        Produto produto = daoProduto.buscarPorID(conteudo);
        Local local = daoLocal.buscarPorIdProduto(conteudo);

        txtNome.setText(produto.getNome());
        txtMarca.setText(produto.getMarca().getNome());
        txtCor.setText(produto.getNomeCor());
        txtTamanho.setText(Integer.toString(produto.getVlTamanhoProduto()));
        txtTipo.setText(produto.getTipo());

        if(produto.getQuantidade() != 0){
            txtDisponivel.setText("Disponivel");
        } else{
            txtDisponivel.setText("Não Disponivel");
        }

        txtEstante.setText(local.getEstante());
        txtColuna.setText(Integer.toString(local.getColuna()));
        txtPrateleira.setText(Integer.toString(local.getPrateleira()));
    }
}