// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CloseContentsAndUpdateMetadataRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CloseContentsAndUpdateMetadataRequest> CREATOR;
    final DriveId EV;
    final MetadataBundle EW;
    final Contents EX;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    CloseContentsAndUpdateMetadataRequest(final int xh, final DriveId ev, final MetadataBundle ew, final Contents ex) {
        this.xH = xh;
        this.EV = ev;
        this.EW = ew;
        this.EX = ex;
    }
    
    public CloseContentsAndUpdateMetadataRequest(final DriveId driveId, final MetadataBundle metadataBundle, final Contents contents) {
        this(1, driveId, metadataBundle, contents);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
