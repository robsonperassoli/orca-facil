package br.com.orcafacil.mobile;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import br.com.orcafacil.integration.api.to.ItemDoPedidoEmExecucao;
import br.com.orcafacil.integration.api.to.PedidoEmExecucao;
import br.com.orcafacil.mobile.adapters.ItemDoPedidoArrayAdapter;
import br.com.orcafacil.mobile.adapters.ItemDoPedidoEmExecucaoArrayAdapter;

import com.br.orcafacil.mobile.R;

public class VisualizaPedidoActivity extends OrcaFacilActivity 
			implements View.OnClickListener, OnItemClickListener {
    
	private TextView txtRequisicao;
	private TextView txtData;
	private Button btFecharVisualizaPedido;
	
	private PedidoEmExecucao pedido;
	
	private ListView lvItensDoPedido;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_pedido);
        
        txtRequisicao = (TextView) findViewById(R.id.txtRequisicao);
        txtData = (TextView) findViewById(R.id.txtData);
        btFecharVisualizaPedido = (Button) findViewById(R.id.btFecharVisualizaPedido);
        btFecharVisualizaPedido.setOnClickListener(this);
        lvItensDoPedido = (ListView) findViewById(R.id.lvItensDoPedido);
        lvItensDoPedido.setOnItemClickListener(this);
        
        pedido = (PedidoEmExecucao) getIntent().getSerializableExtra("pedido");
        
        txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(pedido.getData()));
        txtRequisicao.setText("" + pedido.getNumeroRequisicao());
        
        ItemDoPedidoEmExecucaoArrayAdapter adapter = new ItemDoPedidoEmExecucaoArrayAdapter(this, lvItensDoPedido.getId(), pedido.getItens());
        lvItensDoPedido.setAdapter(adapter);
    }


	@Override
	public void onClick(View view) {
		finish();
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int selectedPosition, long id) {
		Intent it = new Intent(this, VisualizarItemPedidoActivity.class);
		it.putExtra("item", pedido.getItens().get(selectedPosition));
		startActivity(it);
	}

}