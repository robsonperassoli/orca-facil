package br.com.orcafacil.mobile.adapters;

import java.util.List;

import com.br.orcafacil.mobile.R;

import br.com.orcafacil.integration.api.to.ItemDoPedido;
import br.com.orcafacil.integration.api.to.Produto;
import br.com.orcafacil.mobile.util.ConverterUtils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemDoPedidoArrayAdapter extends ArrayAdapter<ItemDoPedido> {

	private static final String tag = "ItemDoPedidoArrayAdapter";
	
	private TextView txtDescricao;
	private TextView txtQuantidade;
	private TextView txtItem;
	
	public ItemDoPedidoArrayAdapter(Context context, int textViewResourceId,
			List<ItemDoPedido> objects) {
		super(context, textViewResourceId, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
	    if (row == null) {
	      LayoutInflater inflater = (LayoutInflater) this.getContext()
	          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      
	      row = inflater.inflate(R.layout.item_pedido, parent, false);
	    }

	    ItemDoPedido item = getItem(position);

	    txtDescricao = (TextView) row.findViewById(R.id.txtDescricao);
	    txtQuantidade = (TextView) row.findViewById(R.id.txtQuantidade);
	    txtItem = (TextView) row.findViewById(R.id.txtItem);
	    
	    txtDescricao.setText(item.getProduto().getNome());
	    txtQuantidade.setText(new ConverterUtils().toString(item.getQuantidade()));
	    txtItem.setText(item.getItem().toString());
	    
		return row;
	}

}
