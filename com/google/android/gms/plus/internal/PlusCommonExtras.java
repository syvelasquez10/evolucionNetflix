// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.c;
import android.os.Bundle;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class PlusCommonExtras implements SafeParcelable
{
    public static final f CREATOR;
    public static String TAG;
    private final int BR;
    private String alp;
    private String alq;
    
    static {
        PlusCommonExtras.TAG = "PlusCommonExtras";
        CREATOR = new f();
    }
    
    public PlusCommonExtras() {
        this.BR = 1;
        this.alp = "";
        this.alq = "";
    }
    
    PlusCommonExtras(final int br, final String alp, final String alq) {
        this.BR = br;
        this.alp = alp;
        this.alq = alq;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof PlusCommonExtras) {
            final PlusCommonExtras plusCommonExtras = (PlusCommonExtras)o;
            if (this.BR == plusCommonExtras.BR && m.equal(this.alp, plusCommonExtras.alp) && m.equal(this.alq, plusCommonExtras.alq)) {
                return true;
            }
        }
        return false;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.BR, this.alp, this.alq);
    }
    
    public String nc() {
        return this.alp;
    }
    
    public String nd() {
        return this.alq;
    }
    
    public void o(final Bundle bundle) {
        bundle.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", c.a(this));
    }
    
    @Override
    public String toString() {
        return m.h(this).a("versionCode", this.BR).a("Gpsrc", this.alp).a("ClientCallingPackage", this.alq).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
