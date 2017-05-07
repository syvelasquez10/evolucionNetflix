// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.DisplayMetrics;
import com.google.android.gms.analytics.internal.zzam;
import java.util.Locale;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.text.TextUtils;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import android.net.Uri;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;
import com.google.android.gms.common.internal.zzx;
import java.util.List;
import android.content.Context;
import java.util.Iterator;

class zzof$1 implements Runnable
{
    final /* synthetic */ zzob zzaIi;
    final /* synthetic */ zzof zzaIj;
    
    zzof$1(final zzof zzaIj, final zzob zzaIi) {
        this.zzaIj = zzaIj;
        this.zzaIi = zzaIi;
    }
    
    @Override
    public void run() {
        this.zzaIi.zzxo().zza(this.zzaIi);
        final Iterator<zzog> iterator = this.zzaIj.zzaIe.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(this.zzaIi);
        }
        this.zzaIj.zzb(this.zzaIi);
    }
}
