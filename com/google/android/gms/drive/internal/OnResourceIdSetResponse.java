// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnResourceIdSetResponse implements SafeParcelable
{
    public static final Parcelable$Creator<OnResourceIdSetResponse> CREATOR;
    private final int BR;
    private final List<String> NU;
    
    static {
        CREATOR = (Parcelable$Creator)new ar();
    }
    
    OnResourceIdSetResponse(final int br, final List<String> nu) {
        this.BR = br;
        this.NU = nu;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    public List<String> hX() {
        return this.NU;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ar.a(this, parcel, n);
    }
}
