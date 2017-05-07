// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.common.data.DataHolder;
import android.content.IntentFilter;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.DataApi;

public class ax extends ae.a
{
    private final DataApi.DataListener avM;
    private final MessageApi.MessageListener avN;
    private final NodeApi.NodeListener avO;
    private final IntentFilter[] avP;
    
    public ax(final DataApi.DataListener avM, final MessageApi.MessageListener avN, final NodeApi.NodeListener avO, final IntentFilter[] avP) {
        this.avM = avM;
        this.avN = avN;
        this.avO = avO;
        this.avP = avP;
    }
    
    public static ax a(final DataApi.DataListener dataListener, final IntentFilter[] array) {
        return new ax(dataListener, null, null, array);
    }
    
    public static ax a(final MessageApi.MessageListener messageListener, final IntentFilter[] array) {
        return new ax(null, messageListener, null, array);
    }
    
    public static ax a(final NodeApi.NodeListener nodeListener) {
        return new ax(null, null, nodeListener, null);
    }
    
    public void Z(final DataHolder dataHolder) {
        if (this.avM != null) {
            try {
                this.avM.onDataChanged(new DataEventBuffer(dataHolder));
                return;
            }
            finally {
                dataHolder.close();
            }
        }
        dataHolder.close();
    }
    
    public void a(final ah ah) {
        if (this.avN != null) {
            this.avN.onMessageReceived(ah);
        }
    }
    
    public void a(final ak ak) {
        if (this.avO != null) {
            this.avO.onPeerConnected(ak);
        }
    }
    
    public void b(final ak ak) {
        if (this.avO != null) {
            this.avO.onPeerDisconnected(ak);
        }
    }
    
    public IntentFilter[] pZ() {
        return this.avP;
    }
}
