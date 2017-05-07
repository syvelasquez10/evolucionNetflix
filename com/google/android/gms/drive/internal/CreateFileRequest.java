// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateFileRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CreateFileRequest> CREATOR;
    final int BR;
    final Contents NX;
    final String Nf;
    final MetadataBundle Od;
    final Integer Oe;
    final DriveId Of;
    final boolean Og;
    final int Oh;
    final int Oi;
    
    static {
        CREATOR = (Parcelable$Creator)new j();
    }
    
    CreateFileRequest(final int br, final DriveId driveId, final MetadataBundle metadataBundle, final Contents nx, final Integer oe, final boolean og, final String nf, final int oh, final int oi) {
        if (nx != null && oi != 0) {
            n.b(nx.getRequestId() == oi, (Object)"inconsistent contents reference");
        }
        if ((oe == null || oe == 0) && nx == null && oi == 0) {
            throw new IllegalArgumentException("Need a valid contents");
        }
        this.BR = br;
        this.Of = n.i(driveId);
        this.Od = n.i(metadataBundle);
        this.NX = nx;
        this.Oe = oe;
        this.Nf = nf;
        this.Oh = oh;
        this.Og = og;
        this.Oi = oi;
    }
    
    public CreateFileRequest(final DriveId driveId, final MetadataBundle metadataBundle, final int n, final int n2, final ExecutionOptions executionOptions) {
        this(2, driveId, metadataBundle, null, n2, executionOptions.hP(), executionOptions.hO(), executionOptions.hQ(), n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        j.a(this, parcel, n);
    }
}
