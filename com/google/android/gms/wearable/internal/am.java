// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class am implements SafeParcelable
{
    public static final Parcelable$Creator<am> CREATOR;
    public final long avC;
    public final String label;
    public final String packageName;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new an();
    }
    
    am(final int versionCode, final String packageName, final String label, final long avC) {
        this.versionCode = versionCode;
        this.packageName = packageName;
        this.label = label;
        this.avC = avC;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        an.a(this, parcel, n);
    }
}
