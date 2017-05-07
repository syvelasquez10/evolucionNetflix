// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Bundle;
import java.util.Arrays;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class h implements SafeParcelable
{
    public static final j CREATOR;
    private final int BR;
    private final String Dd;
    private final String[] als;
    private final String[] alt;
    private final String[] alu;
    private final String alv;
    private final String alw;
    private final String alx;
    private final String aly;
    private final PlusCommonExtras alz;
    
    static {
        CREATOR = new j();
    }
    
    h(final int br, final String dd, final String[] als, final String[] alt, final String[] alu, final String alv, final String alw, final String alx, final String aly, final PlusCommonExtras alz) {
        this.BR = br;
        this.Dd = dd;
        this.als = als;
        this.alt = alt;
        this.alu = alu;
        this.alv = alv;
        this.alw = alw;
        this.alx = alx;
        this.aly = aly;
        this.alz = alz;
    }
    
    public h(final String dd, final String[] als, final String[] alt, final String[] alu, final String alv, final String alw, final String alx, final PlusCommonExtras alz) {
        this.BR = 1;
        this.Dd = dd;
        this.als = als;
        this.alt = alt;
        this.alu = alu;
        this.alv = alv;
        this.alw = alw;
        this.alx = alx;
        this.aly = null;
        this.alz = alz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof h) {
            final h h = (h)o;
            if (this.BR == h.BR && m.equal(this.Dd, h.Dd) && Arrays.equals(this.als, h.als) && Arrays.equals(this.alt, h.alt) && Arrays.equals(this.alu, h.alu) && m.equal(this.alv, h.alv) && m.equal(this.alw, h.alw) && m.equal(this.alx, h.alx) && m.equal(this.aly, h.aly) && m.equal(this.alz, h.alz)) {
                return true;
            }
        }
        return false;
    }
    
    public String getAccountName() {
        return this.Dd;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.BR, this.Dd, this.als, this.alt, this.alu, this.alv, this.alw, this.alx, this.aly, this.alz);
    }
    
    public String[] ne() {
        return this.als;
    }
    
    public String[] nf() {
        return this.alt;
    }
    
    public String[] ng() {
        return this.alu;
    }
    
    public String nh() {
        return this.alv;
    }
    
    public String ni() {
        return this.alw;
    }
    
    public String nj() {
        return this.alx;
    }
    
    public String nk() {
        return this.aly;
    }
    
    public PlusCommonExtras nl() {
        return this.alz;
    }
    
    public Bundle nm() {
        final Bundle bundle = new Bundle();
        bundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
        this.alz.o(bundle);
        return bundle;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("versionCode", this.BR).a("accountName", this.Dd).a("requestedScopes", this.als).a("visibleActivities", this.alt).a("requiredFeatures", this.alu).a("packageNameForAuth", this.alv).a("callingPackageName", this.alw).a("applicationName", this.alx).a("extra", this.alz.toString()).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
