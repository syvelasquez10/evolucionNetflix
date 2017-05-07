// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ao implements SafeParcelable
{
    public static final Parcelable$Creator<ao> CREATOR;
    public final m avp;
    public final int statusCode;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new ap();
    }
    
    ao(final int versionCode, final int statusCode, final m avp) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.avp = avp;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ap.a(this, parcel, n);
    }
}
