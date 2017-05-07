// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.internal.l$a;

class GoogleMap$10 extends l$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$OnMarkerClickListener aiq;
    
    GoogleMap$10(final GoogleMap aif, final GoogleMap$OnMarkerClickListener aiq) {
        this.aif = aif;
        this.aiq = aiq;
    }
    
    public boolean a(final f f) {
        return this.aiq.onMarkerClick(new Marker(f));
    }
}
