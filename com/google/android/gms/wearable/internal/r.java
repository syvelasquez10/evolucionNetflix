// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import com.google.android.gms.wearable.c;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@Deprecated
public class r implements SafeParcelable
{
    public static final Parcelable$Creator<r> CREATOR;
    public final c avm;
    public final int statusCode;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new s();
    }
    
    r(final int versionCode, final int statusCode, final c avm) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.avm = avm;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        s.a(this, parcel, n);
    }
}
