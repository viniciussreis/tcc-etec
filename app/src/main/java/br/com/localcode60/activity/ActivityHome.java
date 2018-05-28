package br.com.localcode60.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import br.com.localcode60.dao.LocalDAO;
import br.com.localcode60.dao.ProdutoDAO;
import com.example.eduardo.localcode60.R;

import br.com.localcode60.modelo.Local;
import br.com.localcode60.modelo.Produto;

public class ActivityHome extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        final Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanQR(view);
            }
        });

    }

    /**
     * Método para Leitura de QR code, junto de Verificação se há cadastro do produto no local.
     *
     * @param v
     */
    public void scanQR(View v) {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RESULT_CANCELED) {
            if (resultCode == RESULT_OK) {
                String conteudo = intent.getStringExtra("SCAN_RESULT");

                ProdutoDAO produtoDAO = new ProdutoDAO(this);
                Produto produto = produtoDAO.buscarPorID(Integer.parseInt(conteudo));

                if(produto != null ) {

                    LocalDAO daoLocal = new LocalDAO(this);
                    Local local = daoLocal.buscarPorIdProduto(Integer.parseInt(conteudo));

                    if (local != null) {
                        Intent intente = new Intent(this, ActivityResultado.class);
                        intente.putExtra("conteudo", conteudo);
                        startActivity(intente);
                    } else {
                        Intent intente = new Intent(this, ActivityCadastro.class);
                        intente.putExtra("conteudo", conteudo);
                        startActivity(intente);
                    }

                }else{
                    Snackbar snackbar = Snackbar
                            .make(getCurrentFocus(), "O Código do produto não existe!", Snackbar.LENGTH_LONG);
                    snackbar.show();

                }

            }
        }
    }

    /**
     * Cria o menu e o infla, e mostra todos os itens presentes no layout.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {}

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            if(getArguments().getInt(ARG_SECTION_NUMBER ) == 1){
                View rootView = inflater.inflate(R.layout.fragment_fragment_codigo, container, false);
                final EditText editText = (EditText) rootView.findViewById(R.id.edtInsiraCodigo);
                Button button = (Button) rootView.findViewById(R.id.btnBuscarCodigo);

                button.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v){
                        final String texto = editText.getText().toString();

                        if(!texto.isEmpty()){

                            ProdutoDAO produtoDAO = new ProdutoDAO(getActivity());
                            Produto produto = produtoDAO.buscarPorID(Integer.parseInt(texto));

                            if(produto != null ) {

                                LocalDAO daoLocal = new LocalDAO(getActivity());
                                Local local = daoLocal.buscarPorIdProduto(Integer.parseInt(texto));

                                if (local != null) {
                                    Intent intente = new Intent(getActivity(), ActivityResultado.class);
                                    intente.putExtra("conteudo", texto);
                                    startActivity(intente);
                                } else {
                                    Intent intente = new Intent(getActivity(), ActivityCadastro.class);
                                    intente.putExtra("conteudo", texto);
                                    startActivity(intente);
                                }

                            } else{

                                Snackbar snackbar = Snackbar
                                        .make(getView(), "O Código do produto não existe!", Snackbar.LENGTH_LONG);
                                snackbar.show();

                            }
                        } else{
                            Snackbar snackbar = Snackbar
                                    .make(getView(), "Digite um Código!", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }

                });

                return rootView;

            } else if(getArguments().getInt(ARG_SECTION_NUMBER ) == 2){
                View rootView = inflater.inflate(R.layout.fragment_fragment_busca, container, false);

                final NumberPicker np = (NumberPicker) rootView.findViewById(R.id.npTamanho);
                np.setMinValue(24);
                np.setMaxValue(47);

                final Spinner spinnerTipo = (Spinner) rootView.findViewById(R.id.spiTipo);

                String[] opcoesTipo = {"Bota","Chuteira","Salto","Sapato Social","Tênis"};

                ArrayAdapter<String> listaDeOpcoesTipo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opcoesTipo);
                spinnerTipo.setAdapter(listaDeOpcoesTipo);

                final Spinner spinnerMarca = (Spinner) rootView.findViewById(R.id.spiMarca);

                String[] opcoesMarca = {"Selecione","Adidas", "Asics", "Converse", "Nike", "Puma"};

                ArrayAdapter<String> listaDeOpcoesMarca = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opcoesMarca);
                spinnerMarca.setAdapter(listaDeOpcoesMarca);

                final Spinner spinnerCor = (Spinner) rootView.findViewById(R.id.spiCor);

                String[] opcoesCor = {"Selecione","Azul", "Cinza", "Preto", "Rosa", "Vermelho"};

                ArrayAdapter<String> listaDeOpcoesCor = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opcoesCor);
                spinnerCor.setAdapter(listaDeOpcoesCor);

                Button button = (Button) rootView.findViewById(R.id.btnBuscarPersonalizado);
                button.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        String tamanho = Integer.toString(np.getValue());
                        String tipo = spinnerTipo.getSelectedItem().toString();
                        String marca = spinnerMarca.getSelectedItem().toString();
                        String cor = spinnerCor.getSelectedItem().toString();

                        // TODO REFATORAR
                        if(marca == "Selecione" && cor == "Selecione"){
                            // TODO IMPLEMENTAR
                        } else if(marca != "Selecione" && cor == "Selecione"){
                            // TODO IMPLEMENTAR
                        } else if(marca == "Selecione" && cor != "Selecione"){
                            // TODO IMPLEMENTAR
                        } else if(marca != "Selecione" && cor != "Selecione"){
                            // TODO IMPLEMENTAR
                        }

                    }

                });
                return rootView;
            }

            View rootView = inflater.inflate(R.layout.fragment_fragment_codigo, container, false);
            return rootView;

        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.codigo);
                case 1:
                    return getString(R.string.busca);
            }
            return null;
        }

    }
}