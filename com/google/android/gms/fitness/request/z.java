// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class z implements SafeParcelable
{
    public static final Parcelable$Creator<z> CREATOR;
    private final int BR;
    private final PendingIntent mPendingIntent;
    
    static {
        CREATOR = (Parcelable$Creator)new aa();
    }
    
    z(final int br, final PendingIntent mPendingIntent) {
        this.BR = br;
        this.mPendingIntent = mPendingIntent;
    }
    
    public z(final PendingIntent mPendingIntent) {
        this.BR = 3;
        this.mPendingIntent = mPendingIntent;
    }
    
    private boolean a(final z z) {
        return m.equal(this.mPendingIntent, z.mPendingIntent);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof z && this.a((z)o));
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.mPendingIntent);
    }
    
    public PendingIntent jl() {
        return this.mPendingIntent;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("pendingIntent", this.mPendingIntent).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aa.a(this, parcel, n);
    }
}
