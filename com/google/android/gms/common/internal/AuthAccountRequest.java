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
    final int zzCY;
    final IBinder zzZN;
    final Scope[] zzZO;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    AuthAccountRequest(final int zzCY, final IBinder zzZN, final Scope[] zzZO) {
        this.zzCY = zzCY;
        this.zzZN = zzZN;
        this.zzZO = zzZO;
    }
    
    public AuthAccountRequest(final IAccountAccessor accountAccessor, final Set<Scope> set) {
        this(1, accountAccessor.asBinder(), set.toArray(new Scope[set.size()]));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
