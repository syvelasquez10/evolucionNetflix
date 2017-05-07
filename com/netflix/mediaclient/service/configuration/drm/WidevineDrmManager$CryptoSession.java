// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.Log;

class WidevineDrmManager$CryptoSession
{
    public byte[] kceKeyId;
    public byte[] kchKeyId;
    public byte[] pendingSessionId;
    public byte[] sessionId;
    
    public byte[] activatePendingSessionId() {
        byte[] sessionId = null;
        synchronized (this) {
            if (this.pendingSessionId == null) {
                Log.e(WidevineDrmManager.TAG, "Pending session does NOT exist! Do nothing!");
            }
            else {
                Log.d(WidevineDrmManager.TAG, "Pending session does exist! Move pending to current session id and return old!");
                sessionId = this.sessionId;
                this.sessionId = this.pendingSessionId;
                this.pendingSessionId = null;
            }
            return sessionId;
        }
    }
    
    public void reset() {
        synchronized (this) {
            this.pendingSessionId = null;
            this.sessionId = null;
            this.kceKeyId = null;
            this.kchKeyId = null;
        }
    }
}
