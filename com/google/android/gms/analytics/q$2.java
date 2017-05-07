// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.os.Handler;
import android.content.Context;
import android.os.Message;
import android.os.Handler$Callback;

class q$2 implements Handler$Callback
{
    final /* synthetic */ q yp;
    
    q$2(final q yp) {
        this.yp = yp;
    }
    
    public boolean handleMessage(final Message message) {
        if (1 == message.what && q.yc.equals(message.obj)) {
            t.eq().B(true);
            this.yp.dispatchLocalHits();
            t.eq().B(false);
            if (this.yp.yf > 0 && !this.yp.yn) {
                this.yp.mHandler.sendMessageDelayed(this.yp.mHandler.obtainMessage(1, q.yc), (long)(this.yp.yf * 1000));
            }
        }
        return true;
    }
}
