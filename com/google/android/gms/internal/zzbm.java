// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.io.IOException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Comparator;
import java.util.Arrays;

public class zzbm
{
    private final int zzsp;
    private final int zzsq;
    private final int zzsr;
    private final zzbl zzss;
    
    public zzbm(final int zzsq) {
        this.zzss = new zzbo();
        this.zzsq = zzsq;
        this.zzsp = 6;
        this.zzsr = 0;
    }
    
    private String zzA(String zzcz) {
        final String[] split = zzcz.split("\n");
        if (split.length == 0) {
            return "";
        }
        zzcz = (String)this.zzcz();
        Arrays.sort(split, new zzbm$1(this));
        for (int n = 0; n < split.length && n < this.zzsq; ++n) {
            if (split[n].trim().length() != 0) {
                try {
                    ((zzbm$zza)zzcz).write(this.zzss.zzz(split[n]));
                    continue;
                }
                catch (IOException ex) {
                    zzb.zzb("Error while writing hash to byteStream", ex);
                }
                break;
            }
        }
        return ((zzbm$zza)zzcz).toString();
    }
    
    String zzB(String zzcz) {
        final String[] split = zzcz.split("\n");
        if (split.length == 0) {
            return "";
        }
        zzcz = (String)this.zzcz();
        final PriorityQueue<zzbp$zza> priorityQueue = new PriorityQueue<zzbp$zza>(this.zzsq, new zzbm$2(this));
        for (int i = 0; i < split.length; ++i) {
            final String[] zzD = zzbn.zzD(split[i]);
            if (zzD.length >= this.zzsp) {
                zzbp.zza(zzD, this.zzsq, this.zzsp, priorityQueue);
            }
        }
        for (final zzbp$zza zzbp$zza : priorityQueue) {
            try {
                ((zzbm$zza)zzcz).write(this.zzss.zzz(zzbp$zza.zzsx));
                continue;
            }
            catch (IOException ex) {
                zzb.zzb("Error while writing hash to byteStream", ex);
            }
            break;
        }
        return ((zzbm$zza)zzcz).toString();
    }
    
    public String zza(final ArrayList<String> list) {
        final StringBuffer sb = new StringBuffer();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toLowerCase(Locale.US));
            sb.append('\n');
        }
        switch (this.zzsr) {
            default: {
                return "";
            }
            case 0: {
                return this.zzB(sb.toString());
            }
            case 1: {
                return this.zzA(sb.toString());
            }
        }
    }
    
    zzbm$zza zzcz() {
        return new zzbm$zza();
    }
}
