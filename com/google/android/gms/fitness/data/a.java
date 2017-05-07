// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.internal.kv;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class a implements SafeParcelable
{
    public static final Parcelable$Creator<a> CREATOR;
    public static final a Sp;
    private final int BR;
    private final String BZ;
    private final String Sq;
    private final String Sr;
    
    static {
        Sp = new a("com.google.android.gms", String.valueOf(6111000), null);
        CREATOR = (Parcelable$Creator)new b();
    }
    
    a(final int br, final String s, final String s2, final String sr) {
        this.BR = br;
        this.BZ = n.i(s);
        this.Sq = "";
        this.Sr = sr;
    }
    
    public a(final String s, final String s2, final String s3) {
        this(1, s, "", s3);
    }
    
    private boolean a(final a a) {
        return this.BZ.equals(a.BZ) && m.equal(this.Sq, a.Sq) && m.equal(this.Sr, a.Sr);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof a && this.a((a)o));
    }
    
    public String getPackageName() {
        return this.BZ;
    }
    
    public String getVersion() {
        return this.Sq;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.BZ, this.Sq, this.Sr);
    }
    
    a iA() {
        return new a(kv.bq(this.BZ), kv.bq(this.Sq), kv.bq(this.Sr));
    }
    
    public String iz() {
        return this.Sr;
    }
    
    @Override
    public String toString() {
        return String.format("Application{%s:%s:%s}", this.BZ, this.Sq, this.Sr);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
