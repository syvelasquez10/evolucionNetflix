// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.content.Context;
import android.os.Message;
import android.os.Handler$Callback;

class cy$2 implements Handler$Callback
{
    final /* synthetic */ cy arq;
    
    cy$2(final cy arq) {
        this.arq = arq;
    }
    
    public boolean handleMessage(final Message message) {
        if (1 == message.what && cy.yc.equals(message.obj)) {
            this.arq.dispatch();
            if (this.arq.ari > 0 && !this.arq.aro) {
                this.arq.handler.sendMessageDelayed(this.arq.handler.obtainMessage(1, cy.yc), (long)this.arq.ari);
            }
        }
        return true;
    }
}
