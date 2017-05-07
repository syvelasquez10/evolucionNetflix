// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class p implements SafeParcelable
{
    public static final Parcelable$Creator<p> CREATOR;
    public final int avl;
    public final int statusCode;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new q();
    }
    
    p(final int versionCode, final int statusCode, final int avl) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.avl = avl;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        q.a(this, parcel, n);
    }
}
