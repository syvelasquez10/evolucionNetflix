// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

class ServiceManager$CallbackMuxer$MuxedCallback
{
    private static int sRequestIdCounter;
    private final ManagerCallback callback;
    private final int requestId;
    
    static {
        ServiceManager$CallbackMuxer$MuxedCallback.sRequestIdCounter = 0;
    }
    
    public ServiceManager$CallbackMuxer$MuxedCallback(final ManagerCallback callback) {
        ++ServiceManager$CallbackMuxer$MuxedCallback.sRequestIdCounter;
        this.requestId = ServiceManager$CallbackMuxer$MuxedCallback.sRequestIdCounter;
        this.callback = callback;
    }
    
    public ManagerCallback getDemuxedCallback() {
        return this.callback;
    }
    
    public int getRequestId() {
        return this.requestId;
    }
}
