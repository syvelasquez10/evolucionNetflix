// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.Map;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzq;
import android.os.Bundle;

class zze$zzd implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    final /* synthetic */ zze zzXc;
    
    private zze$zzd(final zze zzXc) {
        this.zzXc = zzXc;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.zzXc.zzWS.zza(new zze$zzb(this.zzXc));
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzXc.zzWK.lock();
        try {
            if (this.zzXc.zzc(connectionResult)) {
                this.zzXc.zzmG();
                this.zzXc.zzmE();
            }
            else {
                this.zzXc.zzd(connectionResult);
            }
        }
        finally {
            this.zzXc.zzWK.unlock();
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
}
