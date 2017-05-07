// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

class pi
{
    private static final pj awB;
    private boolean awC;
    private int[] awD;
    private pj[] awE;
    private int mSize;
    
    static {
        awB = new pj();
    }
    
    public pi() {
        this(10);
    }
    
    public pi(int idealIntArraySize) {
        this.awC = false;
        idealIntArraySize = this.idealIntArraySize(idealIntArraySize);
        this.awD = new int[idealIntArraySize];
        this.awE = new pj[idealIntArraySize];
        this.mSize = 0;
    }
    
    private boolean a(final int[] array, final int[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean a(final pj[] array, final pj[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            if (!array[i].equals(array2[i])) {
                return false;
            }
        }
        return true;
    }
    
    private int gF(final int n) {
        int i = 0;
        int n2 = this.mSize - 1;
        while (i <= n2) {
            final int n3 = i + n2 >>> 1;
            final int n4 = this.awD[n3];
            if (n4 < n) {
                i = n3 + 1;
            }
            else {
                if (n4 <= n) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return ~i;
    }
    
    private void gc() {
        final int mSize = this.mSize;
        final int[] awD = this.awD;
        final pj[] awE = this.awE;
        int i = 0;
        int mSize2 = 0;
        while (i < mSize) {
            final pj pj = awE[i];
            int n = mSize2;
            if (pj != pi.awB) {
                if (i != mSize2) {
                    awD[mSize2] = awD[i];
                    awE[mSize2] = pj;
                    awE[i] = null;
                }
                n = mSize2 + 1;
            }
            ++i;
            mSize2 = n;
        }
        this.awC = false;
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
    
    public void a(final int n, final pj pj) {
        final int gf = this.gF(n);
        if (gf >= 0) {
            this.awE[gf] = pj;
            return;
        }
        final int n2 = ~gf;
        if (n2 < this.mSize && this.awE[n2] == pi.awB) {
            this.awD[n2] = n;
            this.awE[n2] = pj;
            return;
        }
        int n3 = n2;
        if (this.awC) {
            n3 = n2;
            if (this.mSize >= this.awD.length) {
                this.gc();
                n3 = ~this.gF(n);
            }
        }
        if (this.mSize >= this.awD.length) {
            final int idealIntArraySize = this.idealIntArraySize(this.mSize + 1);
            final int[] awD = new int[idealIntArraySize];
            final pj[] awE = new pj[idealIntArraySize];
            System.arraycopy(this.awD, 0, awD, 0, this.awD.length);
            System.arraycopy(this.awE, 0, awE, 0, this.awE.length);
            this.awD = awD;
            this.awE = awE;
        }
        if (this.mSize - n3 != 0) {
            System.arraycopy(this.awD, n3, this.awD, n3 + 1, this.mSize - n3);
            System.arraycopy(this.awE, n3, this.awE, n3 + 1, this.mSize - n3);
        }
        this.awD[n3] = n;
        this.awE[n3] = pj;
        ++this.mSize;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof pi)) {
                return false;
            }
            final pi pi = (pi)o;
            if (this.size() != pi.size()) {
                return false;
            }
            if (!this.a(this.awD, pi.awD, this.mSize) || !this.a(this.awE, pi.awE, this.mSize)) {
                return false;
            }
        }
        return true;
    }
    
    public pj gD(int gf) {
        gf = this.gF(gf);
        if (gf < 0 || this.awE[gf] == pi.awB) {
            return null;
        }
        return this.awE[gf];
    }
    
    public pj gE(final int n) {
        if (this.awC) {
            this.gc();
        }
        return this.awE[n];
    }
    
    @Override
    public int hashCode() {
        if (this.awC) {
            this.gc();
        }
        int n = 17;
        for (int i = 0; i < this.mSize; ++i) {
            n = (n * 31 + this.awD[i]) * 31 + this.awE[i].hashCode();
        }
        return n;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public int size() {
        if (this.awC) {
            this.gc();
        }
        return this.mSize;
    }
}
