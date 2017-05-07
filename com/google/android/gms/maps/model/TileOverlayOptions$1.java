// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.maps.model.internal.i$a;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.i;

class TileOverlayOptions$1 implements TileProvider
{
    private final i akj;
    final /* synthetic */ TileOverlayOptions akk;
    
    TileOverlayOptions$1(final TileOverlayOptions akk) {
        this.akk = akk;
        this.akj = this.akk.akg;
    }
    
    @Override
    public Tile getTile(final int n, final int n2, final int n3) {
        try {
            return this.akj.getTile(n, n2, n3);
        }
        catch (RemoteException ex) {
            return null;
        }
    }
}
