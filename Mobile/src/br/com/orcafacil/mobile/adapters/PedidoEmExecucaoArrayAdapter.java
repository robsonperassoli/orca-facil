package br.com.orcafacil.mobile.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import com.br.orcafacil.mobile.R;

import br.com.orcafacil.integration.api.to.PedidoEmExecucao;
import br.com.orcafacil.integration.api.to.Produto;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PedidoEmExecucaoArrayAdapter extends ArrayAdapter<PedidoEmExecucao> {
	
	private TextView txtRequisicao;
	private TextView txtData;
	private TextView txtSituacao;
	private TextView txtQtdItens;
	
	public PedidoEmExecucaoArrayAdapter(Context context, int textViewResourceId,
			List<PedidoEmExecucao> objects) {
		super(context, textViewResourceId, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
	    if (row == null) {
	      LayoutInflater inflater = (LayoutInflater) this.getContext()
	          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      
	      row = inflater.inflate(R.layout.item_consulta_pedido, parent, false);
	    }

	    PedidoEmExecucao pedido = getItem(position);

	    txtRequisicao = (TextView) row.findViewById(R.id.txtRequisicao);
	    txtData = (TextView) row.findViewById(R.id.txtData);
	    txtSituacao = (TextView) row.findViewById(R.id.txtSituacao);
	    txtQtdItens= (TextView) row.findViewById(R.id.txtQtdItens);
	    
	    txtRequisicao.setText(pedido.getNumeroRequisicao().toString());
	    txtData.setText(new SimpleDateFormat("dd/MM/yyyy").format(pedido.getData()));
	    txtQtdItens.setText(pedido.getItens().size() + " itens");
	    String situacao = "";

	    switch(pedido.getSituacao()) {
	    	case PedidoEmExecucao.APROVADO:
	    		situacao = "Aprovado";
	    	break;
	    	
	    	case PedidoEmExecucao.PARCIALMENTE_APROVADO:
	    		situacao = "Parcialmente Aprovado";
    		break;
	    	case PedidoEmExecucao.CANCELADO:
	    		situacao = "Cancelado";
    		break;
	    }
	    txtSituacao.setText(situacao);
	    
		return row;
	}

}
