// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.internal.fq;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateFileRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CreateFileRequest> CREATOR;
    final Contents EX;
    final MetadataBundle EZ;
    final DriveId Fa;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    CreateFileRequest(final int xh, final DriveId driveId, final MetadataBundle metadataBundle, final Contents contents) {
        this.xH = xh;
        this.Fa = fq.f(driveId);
        this.EZ = fq.f(metadataBundle);
        this.EX = fq.f(contents);
    }
    
    public CreateFileRequest(final DriveId driveId, final MetadataBundle metadataBundle, final Contents contents) {
        this(1, driveId, metadataBundle, contents);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
