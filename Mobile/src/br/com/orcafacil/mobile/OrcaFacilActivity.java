package br.com.orcafacil.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;
import br.com.orcafacil.integration.api.to.Credentials;
import br.com.orcafacil.mobile.dao.PedidoDAO;
import br.com.orcafacil.mobile.receiver.ConectivityReceiver;
import br.com.orcafacil.mobile.receiver.ConnectivityReceiverListener;
import br.com.orcafacil.mobile.service.PedidoService;

public class OrcaFacilActivity extends Activity implements ConnectivityReceiverListener {
	public static final String ARQUIVO_PREFERENCIAS = "auth_config";
	
	private ConectivityReceiver conectivityReceiver;
	
	private PedidoDAO pedidoDAO;
	private PedidoService pedidoService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		pedidoDAO = new PedidoDAO(this);
		pedidoService = new PedidoService();
		
		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        conectivityReceiver = new ConectivityReceiver();
        conectivityReceiver.addListener(this);
        registerReceiver(conectivityReceiver, filter);
	}
	
	private SharedPreferences getPreferences(){
		return getSharedPreferences(ARQUIVO_PREFERENCIAS, Context.MODE_PRIVATE);
	}
	
	private Editor getPreferencesEditor(){
		SharedPreferences prefs = getPreferences();
		return prefs.edit();
	}
	
	public void setCredentials(String email, String apiKey){
		SharedPreferences.Editor editor = getPreferencesEditor();
		editor.putString("email", email);
		editor.putString("api-key", apiKey);
		editor.commit();
	}
	
	public Credentials getApplicationCredentials(){
		Credentials credentials = null;
		if(getPreferences().getAll().get("email") != null){
			credentials = new Credentials();
			credentials.setEmail(getPreferences().getAll().get("email").toString());
			credentials.setApiKey(getPreferences().getAll().get("api-key").toString());			
		}
		return credentials;
	}
	
	@Override
	protected void onDestroy() {
		unregisterReceiver(conectivityReceiver);
		super.onDestroy();
	}

	@Override
	public void execute() {
		if(pedidoDAO.existemPedidosPendentes()){
			new AlertDialog.Builder(this)
			.setMessage("Você possui pedidos salvos no seu dispositivo, deseja enviá-los?")
			.setCancelable(false)
			.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					pedidoService.salvarLote(getApplicationCredentials().getApiKey(), pedidoDAO.getAll());
					pedidoDAO.deleteAll();
					Toast.makeText(OrcaFacilActivity.this, "Pedidos enviados com sucesso.", Toast.LENGTH_LONG).show();
				}
			})
			.setNegativeButton("Não", null)
			.create()
			.show();	
		}
	}
}
