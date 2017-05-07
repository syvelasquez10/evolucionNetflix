// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class c implements SafeParcelable
{
    public static final d CREATOR;
    private final int BR;
    int aem;
    int aen;
    long aeo;
    
    static {
        CREATOR = new d();
    }
    
    c(final int br, final int aem, final int aen, final long aeo) {
        this.BR = br;
        this.aem = aem;
        this.aen = aen;
        this.aeo = aeo;
    }
    
    private String ed(final int n) {
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
        if (o instanceof c) {
            final c c = (c)o;
            if (this.aem == c.aem && this.aen == c.aen && this.aeo == c.aeo) {
                return true;
            }
        }
        return false;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.aem, this.aen, this.aeo);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LocationStatus[cell status: ").append(this.ed(this.aem));
        sb.append(", wifi status: ").append(this.ed(this.aen));
        sb.append(", elapsed realtime ns: ").append(this.aeo);
        sb.append(']');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
