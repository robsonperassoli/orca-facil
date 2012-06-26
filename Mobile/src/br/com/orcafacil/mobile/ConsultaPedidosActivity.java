package br.com.orcafacil.mobile;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.orcafacil.integration.api.to.ItemDoPedido;
import br.com.orcafacil.integration.api.to.Pedido;
import br.com.orcafacil.integration.api.to.PedidoEmExecucao;
import br.com.orcafacil.mobile.adapters.ItemDoPedidoArrayAdapter;
import br.com.orcafacil.mobile.adapters.PedidoEmExecucaoArrayAdapter;
import br.com.orcafacil.mobile.service.PedidoService;

import com.br.orcafacil.mobile.R;

public class ConsultaPedidosActivity extends OrcaFacilActivity 
			implements OnItemClickListener {
    
	private PedidoService pedidoService;
	
	private ListView lvConsultaPedidos;
	
	private List<PedidoEmExecucao> pedidosEmExecucao;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_pedidos);
        
        lvConsultaPedidos = (ListView) findViewById(R.id.lvConsultaPedidos);
        lvConsultaPedidos.setOnItemClickListener(this);
        
		pedidoService = new PedidoService();
		pedidosEmExecucao = pedidoService.buscarTodos(getApplicationCredentials().getApiKey());
		
		PedidoEmExecucaoArrayAdapter adapter = new PedidoEmExecucaoArrayAdapter(this, lvConsultaPedidos.getId(), pedidosEmExecucao);
		lvConsultaPedidos.setAdapter(adapter);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int selectedPosition, long id) {
		Intent it = new Intent(this, VisualizaPedidoActivity.class);
		it.putExtra("pedido", pedidosEmExecucao.get(selectedPosition));
		startActivity(it);
	}

}