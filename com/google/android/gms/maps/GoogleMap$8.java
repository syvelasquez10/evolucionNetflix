// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.internal.i$a;

class GoogleMap$8 extends i$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$OnMapClickListener aio;
    
    GoogleMap$8(final GoogleMap aif, final GoogleMap$OnMapClickListener aio) {
        this.aif = aif;
        this.aio = aio;
    }
    
    public void onMapClick(final LatLng latLng) {
        this.aio.onMapClick(latLng);
    }
}
