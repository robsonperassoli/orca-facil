package br.com.orcafacil.mobile;

import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import br.com.orcafacil.integration.api.to.ItemDoPedido;
import br.com.orcafacil.integration.api.to.Pedido;
import br.com.orcafacil.integration.api.to.PedidoEmExecucao;
import br.com.orcafacil.mobile.adapters.ItemDoPedidoArrayAdapter;
import br.com.orcafacil.mobile.adapters.PedidoEmExecucaoArrayAdapter;
import br.com.orcafacil.mobile.service.PedidoService;

import com.br.orcafacil.mobile.R;

public class ConsultaPedidosActivity extends OrcaFacilActivity {
    
	private PedidoService pedidoService;
	
	private ListView lvConsultaPedidos;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_pedidos);
        
        lvConsultaPedidos = (ListView) findViewById(R.id.lvConsultaPedidos);
        
        pedidoService = new PedidoService();
        List<PedidoEmExecucao> pedidosEmExecucao = pedidoService.buscarTodos(getApplicationCredentials().getApiKey());
        
		PedidoEmExecucaoArrayAdapter adapter = new PedidoEmExecucaoArrayAdapter(this, lvConsultaPedidos.getId(), pedidosEmExecucao);
		lvConsultaPedidos.setAdapter(adapter);
    }

}