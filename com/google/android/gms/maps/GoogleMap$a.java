// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.b$a;

final class GoogleMap$a extends b$a
{
    private final GoogleMap$CancelableCallback aiu;
    
    GoogleMap$a(final GoogleMap$CancelableCallback aiu) {
        this.aiu = aiu;
    }
    
    public void onCancel() {
        this.aiu.onCancel();
    }
    
    public void onFinish() {
        this.aiu.onFinish();
    }
}
