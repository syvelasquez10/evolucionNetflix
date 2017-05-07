// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hi implements SafeParcelable
{
    public static final hj CREATOR;
    public final String Bn;
    public final String Bo;
    public final int versionCode;
    
    static {
        CREATOR = new hj();
    }
    
    public hi(final int versionCode, final String bn, final String bo) {
        this.versionCode = versionCode;
        this.Bn = bn;
        this.Bo = bo;
    }
    
    public int describeContents() {
        final hj creator = hi.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || !(o instanceof hi)) {
                return false;
            }
            final hi hi = (hi)o;
            if (!this.Bo.equals(hi.Bo) || !this.Bn.equals(hi.Bn)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.Bn, this.Bo);
    }
    
    @Override
    public String toString() {
        return ee.e(this).a("clientPackageName", this.Bn).a("locale", this.Bo).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hj creator = hi.CREATOR;
        hj.a(this, parcel, n);
    }
}
