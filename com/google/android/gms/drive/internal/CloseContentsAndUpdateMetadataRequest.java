// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CloseContentsAndUpdateMetadataRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CloseContentsAndUpdateMetadataRequest> CREATOR;
    final int BR;
    final DriveId NV;
    final MetadataBundle NW;
    final Contents NX;
    final int NY;
    final String Nf;
    final boolean Ng;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    CloseContentsAndUpdateMetadataRequest(final int br, final DriveId nv, final MetadataBundle nw, final Contents nx, final boolean ng, final String nf, final int ny) {
        this.BR = br;
        this.NV = nv;
        this.NW = nw;
        this.NX = nx;
        this.Ng = ng;
        this.Nf = nf;
        this.NY = ny;
    }
    
    public CloseContentsAndUpdateMetadataRequest(final DriveId driveId, final MetadataBundle metadataBundle, final Contents contents, final ExecutionOptions executionOptions) {
        this(1, driveId, metadataBundle, contents, executionOptions.hP(), executionOptions.hO(), executionOptions.hQ());
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
