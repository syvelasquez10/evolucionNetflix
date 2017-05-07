// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class mk implements SafeParcelable
{
    public static final ml CREATOR;
    final int BR;
    private final String afo;
    private final String mTag;
    
    static {
        CREATOR = new ml();
    }
    
    mk(final int br, final String afo, final String mTag) {
        this.BR = br;
        this.afo = afo;
        this.mTag = mTag;
    }
    
    public int describeContents() {
        final ml creator = mk.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof mk) {
            final mk mk = (mk)o;
            if (m.equal(this.afo, mk.afo) && m.equal(this.mTag, mk.mTag)) {
                return true;
            }
        }
        return false;
    }
    
    public String getTag() {
        return this.mTag;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.afo, this.mTag);
    }
    
    public String mi() {
        return this.afo;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("mPlaceId", this.afo).a("mTag", this.mTag).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ml creator = mk.CREATOR;
        ml.a(this, parcel, n);
    }
}
