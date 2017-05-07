// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.common.api.ResultCallback;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzx;
import java.util.HashSet;
import com.google.android.gms.tagmanager.TagManager;
import java.util.Set;
import android.content.Context;

public class zzqg
{
    private static zzqg zzaTp;
    private Context mContext;
    private boolean mStarted;
    private final Set<zzqg$zza> zzaTq;
    private TagManager zzaTr;
    private zzqf zzpo;
    
    zzqg(final Context mContext, final TagManager zzaTr) {
        this.zzaTq = new HashSet<zzqg$zza>();
        this.zzaTr = null;
        this.mContext = mContext;
        this.zzaTr = zzaTr;
    }
    
    public static zzqg zzaR(final Context context) {
        zzx.zzv(context);
        Label_0041: {
            if (zzqg.zzaTp != null) {
                break Label_0041;
            }
            synchronized (zzqg.class) {
                if (zzqg.zzaTp == null) {
                    zzqg.zzaTp = new zzqg(context, TagManager.getInstance(context.getApplicationContext()));
                }
                return zzqg.zzaTp;
            }
        }
    }
    
    private void zzgK() {
        synchronized (this) {
            final Iterator<zzqg$zza> iterator = this.zzaTq.iterator();
            while (iterator.hasNext()) {
                iterator.next().zzbo();
            }
        }
    }
    // monitorexit(this)
    
    public void start() {
        synchronized (this) {
            if (this.mStarted) {
                throw new IllegalStateException("Method start() has already been called");
            }
        }
        if (this.zzpo == null) {
            throw new IllegalStateException("No settings configured");
        }
        this.mStarted = true;
        this.zzaTr.zzc(this.zzpo.zzBj(), -1, "admob").setResultCallback(new zzqg$1(this));
    }
    // monitorexit(this)
    
    public zzqf zzBn() {
        synchronized (this) {
            return this.zzpo;
        }
    }
    
    public void zza(final zzqf zzqf) {
        synchronized (this) {
            if (this.mStarted) {
                throw new IllegalStateException("Settings can't be changed after TagManager has been started");
            }
        }
        final zzqf zzpo;
        this.zzpo = zzpo;
    }
    // monitorexit(this)
    
    public void zza(final zzqg$zza zzqg$zza) {
        synchronized (this) {
            this.zzaTq.add(zzqg$zza);
        }
    }
}
