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
    final MetadataBundle EZ;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new af();
    }
    
    OnMetadataResponse(final int xh, final MetadataBundle ez) {
        this.xH = xh;
        this.EZ = ez;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public MetadataBundle fQ() {
        return this.EZ;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        af.a(this, parcel, n);
    }
}
