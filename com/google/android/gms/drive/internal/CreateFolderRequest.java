// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.internal.eg;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateFolderRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CreateFolderRequest> CREATOR;
    final int kg;
    final MetadataBundle qZ;
    final DriveId ra;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    CreateFolderRequest(final int kg, final DriveId driveId, final MetadataBundle metadataBundle) {
        this.kg = kg;
        this.ra = eg.f(driveId);
        this.qZ = eg.f(metadataBundle);
    }
    
    public CreateFolderRequest(final DriveId driveId, final MetadataBundle metadataBundle) {
        this(1, driveId, metadataBundle);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
