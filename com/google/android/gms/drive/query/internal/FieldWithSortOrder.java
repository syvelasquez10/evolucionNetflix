// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FieldWithSortOrder implements SafeParcelable
{
    public static final c CREATOR;
    final String FM;
    final boolean GJ;
    final int xH;
    
    static {
        CREATOR = new c();
    }
    
    FieldWithSortOrder(final int xh, final String fm, final boolean gj) {
        this.xH = xh;
        this.FM = fm;
        this.GJ = gj;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
