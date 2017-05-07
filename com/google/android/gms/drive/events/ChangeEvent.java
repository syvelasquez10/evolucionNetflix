// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ChangeEvent implements SafeParcelable, ResourceEvent
{
    public static final Parcelable$Creator<ChangeEvent> CREATOR;
    final int BR;
    final DriveId MO;
    final int NE;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    ChangeEvent(final int br, final DriveId mo, final int ne) {
        this.BR = br;
        this.MO = mo;
        this.NE = ne;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public DriveId getDriveId() {
        return this.MO;
    }
    
    public int getType() {
        return 1;
    }
    
    public boolean hasContentChanged() {
        return (this.NE & 0x2) != 0x0;
    }
    
    public boolean hasMetadataChanged() {
        return (this.NE & 0x1) != 0x0;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "ChangeEvent [id=%s,changeFlags=%x]", this.MO, this.NE);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
