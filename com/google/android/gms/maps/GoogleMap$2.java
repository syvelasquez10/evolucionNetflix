// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.dynamic.e;
import android.location.Location;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.internal.o$a;

class GoogleMap$2 extends o$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$OnMyLocationChangeListener aig;
    
    GoogleMap$2(final GoogleMap aif, final GoogleMap$OnMyLocationChangeListener aig) {
        this.aif = aif;
        this.aig = aig;
    }
    
    public void g(final d d) {
        this.aig.onMyLocationChange(e.f(d));
    }
}
