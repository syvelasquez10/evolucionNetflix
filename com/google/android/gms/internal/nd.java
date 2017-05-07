// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.n;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.panorama.PanoramaApi$PanoramaResult;

class nd implements PanoramaApi$PanoramaResult
{
    private final Status CM;
    private final Intent akr;
    
    public nd(final Status status, final Intent akr) {
        this.CM = n.i(status);
        this.akr = akr;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    @Override
    public Intent getViewerIntent() {
        return this.akr;
    }
}
