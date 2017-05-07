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
    final int kg;
    final FilterHolder sc;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    NotFilter(final int kg, final FilterHolder sc) {
        this.kg = kg;
        this.sc = sc;
    }
    
    public NotFilter(final Filter filter) {
        this(1, new FilterHolder(filter));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
}
