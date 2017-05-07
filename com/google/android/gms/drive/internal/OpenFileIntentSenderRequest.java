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
    final int kg;
    final String qL;
    final DriveId qM;
    final String[] qW;
    
    static {
        CREATOR = (Parcelable$Creator)new x();
    }
    
    OpenFileIntentSenderRequest(final int kg, final String ql, final String[] qw, final DriveId qm) {
        this.kg = kg;
        this.qL = ql;
        this.qW = qw;
        this.qM = qm;
    }
    
    public OpenFileIntentSenderRequest(final String s, final String[] array, final DriveId driveId) {
        this(1, s, array, driveId);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        x.a(this, parcel, n);
    }
}
