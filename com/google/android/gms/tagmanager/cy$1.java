// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Handler$Callback;
import android.os.Handler;
import android.content.Context;

class cy$1 implements au
{
    final /* synthetic */ cy arq;
    
    cy$1(final cy arq) {
        this.arq = arq;
    }
    
    @Override
    public void z(final boolean b) {
        this.arq.a(b, this.arq.connected);
    }
}
