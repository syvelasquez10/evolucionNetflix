// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import com.google.android.gms.wearable.c;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class t implements SafeParcelable
{
    public static final Parcelable$Creator<t> CREATOR;
    public final c[] avn;
    public final int statusCode;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new u();
    }
    
    t(final int versionCode, final int statusCode, final c[] avn) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.avn = avn;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        u.a(this, parcel, n);
    }
}
