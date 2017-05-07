// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Collection;
import android.os.Parcel;
import com.google.android.gms.common.GoogleApiAvailability;
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
    final int zzafq;
    int zzafr;
    String zzafs;
    IBinder zzaft;
    Scope[] zzafu;
    Bundle zzafv;
    Account zzafw;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    public GetServiceRequest(final int zzafq) {
        this.version = 2;
        this.zzafr = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzafq = zzafq;
    }
    
    GetServiceRequest(final int version, final int zzafq, final int zzafr, final String zzafs, final IBinder zzaft, final Scope[] zzafu, final Bundle zzafv, final Account zzafw) {
        this.version = version;
        this.zzafq = zzafq;
        this.zzafr = zzafr;
        this.zzafs = zzafs;
        if (version < 2) {
            this.zzafw = this.zzaG(zzaft);
        }
        else {
            this.zzaft = zzaft;
            this.zzafw = zzafw;
        }
        this.zzafu = zzafu;
        this.zzafv = zzafv;
    }
    
    private Account zzaG(final IBinder binder) {
        Account zzb = null;
        if (binder != null) {
            zzb = zza.zzb(zzp$zza.zzaH(binder));
        }
        return zzb;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
    
    public GetServiceRequest zzc(final Account zzafw) {
        this.zzafw = zzafw;
        return this;
    }
    
    public GetServiceRequest zzc(final zzp zzp) {
        if (zzp != null) {
            this.zzaft = zzp.asBinder();
        }
        return this;
    }
    
    public GetServiceRequest zzcl(final String zzafs) {
        this.zzafs = zzafs;
        return this;
    }
    
    public GetServiceRequest zzd(final Collection<Scope> collection) {
        this.zzafu = collection.toArray(new Scope[collection.size()]);
        return this;
    }
    
    public GetServiceRequest zzg(final Bundle zzafv) {
        this.zzafv = zzafv;
        return this;
    }
}
