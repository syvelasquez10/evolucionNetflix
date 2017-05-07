// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AuthAccountResult implements SafeParcelable
{
    public static final Parcelable$Creator<AuthAccountResult> CREATOR;
    final int zzCY;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public AuthAccountResult() {
        this(1);
    }
    
    AuthAccountResult(final int zzCY) {
        this.zzCY = zzCY;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
}
