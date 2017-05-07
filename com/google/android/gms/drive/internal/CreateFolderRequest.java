// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateFolderRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CreateFolderRequest> CREATOR;
    final int BR;
    final MetadataBundle Od;
    final DriveId Of;
    
    static {
        CREATOR = (Parcelable$Creator)new k();
    }
    
    CreateFolderRequest(final int br, final DriveId driveId, final MetadataBundle metadataBundle) {
        this.BR = br;
        this.Of = n.i(driveId);
        this.Od = n.i(metadataBundle);
    }
    
    public CreateFolderRequest(final DriveId driveId, final MetadataBundle metadataBundle) {
        this(1, driveId, metadataBundle);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        k.a(this, parcel, n);
    }
}
