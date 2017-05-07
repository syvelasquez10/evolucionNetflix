// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class aq implements SafeParcelable
{
    public static final Parcelable$Creator<aq> CREATOR;
    final int BR;
    public final ae auZ;
    
    static {
        CREATOR = (Parcelable$Creator)new ar();
    }
    
    aq(final int br, final IBinder binder) {
        this.BR = br;
        if (binder != null) {
            this.auZ = ae$a.bS(binder);
            return;
        }
        this.auZ = null;
    }
    
    public aq(final ae auZ) {
        this.BR = 1;
        this.auZ = auZ;
    }
    
    public int describeContents() {
        return 0;
    }
    
    IBinder pT() {
        if (this.auZ == null) {
            return null;
        }
        return this.auZ.asBinder();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ar.a(this, parcel, n);
    }
}
