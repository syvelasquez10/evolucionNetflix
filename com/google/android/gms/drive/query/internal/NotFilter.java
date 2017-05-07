// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class NotFilter implements SafeParcelable, Filter
{
    public static final Parcelable$Creator<NotFilter> CREATOR;
    final FilterHolder GT;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    NotFilter(final int xh, final FilterHolder gt) {
        this.xH = xh;
        this.GT = gt;
    }
    
    public NotFilter(final Filter filter) {
        this(1, new FilterHolder(filter));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
}
