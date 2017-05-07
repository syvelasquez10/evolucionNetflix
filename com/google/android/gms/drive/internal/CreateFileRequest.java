// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.internal.eg;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateFileRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CreateFileRequest> CREATOR;
    final int kg;
    final Contents qX;
    final MetadataBundle qZ;
    final DriveId ra;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    CreateFileRequest(final int kg, final DriveId driveId, final MetadataBundle metadataBundle, final Contents contents) {
        this.kg = kg;
        this.ra = eg.f(driveId);
        this.qZ = eg.f(metadataBundle);
        this.qX = eg.f(contents);
    }
    
    public CreateFileRequest(final DriveId driveId, final MetadataBundle metadataBundle, final Contents contents) {
        this(1, driveId, metadataBundle, contents);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
