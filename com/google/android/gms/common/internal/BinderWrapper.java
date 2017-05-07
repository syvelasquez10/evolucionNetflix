// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class BinderWrapper implements Parcelable
{
    public static final Parcelable$Creator<BinderWrapper> CREATOR;
    private IBinder zzaeJ;
    
    static {
        CREATOR = (Parcelable$Creator)new BinderWrapper$1();
    }
    
    public BinderWrapper() {
        this.zzaeJ = null;
    }
    
    public BinderWrapper(final IBinder zzaeJ) {
        this.zzaeJ = null;
        this.zzaeJ = zzaeJ;
    }
    
    private BinderWrapper(final Parcel parcel) {
        this.zzaeJ = null;
        this.zzaeJ = parcel.readStrongBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStrongBinder(this.zzaeJ);
    }
}
