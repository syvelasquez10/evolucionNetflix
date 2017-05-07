// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import java.util.Collection;
import android.os.Handler;
import com.google.android.gms.signin.zzb;
import com.google.android.gms.internal.zzld;
import java.util.HashSet;
import com.google.android.gms.signin.zze$zza;
import java.util.ArrayList;
import com.google.android.gms.signin.zze;
import com.google.android.gms.signin.zzd;
import com.google.android.gms.common.GoogleApiAvailability;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzf$zza;
import java.util.Map;
import android.view.View;
import java.util.Set;
import android.os.Looper;
import android.accounts.Account;
import android.content.Context;

class GoogleApiClient$Builder$1 implements Runnable
{
    final /* synthetic */ GoogleApiClient zzVc;
    final /* synthetic */ GoogleApiClient$Builder zzZn;
    
    GoogleApiClient$Builder$1(final GoogleApiClient$Builder zzZn, final GoogleApiClient zzVc) {
        this.zzZn = zzZn;
        this.zzVc = zzVc;
    }
    
    @Override
    public void run() {
        if (this.zzZn.zzZe.isFinishing() || this.zzZn.zzZe.getSupportFragmentManager().isDestroyed()) {
            return;
        }
        this.zzZn.zza(zzp.zzb(this.zzZn.zzZe), this.zzVc);
    }
}
