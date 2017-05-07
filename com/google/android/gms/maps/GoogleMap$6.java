// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.h;
import com.google.android.gms.maps.internal.ILocationSourceDelegate$a;

class GoogleMap$6 extends ILocationSourceDelegate$a
{
    final /* synthetic */ GoogleMap aif;
    final /* synthetic */ LocationSource aik;
    
    GoogleMap$6(final GoogleMap aif, final LocationSource aik) {
        this.aif = aif;
        this.aik = aik;
    }
    
    public void activate(final h h) {
        this.aik.activate(new GoogleMap$6$1(this, h));
    }
    
    public void deactivate() {
        this.aik.deactivate();
    }
}
