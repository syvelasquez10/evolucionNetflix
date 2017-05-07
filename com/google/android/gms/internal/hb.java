// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class hb extends gs implements SafeParcelable
{
    private static final hb AJ;
    public static final hc CREATOR;
    private final hd[] AK;
    private final float[] AL;
    final int kg;
    private final long vO;
    
    static {
        CREATOR = new hc();
        AJ = new hb(0, new hd[0], new float[0], 0L);
    }
    
    hb(final int kg, final hd[] ak, final float[] al, final long vo) {
        eg.b(ak.length == al.length, "mismatched places to probabilities arrays");
        this.kg = kg;
        this.AK = ak;
        this.AL = al;
        this.vO = vo;
    }
    
    public hd[] dU() {
        return this.AK;
    }
    
    public float[] dV() {
        return this.AL;
    }
    
    public int describeContents() {
        final hc creator = hb.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof hb)) {
                return false;
            }
            final hb hb = (hb)o;
            if (!this.AK.equals(hb.AK) || !this.AL.equals(hb.AL)) {
                return false;
            }
        }
        return true;
    }
    
    public long getTimestampMillis() {
        return this.vO;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.AK, this.AL);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlaceEstimate{");
        for (int i = 0; i < this.AK.length; ++i) {
            sb.append(String.format("(%f, %s)", this.AL[i], this.AK[i].toString()));
            if (i != this.AK.length - 1) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hc creator = hb.CREATOR;
        hc.a(this, parcel, n);
    }
}
