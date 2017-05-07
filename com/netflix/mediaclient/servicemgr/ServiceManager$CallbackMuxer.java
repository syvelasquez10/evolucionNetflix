// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.Iterator;
import java.util.ArrayList;

class ServiceManager$CallbackMuxer
{
    private final ArrayList<ServiceManager$CallbackMuxer$MuxedCallback> muxedCallbacks;
    
    private ServiceManager$CallbackMuxer() {
        this.muxedCallbacks = new ArrayList<ServiceManager$CallbackMuxer$MuxedCallback>();
    }
    
    public ManagerCallback demuxCallback(final int n) {
        synchronized (this) {
            for (final ServiceManager$CallbackMuxer$MuxedCallback serviceManager$CallbackMuxer$MuxedCallback : this.muxedCallbacks) {
                if (serviceManager$CallbackMuxer$MuxedCallback.getRequestId() == n) {
                    this.muxedCallbacks.remove(serviceManager$CallbackMuxer$MuxedCallback);
                    return serviceManager$CallbackMuxer$MuxedCallback.getDemuxedCallback();
                }
            }
            return null;
        }
    }
    
    public int muxCallback(final ManagerCallback managerCallback) {
        synchronized (this) {
            final ServiceManager$CallbackMuxer$MuxedCallback serviceManager$CallbackMuxer$MuxedCallback = new ServiceManager$CallbackMuxer$MuxedCallback(managerCallback);
            this.muxedCallbacks.add(serviceManager$CallbackMuxer$MuxedCallback);
            return serviceManager$CallbackMuxer$MuxedCallback.getRequestId();
        }
    }
    
    public void reset() {
        synchronized (this) {
            this.muxedCallbacks.clear();
        }
    }
}
