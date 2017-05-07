// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import java.util.Iterator;
import android.content.ComponentCallbacks;
import android.os.Build$VERSION;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import android.content.Context;

public class TagManager
{
    private static TagManager zzaSv;
    private final Context mContext;
    private final DataLayer zzaOT;
    private final zzs zzaRp;
    private final TagManager$zza zzaSs;
    private final zzct zzaSt;
    private final ConcurrentMap<zzo, Boolean> zzaSu;
    
    TagManager(final Context context, final TagManager$zza zzaSs, final DataLayer zzaOT, final zzct zzaSt) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.zzaSt = zzaSt;
        this.zzaSs = zzaSs;
        this.zzaSu = new ConcurrentHashMap<zzo, Boolean>();
        (this.zzaOT = zzaOT).zza(new TagManager$1(this));
        this.zzaOT.zza(new zzd(this.mContext));
        this.zzaRp = new zzs();
        this.zzAU();
    }
    
    public static TagManager getInstance(final Context context) {
        Label_0068: {
            synchronized (TagManager.class) {
                if (TagManager.zzaSv != null) {
                    break Label_0068;
                }
                if (context == null) {
                    zzbg.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
            }
            final Context context2;
            TagManager.zzaSv = new TagManager(context2, new TagManager$2(), new DataLayer(new zzw(context2)), zzcu.zzAP());
        }
        // monitorexit(TagManager.class)
        return TagManager.zzaSv;
    }
    
    private void zzAU() {
        if (Build$VERSION.SDK_INT >= 14) {
            this.mContext.registerComponentCallbacks((ComponentCallbacks)new TagManager$3(this));
        }
    }
    
    private void zzeU(final String s) {
        final Iterator<zzo> iterator = this.zzaSu.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().zzew(s);
        }
    }
    
    public void dispatch() {
        this.zzaSt.dispatch();
    }
    
    public DataLayer getDataLayer() {
        return this.zzaOT;
    }
    
    void zza(final zzo zzo) {
        this.zzaSu.put(zzo, true);
    }
    
    boolean zzb(final zzo zzo) {
        return this.zzaSu.remove(zzo) != null;
    }
    
    public PendingResult<ContainerHolder> zzc(final String s, final int n, final String s2) {
        final zzp zza = this.zzaSs.zza(this.mContext, this, null, s, n, this.zzaRp);
        zza.load(s2);
        return zza;
    }
}
