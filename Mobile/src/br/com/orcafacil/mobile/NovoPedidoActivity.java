package br.com.orcafacil.mobile;

import java.security.Principal;
import java.util.Date;

import com.br.orcafacil.mobile.R;

import br.com.orcafacil.integration.api.to.ItemDoPedido;
import br.com.orcafacil.integration.api.to.Pedido;
import br.com.orcafacil.mobile.adapters.ItemDoPedidoArrayAdapter;
import br.com.orcafacil.mobile.dao.PedidoDAO;
import br.com.orcafacil.mobile.exception.ValidationException;
import br.com.orcafacil.mobile.service.PedidoService;
import br.com.orcafacil.mobile.util.Internet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class NovoPedidoActivity extends OrcaFacilActivity implements View.OnClickListener {
    
	private Pedido pedido;
	
	private ListView listItensPedido;
	
	private PedidoService pedidoService = new PedidoService();
	
	private TabHost tabHostPrincipal;
	
	private PedidoDAO pedidoDAO;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.novo_pedido);
        
        listItensPedido = (ListView) findViewById(R.id.listItensPedido);
        tabHostPrincipal = (TabHost) getParent().findViewById(R.id.tabHostPrincipal);
        
        Button btNovoItem = (Button) findViewById(R.id.btNovoItem);
        btNovoItem.setOnClickListener(this);
        
        Button btSalvar = (Button) findViewById(R.id.btSalvarPedido);
        btSalvar.setOnClickListener(this);
        
        Button btCancelar = (Button) findViewById(R.id.btCancelarPedido);
        btCancelar.setOnClickListener(this);
        
        pedidoDAO = new PedidoDAO(this);
        
        pedido = new Pedido();
        pedido.setData(new Date());
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btNovoItem:
				novoItem();
				break;
				
			case R.id.btSalvarPedido:
				salvarPedido();
				break;
				
			case R.id.btCancelarPedido:
				pedido = new Pedido();
				tabHostPrincipal.setCurrentTabByTag(MainActivity.TB_INICIAL);
				break;
		}
				
	}

	private void salvarPedido() {
		try {
			validarPedido();
			
			if(Internet.isActive(this)){
				pedidoService.salvar(getApplicationCredentials().getApiKey(), pedido);
				Toast.makeText(this, "Seu pedido foi enviado.", Toast.LENGTH_SHORT).show();
			} else {
				pedidoDAO.insert(pedido);
				Toast.makeText(this, "Seu pedido foi salvo no dispositivo devido a falta de conexão com a internet.", Toast.LENGTH_LONG).show();
			}
			
			finish();
			startActivity(new Intent(this, MainActivity.class));
		} catch (ValidationException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	private void validarPedido() throws ValidationException {
		if(pedido.getItens().isEmpty())
			throw new ValidationException("O pedido deve conter pelo menos 1 item.");
	}

	private void novoItem() {
		Intent itNovoItem = new Intent(NovoPedidoActivity.this, NovoItemActivity.class);
		startActivityForResult(itNovoItem, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(resultCode){
			case Activity.RESULT_OK:
				ItemDoPedido novoItem = (ItemDoPedido) data.getSerializableExtra("novoItem");
				novoItem.setItem(pedido.getItens().size() + 1);
				pedido.getItens().add(novoItem);
				
				ItemDoPedidoArrayAdapter adapter = new ItemDoPedidoArrayAdapter(this, listItensPedido.getId(), pedido.getItens());
				listItensPedido.setAdapter(adapter);
			break;
		}
		
	}
	
}