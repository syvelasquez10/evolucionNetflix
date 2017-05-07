// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.cast.Cast$ApplicationConnectionResult;

final class ij$a implements Cast$ApplicationConnectionResult
{
    private final Status CM;
    private final ApplicationMetadata GN;
    private final String GO;
    private final boolean GP;
    private final String vL;
    
    public ij$a(final Status status) {
        this(status, null, null, null, false);
    }
    
    public ij$a(final Status cm, final ApplicationMetadata gn, final String go, final String vl, final boolean gp) {
        this.CM = cm;
        this.GN = gn;
        this.GO = go;
        this.vL = vl;
        this.GP = gp;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
