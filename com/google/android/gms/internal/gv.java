// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class gv implements SafeParcelable
{
    public static final gw CREATOR;
    final int kg;
    private final LocationRequest yl;
    private final gt ym;
    
    static {
        CREATOR = new gw();
    }
    
    public gv(final int kg, final LocationRequest yl, final gt ym) {
        this.kg = kg;
        this.yl = yl;
        this.ym = ym;
    }
    
    public LocationRequest dS() {
        return this.yl;
    }
    
    public gt dT() {
        return this.ym;
    }
    
    public int describeContents() {
        final gw creator = gv.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof gv)) {
                return false;
            }
            final gv gv = (gv)o;
            if (!this.yl.equals(gv.yl) || !this.ym.equals(gv.ym)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.yl, this.ym);
    }
    
    @Override
    public String toString() {
        return ee.e(this).a("locationRequest", this.yl).a("filter", this.ym).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final gw creator = gv.CREATOR;
        gw.a(this, parcel, n);
    }
}
