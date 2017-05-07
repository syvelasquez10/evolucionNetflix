// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RemoveEventListenerRequest implements SafeParcelable
{
    public static final Parcelable$Creator<RemoveEventListenerRequest> CREATOR;
    final int ES;
    final DriveId Ew;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new ak();
    }
    
    RemoveEventListenerRequest(final int xh, final DriveId ew, final int es) {
        this.xH = xh;
        this.Ew = ew;
        this.ES = es;
    }
    
    public RemoveEventListenerRequest(final DriveId driveId, final int n) {
        this(1, driveId, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ak.a(this, parcel, n);
    }
}
