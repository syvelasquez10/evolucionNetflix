// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import java.util.List;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SetResourceParentsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<SetResourceParentsRequest> CREATOR;
    final int BR;
    final DriveId Pr;
    final List<DriveId> Ps;
    
    static {
        CREATOR = (Parcelable$Creator)new ba();
    }
    
    SetResourceParentsRequest(final int br, final DriveId pr, final List<DriveId> ps) {
        this.BR = br;
        this.Pr = pr;
        this.Ps = ps;
    }
    
    public SetResourceParentsRequest(final DriveId driveId, final List<DriveId> list) {
        this(1, driveId, list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ba.a(this, parcel, n);
    }
}
