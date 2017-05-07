// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

class zzn$2 implements Callable<String>
{
    final /* synthetic */ zzn zzMH;
    
    zzn$2(final zzn zzMH) {
        this.zzMH = zzMH;
    }
    
    public String zzji() {
        return this.zzMH.zzjg();
    }
}
