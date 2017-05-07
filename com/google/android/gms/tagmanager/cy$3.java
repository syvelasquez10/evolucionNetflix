// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Handler$Callback;
import android.os.Handler;
import android.content.Context;

class cy$3 implements Runnable
{
    final /* synthetic */ cy arq;
    
    cy$3(final cy arq) {
        this.arq = arq;
    }
    
    @Override
    public void run() {
        this.arq.arg.dispatch();
    }
}
