// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.r;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.IBinder;
import com.google.android.gms.maps.model.internal.g;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class TileOverlayOptions implements SafeParcelable
{
    public static final TileOverlayOptionsCreator CREATOR;
    private float Cw;
    private boolean Cx;
    private g Da;
    private TileProvider Db;
    private boolean Dc;
    private final int kg;
    
    static {
        CREATOR = new TileOverlayOptionsCreator();
    }
    
    public TileOverlayOptions() {
        this.Cx = true;
        this.Dc = true;
        this.kg = 1;
    }
    
    TileOverlayOptions(final int kg, final IBinder binder, final boolean cx, final float cw, final boolean dc) {
        this.Cx = true;
        this.Dc = true;
        this.kg = kg;
        this.Da = g.a.aq(binder);
        TileProvider db;
        if (this.Da == null) {
            db = null;
        }
        else {
            db = new TileProvider() {
                private final g Dd = TileOverlayOptions.this.Da;
                
                @Override
                public Tile getTile(final int n, final int n2, final int n3) {
                    try {
                        return this.Dd.getTile(n, n2, n3);
                    }
                    catch (RemoteException ex) {
                        return null;
                    }
                }
            };
        }
        this.Db = db;
        this.Cx = cx;
        this.Cw = cw;
        this.Dc = dc;
    }
    
    public int describeContents() {
        return 0;
    }
    
    IBinder eI() {
        return this.Da.asBinder();
    }
    
    public TileOverlayOptions fadeIn(final boolean dc) {
        this.Dc = dc;
        return this;
    }
    
    public boolean getFadeIn() {
        return this.Dc;
    }
    
    public TileProvider getTileProvider() {
        return this.Db;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public float getZIndex() {
        return this.Cw;
    }
    
    public boolean isVisible() {
        return this.Cx;
    }
    
    public TileOverlayOptions tileProvider(final TileProvider db) {
        this.Db = db;
        g da;
        if (this.Db == null) {
            da = null;
        }
        else {
            da = new g.a() {
                public Tile getTile(final int n, final int n2, final int n3) {
                    return db.getTile(n, n2, n3);
                }
            };
        }
        this.Da = da;
        return this;
    }
    
    public TileOverlayOptions visible(final boolean cx) {
        this.Cx = cx;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (r.eD()) {
            j.a(this, parcel, n);
            return;
        }
        TileOverlayOptionsCreator.a(this, parcel, n);
    }
    
    public TileOverlayOptions zIndex(final float cw) {
        this.Cw = cw;
        return this;
    }
}
