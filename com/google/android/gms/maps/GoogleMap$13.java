// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.dynamic.e;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.internal.d$a;

class GoogleMap$13 extends d$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$InfoWindowAdapter ait;
    
    GoogleMap$13(final GoogleMap aif, final GoogleMap$InfoWindowAdapter ait) {
        this.aif = aif;
        this.ait = ait;
    }
    
    public com.google.android.gms.dynamic.d f(final f f) {
        return e.k(this.ait.getInfoWindow(new Marker(f)));
    }
    
    public com.google.android.gms.dynamic.d g(final f f) {
        return e.k(this.ait.getInfoContents(new Marker(f)));
    }
}
