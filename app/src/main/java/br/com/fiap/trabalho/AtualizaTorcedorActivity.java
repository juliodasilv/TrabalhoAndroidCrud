package br.com.fiap.trabalho;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import br.com.fiap.trabalho.dao.ClubeDAO;
import br.com.fiap.trabalho.dao.TorcedorDAO;
import br.com.fiap.trabalho.model.Clube;
import br.com.fiap.trabalho.model.Torcedor;
import br.com.fiap.trabalhoandroid.R;

public class AtualizaTorcedorActivity extends AppCompatActivity {

    public final static int CODE_ATUALIZAR_TORCEDOR = 1003;

    private TextView id;
    private TextInputLayout tilNomeTorcedor;
    private Spinner spClube;
    private List<Clube> clubes;
    private TorcedorDAO dao = new TorcedorDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_torcedor);

        tilNomeTorcedor = (TextInputLayout) findViewById(R.id.tilNomeTorcedor);
        spClube = (Spinner) findViewById(R.id.spClube);

        ClubeDAO clubeDAO = new ClubeDAO(this);
        clubes = clubeDAO.getAll();

        ArrayAdapter<Clube> adapter = new ArrayAdapter<Clube>(getApplicationContext(), R.layout.clube_spinner_item, clubes);
        adapter.setDropDownViewResource(R.layout.clube_spinner_item);
        spClube.setAdapter(adapter);

        Intent intent = this.getIntent();
        Torcedor torcedor = dao.findById(intent.getIntExtra("id",1));

        Clube c = new Clube();
        for(Clube c1: clubes){
            if(c1.getId() == torcedor.getClube().getId()){
                c = c1;
            }
        }

        id = (TextView) findViewById(R.id.hidden_value);
        id.setText(String.valueOf(torcedor.getId()).toString());
        tilNomeTorcedor.getEditText().setText(torcedor.getNome());
        spClube.setSelection(adapter.getPosition(c));
    }

    public void atualizar(View v) {
        id = (TextView) findViewById(R.id.hidden_value);

        TorcedorDAO dao = new TorcedorDAO(this);
        Torcedor torcedor = new Torcedor();
        torcedor.setId(Integer.parseInt(id.getText().toString()));
        torcedor.setNome(tilNomeTorcedor.getEditText().getText().toString());
        torcedor.setClube((Clube)spClube.getSelectedItem());
        dao.update(torcedor);
        retornaParaTelaAnterior();
    }

    public void deletar(View v) {
        id = (TextView) findViewById(R.id.hidden_value);

        TorcedorDAO dao = new TorcedorDAO(this);
        dao.delete(Integer.parseInt(id.getText().toString()));
        retornaParaTelaAnterior();
    }

    //retorna para tela de lista de torcedores
    public void retornaParaTelaAnterior() {
        Intent intentMessage=new Intent(AtualizaTorcedorActivity.this, MainActivity.class);
        setResult(CODE_ATUALIZAR_TORCEDOR, intentMessage);
        finish();
    }
}
