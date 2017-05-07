// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnListParentsResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnListParentsResponse> CREATOR;
    final DataHolder FK;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new ae();
    }
    
    OnListParentsResponse(final int xh, final DataHolder fk) {
        this.xH = xh;
        this.FK = fk;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public DataHolder fP() {
        return this.FK;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ae.a(this, parcel, n);
    }
}
