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
    final String EB;
    final DriveId EC;
    final String[] EQ;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new ai();
    }
    
    OpenFileIntentSenderRequest(final int xh, final String eb, final String[] eq, final DriveId ec) {
        this.xH = xh;
        this.EB = eb;
        this.EQ = eq;
        this.EC = ec;
    }
    
    public OpenFileIntentSenderRequest(final String s, final String[] array, final DriveId driveId) {
        this(1, s, array, driveId);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ai.a(this, parcel, n);
    }
}
