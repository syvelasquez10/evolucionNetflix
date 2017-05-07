// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.internal.i$a;

class TileOverlayOptions$2 extends i$a
{
    final /* synthetic */ TileOverlayOptions akk;
    final /* synthetic */ TileProvider akl;
    
    TileOverlayOptions$2(final TileOverlayOptions akk, final TileProvider akl) {
        this.akk = akk;
        this.akl = akl;
    }
    
    public Tile getTile(final int n, final int n2, final int n3) {
        return this.akl.getTile(n, n2, n3);
    }
}
