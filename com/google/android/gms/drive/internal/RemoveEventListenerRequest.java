// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class RemoveEventListenerRequest implements SafeParcelable
{
    public static final Parcelable$Creator<RemoveEventListenerRequest> CREATOR;
    final int BR;
    final DriveId MO;
    final int NS;
    
    static {
        CREATOR = (Parcelable$Creator)new ay();
    }
    
    RemoveEventListenerRequest(final int br, final DriveId mo, final int ns) {
        this.BR = br;
        this.MO = mo;
        this.NS = ns;
    }
    
    public RemoveEventListenerRequest(final DriveId driveId, final int n) {
        this(1, driveId, n);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ay.a(this, parcel, n);
    }
}
