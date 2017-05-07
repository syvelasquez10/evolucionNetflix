// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FACLData implements SafeParcelable
{
    public static final zzb CREATOR;
    final int version;
    FACLConfig zzTD;
    String zzTE;
    boolean zzTF;
    String zzTG;
    
    static {
        CREATOR = new zzb();
    }
    
    FACLData(final int version, final FACLConfig zzTD, final String zzTE, final boolean zzTF, final String zzTG) {
        this.version = version;
        this.zzTD = zzTD;
        this.zzTE = zzTE;
        this.zzTF = zzTF;
        this.zzTG = zzTG;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
