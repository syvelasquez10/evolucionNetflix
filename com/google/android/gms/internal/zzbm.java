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
    private final int zzse;
    private final int zzsf;
    private final int zzsg;
    private final zzbl zzsh;
    
    public zzbm(final int zzsf) {
        this.zzsh = new zzbo();
        this.zzsf = zzsf;
        this.zzse = 6;
        this.zzsg = 0;
    }
    
    private String zzA(String zzcz) {
        final String[] split = zzcz.split("\n");
        if (split.length == 0) {
            return "";
        }
        zzcz = (String)this.zzcz();
        Arrays.sort(split, new zzbm$1(this));
        for (int n = 0; n < split.length && n < this.zzsf; ++n) {
            if (split[n].trim().length() != 0) {
                try {
                    ((zzbm$zza)zzcz).write(this.zzsh.zzz(split[n]));
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
        final PriorityQueue<zzbp$zza> priorityQueue = new PriorityQueue<zzbp$zza>(this.zzsf, new zzbm$2(this));
        for (int i = 0; i < split.length; ++i) {
            final String[] zzD = zzbn.zzD(split[i]);
            if (zzD.length >= this.zzse) {
                zzbp.zza(zzD, this.zzsf, this.zzse, priorityQueue);
            }
        }
        for (final zzbp$zza zzbp$zza : priorityQueue) {
            try {
                ((zzbm$zza)zzcz).write(this.zzsh.zzz(zzbp$zza.zzsm));
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
        switch (this.zzsg) {
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
