// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import android.os.Handler;
import android.os.Looper;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.Set;
import java.util.Queue;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class zzl
{
    private AtomicInteger zzY;
    private final Map<String, Queue<zzk<?>>> zzZ;
    private final Set<zzk<?>> zzaa;
    private final PriorityBlockingQueue<zzk<?>> zzab;
    private final PriorityBlockingQueue<zzk<?>> zzac;
    private zzg[] zzad;
    private zzc zzae;
    private List<zzl$zza> zzaf;
    private final zzb zzj;
    private final zzn zzk;
    private final zzf zzz;
    
    public zzl(final zzb zzb, final zzf zzf) {
        this(zzb, zzf, 4);
    }
    
    public zzl(final zzb zzb, final zzf zzf, final int n) {
        this(zzb, zzf, n, new zze(new Handler(Looper.getMainLooper())));
    }
    
    public zzl(final zzb zzj, final zzf zzz, final int n, final zzn zzk) {
        this.zzY = new AtomicInteger();
        this.zzZ = new HashMap<String, Queue<zzk<?>>>();
        this.zzaa = new HashSet<zzk<?>>();
        this.zzab = new PriorityBlockingQueue<zzk<?>>();
        this.zzac = new PriorityBlockingQueue<zzk<?>>();
        this.zzaf = new ArrayList<zzl$zza>();
        this.zzj = zzj;
        this.zzz = zzz;
        this.zzad = new zzg[n];
        this.zzk = zzk;
    }
    
    public int getSequenceNumber() {
        return this.zzY.incrementAndGet();
    }
    
    public void start() {
        this.stop();
        (this.zzae = new zzc(this.zzab, this.zzac, this.zzj, this.zzk)).start();
        for (int i = 0; i < this.zzad.length; ++i) {
            (this.zzad[i] = new zzg(this.zzac, this.zzz, this.zzj, this.zzk)).start();
        }
    }
    
    public void stop() {
        if (this.zzae != null) {
            this.zzae.quit();
        }
        for (int i = 0; i < this.zzad.length; ++i) {
            if (this.zzad[i] != null) {
                this.zzad[i].quit();
            }
        }
    }
    
    public <T> zzk<T> zze(final zzk<T> zzk) {
        zzk.zza(this);
        synchronized (this.zzaa) {
            this.zzaa.add(zzk);
            // monitorexit(this.zzaa)
            zzk.zza(this.getSequenceNumber());
            zzk.zzc("add-to-queue");
            if (!zzk.zzr()) {
                this.zzac.add(zzk);
                return zzk;
            }
        }
        while (true) {
            final zzk<?> zzk2;
            final String zzh;
            synchronized (this.zzZ) {
                zzh = zzk2.zzh();
                if (this.zzZ.containsKey(zzh)) {
                    Queue<zzk<?>> queue;
                    if ((queue = this.zzZ.get(zzh)) == null) {
                        queue = new LinkedList<zzk<?>>();
                    }
                    queue.add(zzk2);
                    this.zzZ.put(zzh, queue);
                    if (zzs.DEBUG) {
                        zzs.zza("Request for cacheKey=%s is in flight, putting on hold.", zzh);
                    }
                    return (zzk<T>)zzk2;
                }
            }
            this.zzZ.put(zzh, null);
            this.zzab.add(zzk2);
            return (zzk<T>)zzk2;
        }
    }
    
     <T> void zzf(final zzk<T> zzk) {
        synchronized (this.zzaa) {
            this.zzaa.remove(zzk);
            // monitorexit(this.zzaa)
            final List<zzl$zza> zzaf = this.zzaf;
            synchronized (this.zzaa) {
                final Iterator<zzl$zza<T>> iterator = (Iterator<zzl$zza<T>>)this.zzaf.iterator();
                while (iterator.hasNext()) {
                    iterator.next().zzg(zzk);
                }
            }
        }
        // monitorexit(zzaf)
        final zzk zzk2;
        if (zzk2.zzr()) {
            synchronized (this.zzZ) {
                final String zzh = zzk2.zzh();
                final Queue<zzk<?>> queue = this.zzZ.remove(zzh);
                if (queue != null) {
                    if (zzs.DEBUG) {
                        zzs.zza("Releasing %d waiting requests for cacheKey=%s.", queue.size(), zzh);
                    }
                    this.zzab.addAll((Collection<?>)queue);
                }
            }
        }
    }
}
