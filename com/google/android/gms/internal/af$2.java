// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONObject;

class af$2 implements Runnable
{
    final /* synthetic */ af mT;
    final /* synthetic */ JSONObject mU;
    
    af$2(final af mt, final JSONObject mu) {
        this.mT = mt;
        this.mU = mu;
    }
    
    @Override
    public void run() {
        this.mT.a(this.mU);
    }
}
