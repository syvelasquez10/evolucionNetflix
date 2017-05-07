// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.DriveId;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AddEventListenerRequest implements SafeParcelable
{
    public static final Parcelable$Creator<AddEventListenerRequest> CREATOR;
    final int ES;
    final PendingIntent ET;
    final DriveId Ew;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    AddEventListenerRequest(final int xh, final DriveId ew, final int es, final PendingIntent et) {
        this.xH = xh;
        this.Ew = ew;
        this.ES = es;
        this.ET = et;
    }
    
    public AddEventListenerRequest(final DriveId driveId, final int n, final PendingIntent pendingIntent) {
        this(1, driveId, n, pendingIntent);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
