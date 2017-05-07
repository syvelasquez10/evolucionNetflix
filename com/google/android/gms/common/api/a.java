// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.data.DataHolder;

public abstract class a implements Releasable, Result
{
    protected final Status CM;
    protected final DataHolder IC;
    
    protected a(final DataHolder ic) {
        this.CM = new Status(ic.getStatusCode());
        this.IC = ic;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    @Override
    public void release() {
        if (this.IC != null) {
            this.IC.close();
        }
    }
}
