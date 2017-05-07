// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ConflictEvent implements SafeParcelable, DriveEvent
{
    public static final Parcelable$Creator<ConflictEvent> CREATOR;
    final DriveId Ew;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    ConflictEvent(final int xh, final DriveId ew) {
        this.xH = xh;
        this.Ew = ew;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public DriveId getDriveId() {
        return this.Ew;
    }
    
    @Override
    public int getType() {
        return 1;
    }
    
    @Override
    public String toString() {
        return String.format("ConflictEvent [id=%s]", this.Ew);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
