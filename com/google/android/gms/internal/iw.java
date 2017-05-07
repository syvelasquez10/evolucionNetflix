// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public final class iw
{
    private int Hp;
    private int Hq;
    private int Hr;
    private int Hs;
    private int Ht;
    private int Hu;
    private int Hv;
    private final byte[] buffer;
    
    private iw(final byte[] buffer, final int n, final int n2) {
        this.Ht = Integer.MAX_VALUE;
        this.Hu = 64;
        this.Hv = 67108864;
        this.buffer = buffer;
        this.Hp = n;
        this.Hq = n + n2;
        this.Hr = n;
    }
    
    public static iw a(final byte[] array, final int n, final int n2) {
        return new iw(array, n, n2);
    }
    
    public static long n(final long n) {
        return n >>> 1 ^ -(0x1L & n);
    }
    
    public void bI(final int n) throws iy {
        if (this.Hs != n) {
            throw iy.gk();
        }
    }
    
    public boolean bJ(final int n) throws IOException {
        switch (jb.bS(n)) {
            default: {
                throw iy.gl();
            }
            case 0: {
                this.fW();
                return true;
            }
            case 1: {
                this.gb();
                return true;
            }
            case 2: {
                this.bL(this.fY());
                return true;
            }
            case 3: {
                this.fV();
                this.bI(jb.g(jb.bT(n), 4));
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.ga();
                return true;
            }
        }
    }
    
    public byte[] bK(final int n) throws IOException {
        if (n < 0) {
            throw iy.gh();
        }
        if (this.Hr + n > this.Ht) {
            this.bL(this.Ht - this.Hr);
            throw iy.gg();
        }
        if (n <= this.Hq - this.Hr) {
            final byte[] array = new byte[n];
            System.arraycopy(this.buffer, this.Hr, array, 0, n);
            this.Hr += n;
            return array;
        }
        throw iy.gg();
    }
    
    public void bL(final int n) throws IOException {
        if (n < 0) {
            throw iy.gh();
        }
        if (this.Hr + n > this.Ht) {
            this.bL(this.Ht - this.Hr);
            throw iy.gg();
        }
        if (n <= this.Hq - this.Hr) {
            this.Hr += n;
            return;
        }
        throw iy.gg();
    }
    
    public int fU() throws IOException {
        if (this.gc()) {
            return this.Hs = 0;
        }
        this.Hs = this.fY();
        if (this.Hs == 0) {
            throw iy.gj();
        }
        return this.Hs;
    }
    
    public void fV() throws IOException {
        int fu;
        do {
            fu = this.fU();
        } while (fu != 0 && this.bJ(fu));
    }
    
    public int fW() throws IOException {
        return this.fY();
    }
    
    public long fX() throws IOException {
        return n(this.fZ());
    }
    
    public int fY() throws IOException {
        int gd = this.gd();
        if (gd < 0) {
            final int n = gd & 0x7F;
            final byte gd2 = this.gd();
            if (gd2 >= 0) {
                return n | gd2 << 7;
            }
            final int n2 = n | (gd2 & 0x7F) << 7;
            final byte gd3 = this.gd();
            if (gd3 >= 0) {
                return n2 | gd3 << 14;
            }
            final int n3 = n2 | (gd3 & 0x7F) << 14;
            final byte gd4 = this.gd();
            if (gd4 >= 0) {
                return n3 | gd4 << 21;
            }
            final byte gd5 = this.gd();
            final int n4 = gd = (n3 | (gd4 & 0x7F) << 21 | gd5 << 28);
            if (gd5 < 0) {
                for (int i = 0; i < 5; ++i) {
                    gd = n4;
                    if (this.gd() >= 0) {
                        return gd;
                    }
                }
                throw iy.gi();
            }
        }
        return gd;
    }
    
    public long fZ() throws IOException {
        int i = 0;
        long n = 0L;
        while (i < 64) {
            final byte gd = this.gd();
            n |= (gd & 0x7F) << i;
            if ((gd & 0x80) == 0x0) {
                return n;
            }
            i += 7;
        }
        throw iy.gi();
    }
    
    public int ga() throws IOException {
        return (this.gd() & 0xFF) | (this.gd() & 0xFF) << 8 | (this.gd() & 0xFF) << 16 | (this.gd() & 0xFF) << 24;
    }
    
    public long gb() throws IOException {
        return (this.gd() & 0xFFL) << 8 | (this.gd() & 0xFFL) | (this.gd() & 0xFFL) << 16 | (this.gd() & 0xFFL) << 24 | (this.gd() & 0xFFL) << 32 | (this.gd() & 0xFFL) << 40 | (this.gd() & 0xFFL) << 48 | (this.gd() & 0xFFL) << 56;
    }
    
    public boolean gc() {
        return this.Hr == this.Hq;
    }
    
    public byte gd() throws IOException {
        if (this.Hr == this.Hq) {
            throw iy.gg();
        }
        return this.buffer[this.Hr++];
    }
    
    public String readString() throws IOException {
        final int fy = this.fY();
        if (fy <= this.Hq - this.Hr && fy > 0) {
            final String s = new String(this.buffer, this.Hr, fy, "UTF-8");
            this.Hr += fy;
            return s;
        }
        return new String(this.bK(fy), "UTF-8");
    }
}
