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
    final int BR;
    final DriveId NV;
    final MetadataBundle NW;
    
    static {
        CREATOR = (Parcelable$Creator)new bd();
    }
    
    UpdateMetadataRequest(final int br, final DriveId nv, final MetadataBundle nw) {
        this.BR = br;
        this.NV = nv;
        this.NW = nw;
    }
    
    public UpdateMetadataRequest(final DriveId driveId, final MetadataBundle metadataBundle) {
        this(1, driveId, metadataBundle);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        bd.a(this, parcel, n);
    }
}
