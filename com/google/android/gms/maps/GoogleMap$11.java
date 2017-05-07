// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.internal.m$a;

class GoogleMap$11 extends m$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$OnMarkerDragListener air;
    
    GoogleMap$11(final GoogleMap aif, final GoogleMap$OnMarkerDragListener air) {
        this.aif = aif;
        this.air = air;
    }
    
    public void b(final f f) {
        this.air.onMarkerDragStart(new Marker(f));
    }
    
    public void c(final f f) {
        this.air.onMarkerDragEnd(new Marker(f));
    }
    
    public void d(final f f) {
        this.air.onMarkerDrag(new Marker(f));
    }
}
