// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Collection;
import android.os.Parcel;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.accounts.Account;
import android.os.Bundle;
import com.google.android.gms.common.api.Scope;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GetServiceRequest implements SafeParcelable
{
    public static final Parcelable$Creator<GetServiceRequest> CREATOR;
    final int version;
    final int zzaac;
    int zzaad;
    String zzaae;
    IBinder zzaaf;
    Scope[] zzaag;
    Bundle zzaah;
    Account zzaai;
    
    static {
        CREATOR = (Parcelable$Creator)new zzh();
    }
    
    public GetServiceRequest(final int zzaac) {
        this.version = 2;
        this.zzaad = GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzaac = zzaac;
    }
    
    GetServiceRequest(final int version, final int zzaac, final int zzaad, final String zzaae, final IBinder zzaaf, final Scope[] zzaag, final Bundle zzaah, final Account zzaai) {
        this.version = version;
        this.zzaac = zzaac;
        this.zzaad = zzaad;
        this.zzaae = zzaae;
        if (version < 2) {
            this.zzaai = this.zzaC(zzaaf);
        }
        else {
            this.zzaaf = zzaaf;
            this.zzaai = zzaai;
        }
        this.zzaag = zzaag;
        this.zzaah = zzaah;
    }
    
    private Account zzaC(final IBinder binder) {
        Account zza = null;
        if (binder != null) {
            zza = com.google.android.gms.common.internal.zza.zza(IAccountAccessor$zza.zzaD(binder));
        }
        return zza;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzh.zza(this, parcel, n);
    }
    
    public GetServiceRequest zzb(final Account zzaai) {
        this.zzaai = zzaai;
        return this;
    }
    
    public GetServiceRequest zzb(final IAccountAccessor accountAccessor) {
        if (accountAccessor != null) {
            this.zzaaf = accountAccessor.asBinder();
        }
        return this;
    }
    
    public GetServiceRequest zzb(final Collection<Scope> collection) {
        this.zzaag = collection.toArray(new Scope[collection.size()]);
        return this;
    }
    
    public GetServiceRequest zzcb(final String zzaae) {
        this.zzaae = zzaae;
        return this;
    }
    
    public GetServiceRequest zzf(final Bundle zzaah) {
        this.zzaah = zzaah;
        return this;
    }
}
