// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UpdateMetadataRequest implements SafeParcelable
{
    public static final Parcelable$Creator<UpdateMetadataRequest> CREATOR;
    final DriveId EV;
    final MetadataBundle EW;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new an();
    }
    
    UpdateMetadataRequest(final int xh, final DriveId ev, final MetadataBundle ew) {
        this.xH = xh;
        this.EV = ev;
        this.EW = ew;
    }
    
    public UpdateMetadataRequest(final DriveId driveId, final MetadataBundle metadataBundle) {
        this(1, driveId, metadataBundle);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        an.a(this, parcel, n);
    }
}
