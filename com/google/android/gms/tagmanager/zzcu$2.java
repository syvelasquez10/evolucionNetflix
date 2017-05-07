// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Handler$Callback;

class zzcu$2 implements Handler$Callback
{
    final /* synthetic */ zzcu zzaSj;
    
    zzcu$2(final zzcu zzaSj) {
        this.zzaSj = zzaSj;
    }
    
    public boolean handleMessage(final Message message) {
        if (1 == message.what && zzcu.zzaRX.equals(message.obj)) {
            this.zzaSj.dispatch();
            if (this.zzaSj.zzaSb > 0 && !this.zzaSj.zzaSh) {
                this.zzaSj.handler.sendMessageDelayed(this.zzaSj.handler.obtainMessage(1, zzcu.zzaRX), (long)this.zzaSj.zzaSb);
            }
        }
        return true;
    }
}
