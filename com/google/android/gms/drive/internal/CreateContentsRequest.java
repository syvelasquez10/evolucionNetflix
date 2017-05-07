// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateContentsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CreateContentsRequest> CREATOR;
    final int kg;
    
    static {
        CREATOR = (Parcelable$Creator)new c();
    }
    
    public CreateContentsRequest() {
        this(1);
    }
    
    CreateContentsRequest(final int kg) {
        this.kg = kg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        c.a(this, parcel, n);
    }
}
