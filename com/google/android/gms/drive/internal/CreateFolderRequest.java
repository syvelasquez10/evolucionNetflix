// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.internal.fq;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateFolderRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CreateFolderRequest> CREATOR;
    final MetadataBundle EZ;
    final DriveId Fa;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    CreateFolderRequest(final int xh, final DriveId driveId, final MetadataBundle metadataBundle) {
        this.xH = xh;
        this.Fa = fq.f(driveId);
        this.EZ = fq.f(metadataBundle);
    }
    
    public CreateFolderRequest(final DriveId driveId, final MetadataBundle metadataBundle) {
        this(1, driveId, metadataBundle);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
}
