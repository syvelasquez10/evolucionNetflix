// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class mg implements SafeParcelable
{
    public static final mh CREATOR;
    private final int BR;
    private final int adW;
    private final int afe;
    private final mi aff;
    
    static {
        CREATOR = new mh();
    }
    
    mg(final int br, final int adW, final int afe, final mi aff) {
        this.BR = br;
        this.adW = adW;
        this.afe = afe;
        this.aff = aff;
    }
    
    public int describeContents() {
        final mh creator = mg.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof mg)) {
                return false;
            }
            final mg mg = (mg)o;
            if (this.adW != mg.adW || this.afe != mg.afe || !this.aff.equals(mg.aff)) {
                return false;
            }
        }
        return true;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.adW, this.afe);
    }
    
    public int ma() {
        return this.adW;
    }
    
    public int me() {
        return this.afe;
    }
    
    public mi mf() {
        return this.aff;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("transitionTypes", this.adW).a("loiteringTimeMillis", this.afe).a("placeFilter", this.aff).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final mh creator = mg.CREATOR;
        mh.a(this, parcel, n);
    }
}
