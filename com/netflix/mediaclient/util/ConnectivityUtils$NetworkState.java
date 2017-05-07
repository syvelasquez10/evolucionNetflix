// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.net.LogMobileType;

public class ConnectivityUtils$NetworkState
{
    public boolean connected;
    public LogMobileType currentConnectionType;
    public String ipAddr;
    public String sid;
    public boolean wifi;
    
    public ConnectivityUtils$NetworkState(final boolean connected, final boolean wifi, final String sid) {
        this.connected = connected;
        this.wifi = wifi;
        this.sid = sid;
    }
    
    @Override
    public String toString() {
        return "NetworkState [connected=" + this.connected + ", wifi=" + this.wifi + ", sid=" + this.sid + ", currentConnectionType=" + this.currentConnectionType + ", ipAddr=" + this.ipAddr + "]";
    }
}
