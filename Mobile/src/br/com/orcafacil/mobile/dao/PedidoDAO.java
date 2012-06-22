package br.com.orcafacil.mobile.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.orcafacil.integration.api.to.ItemDoPedido;
import br.com.orcafacil.integration.api.to.Pedido;
import br.com.orcafacil.mobile.db.DataBaseHelper;

public class PedidoDAO {
	private SQLiteDatabase db; 
	
	private ProdutoDAO produtoDAO;
	private ItemDoPedidoDAO itemDoPedidoDAO;
	
	private String[] colunasPedido = new String[]{
			"PED_CODIGO",
			"PED_DATA",
			"PED_LATITUDE",
			"PED_LONGITUDE"
	}; 
	
	public PedidoDAO(Context context) {
		this.db = DataBaseHelper.getHelper(context).getWritableDatabase();
		produtoDAO = new ProdutoDAO(context);
		itemDoPedidoDAO = new ItemDoPedidoDAO(context);
	}
	
	public void insert(Pedido pedido) {
		ContentValues values = new ContentValues();
		Integer idPedido = getNextId();
		values.put("PED_CODIGO", idPedido);
		values.put("PED_DATA", pedido.getData().getTime());
		values.put("PED_LATITUDE", pedido.getLatitude());
		values.put("PED_LONGITUDE", pedido.getLongitude());
		
		db.beginTransaction();
		try{
			db.insert("PEDIDO", null, values);
			for(ItemDoPedido item : pedido.getItens()) {
				itemDoPedidoDAO.insert(item, idPedido);
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();			
		}
		
	}
	
	public List<Pedido> getAll(){
		Cursor c = db.query("PEDIDO", colunasPedido, null, null, null, null, null);
		c.moveToFirst();
		List<Pedido> pedidos = new ArrayList<Pedido>();
		while(!c.isAfterLast()) {
			Pedido p = new Pedido();
			p.setData(new Date(c.getLong(1)));
			p.setLatitude(c.getLong(2));
			p.setLongitude(c.getLong(3));
			Integer idPedido = c.getInt(0);
			p.setItens(itemDoPedidoDAO.getAll(idPedido));
			pedidos.add(p);
			c.moveToNext();
		}
		return pedidos;
	}
	
	private Integer getNextId(){
		Cursor c = db.query("PEDIDO", new String[]{"max(PED_CODIGO)"}, null, null, null, null, null);
		c.moveToFirst();
		return c.getInt(0) + 1;
	}
	
	public boolean existemPedidosPendentes(){
		Cursor c = db.query("PEDIDO", new String[]{"PED_CODIGO"}, null, null, null, null, null);
		c.moveToFirst();
		return c.getCount() > 0;
	}

	public void deleteAll() {
		db.execSQL("delete from PEDIDO_ITEM");
		db.execSQL("delete from PEDIDO");
	}
}
