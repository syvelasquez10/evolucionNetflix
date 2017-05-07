// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.query.Query;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class QueryRequest implements SafeParcelable
{
    public static final Parcelable$Creator<QueryRequest> CREATOR;
    final Query FL;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new aj();
    }
    
    QueryRequest(final int xh, final Query fl) {
        this.xH = xh;
        this.FL = fl;
    }
    
    public QueryRequest(final Query query) {
        this(1, query);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aj.a(this, parcel, n);
    }
}
