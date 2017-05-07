// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.maps.internal.v;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Tile implements SafeParcelable
{
    public static final u CREATOR;
    private final int BR;
    public final byte[] data;
    public final int height;
    public final int width;
    
    static {
        CREATOR = new u();
    }
    
    Tile(final int br, final int width, final int height, final byte[] data) {
        this.BR = br;
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
        return this.BR;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (v.mK()) {
            com.google.android.gms.maps.model.v.a(this, parcel, n);
            return;
        }
        u.a(this, parcel, n);
    }
}
