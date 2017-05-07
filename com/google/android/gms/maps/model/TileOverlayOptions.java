// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.maps.model.internal.i$a;
import android.os.IBinder;
import com.google.android.gms.maps.model.internal.i;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class TileOverlayOptions implements SafeParcelable
{
    public static final w CREATOR;
    private final int BR;
    private float ajA;
    private boolean ajB;
    private i akg;
    private TileProvider akh;
    private boolean aki;
    
    static {
        CREATOR = new w();
    }
    
    public TileOverlayOptions() {
        this.ajB = true;
        this.aki = true;
        this.BR = 1;
    }
    
    TileOverlayOptions(final int br, final IBinder binder, final boolean ajB, final float ajA, final boolean aki) {
        this.ajB = true;
        this.aki = true;
        this.BR = br;
        this.akg = i$a.by(binder);
        TileProvider akh;
        if (this.akg == null) {
            akh = null;
        }
        else {
            akh = new TileOverlayOptions$1(this);
        }
        this.akh = akh;
        this.ajB = ajB;
        this.ajA = ajA;
        this.aki = aki;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public TileOverlayOptions fadeIn(final boolean aki) {
        this.aki = aki;
        return this;
    }
    
    public boolean getFadeIn() {
        return this.aki;
    }
    
    public TileProvider getTileProvider() {
        return this.akh;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public float getZIndex() {
        return this.ajA;
    }
    
    public boolean isVisible() {
        return this.ajB;
    }
    
    IBinder mP() {
        return this.akg.asBinder();
    }
    
    public TileOverlayOptions tileProvider(final TileProvider akh) {
        this.akh = akh;
        i akg;
        if (this.akh == null) {
            akg = null;
        }
        else {
            akg = new TileOverlayOptions$2(this, akh);
        }
        this.akg = akg;
        return this;
    }
    
    public TileOverlayOptions visible(final boolean ajB) {
        this.ajB = ajB;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            x.a(this, parcel, n);
            return;
        }
        w.a(this, parcel, n);
    }
    
    public TileOverlayOptions zIndex(final float ajA) {
        this.ajA = ajA;
        return this;
    }
}
