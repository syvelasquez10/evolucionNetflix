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
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Map;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.signin.zzd;
import android.content.Context;
import com.google.android.gms.signin.internal.zze;

class zzg$zzc extends zzg$zzi
{
    final /* synthetic */ zzg zzZK;
    
    private zzg$zzc(final zzg zzZK) {
        this.zzZK = zzZK;
        super(zzZK, null);
    }
    
    public void zznn() {
        this.zzZK.zzZA.zza(this.zzZK.zzZE, this.zzZK.zzZq.zzaah, new zzg$zza(this.zzZK));
    }
}
