// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;

public class zzu
{
    protected static final Comparator<byte[]> zzaw;
    private List<byte[]> zzas;
    private List<byte[]> zzat;
    private int zzau;
    private final int zzav;
    
    static {
        zzaw = new zzu$1();
    }
    
    public zzu(final int zzav) {
        this.zzas = new LinkedList<byte[]>();
        this.zzat = new ArrayList<byte[]>(64);
        this.zzau = 0;
        this.zzav = zzav;
    }
    
    private void zzy() {
        synchronized (this) {
            while (this.zzau > this.zzav) {
                final byte[] array = this.zzas.remove(0);
                this.zzat.remove(array);
                this.zzau -= array.length;
            }
        }
    }
    // monitorexit(this)
    
    public void zza(final byte[] array) {
        // monitorenter(this)
        if (array == null) {
            return;
        }
        try {
            if (array.length <= this.zzav) {
                this.zzas.add(array);
                final int binarySearch = Collections.binarySearch(this.zzat, array, zzu.zzaw);
                int n;
                if ((n = binarySearch) < 0) {
                    n = -binarySearch - 1;
                }
                this.zzat.add(n, array);
                this.zzau += array.length;
                this.zzy();
            }
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public byte[] zzb(final int n) {
        // monitorenter(this)
        int i = 0;
        try {
            while (i < this.zzat.size()) {
                final byte[] array = this.zzat.get(i);
                if (array.length >= n) {
                    this.zzau -= array.length;
                    this.zzat.remove(i);
                    this.zzas.remove(array);
                    return array;
                }
                ++i;
            }
            return new byte[n];
        }
        finally {
        }
        // monitorexit(this)
    }
}
