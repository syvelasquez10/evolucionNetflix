// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class i implements SafeParcelable
{
    public static final Parcelable$Creator<i> CREATOR;
    private final int BR;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new j();
    }
    
    i(final int br, final String mName) {
        this.BR = br;
        this.mName = mName;
    }
    
    public i(final String mName) {
        this.BR = 1;
        this.mName = mName;
    }
    
    private boolean a(final i i) {
        return m.equal(this.mName, i.mName);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof i && this.a((i)o));
    }
    
    public String getName() {
        return this.mName;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.mName);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("name", this.mName).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
