// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class MatchAllFilter implements SafeParcelable, Filter
{
    public static final h CREATOR;
    final int xH;
    
    static {
        CREATOR = new h();
    }
    
    public MatchAllFilter() {
        this(1);
    }
    
    MatchAllFilter(final int xh) {
        this.xH = xh;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
