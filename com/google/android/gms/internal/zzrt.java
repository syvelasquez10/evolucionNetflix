// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

class zzrt implements Cloneable
{
    private static final zzru zzbcd;
    private int mSize;
    private boolean zzbce;
    private int[] zzbcf;
    private zzru[] zzbcg;
    
    static {
        zzbcd = new zzru();
    }
    
    public zzrt() {
        this(10);
    }
    
    public zzrt(int idealIntArraySize) {
        this.zzbce = false;
        idealIntArraySize = this.idealIntArraySize(idealIntArraySize);
        this.zzbcf = new int[idealIntArraySize];
        this.zzbcg = new zzru[idealIntArraySize];
        this.mSize = 0;
    }
    
    private void gc() {
        final int mSize = this.mSize;
        final int[] zzbcf = this.zzbcf;
        final zzru[] zzbcg = this.zzbcg;
        int i = 0;
        int mSize2 = 0;
        while (i < mSize) {
            final zzru zzru = zzbcg[i];
            int n = mSize2;
            if (zzru != zzrt.zzbcd) {
                if (i != mSize2) {
                    zzbcf[mSize2] = zzbcf[i];
                    zzbcg[mSize2] = zzru;
                    zzbcg[i] = null;
                }
                n = mSize2 + 1;
            }
            ++i;
            mSize2 = n;
        }
        this.zzbce = false;
        this.mSize = mSize2;
    }
    
    private int idealByteArraySize(final int n) {
        int n2 = 4;
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= 32) {
                break;
            }
            if (n <= (1 << n2) - 12) {
                n3 = (1 << n2) - 12;
                break;
            }
            ++n2;
        }
        return n3;
    }
    
    private int idealIntArraySize(final int n) {
        return this.idealByteArraySize(n * 4) / 4;
    }
    
    private boolean zza(final int[] array, final int[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean zza(final zzru[] array, final zzru[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            if (!array[i].equals(array2[i])) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzrt)) {
                return false;
            }
            final zzrt zzrt = (zzrt)o;
            if (this.size() != zzrt.size()) {
                return false;
            }
            if (!this.zza(this.zzbcf, zzrt.zzbcf, this.mSize) || !this.zza(this.zzbcg, zzrt.zzbcg, this.mSize)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        if (this.zzbce) {
            this.gc();
        }
        int n = 17;
        for (int i = 0; i < this.mSize; ++i) {
            n = (n * 31 + this.zzbcf[i]) * 31 + this.zzbcg[i].hashCode();
        }
        return n;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public int size() {
        if (this.zzbce) {
            this.gc();
        }
        return this.mSize;
    }
    
    public final zzrt zzDn() {
        int i = 0;
        final int size = this.size();
        final zzrt zzrt = new zzrt(size);
        System.arraycopy(this.zzbcf, 0, zzrt.zzbcf, 0, size);
        while (i < size) {
            if (this.zzbcg[i] != null) {
                zzrt.zzbcg[i] = this.zzbcg[i].zzDo();
            }
            ++i;
        }
        zzrt.mSize = size;
        return zzrt;
    }
    
    public zzru zzlB(final int n) {
        if (this.zzbce) {
            this.gc();
        }
        return this.zzbcg[n];
    }
}
