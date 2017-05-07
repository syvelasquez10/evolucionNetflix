// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class x implements SafeParcelable
{
    public static final Parcelable$Creator<x> CREATOR;
    private final int BR;
    private final String Tf;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new y();
    }
    
    x(final int br, final String mName, final String tf) {
        this.BR = br;
        this.mName = mName;
        this.Tf = tf;
    }
    
    private x(final x$a x$a) {
        this.BR = 1;
        this.mName = x$a.mName;
        this.Tf = x$a.Tf;
    }
    
    private boolean a(final x x) {
        return m.equal(this.mName, x.mName) && m.equal(this.Tf, x.Tf);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof x && this.a((x)o));
    }
    
    public String getIdentifier() {
        return this.Tf;
    }
    
    public String getName() {
        return this.mName;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.mName, this.Tf);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("name", this.mName).a("identifier", this.Tf).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        y.a(this, parcel, n);
    }
}
