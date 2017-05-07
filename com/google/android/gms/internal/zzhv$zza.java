// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.DownloadManager$Request;

class zzhv$zza extends zzhv
{
    zzhv$zza() {
        super(null);
    }
    
    @Override
    public boolean zza(final DownloadManager$Request downloadManager$Request) {
        downloadManager$Request.setShowRunningNotification(true);
        return true;
    }
    
    @Override
    public int zzgv() {
        return 6;
    }
    
    @Override
    public int zzgw() {
        return 7;
    }
}
