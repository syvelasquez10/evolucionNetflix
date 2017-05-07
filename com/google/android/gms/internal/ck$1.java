// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;

class ck$1 implements Runnable
{
    final /* synthetic */ cq pU;
    final /* synthetic */ ck pV;
    
    ck$1(final ck pv, final cq pu) {
        this.pV = pv;
        this.pU = pu;
    }
    
    @Override
    public void run() {
        try {
            this.pU.qz.destroy();
        }
        catch (RemoteException ex) {
            gs.d("Could not destroy mediation adapter.", (Throwable)ex);
        }
    }
}
