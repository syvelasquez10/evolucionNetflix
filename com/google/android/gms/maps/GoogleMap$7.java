// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.internal.e$a;

class GoogleMap$7 extends e$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ GoogleMap$OnCameraChangeListener ain;
    
    GoogleMap$7(final GoogleMap aif, final GoogleMap$OnCameraChangeListener ain) {
        this.aif = aif;
        this.ain = ain;
    }
    
    public void onCameraChange(final CameraPosition cameraPosition) {
        this.ain.onCameraChange(cameraPosition);
    }
}
