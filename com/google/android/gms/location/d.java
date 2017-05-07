// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.internal.ee;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class d implements SafeParcelable
{
    public static final e CREATOR;
    private final int kg;
    int xG;
    int xH;
    long xI;
    
    static {
        CREATOR = new e();
    }
    
    d(final int kg, final int xg, final int xh, final long xi) {
        this.kg = kg;
        this.xG = xg;
        this.xH = xh;
        this.xI = xi;
    }
    
    private String aQ(final int n) {
        switch (n) {
            default: {
                return "STATUS_UNKNOWN";
            }
            case 0: {
                return "STATUS_SUCCESSFUL";
            }
            case 2: {
                return "STATUS_TIMED_OUT_ON_SCAN";
            }
            case 3: {
                return "STATUS_NO_INFO_IN_DATABASE";
            }
            case 4: {
                return "STATUS_INVALID_SCAN";
            }
            case 5: {
                return "STATUS_UNABLE_TO_QUERY_DATABASE";
            }
            case 6: {
                return "STATUS_SCANS_DISABLED_IN_SETTINGS";
            }
            case 7: {
                return "STATUS_LOCATION_DISABLED_IN_SETTINGS";
            }
            case 8: {
                return "STATUS_IN_PROGRESS";
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof d) {
            final d d = (d)o;
            if (this.xG == d.xG && this.xH == d.xH && this.xI == d.xI) {
                return true;
            }
        }
        return false;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.xG, this.xH, this.xI);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LocationStatus[cell status: ").append(this.aQ(this.xG));
        sb.append(", wifi status: ").append(this.aQ(this.xH));
        sb.append(", elapsed realtime ns: ").append(this.xI);
        sb.append(']');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
