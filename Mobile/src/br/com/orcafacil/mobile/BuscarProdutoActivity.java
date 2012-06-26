package br.com.orcafacil.mobile;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import br.com.orcafacil.integration.api.to.Produto;
import br.com.orcafacil.mobile.adapters.ProdutoArrayAdapter;
import br.com.orcafacil.mobile.exception.ValidationException;
import br.com.orcafacil.mobile.service.ProdutoService;
import br.com.orcafacil.mobile.util.Internet;

import com.br.orcafacil.mobile.R;

public class BuscarProdutoActivity extends OrcaFacilActivity implements View.OnClickListener, OnItemClickListener {
    
	
	private ProdutoService produtoService;
	
	private ListView listProdutos;
	
	private List<Produto> produtosEncontrados;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.busca_produtos);
        
        produtoService = new ProdutoService();
        
        Button btBuscarProduto = (Button) findViewById(R.id.btBuscarProduto);
        btBuscarProduto.setOnClickListener(this);
        
        listProdutos = (ListView) findViewById(R.id.listProdutos);
        listProdutos.setOnItemClickListener(this);
        
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btBuscarProduto:
			try {
				filtrarProdutos();
			} catch (Exception e) {
				Toast toastException = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
				toastException.show();
			}
			break;
			
		default:
			break;
		}
				
	}

	private void filtrarProdutos() throws Exception {
		EditText edtBuscarProdutos = (EditText) findViewById(R.id.edtBuscarProdutos);
		String filtroNome = edtBuscarProdutos.getText().toString();
		if(filtroNome.isEmpty())
			throw new ValidationException("A consulta não pode estar em branco");
		
		if(!Internet.isActive(this))
			throw new Exception("Não é possível executar a consulta, dispositivo sem conectividade.");
		
		produtosEncontrados = produtoService.buscar(filtroNome);
		if(produtosEncontrados.isEmpty())
			throw new Exception("O filtro não retornou nenhum produto");
		
		popularListaDeProdutos(produtosEncontrados);
	}

	private void popularListaDeProdutos(List<Produto> produtosEncontrados) {
		ProdutoArrayAdapter ad = new ProdutoArrayAdapter(this, R.layout.item_produto, produtosEncontrados);
		ListView lv = (ListView) findViewById(R.id.listProdutos);
		lv.setAdapter(ad);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int selectedPosition, long id) {
		Produto produtoSelecionado = produtosEncontrados.get(selectedPosition);
		Intent itPtoduto = new Intent();
		itPtoduto.putExtra("produto", produtoSelecionado);
		setResult(Activity.RESULT_OK, itPtoduto);
		finish();
		//Toast.makeText(this, produtoSelecionado.getNome(), Toast.LENGTH_SHORT).show();
	}

}