// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.ah;

class WearableListenerService$a$2 implements Runnable
{
    final /* synthetic */ WearableListenerService$a auW;
    final /* synthetic */ ah auX;
    
    WearableListenerService$a$2(final WearableListenerService$a auW, final ah auX) {
        this.auW = auW;
        this.auX = auX;
    }
    
    @Override
    public void run() {
        this.auW.auU.onMessageReceived(this.auX);
    }
}
