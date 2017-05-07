// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Collection;
import android.os.RemoteException;
import android.os.DeadObjectException;
import android.os.Bundle;
import android.os.IBinder;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.content.ServiceConnection;
import android.util.Log;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Looper;
import android.accounts.Account;
import android.os.Handler;
import android.content.Context;
import com.google.android.gms.common.api.Api$zzb;
import android.os.IInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient$zza;

public class zzj$zzf implements GoogleApiClient$zza
{
    final /* synthetic */ zzj zzadH;
    
    public zzj$zzf(final zzj zzadH) {
        this.zzadH = zzadH;
    }
    
    @Override
    public void zza(final ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            this.zzadH.zza(null, this.zzadH.zzZp);
        }
        else if (this.zzadH.zzadC != null) {
            this.zzadH.zzadC.onConnectionFailed(connectionResult);
        }
    }
    
    @Override
    public void zzb(final ConnectionResult connectionResult) {
        throw new IllegalStateException("Legacy GmsClient received onReportAccountValidation callback.");
    }
}
