// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Tile implements SafeParcelable
{
    public static final TileCreator CREATOR;
    public final byte[] data;
    public final int height;
    public final int width;
    private final int xH;
    
    static {
        CREATOR = new TileCreator();
    }
    
    Tile(final int xh, final int width, final int height, final byte[] data) {
        this.xH = xh;
        this.width = width;
        this.height = height;
        this.data = data;
    }
    
    public Tile(final int n, final int n2, final byte[] array) {
        this(1, n, n2, array);
    }
    
    public int describeContents() {
        return 0;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.iB()) {
            i.a(this, parcel, n);
            return;
        }
        TileCreator.a(this, parcel, n);
    }
}
