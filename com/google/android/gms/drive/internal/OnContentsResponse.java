// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnContentsResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnContentsResponse> CREATOR;
    final int kg;
    final Contents qK;
    
    static {
        CREATOR = (Parcelable$Creator)new r();
    }
    
    OnContentsResponse(final int kg, final Contents qk) {
        this.kg = kg;
        this.qK = qk;
    }
    
    public Contents cQ() {
        return this.qK;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        r.a(this, parcel, n);
    }
}
