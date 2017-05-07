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
    final int zzadn;
    int zzado;
    String zzadp;
    IBinder zzadq;
    Scope[] zzadr;
    Bundle zzads;
    Account zzadt;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    public GetServiceRequest(final int zzadn) {
        this.version = 2;
        this.zzado = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzadn = zzadn;
    }
    
    GetServiceRequest(final int version, final int zzadn, final int zzado, final String zzadp, final IBinder zzadq, final Scope[] zzadr, final Bundle zzads, final Account zzadt) {
        this.version = version;
        this.zzadn = zzadn;
        this.zzado = zzado;
        this.zzadp = zzadp;
        if (version < 2) {
            this.zzadt = this.zzaG(zzadq);
        }
        else {
            this.zzadq = zzadq;
            this.zzadt = zzadt;
        }
        this.zzadr = zzadr;
        this.zzads = zzads;
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
    
    public GetServiceRequest zzb(final Account zzadt) {
        this.zzadt = zzadt;
        return this;
    }
    
    public GetServiceRequest zzc(final zzp zzp) {
        if (zzp != null) {
            this.zzadq = zzp.asBinder();
        }
        return this;
    }
    
    public GetServiceRequest zzck(final String zzadp) {
        this.zzadp = zzadp;
        return this;
    }
    
    public GetServiceRequest zzd(final Collection<Scope> collection) {
        this.zzadr = collection.toArray(new Scope[collection.size()]);
        return this;
    }
    
    public GetServiceRequest zzg(final Bundle zzads) {
        this.zzads = zzads;
        return this;
    }
}
