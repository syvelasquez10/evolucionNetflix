// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class q implements SafeParcelable
{
    public static final Parcelable$Creator<q> CREATOR;
    final int BR;
    private final Session Sk;
    private final DataSet Th;
    
    static {
        CREATOR = (Parcelable$Creator)new r();
    }
    
    q(final int br, final Session sk, final DataSet th) {
        this.BR = br;
        this.Sk = sk;
        this.Th = th;
    }
    
    private boolean a(final q q) {
        return m.equal(this.Sk, q.Sk) && m.equal(this.Th, q.Th);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof q && this.a((q)o));
    }
    
    public Session getSession() {
        return this.Sk;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sk, this.Th);
    }
    
    public DataSet iP() {
        return this.Th;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("session", this.Sk).a("dataSet", this.Th).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        r.a(this, parcel, n);
    }
}
