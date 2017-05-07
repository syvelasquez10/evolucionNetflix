// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ab implements SafeParcelable
{
    public static final Parcelable$Creator<ab> CREATOR;
    public final ak avr;
    public final int statusCode;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new ac();
    }
    
    ab(final int versionCode, final int statusCode, final ak avr) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.avr = avr;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ac.a(this, parcel, n);
    }
}
