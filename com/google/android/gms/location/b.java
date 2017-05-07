// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.internal.fo;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class b implements SafeParcelable
{
    public static final c CREATOR;
    int Oh;
    int Oi;
    long Oj;
    private final int xH;
    
    static {
        CREATOR = new c();
    }
    
    b(final int xh, final int oh, final int oi, final long oj) {
        this.xH = xh;
        this.Oh = oh;
        this.Oi = oi;
        this.Oj = oj;
    }
    
    private String by(final int n) {
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
        if (o instanceof b) {
            final b b = (b)o;
            if (this.Oh == b.Oh && this.Oi == b.Oi && this.Oj == b.Oj) {
                return true;
            }
        }
        return false;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.Oh, this.Oi, this.Oj);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LocationStatus[cell status: ").append(this.by(this.Oh));
        sb.append(", wifi status: ").append(this.by(this.Oi));
        sb.append(", elapsed realtime ns: ").append(this.Oj);
        sb.append(']');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
