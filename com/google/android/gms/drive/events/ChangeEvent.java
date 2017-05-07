// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ChangeEvent implements SafeParcelable, ResourceEvent
{
    public static final Parcelable$Creator<ChangeEvent> CREATOR;
    final int ER;
    final DriveId Ew;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    ChangeEvent(final int xh, final DriveId ew, final int er) {
        this.xH = xh;
        this.Ew = ew;
        this.ER = er;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public DriveId getDriveId() {
        return this.Ew;
    }
    
    public int getType() {
        return 1;
    }
    
    public boolean hasContentChanged() {
        return (this.ER & 0x2) != 0x0;
    }
    
    public boolean hasMetadataChanged() {
        return (this.ER & 0x1) != 0x0;
    }
    
    @Override
    public String toString() {
        return String.format("ChangeEvent [id=%s,changeFlags=%x]", this.Ew, this.ER);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
