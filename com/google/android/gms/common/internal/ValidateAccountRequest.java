// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Bundle;
import com.google.android.gms.common.api.Scope;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValidateAccountRequest implements SafeParcelable
{
    public static final Parcelable$Creator<ValidateAccountRequest> CREATOR;
    final int mVersionCode;
    private final String zzSb;
    final IBinder zzaeH;
    private final Scope[] zzaeI;
    private final int zzagu;
    private final Bundle zzagv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzad();
    }
    
    ValidateAccountRequest(final int mVersionCode, final int zzagu, final IBinder zzaeH, final Scope[] zzaeI, final Bundle zzagv, final String zzSb) {
        this.mVersionCode = mVersionCode;
        this.zzagu = zzagu;
        this.zzaeH = zzaeH;
        this.zzaeI = zzaeI;
        this.zzagv = zzagv;
        this.zzSb = zzSb;
    }
    
    public ValidateAccountRequest(final zzp zzp, final Scope[] array, final String s, final Bundle bundle) {
        final int google_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        IBinder binder;
        if (zzp == null) {
            binder = null;
        }
        else {
            binder = zzp.asBinder();
        }
        this(1, google_PLAY_SERVICES_VERSION_CODE, binder, array, bundle, s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCallingPackage() {
        return this.zzSb;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzad.zza(this, parcel, n);
    }
    
    public int zzpu() {
        return this.zzagu;
    }
    
    public Scope[] zzpv() {
        return this.zzaeI;
    }
    
    public Bundle zzpw() {
        return this.zzagv;
    }
}
