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
    final String EB;
    final DriveId EC;
    final MetadataBundle EZ;
    final int Eu;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    CreateFileIntentSenderRequest(final int xh, final MetadataBundle ez, final int eu, final String eb, final DriveId ec) {
        this.xH = xh;
        this.EZ = ez;
        this.Eu = eu;
        this.EB = eb;
        this.EC = ec;
    }
    
    public CreateFileIntentSenderRequest(final MetadataBundle metadataBundle, final int n, final String s, final DriveId driveId) {
        this(1, metadataBundle, n, s, driveId);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
}
