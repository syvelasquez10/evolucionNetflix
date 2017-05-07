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
    private IBinder zzZP;
    
    static {
        CREATOR = (Parcelable$Creator)new BinderWrapper$1();
    }
    
    public BinderWrapper() {
        this.zzZP = null;
    }
    
    public BinderWrapper(final IBinder zzZP) {
        this.zzZP = null;
        this.zzZP = zzZP;
    }
    
    private BinderWrapper(final Parcel parcel) {
        this.zzZP = null;
        this.zzZP = parcel.readStrongBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStrongBinder(this.zzZP);
    }
}
