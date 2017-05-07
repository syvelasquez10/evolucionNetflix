// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.PriorityQueue;
import com.google.android.gms.ads.internal.util.client.zzb;

public class zzbp
{
    static long zza(final int n, final int n2, final long n3, final long n4, final long n5) {
        return ((n3 + 1073807359L - (n + 2147483647L) % 1073807359L * n4 % 1073807359L) % 1073807359L * n5 % 1073807359L + (n2 + 2147483647L) % 1073807359L) % 1073807359L;
    }
    
    static long zza(final long n, final int n2) {
        long n3;
        if (n2 == 0) {
            n3 = 1L;
        }
        else {
            n3 = n;
            if (n2 != 1) {
                if (n2 % 2 == 0) {
                    return zza(n * n % 1073807359L, n2 / 2) % 1073807359L;
                }
                return zza(n * n % 1073807359L, n2 / 2) % 1073807359L * n % 1073807359L;
            }
        }
        return n3;
    }
    
    static String zza(final String[] array, final int n, final int n2) {
        if (array.length < n + n2) {
            zzb.e("Unable to construct shingle");
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = n; i < n + n2 - 1; ++i) {
            sb.append(array[i]);
            sb.append(' ');
        }
        sb.append(array[n + n2 - 1]);
        return sb.toString();
    }
    
    static void zza(final int n, final long n2, final String s, final PriorityQueue<zzbp$zza> priorityQueue) {
        final zzbp$zza zzbp$zza = new zzbp$zza(n2, s);
        if ((priorityQueue.size() != n || priorityQueue.peek().value <= zzbp$zza.value) && !priorityQueue.contains(zzbp$zza)) {
            priorityQueue.add(zzbp$zza);
            if (priorityQueue.size() > n) {
                priorityQueue.poll();
            }
        }
    }
    
    public static void zza(final String[] array, final int n, final int n2, final PriorityQueue<zzbp$zza> priorityQueue) {
        long n3 = zzb(array, 0, n2);
        zza(n, n3, zza(array, 0, n2), priorityQueue);
        final long zza = zza(16785407L, n2 - 1);
        for (int i = 1; i < array.length - n2 + 1; ++i) {
            n3 = zza(zzbn.zzC(array[i - 1]), zzbn.zzC(array[i + n2 - 1]), n3, zza, 16785407L);
            zza(n, n3, zza(array, i, n2), priorityQueue);
        }
    }
    
    private static long zzb(final String[] array, final int n, final int n2) {
        long n3 = (zzbn.zzC(array[n]) + 2147483647L) % 1073807359L;
        for (int i = n + 1; i < n + n2; ++i) {
            n3 = (n3 * 16785407L % 1073807359L + (zzbn.zzC(array[i]) + 2147483647L) % 1073807359L) % 1073807359L;
        }
        return n3;
    }
}
