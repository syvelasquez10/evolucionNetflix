// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.DownloadManager$Request;

public class zzie$zza extends zzie
{
    public zzie$zza() {
        super(null);
    }
    
    @Override
    public boolean zza(final DownloadManager$Request downloadManager$Request) {
        downloadManager$Request.setShowRunningNotification(true);
        return true;
    }
    
    @Override
    public int zzgG() {
        return 6;
    }
    
    @Override
    public int zzgH() {
        return 7;
    }
}
