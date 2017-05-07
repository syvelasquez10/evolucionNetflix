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
    final int kg;
    final Query rA;
    
    static {
        CREATOR = (Parcelable$Creator)new y();
    }
    
    QueryRequest(final int kg, final Query ra) {
        this.kg = kg;
        this.rA = ra;
    }
    
    public QueryRequest(final Query query) {
        this(1, query);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        y.a(this, parcel, n);
    }
}
