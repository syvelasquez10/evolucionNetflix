// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class au implements SafeParcelable
{
    public static final Parcelable$Creator<au> CREATOR;
    public final long avC;
    public final List<am> avE;
    public final int statusCode;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new av();
    }
    
    au(final int versionCode, final int statusCode, final long avC, final List<am> avE) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.avC = avC;
        this.avE = avE;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        av.a(this, parcel, n);
    }
}
