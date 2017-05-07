// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ScopeDetail implements SafeParcelable
{
    public static final zzc CREATOR;
    String description;
    final int version;
    String zzTH;
    String zzTI;
    String zzTJ;
    String zzTK;
    List<String> zzTL;
    public FACLData zzTM;
    
    static {
        CREATOR = new zzc();
    }
    
    ScopeDetail(final int version, final String description, final String zzTH, final String zzTI, final String zzTJ, final String zzTK, final List<String> zzTL, final FACLData zzTM) {
        this.version = version;
        this.description = description;
        this.zzTH = zzTH;
        this.zzTI = zzTI;
        this.zzTJ = zzTJ;
        this.zzTK = zzTK;
        this.zzTL = zzTL;
        this.zzTM = zzTM;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
