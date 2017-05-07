// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.dynamic.e;
import android.graphics.Bitmap;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.internal.s$a;

class GoogleMap$5 extends s$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$SnapshotReadyCallback aij;
    
    GoogleMap$5(final GoogleMap aif, final GoogleMap$SnapshotReadyCallback aij) {
        this.aif = aif;
        this.aij = aij;
    }
    
    public void h(final d d) {
        this.aij.onSnapshotReady(e.f(d));
    }
    
    public void onSnapshotReady(final Bitmap bitmap) {
        this.aij.onSnapshotReady(bitmap);
    }
}
