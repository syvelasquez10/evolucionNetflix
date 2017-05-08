// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.net;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.transport.NativeTransport;

public class DnsManager
{
    private static final String TAG = "nf_dns";
    private static DnsManager sInstance;
    private String[] mDnsServers;
    
    static {
        DnsManager.sInstance = new DnsManager();
    }
    
    public static DnsManager getInstance() {
        return DnsManager.sInstance;
    }
    
    public void cacheFlush() {
        synchronized (this) {
            this.mDnsServers = NativeTransport.getDnsList();
        }
    }
    
    public String[] getNameServers() {
        synchronized (this) {
            if (this.mDnsServers == null) {
                this.mDnsServers = NativeTransport.getDnsList();
            }
            if (Log.isLoggable()) {
                final String[] mDnsServers = this.mDnsServers;
                for (int length = mDnsServers.length, i = 0; i < length; ++i) {
                    Log.d("nf_dns", "ns:" + mDnsServers[i]);
                }
            }
            return this.mDnsServers;
        }
    }
}
