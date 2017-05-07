// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class ky$a extends kl$a
{
    private final BaseImplementation$b<DataReadResult> De;
    private int TB;
    private DataReadResult TC;
    
    private ky$a(final BaseImplementation$b<DataReadResult> de) {
        this.TB = 0;
        this.TC = null;
        this.De = de;
    }
    
    public void a(final DataReadResult tc) {
        synchronized (this) {
            Log.v("Fitness", "Received batch result");
            if (this.TC == null) {
                this.TC = tc;
            }
            else {
                this.TC.b(tc);
            }
            ++this.TB;
            if (this.TB == this.TC.jF()) {
                this.De.b(this.TC);
            }
        }
    }
}
