// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CreateContentsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<CreateContentsRequest> CREATOR;
    final int BR;
    final int MN;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    public CreateContentsRequest(final int n) {
        this(1, n);
    }
    
    CreateContentsRequest(final int br, final int mn) {
        this.BR = br;
        n.b(mn == 536870912 || mn == 805306368, (Object)"Cannot create a new read-only contents!");
        this.MN = mn;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
}
