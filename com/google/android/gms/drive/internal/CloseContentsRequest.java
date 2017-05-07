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
    final Contents EX;
    final Boolean EY;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    CloseContentsRequest(final int xh, final Contents ex, final Boolean ey) {
        this.xH = xh;
        this.EX = ex;
        this.EY = ey;
    }
    
    public CloseContentsRequest(final Contents contents, final boolean b) {
        this(1, contents, b);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
