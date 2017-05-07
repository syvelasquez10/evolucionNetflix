// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.n$a;

class GoogleMap$3 extends n$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$OnMyLocationButtonClickListener aih;
    
    GoogleMap$3(final GoogleMap aif, final GoogleMap$OnMyLocationButtonClickListener aih) {
        this.aif = aif;
        this.aih = aih;
    }
    
    public boolean onMyLocationButtonClick() {
        return this.aih.onMyLocationButtonClick();
    }
}
