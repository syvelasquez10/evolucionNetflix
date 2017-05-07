// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.internal.r$a;

class StreetViewPanorama$3 extends r$a
{
    final /* synthetic */ StreetViewPanorama aiS;
    final /* synthetic */ StreetViewPanorama$OnStreetViewPanoramaClickListener aiU;
    
    StreetViewPanorama$3(final StreetViewPanorama aiS, final StreetViewPanorama$OnStreetViewPanoramaClickListener aiU) {
        this.aiS = aiS;
        this.aiU = aiU;
    }
    
    public void onStreetViewPanoramaClick(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.aiU.onStreetViewPanoramaClick(streetViewPanoramaOrientation);
    }
}
