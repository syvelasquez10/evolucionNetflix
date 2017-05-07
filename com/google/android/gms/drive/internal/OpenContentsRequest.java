// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OpenContentsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<OpenContentsRequest> CREATOR;
    final DriveId EV;
    final int Ev;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new ah();
    }
    
    OpenContentsRequest(final int xh, final DriveId ev, final int ev2) {
        this.xH = xh;
        this.EV = ev;
        this.Ev = ev2;
    }
    
    public OpenContentsRequest(final DriveId driveId, final int n) {
        this(1, driveId, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ah.a(this, parcel, n);
    }
}
