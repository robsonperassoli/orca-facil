package br.com.orcafacil.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.orcafacil.mobile.service.AutenticationService;

import com.br.orcafacil.mobile.R;

public class AuthConfigActivity extends OrcaFacilActivity 
			implements View.OnClickListener {
    
	private EditText edtEmail;
	private EditText edtApiKey;
	
	private Button btAplicarConfig;
	
	private AutenticationService autenticationService;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_config);
        
        if (getApplicationCredentials() != null)
			irParaMenuPrincipal();
        
        autenticationService = new AutenticationService();
        
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtApiKey = (EditText) findViewById(R.id.edtApiKey);
        
        btAplicarConfig = (Button) findViewById(R.id.btAplicarConfig);
        btAplicarConfig.setOnClickListener(this);
        
    }

	private void irParaMenuPrincipal() {
		Intent itMenu = new Intent();
		itMenu.setClass(AuthConfigActivity.this, MainActivity.class);
		startActivity(itMenu);
		finish();
	}

	@Override
	public void onClick(View view) {
		try {
			String email = edtEmail.getText().toString();
			String apiKey = edtApiKey.getText().toString();
			if(email.isEmpty() || apiKey.isEmpty())
				throw new Exception("Os campos E-mail e API Key não podem estar em branco");
			
			boolean autenticado = autenticationService.autenticar(email, apiKey);
			
			if(autenticado){
				setCredentials(email, apiKey);
				irParaMenuPrincipal();
			} else
				throw new Exception("Erro na autenticação, os dados informados são inválidos.");
			
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		
	}

}