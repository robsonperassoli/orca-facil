package br.com.orcafacil.mobile;

import java.text.SimpleDateFormat;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.com.orcafacil.integration.api.to.Fornecedor;
import br.com.orcafacil.integration.api.to.ItemDoPedidoEmExecucao;
import br.com.orcafacil.integration.api.to.PedidoEmExecucao;
import br.com.orcafacil.integration.api.to.Pontuacao;
import br.com.orcafacil.mobile.adapters.ItemDoPedidoArrayAdapter;
import br.com.orcafacil.mobile.adapters.ItemDoPedidoEmExecucaoArrayAdapter;
import br.com.orcafacil.mobile.service.PontuacaoService;
import br.com.orcafacil.mobile.service.TipoPontuacao;

import com.br.orcafacil.mobile.R;

public class VisualizarItemPedidoActivity extends OrcaFacilActivity 
			implements View.OnClickListener {
    
	private TextView txtNomeFornecedor;
	private TextView txtProduto;
	private TextView txtUnitario;
	private TextView txtValorEntrega;
	private TextView txtTotal;
	private TextView txtQuantidade;
	
	private Button btPositivar;
	private Button btNegativar;
	private Button btOk;
	
	private ItemDoPedidoEmExecucao item;

	private PontuacaoService pontuacaoService;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_item_pedido);
        
        pontuacaoService = new PontuacaoService();
        
        txtNomeFornecedor = (TextView) findViewById(R.id.txtNomeFornecedor);
        txtProduto = (TextView) findViewById(R.id.txtProduto);
        txtQuantidade = (TextView) findViewById(R.id.txtQuantidade);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        txtUnitario = (TextView) findViewById(R.id.txtUnitario);
        txtValorEntrega  = (TextView) findViewById(R.id.txtValorEntrega);
        
        btPositivar = (Button) findViewById(R.id.btPositivar);
        btPositivar.setOnClickListener(this);
        btNegativar = (Button) findViewById(R.id.btNegativar);
        btNegativar.setOnClickListener(this);
        btOk = (Button) findViewById(R.id.btOk);
        btOk.setOnClickListener(this);
        
        item = (ItemDoPedidoEmExecucao) getIntent().getSerializableExtra("item");
        
        txtProduto.setText(item.getProduto().getNome());
        txtNomeFornecedor.setText(item.getFornecedor().getNome());
        txtQuantidade.setText(""+item.getQuantidade());
        txtTotal.setText(""+item.getTotal());
        txtUnitario.setText(""+item.getUnitario());
        txtValorEntrega.setText(""+item.getValorEntrega());
    }


	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btOk:
				finish();
				break;
			case R.id.btNegativar:
				negativar();
				break;
			case R.id.btPositivar:
				positivar();
				break;
		}
	}


	private void positivar() {
		Pontuacao pontuacao = new Pontuacao(item.getFornecedor(), TipoPontuacao.positiva.ordinal(), "");
		pontuacaoService.pontuar(getApplicationCredentials().getApiKey(), pontuacao);
		Toast.makeText(this, "Fornecedor positivado", Toast.LENGTH_LONG).show();
	}


	private void negativar() {
		Pontuacao pontuacao = new Pontuacao(item.getFornecedor(), TipoPontuacao.negativa.ordinal(), "");
		pontuacaoService.pontuar(getApplicationCredentials().getApiKey(), pontuacao);
		Toast.makeText(this, "Fornecedor negativado", Toast.LENGTH_LONG).show();
	}


}