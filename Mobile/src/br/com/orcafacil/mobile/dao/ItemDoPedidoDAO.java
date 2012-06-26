package br.com.orcafacil.mobile.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.orcafacil.integration.api.to.ItemDoPedido;
import br.com.orcafacil.integration.api.to.Pedido;
import br.com.orcafacil.mobile.db.DataBaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemDoPedidoDAO {
	private SQLiteDatabase db;
	
	private ProdutoDAO produtoDAO;
	
	public ItemDoPedidoDAO(Context context) {
		db = DataBaseHelper.getHelper(context).getWritableDatabase();
		produtoDAO = new ProdutoDAO(context);
	}
	
	public void insert(ItemDoPedido item, Integer idPedido) {
		if(!produtoDAO.exists(item.getProduto().getId()))
			produtoDAO.insert(item.getProduto());
		
		ContentValues values = new ContentValues();
		values.put("PED_CODIGO", idPedido);
		values.put("PEI_ITEM", item.getItem());
		values.put("PRO_ID", item.getProduto().getId());
		values.put("PEI_QUANTIDADE", item.getQuantidade());
		db.insert("PEDIDO_ITEM", null, values);
	}

	public List<ItemDoPedido> getAll(Integer idPedido) {
		Cursor c = db.query("PEDIDO_ITEM", new String[]{"PEI_ITEM","PRO_ID","PEI_QUANTIDADE"}, null, null, null, null, null);
		c.moveToFirst();
		List<ItemDoPedido> itens = new ArrayList<ItemDoPedido>();
		while(!c.isAfterLast()) {
			ItemDoPedido item = new ItemDoPedido();
			item.setItem(c.getInt(0));
			item.setProduto(produtoDAO.load(c.getLong(1)));
			item.setQuantidade(c.getDouble(2));
			itens.add(item);
			c.moveToNext();
		}
		return itens;
	}
}
