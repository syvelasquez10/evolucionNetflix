// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.n;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

final class c$a extends Handler
{
    final /* synthetic */ c Jm;
    
    public c$a(final c jm, final Looper looper) {
        this.Jm = jm;
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        boolean b = true;
        if (message.what != 1) {
            b = false;
        }
        n.K(b);
        this.Jm.b((c$b)message.obj);
    }
}
