// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnListEntriesResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnListEntriesResponse> CREATOR;
    final int kg;
    final DataHolder rz;
    
    static {
        CREATOR = (Parcelable$Creator)new u();
    }
    
    OnListEntriesResponse(final int kg, final DataHolder rz) {
        this.kg = kg;
        this.rz = rz;
    }
    
    public DataHolder cT() {
        return this.rz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        u.a(this, parcel, n);
    }
}
