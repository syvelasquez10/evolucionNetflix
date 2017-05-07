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
    final IBinder zzacC;
    private final Scope[] zzacD;
    private final int zzaeq;
    private final Bundle zzaer;
    private final String zzaes;
    
    static {
        CREATOR = (Parcelable$Creator)new zzad();
    }
    
    ValidateAccountRequest(final int mVersionCode, final int zzaeq, final IBinder zzacC, final Scope[] zzacD, final Bundle zzaer, final String zzaes) {
        this.mVersionCode = mVersionCode;
        this.zzaeq = zzaeq;
        this.zzacC = zzacC;
        this.zzacD = zzacD;
        this.zzaer = zzaer;
        this.zzaes = zzaes;
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
        return this.zzaes;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzad.zza(this, parcel, n);
    }
    
    public int zzoS() {
        return this.zzaeq;
    }
    
    public Scope[] zzoT() {
        return this.zzacD;
    }
    
    public Bundle zzoU() {
        return this.zzaer;
    }
}
