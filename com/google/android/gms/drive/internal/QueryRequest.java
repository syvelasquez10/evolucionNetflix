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
    final int BR;
    final Query Pq;
    
    static {
        CREATOR = (Parcelable$Creator)new ax();
    }
    
    QueryRequest(final int br, final Query pq) {
        this.BR = br;
        this.Pq = pq;
    }
    
    public QueryRequest(final Query query) {
        this(1, query);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ax.a(this, parcel, n);
    }
}
