// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OpenFileIntentSenderRequest implements SafeParcelable
{
    public static final Parcelable$Creator<OpenFileIntentSenderRequest> CREATOR;
    final int BR;
    final String No;
    final String[] Np;
    final DriveId Nq;
    
    static {
        CREATOR = (Parcelable$Creator)new aw();
    }
    
    OpenFileIntentSenderRequest(final int br, final String no, final String[] np, final DriveId nq) {
        this.BR = br;
        this.No = no;
        this.Np = np;
        this.Nq = nq;
    }
    
    public OpenFileIntentSenderRequest(final String s, final String[] array, final DriveId driveId) {
        this(1, s, array, driveId);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aw.a(this, parcel, n);
    }
}
