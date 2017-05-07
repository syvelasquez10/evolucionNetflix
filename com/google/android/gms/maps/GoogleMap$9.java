// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.internal.k$a;

class GoogleMap$9 extends k$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$OnMapLongClickListener aip;
    
    GoogleMap$9(final GoogleMap aif, final GoogleMap$OnMapLongClickListener aip) {
        this.aif = aif;
        this.aip = aip;
    }
    
    public void onMapLongClick(final LatLng latLng) {
        this.aip.onMapLongClick(latLng);
    }
}
