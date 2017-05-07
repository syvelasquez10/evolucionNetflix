// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Bundle;
import com.google.android.gms.common.api.Scope;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValidateAccountRequest implements SafeParcelable
{
    public static final Parcelable$Creator<ValidateAccountRequest> CREATOR;
    final int zzCY;
    final IBinder zzZN;
    private final Scope[] zzZO;
    private final int zzabf;
    private final Bundle zzabg;
    private final String zzabh;
    
    static {
        CREATOR = (Parcelable$Creator)new zzab();
    }
    
    ValidateAccountRequest(final int zzCY, final int zzabf, final IBinder zzZN, final Scope[] zzZO, final Bundle zzabg, final String zzabh) {
        this.zzCY = zzCY;
        this.zzabf = zzabf;
        this.zzZN = zzZN;
        this.zzZO = zzZO;
        this.zzabg = zzabg;
        this.zzabh = zzabh;
    }
    
    public ValidateAccountRequest(final IAccountAccessor accountAccessor, final Scope[] array, final String s, final Bundle bundle) {
        final int google_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        IBinder binder;
        if (accountAccessor == null) {
            binder = null;
        }
        else {
            binder = accountAccessor.asBinder();
        }
        this(1, google_PLAY_SERVICES_VERSION_CODE, binder, array, bundle, s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCallingPackage() {
        return this.zzabh;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzab.zza(this, parcel, n);
    }
    
    public int zzob() {
        return this.zzabf;
    }
    
    public Scope[] zzoc() {
        return this.zzZO;
    }
    
    public Bundle zzod() {
        return this.zzabg;
    }
}
