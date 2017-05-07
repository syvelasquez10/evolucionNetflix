// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnMetadataResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnMetadataResponse> CREATOR;
    final int kg;
    final MetadataBundle qZ;
    
    static {
        CREATOR = (Parcelable$Creator)new v();
    }
    
    OnMetadataResponse(final int kg, final MetadataBundle qz) {
        this.kg = kg;
        this.qZ = qz;
    }
    
    public MetadataBundle cU() {
        return this.qZ;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        v.a(this, parcel, n);
    }
}
