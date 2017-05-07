// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.HashMap;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import java.util.HashSet;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.zze;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.signin.zzd;
import android.content.Context;

abstract class zzg$zzi implements Runnable
{
    final /* synthetic */ zzg zzZK;
    
    private zzg$zzi(final zzg zzZK) {
        this.zzZK = zzZK;
    }
    
    @Override
    public void run() {
        this.zzZK.zzZs.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            this.zznn();
        }
        catch (RuntimeException ex) {
            this.zzZK.zzZq.zza(ex);
        }
        finally {
            this.zzZK.zzZs.unlock();
        }
    }
    
    protected abstract void zznn();
}
