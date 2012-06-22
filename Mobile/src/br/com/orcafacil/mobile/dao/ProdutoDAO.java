package br.com.orcafacil.mobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.orcafacil.integration.api.to.Produto;
import br.com.orcafacil.mobile.db.DataBaseHelper;

public class ProdutoDAO {
	
	private SQLiteDatabase db;
	
	public ProdutoDAO(Context context) {
		this.db = DataBaseHelper.getHelper(context).getWritableDatabase();
	}
	
	public Produto load(Long id) {
		Cursor c = db.query("PRODUTO", new String[]{"PRO_ID", "PRO_NOME"}, "PRO_ID = ?", new String[]{id.toString()}, null, null, null);
		c.moveToFirst();
		Produto produto = new Produto();
		produto.setId(c.getLong(0));
		produto.setNome(c.getString(1));
		return produto;
	}
	
	public void insert(Produto p) {
		ContentValues values = new ContentValues();
		values.put("PRO_ID", p.getId());
		values.put("PRO_NOME", p.getNome());
		db.insert("PRODUTO", null, values);

	}
	
	public boolean exists(Long id){
		Cursor c = db.query("PRODUTO", new String[]{"PRO_ID"}, "PRO_ID = ?", new String[]{id.toString()}, null, null, null);
		c.moveToFirst();
		return c.getCount() > 0;
	}
}
