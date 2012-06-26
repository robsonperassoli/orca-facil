package br.com.orcafacil.mobile.util;

import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class WSUtil {
	public Object get(Class resourceClass){
		try {
			RegisterBuiltin.registerProviders(ResteasyProviderFactory.getInstance());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao registrar provider", e);
		}
        return ProxyFactory.create(resourceClass, "http://192.168.1.102:8084/rest/");
	}
}
