// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.content.IntentFilter;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class b implements SafeParcelable
{
    public static final Parcelable$Creator<b> CREATOR;
    final int BR;
    public final ae auZ;
    public final IntentFilter[] ava;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    b(final int br, final IBinder binder, final IntentFilter[] ava) {
        this.BR = br;
        if (binder != null) {
            this.auZ = ae.a.bS(binder);
        }
        else {
            this.auZ = null;
        }
        this.ava = ava;
    }
    
    public b(final ax auZ) {
        this.BR = 1;
        this.auZ = auZ;
        this.ava = auZ.pZ();
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
        c.a(this, parcel, n);
    }
}
