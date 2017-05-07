// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateFileIntentSenderRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CreateFileIntentSenderRequest> CREATOR;
    final int kg;
    final int qE;
    final String qL;
    final DriveId qM;
    final MetadataBundle qZ;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    CreateFileIntentSenderRequest(final int kg, final MetadataBundle qz, final int qe, final String ql, final DriveId qm) {
        this.kg = kg;
        this.qZ = qz;
        this.qE = qe;
        this.qL = ql;
        this.qM = qm;
    }
    
    public CreateFileIntentSenderRequest(final MetadataBundle metadataBundle, final int n, final String s, final DriveId driveId) {
        this(1, metadataBundle, n, s, driveId);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
