// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class f implements SafeParcelable
{
    public static final g CREATOR;
    private final int kg;
    private final String xJ;
    private final PendingIntent xr;
    
    static {
        CREATOR = new g();
    }
    
    public f(final int kg, final PendingIntent xr, final String xj) {
        this.kg = kg;
        this.xr = xr;
        this.xJ = xj;
    }
    
    public PendingIntent dB() {
        return this.xr;
    }
    
    public String dC() {
        return this.xJ;
    }
    
    public int describeContents() {
        final g creator = f.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final f f = (f)o;
            if (this.xr == null) {
                if (f.xr != null) {
                    return false;
                }
            }
            else if (!this.xr.equals((Object)f.xr)) {
                return false;
            }
            if (this.xJ == null) {
                if (f.xJ != null) {
                    return false;
                }
            }
            else if (!this.xJ.equals(f.xJ)) {
                return false;
            }
        }
        return true;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.xr == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.xr.hashCode();
        }
        if (this.xJ != null) {
            hashCode = this.xJ.hashCode();
        }
        return (hashCode2 + 31) * 31 + hashCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final g creator = f.CREATOR;
        g.a(this, parcel, n);
    }
}
