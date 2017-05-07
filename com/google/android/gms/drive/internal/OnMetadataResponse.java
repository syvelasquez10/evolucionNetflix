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
    final int BR;
    final MetadataBundle Od;
    
    static {
        CREATOR = (Parcelable$Creator)new ap();
    }
    
    OnMetadataResponse(final int br, final MetadataBundle od) {
        this.BR = br;
        this.Od = od;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public MetadataBundle il() {
        return this.Od;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ap.a(this, parcel, n);
    }
}
