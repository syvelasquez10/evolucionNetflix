// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import java.util.Set;
import com.google.android.gms.common.api.Scope;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AuthAccountRequest implements SafeParcelable
{
    public static final Parcelable$Creator<AuthAccountRequest> CREATOR;
    final int mVersionCode;
    final IBinder zzacC;
    final Scope[] zzacD;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    AuthAccountRequest(final int mVersionCode, final IBinder zzacC, final Scope[] zzacD) {
        this.mVersionCode = mVersionCode;
        this.zzacC = zzacC;
        this.zzacD = zzacD;
    }
    
    public AuthAccountRequest(final zzp zzp, final Set<Scope> set) {
        this(1, zzp.asBinder(), set.toArray(new Scope[set.size()]));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
