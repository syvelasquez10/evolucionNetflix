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
    final Contents EA;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new z();
    }
    
    OnContentsResponse(final int xh, final Contents ea) {
        this.xH = xh;
        this.EA = ea;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Contents fI() {
        return this.EA;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        z.a(this, parcel, n);
    }
}
