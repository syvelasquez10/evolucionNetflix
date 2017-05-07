// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.receiver;

import java.util.Iterator;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public final class NetworkMonitorRepository
{
    private static final String TAG = "nf_net";
    private static NetworkMonitorRepository instance;
    private final List<NetworkMonitorListener> listeners;
    
    static {
        NetworkMonitorRepository.instance = new NetworkMonitorRepository();
    }
    
    private NetworkMonitorRepository() {
        this.listeners = Collections.synchronizedList(new ArrayList<NetworkMonitorListener>());
    }
    
    public static final NetworkMonitorRepository getInstance() {
        return NetworkMonitorRepository.instance;
    }
    
    public void addListener(final NetworkMonitorListener networkMonitorListener) {
        synchronized (this) {
            if (Log.isLoggable("nf_net", 3)) {
                Log.d("nf_net", "Adding listener: " + networkMonitorListener);
            }
            this.listeners.add(networkMonitorListener);
        }
    }
    
    public final void connectivityChange(final Intent intent) {
        // monitorenter(this)
        Label_0017: {
            if (intent != null) {
                break Label_0017;
            }
            while (true) {
                try {
                    Log.e("nf_net", "Intent can not be null!");
                    return;
                    // iftrue(Label_0052:, this.listeners != null && !this.listeners.isEmpty())
                    Log.d("nf_net", "Nobody is listening for connectivity changes.");
                    return;
                }
                finally {
                }
                // monitorexit(this)
                final Iterator<NetworkMonitorListener> iterator;
                Label_0052: {
                    iterator = this.listeners.iterator();
                }
                while (iterator.hasNext()) {
                    final Intent intent2;
                    new BackgroundTask().execute(new Runnable() {
                        final /* synthetic */ NetworkMonitorListener val$fListener = iterator.next();
                        
                        @Override
                        public void run() {
                            if (Log.isLoggable("nf_net", 3)) {
                                Log.d("nf_net", "Alerting: " + this.val$fListener);
                            }
                            this.val$fListener.connectivityChange(intent2);
                        }
                    });
                }
            }
        }
    }
    
    public boolean removeListener(final NetworkMonitorListener networkMonitorListener) {
        synchronized (this) {
            if (Log.isLoggable("nf_net", 3)) {
                Log.d("nf_net", "Removing listener: " + networkMonitorListener);
            }
            return this.listeners.remove(networkMonitorListener);
        }
    }
}
