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
    final int BR;
    final String No;
    final DriveId Nq;
    final MetadataBundle Od;
    final Integer Oe;
    final int uQ;
    
    static {
        CREATOR = (Parcelable$Creator)new i();
    }
    
    CreateFileIntentSenderRequest(final int br, final MetadataBundle od, final int uq, final String no, final DriveId nq, final Integer oe) {
        this.BR = br;
        this.Od = od;
        this.uQ = uq;
        this.No = no;
        this.Nq = nq;
        this.Oe = oe;
    }
    
    public CreateFileIntentSenderRequest(final MetadataBundle metadataBundle, final int n, final String s, final DriveId driveId, final int n2) {
        this(1, metadataBundle, n, s, driveId, n2);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        i.a(this, parcel, n);
    }
}
