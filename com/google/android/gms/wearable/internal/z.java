// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class z implements SafeParcelable
{
    public static final Parcelable$Creator<z> CREATOR;
    public final ParcelFileDescriptor avq;
    public final int statusCode;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new aa();
    }
    
    z(final int versionCode, final int statusCode, final ParcelFileDescriptor avq) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.avq = avq;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        aa.a(this, parcel, n | 0x1);
    }
}
