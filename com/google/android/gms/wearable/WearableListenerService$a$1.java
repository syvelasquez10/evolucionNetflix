// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import com.google.android.gms.common.data.DataHolder;

class WearableListenerService$a$1 implements Runnable
{
    final /* synthetic */ DataHolder auV;
    final /* synthetic */ WearableListenerService$a auW;
    
    WearableListenerService$a$1(final WearableListenerService$a auW, final DataHolder auV) {
        this.auW = auW;
        this.auV = auV;
    }
    
    @Override
    public void run() {
        final DataEventBuffer dataEventBuffer = new DataEventBuffer(this.auV);
        try {
            this.auW.auU.onDataChanged(dataEventBuffer);
        }
        finally {
            dataEventBuffer.release();
        }
    }
}
