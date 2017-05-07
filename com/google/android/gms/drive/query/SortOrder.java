// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import android.os.Parcel;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SortOrder implements SafeParcelable
{
    public static final Parcelable$Creator<SortOrder> CREATOR;
    final List<FieldWithSortOrder> GF;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    SortOrder(final int xh, final List<FieldWithSortOrder> gf) {
        this.xH = xh;
        this.GF = gf;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
