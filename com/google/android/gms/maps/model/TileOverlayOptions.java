// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.IBinder;
import com.google.android.gms.maps.model.internal.i;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class TileOverlayOptions implements SafeParcelable
{
    public static final TileOverlayOptionsCreator CREATOR;
    private float SN;
    private boolean SO;
    private i Tt;
    private TileProvider Tu;
    private boolean Tv;
    private final int xH;
    
    static {
        CREATOR = new TileOverlayOptionsCreator();
    }
    
    public TileOverlayOptions() {
        this.SO = true;
        this.Tv = true;
        this.xH = 1;
    }
    
    TileOverlayOptions(final int xh, final IBinder binder, final boolean so, final float sn, final boolean tv) {
        this.SO = true;
        this.Tv = true;
        this.xH = xh;
        this.Tt = i.a.aK(binder);
        TileProvider tu;
        if (this.Tt == null) {
            tu = null;
        }
        else {
            tu = new TileProvider() {
                private final i Tw = TileOverlayOptions.this.Tt;
                
                @Override
                public Tile getTile(final int n, final int n2, final int n3) {
                    try {
                        return this.Tw.getTile(n, n2, n3);
                    }
                    catch (RemoteException ex) {
                        return null;
                    }
                }
            };
        }
        this.Tu = tu;
        this.SO = so;
        this.SN = sn;
        this.Tv = tv;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public TileOverlayOptions fadeIn(final boolean tv) {
        this.Tv = tv;
        return this;
    }
    
    public boolean getFadeIn() {
        return this.Tv;
    }
    
    public TileProvider getTileProvider() {
        return this.Tu;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public float getZIndex() {
        return this.SN;
    }
    
    IBinder iG() {
        return this.Tt.asBinder();
    }
    
    public boolean isVisible() {
        return this.SO;
    }
    
    public TileOverlayOptions tileProvider(final TileProvider tu) {
        this.Tu = tu;
        i tt;
        if (this.Tu == null) {
            tt = null;
        }
        else {
            tt = new i.a() {
                public Tile getTile(final int n, final int n2, final int n3) {
                    return tu.getTile(n, n2, n3);
                }
            };
        }
        this.Tt = tt;
        return this;
    }
    
    public TileOverlayOptions visible(final boolean so) {
        this.SO = so;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            j.a(this, parcel, n);
            return;
        }
        TileOverlayOptionsCreator.a(this, parcel, n);
    }
    
    public TileOverlayOptions zIndex(final float sn) {
        this.SN = sn;
        return this;
    }
}
