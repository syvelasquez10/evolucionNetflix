// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api$a;
import android.content.Context;
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.common.api.BaseImplementation$a;
import com.google.android.gms.common.api.Result;

abstract class nb$d<R extends Result> extends BaseImplementation$a<R, nc>
{
    protected nb$d() {
        super(Panorama.CU);
    }
    
    protected abstract void a(final Context p0, final na p1);
    
    @Override
    protected final void a(final nc nc) {
        this.a(nc.getContext(), nc.gS());
    }
}
