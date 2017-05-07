// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.internal.f;
import com.google.android.gms.maps.internal.g$a;

class GoogleMap$12 extends g$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$OnInfoWindowClickListener ais;
    
    GoogleMap$12(final GoogleMap aif, final GoogleMap$OnInfoWindowClickListener ais) {
        this.aif = aif;
        this.ais = ais;
    }
    
    public void e(final f f) {
        this.ais.onInfoWindowClick(new Marker(f));
    }
}
