// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class v implements SafeParcelable
{
    public static final Parcelable$Creator<v> CREATOR;
    public final List<ak> avo;
    public final int statusCode;
    public final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new w();
    }
    
    v(final int versionCode, final int statusCode, final List<ak> avo) {
        this.versionCode = versionCode;
        this.statusCode = statusCode;
        this.avo = avo;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        w.a(this, parcel, n);
    }
}
