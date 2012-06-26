package br.com.orcafacil.mobile.adapters;

import java.util.List;

import com.br.orcafacil.mobile.R;

import br.com.orcafacil.integration.api.to.Produto;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ProdutoArrayAdapter extends ArrayAdapter<Produto> {

	private static final String tag = "ProdutoArrayAdapter";
	
	private TextView txtNomeProduto;
	private TextView txtIdProduto;
	
	public ProdutoArrayAdapter(Context context, int textViewResourceId,
			List<Produto> objects) {
		super(context, textViewResourceId, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
	    if (row == null) {
	      LayoutInflater inflater = (LayoutInflater) this.getContext()
	          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      
	      row = inflater.inflate(R.layout.item_produto, parent, false);
	    }

	    Produto produto = getItem(position);

	    txtNomeProduto = (TextView) row.findViewById(R.id.txtNomeProduto);
	    txtIdProduto = (TextView) row.findViewById(R.id.txtIdProduto);
	    
	    txtNomeProduto.setText(produto.getNome());
	    txtIdProduto.setText(produto.getId().toString());
	    
		return row;
	}

}
