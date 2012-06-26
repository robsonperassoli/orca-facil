package br.com.orcafacil.mobile.adapters;

import java.util.List;

import com.br.orcafacil.mobile.R;

import br.com.orcafacil.integration.api.to.ItemDoPedidoEmExecucao;
import br.com.orcafacil.integration.api.to.Produto;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemDoPedidoEmExecucaoArrayAdapter extends ArrayAdapter<ItemDoPedidoEmExecucao> {

	private TextView txtNomeProduto;
	private TextView txtFornecedor;
	
	public ItemDoPedidoEmExecucaoArrayAdapter(Context context, int textViewResourceId,
			List<ItemDoPedidoEmExecucao> objects) {
		super(context, textViewResourceId, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
	    if (row == null) {
	      LayoutInflater inflater = (LayoutInflater) this.getContext()
	          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      
	      row = inflater.inflate(R.layout.item_pedido_visualizacao, parent, false);
	    }

	    ItemDoPedidoEmExecucao item = getItem(position);

	    txtNomeProduto = (TextView) row.findViewById(R.id.txtNomeProduto);
	    txtFornecedor = (TextView) row.findViewById(R.id.txtFornecedor);
	    
	    txtNomeProduto.setText(item.getProduto().getNome());
	    txtFornecedor.setText(item.getFornecedor().getNome());
	    
		return row;
	}

}
