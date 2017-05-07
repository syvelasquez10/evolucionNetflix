// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

@zzgk
public class zzhr extends Handler
{
    public zzhr(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        try {
            super.handleMessage(message);
        }
        catch (Exception ex) {
            zzp.zzbA().zzc(ex, false);
            throw ex;
        }
    }
}
