/**
 * 
 */
package br.com.orcafacil.mobile.receiver;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConectivityReceiver extends BroadcastReceiver {


	private List<ConnectivityReceiverListener> listeners = new ArrayList<ConnectivityReceiverListener>();
	
	public void addListener(ConnectivityReceiverListener listener){
		listeners.add(listener);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		boolean noConnectivity = intent.getBooleanExtra(
				ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
		String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
		boolean isFailover = intent.getBooleanExtra(
				ConnectivityManager.EXTRA_IS_FAILOVER, false);
		NetworkInfo currentNetworkInfo = (NetworkInfo) intent
				.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
		NetworkInfo otherNetworkInfo = (NetworkInfo) intent
				.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);
		if (noConnectivity || isFailover) {
			Log.w("RECEIVER", "Sem conectividade!");
		} else {
			for(ConnectivityReceiverListener listener : listeners) {
				listener.execute();
			}
		}
	}
}