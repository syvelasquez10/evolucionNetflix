// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.internal.q$a;

class StreetViewPanorama$1 extends q$a
{
    final /* synthetic */ StreetViewPanorama$OnStreetViewPanoramaChangeListener aiR;
    final /* synthetic */ StreetViewPanorama aiS;
    
    StreetViewPanorama$1(final StreetViewPanorama aiS, final StreetViewPanorama$OnStreetViewPanoramaChangeListener aiR) {
        this.aiS = aiS;
        this.aiR = aiR;
    }
    
    public void onStreetViewPanoramaChange(final StreetViewPanoramaLocation streetViewPanoramaLocation) {
        this.aiR.onStreetViewPanoramaChange(streetViewPanoramaLocation);
    }
}
