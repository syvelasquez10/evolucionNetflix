// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CloseContentsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CloseContentsRequest> CREATOR;
    final int kg;
    final Contents qX;
    final Boolean qY;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    CloseContentsRequest(final int kg, final Contents qx, final Boolean qy) {
        this.kg = kg;
        this.qX = qx;
        this.qY = qy;
    }
    
    public CloseContentsRequest(final Contents contents, final boolean b) {
        this(1, contents, b);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
