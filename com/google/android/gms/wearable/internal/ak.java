// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ak implements SafeParcelable, Node
{
    public static final Parcelable$Creator<ak> CREATOR;
    private final String BL;
    final int BR;
    private final String Nz;
    
    static {
        CREATOR = (Parcelable$Creator)new al();
    }
    
    ak(final int br, final String bl, final String nz) {
        this.BR = br;
        this.BL = bl;
        this.Nz = nz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof ak) {
            final ak ak = (ak)o;
            if (ak.BL.equals(this.BL) && ak.Nz.equals(this.Nz)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String getDisplayName() {
        return this.Nz;
    }
    
    @Override
    public String getId() {
        return this.BL;
    }
    
    @Override
    public int hashCode() {
        return (this.BL.hashCode() + 629) * 37 + this.Nz.hashCode();
    }
    
    @Override
    public String toString() {
        return "NodeParcelable{" + this.BL + "," + this.Nz + "}";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        al.a(this, parcel, n);
    }
}
