// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Locale;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class mw implements SafeParcelable
{
    public static final mx CREATOR;
    public final String Dv;
    public final String ahY;
    public final String ahZ;
    public final int versionCode;
    
    static {
        CREATOR = new mx();
    }
    
    public mw(final int versionCode, final String ahY, final String ahZ, final String dv) {
        this.versionCode = versionCode;
        this.ahY = ahY;
        this.ahZ = ahZ;
        this.Dv = dv;
    }
    
    public mw(final String ahY, final Locale locale, final String dv) {
        this.versionCode = 0;
        this.ahY = ahY;
        this.ahZ = locale.toString();
        this.Dv = dv;
    }
    
    public int describeContents() {
        final mx creator = mw.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || !(o instanceof mw)) {
                return false;
            }
            final mw mw = (mw)o;
            if (!this.ahZ.equals(mw.ahZ) || !this.ahY.equals(mw.ahY) || !m.equal(this.Dv, mw.Dv)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.ahY, this.ahZ, this.Dv);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("clientPackageName", this.ahY).a("locale", this.ahZ).a("accountName", this.Dv).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final mx creator = mw.CREATOR;
        mx.a(this, parcel, n);
    }
}
