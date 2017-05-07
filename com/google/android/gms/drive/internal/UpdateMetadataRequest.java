// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class UpdateMetadataRequest implements SafeParcelable
{
    public static final Parcelable$Creator<UpdateMetadataRequest> CREATOR;
    final int kg;
    final MetadataBundle rB;
    final DriveId rr;
    
    static {
        CREATOR = (Parcelable$Creator)new aa();
    }
    
    UpdateMetadataRequest(final int kg, final DriveId rr, final MetadataBundle rb) {
        this.kg = kg;
        this.rr = rr;
        this.rB = rb;
    }
    
    public UpdateMetadataRequest(final DriveId driveId, final MetadataBundle metadataBundle) {
        this(1, driveId, metadataBundle);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aa.a(this, parcel, n);
    }
}
