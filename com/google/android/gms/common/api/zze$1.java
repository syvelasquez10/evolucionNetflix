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
import android.os.Bundle;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzps;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;

class zze$1 implements Runnable
{
    final /* synthetic */ ConnectionResult zzXb;
    final /* synthetic */ zze zzXc;
    
    zze$1(final zze zzXc, final ConnectionResult zzXb) {
        this.zzXc = zzXc;
        this.zzXb = zzXb;
    }
    
    @Override
    public void run() {
        this.zzXc.zzWK.lock();
        try {
            this.zzXc.zzd(this.zzXb);
        }
        finally {
            this.zzXc.zzWK.unlock();
        }
    }
}
