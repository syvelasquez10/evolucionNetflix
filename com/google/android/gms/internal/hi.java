// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hi implements SafeParcelable
{
    public static final hj CREATOR;
    private final String OE;
    private final String mTag;
    final int xH;
    
    static {
        CREATOR = new hj();
    }
    
    hi(final int xh, final String oe, final String mTag) {
        this.xH = xh;
        this.OE = oe;
        this.mTag = mTag;
    }
    
    public int describeContents() {
        final hj creator = hi.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof hi) {
            final hi hi = (hi)o;
            if (fo.equal(this.OE, hi.OE) && fo.equal(this.mTag, hi.mTag)) {
                return true;
            }
        }
        return false;
    }
    
    public String getTag() {
        return this.mTag;
    }
    
    public String hY() {
        return this.OE;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.OE, this.mTag);
    }
    
    @Override
    public String toString() {
        return fo.e(this).a("mPlaceId", this.OE).a("mTag", this.mTag).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hj creator = hi.CREATOR;
        hj.a(this, parcel, n);
    }
}
