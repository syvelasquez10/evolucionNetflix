// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import android.media.NotProvisionedException;

class NfDrmManager$5 implements Runnable
{
    final /* synthetic */ NfDrmManager this$0;
    final /* synthetic */ byte[] val$sessionId;
    
    NfDrmManager$5(final NfDrmManager this$0, final byte[] val$sessionId) {
        this.this$0 = this$0;
        this.val$sessionId = val$sessionId;
    }
    
    @Override
    public void run() {
        final NfDrmSession drmSessionWithSessionId = this.this$0.getDrmSessionWithSessionId(this.val$sessionId);
        if (drmSessionWithSessionId == null) {
            return;
        }
        try {
            drmSessionWithSessionId.postKeyRequest();
        }
        catch (NotProvisionedException ex) {}
    }
}
