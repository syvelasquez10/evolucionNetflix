// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzf;
import java.util.Collection;
import com.google.android.gms.common.internal.zzx;
import android.os.Handler;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzqu;
import com.google.android.gms.internal.zzme;
import java.util.HashSet;
import java.util.ArrayList;
import com.google.android.gms.internal.zzqx;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Map;
import android.view.View;
import java.util.Set;
import android.accounts.Account;
import android.content.Context;
import com.google.android.gms.internal.zzlp;

class GoogleApiClient$Builder$1 implements Runnable
{
    final /* synthetic */ GoogleApiClient zzWT;
    final /* synthetic */ GoogleApiClient$Builder zzaaU;
    
    GoogleApiClient$Builder$1(final GoogleApiClient$Builder zzaaU, final GoogleApiClient zzWT) {
        this.zzaaU = zzaaU;
        this.zzWT = zzWT;
    }
    
    @Override
    public void run() {
        if (this.zzaaU.zzaaL.isFinishing() || this.zzaaU.zzaaL.getSupportFragmentManager().isDestroyed()) {
            return;
        }
        this.zzaaU.zza(zzlp.zzb(this.zzaaU.zzaaL), this.zzWT);
    }
}
