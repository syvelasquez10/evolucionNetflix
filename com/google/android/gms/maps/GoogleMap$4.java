// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.j$a;

class GoogleMap$4 extends j$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$OnMapLoadedCallback aii;
    
    GoogleMap$4(final GoogleMap aif, final GoogleMap$OnMapLoadedCallback aii) {
        this.aif = aif;
        this.aii = aii;
    }
    
    public void onMapLoaded() {
        this.aii.onMapLoaded();
    }
}
