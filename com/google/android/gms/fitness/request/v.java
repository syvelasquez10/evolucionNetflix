// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.fitness.data.Session;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class v implements SafeParcelable
{
    public static final Parcelable$Creator<v> CREATOR;
    private final int BR;
    private final Session Sk;
    
    static {
        CREATOR = (Parcelable$Creator)new w();
    }
    
    v(final int br, final Session sk) {
        this.BR = br;
        this.Sk = sk;
    }
    
    private v(final a a) {
        this.BR = 1;
        this.Sk = a.Sk;
    }
    
    private boolean a(final v v) {
        return m.equal(this.Sk, v.Sk);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof v && this.a((v)o));
    }
    
    public Session getSession() {
        return this.Sk;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sk);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("session", this.Sk).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        w.a(this, parcel, n);
    }
    
    public static class a
    {
        private Session Sk;
        
        public a b(final Session sk) {
            n.b(sk.getEndTimeMillis() == 0L, (Object)"Cannot start a session which has already ended");
            this.Sk = sk;
            return this;
        }
        
        public v jx() {
            return new v(this, null);
        }
    }
}
