package br.com.orcafacil.mobile;

import com.br.orcafacil.mobile.R;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends ActivityGroup {
	private TabHost tabHost;
	
	public static final String TB_INICIAL = "inicial";
	public static final String TB_MEUS_PEDIDOS = "meus_pedidos";
	public static final String TB_NOVO_PEDIDO = "novo_pedido";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Resources res = getResources();
		tabHost = (TabHost) findViewById(R.id.tabHostPrincipal);
		tabHost.setup(this.getLocalActivityManager());
		TabHost.TabSpec spec;
		Intent intent;
		
		intent = new Intent().setClass(this, HomeActivity.class);
		spec = tabHost.newTabSpec(TB_INICIAL).setIndicator("Inicial",res.getDrawable(R.drawable.home))
				.setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this, ConsultaPedidosActivity.class);
		spec = tabHost.newTabSpec(TB_MEUS_PEDIDOS).setIndicator("Meus Pedidos",res.getDrawable(R.drawable.consulta_pedidos))
				.setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this, NovoPedidoActivity.class);
		spec = tabHost.newTabSpec(TB_NOVO_PEDIDO).setIndicator("Novo Pedido",res.getDrawable(R.drawable.novo_pedido))
				.setContent(intent);
		tabHost.addTab(spec);
		
		tabHost.setCurrentTabByTag(TB_INICIAL);
	}
	
}