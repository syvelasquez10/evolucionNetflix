// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class as implements SafeParcelable
{
    public static final Parcelable$Creator<as> CREATOR;
    public final int avD;
    public final int statusCode;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new at();
    }
    
    as(final int versionCode, final int statusCode, final int avD) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.avD = avD;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        at.a(this, parcel, n);
    }
}
