// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.ak;

class WearableListenerService$a$3 implements Runnable
{
    final /* synthetic */ WearableListenerService$a auW;
    final /* synthetic */ ak auY;
    
    WearableListenerService$a$3(final WearableListenerService$a auW, final ak auY) {
        this.auW = auW;
        this.auY = auY;
    }
    
    @Override
    public void run() {
        this.auW.auU.onPeerConnected(this.auY);
    }
}
