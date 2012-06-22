package br.com.orcafacil.mobile;

import com.br.orcafacil.mobile.R;

import br.com.orcafacil.integration.api.to.ItemDoPedido;
import br.com.orcafacil.integration.api.to.Produto;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class NovoItemActivity extends OrcaFacilActivity implements View.OnClickListener {
    
	private ItemDoPedido novoItem;
	private TextView txtDescricaoProduto;
	private EditText edtQuantidade;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_item);
        
        ImageButton btPesquisaProduto = (ImageButton) findViewById(R.id.btPesquisaProduto);
        btPesquisaProduto.setOnClickListener(this);
        
        Button btCancelar = (Button) findViewById(R.id.btCancelarItem);
        btCancelar.setOnClickListener(this);
        
        Button btSalvarItem = (Button) findViewById(R.id.btSalvarItem);
        btSalvarItem.setOnClickListener(this);
        
        txtDescricaoProduto = (TextView) findViewById(R.id.txtDescricaoProduto);
        edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
        
        novoItem = new ItemDoPedido();
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btPesquisaProduto:
				pesquisarProduto();
				break;
			case R.id.btCancelarItem:
				setResult(Activity.RESULT_CANCELED);
				finish();
				break;
			case R.id.btSalvarItem:
				salvarItem();
				break;
			default:
				break;
		}
				
	}

	private void salvarItem() {
		String quantidadeItemStr = edtQuantidade.getText().toString();
		if(!quantidadeItemStr.isEmpty())
			novoItem.setQuantidade(Double.parseDouble(quantidadeItemStr));
		Intent itResultItem = new Intent();
		itResultItem.putExtra("novoItem", novoItem);
		setResult(Activity.RESULT_OK, itResultItem);
		finish();
	}

	private void pesquisarProduto() {
		Intent itBuscarProduto = new Intent(NovoItemActivity.this, BuscarProdutoActivity.class);
		startActivityForResult(itBuscarProduto, 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Produto produtoSelecionado = (Produto) data.getSerializableExtra("produto");
		novoItem.setProduto(produtoSelecionado);
		setFields(novoItem);
		//Toast.makeText(this, produtoSelecionado.getNome(), Toast.LENGTH_SHORT).show();
	}
	
	private void setFields(ItemDoPedido item){
		txtDescricaoProduto.setText(item.getProduto().getNome());
		edtQuantidade.setText(""+item.getQuantidade());
	}

}