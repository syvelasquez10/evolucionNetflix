// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.internal.p$a;

class StreetViewPanorama$2 extends p$a
{
    final /* synthetic */ StreetViewPanorama aiS;
    final /* synthetic */ StreetViewPanorama$OnStreetViewPanoramaCameraChangeListener aiT;
    
    StreetViewPanorama$2(final StreetViewPanorama aiS, final StreetViewPanorama$OnStreetViewPanoramaCameraChangeListener aiT) {
        this.aiS = aiS;
        this.aiT = aiT;
    }
    
    public void onStreetViewPanoramaCameraChange(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.aiT.onStreetViewPanoramaCameraChange(streetViewPanoramaCamera);
    }
}
