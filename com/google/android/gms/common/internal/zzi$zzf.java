// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.DeadObjectException;
import java.util.Collection;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.content.ServiceConnection;
import android.util.Log;
import java.util.Iterator;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.ArrayList;
import android.os.Looper;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.accounts.Account;
import android.os.Handler;
import android.content.Context;
import com.google.android.gms.common.api.Api$Client;
import android.os.IInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionProgressReportCallbacks;

public class zzi$zzf implements GoogleApiClient$ConnectionProgressReportCallbacks
{
    final /* synthetic */ zzi zzaaw;
    
    public zzi$zzf(final zzi zzaaw) {
        this.zzaaw = zzaaw;
    }
    
    @Override
    public void onReportAccountValidation(final ConnectionResult connectionResult) {
        throw new IllegalStateException("Legacy GmsClient received onReportAccountValidation callback.");
    }
    
    @Override
    public void onReportServiceBinding(final ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            this.zzaaw.getRemoteService(null, this.zzaaw.zzWI);
        }
        else if (this.zzaaw.zzaar != null) {
            this.zzaaw.zzaar.onConnectionFailed(connectionResult);
        }
    }
}
