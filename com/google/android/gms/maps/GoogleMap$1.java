// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.internal.d;
import com.google.android.gms.maps.internal.f$a;

class GoogleMap$1 extends f$a
{
    final /* synthetic */ GoogleMap$OnIndoorStateChangeListener aie;
    final /* synthetic */ GoogleMap aif;
    
    GoogleMap$1(final GoogleMap aif, final GoogleMap$OnIndoorStateChangeListener aie) {
        this.aif = aif;
        this.aie = aie;
    }
    
    public void a(final d d) {
        this.aie.onIndoorLevelActivated(new IndoorBuilding(d));
    }
    
    public void onIndoorBuildingFocused() {
        this.aie.onIndoorBuildingFocused();
    }
}
