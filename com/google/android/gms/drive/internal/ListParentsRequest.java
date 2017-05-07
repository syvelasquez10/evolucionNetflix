// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ListParentsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<ListParentsRequest> CREATOR;
    final DriveId FB;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new x();
    }
    
    ListParentsRequest(final int xh, final DriveId fb) {
        this.xH = xh;
        this.FB = fb;
    }
    
    public ListParentsRequest(final DriveId driveId) {
        this(1, driveId);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        x.a(this, parcel, n);
    }
}
