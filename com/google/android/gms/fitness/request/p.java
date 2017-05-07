// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.IBinder;
import android.app.PendingIntent;
import com.google.android.gms.fitness.data.k;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class p implements SafeParcelable
{
    public static final Parcelable$Creator<p> CREATOR;
    private final int BR;
    private final k Up;
    private final PendingIntent mPendingIntent;
    
    static {
        CREATOR = (Parcelable$Creator)new q();
    }
    
    p(final int br, final IBinder binder, final PendingIntent mPendingIntent) {
        this.BR = br;
        k an;
        if (binder == null) {
            an = null;
        }
        else {
            an = k.a.an(binder);
        }
        this.Up = an;
        this.mPendingIntent = mPendingIntent;
    }
    
    public p(final k up, final PendingIntent mPendingIntent) {
        this.BR = 2;
        this.Up = up;
        this.mPendingIntent = mPendingIntent;
    }
    
    public int describeContents() {
        return 0;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public PendingIntent jl() {
        return this.mPendingIntent;
    }
    
    IBinder jq() {
        if (this.Up == null) {
            return null;
        }
        return this.Up.asBinder();
    }
    
    @Override
    public String toString() {
        return String.format("SensorUnregistrationRequest{%s}", this.Up);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        q.a(this, parcel, n);
    }
}
