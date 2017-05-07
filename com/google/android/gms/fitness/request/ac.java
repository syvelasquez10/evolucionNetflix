// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ac implements SafeParcelable
{
    public static final Parcelable$Creator<ac> CREATOR;
    private final int BR;
    private final k UF;
    
    static {
        CREATOR = (Parcelable$Creator)new ad();
    }
    
    ac(final int br, final IBinder binder) {
        this.BR = br;
        this.UF = k.a.ay(binder);
    }
    
    public ac(final BleScanCallback bleScanCallback) {
        this.BR = 1;
        this.UF = a.a.iV().b(bleScanCallback);
    }
    
    public int describeContents() {
        return 0;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public IBinder jz() {
        return this.UF.asBinder();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ad.a(this, parcel, n);
    }
}
