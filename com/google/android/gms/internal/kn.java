// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public final class kn
{
    private int adK;
    private int adL;
    private int adM;
    private int adN;
    private int adO;
    private int adP;
    private int adQ;
    private int adR;
    private int adS;
    private final byte[] buffer;
    
    private kn(final byte[] buffer, final int n, final int n2) {
        this.adP = Integer.MAX_VALUE;
        this.adR = 64;
        this.adS = 67108864;
        this.buffer = buffer;
        this.adK = n;
        this.adL = n + n2;
        this.adN = n;
    }
    
    public static kn a(final byte[] array, final int n, final int n2) {
        return new kn(array, n, n2);
    }
    
    private void mr() {
        this.adL += this.adM;
        final int adL = this.adL;
        if (adL > this.adP) {
            this.adM = adL - this.adP;
            this.adL -= this.adM;
            return;
        }
        this.adM = 0;
    }
    
    public static kn n(final byte[] array) {
        return a(array, 0, array.length);
    }
    
    public static long x(final long n) {
        return n >>> 1 ^ -(0x1L & n);
    }
    
    public void a(final kt kt) throws IOException {
        final int mn = this.mn();
        if (this.adQ >= this.adR) {
            throw ks.mE();
        }
        final int cr = this.cR(mn);
        ++this.adQ;
        kt.b(this);
        this.cP(0);
        --this.adQ;
        this.cS(cr);
    }
    
    public void a(final kt kt, final int n) throws IOException {
        if (this.adQ >= this.adR) {
            throw ks.mE();
        }
        ++this.adQ;
        kt.b(this);
        this.cP(kw.l(n, 4));
        --this.adQ;
    }
    
    public void cP(final int n) throws ks {
        if (this.adO != n) {
            throw ks.mC();
        }
    }
    
    public boolean cQ(final int n) throws IOException {
        switch (kw.de(n)) {
            default: {
                throw ks.mD();
            }
            case 0: {
                this.mk();
                return true;
            }
            case 1: {
                this.mq();
                return true;
            }
            case 2: {
                this.cV(this.mn());
                return true;
            }
            case 3: {
                this.mi();
                this.cP(kw.l(kw.df(n), 4));
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.mp();
                return true;
            }
        }
    }
    
    public int cR(int adP) throws ks {
        if (adP < 0) {
            throw ks.mz();
        }
        adP += this.adN;
        final int adP2 = this.adP;
        if (adP > adP2) {
            throw ks.my();
        }
        this.adP = adP;
        this.mr();
        return adP2;
    }
    
    public void cS(final int adP) {
        this.adP = adP;
        this.mr();
    }
    
    public void cT(final int n) {
        if (n > this.adN - this.adK) {
            throw new IllegalArgumentException("Position " + n + " is beyond current " + (this.adN - this.adK));
        }
        if (n < 0) {
            throw new IllegalArgumentException("Bad position " + n);
        }
        this.adN = this.adK + n;
    }
    
    public byte[] cU(final int n) throws IOException {
        if (n < 0) {
            throw ks.mz();
        }
        if (this.adN + n > this.adP) {
            this.cV(this.adP - this.adN);
            throw ks.my();
        }
        if (n <= this.adL - this.adN) {
            final byte[] array = new byte[n];
            System.arraycopy(this.buffer, this.adN, array, 0, n);
            this.adN += n;
            return array;
        }
        throw ks.my();
    }
    
    public void cV(final int n) throws IOException {
        if (n < 0) {
            throw ks.mz();
        }
        if (this.adN + n > this.adP) {
            this.cV(this.adP - this.adN);
            throw ks.my();
        }
        if (n <= this.adL - this.adN) {
            this.adN += n;
            return;
        }
        throw ks.my();
    }
    
    public int getPosition() {
        return this.adN - this.adK;
    }
    
    public byte[] h(final int n, final int n2) {
        if (n2 == 0) {
            return kw.aeh;
        }
        final byte[] array = new byte[n2];
        System.arraycopy(this.buffer, this.adK + n, array, 0, n2);
        return array;
    }
    
    public int mh() throws IOException {
        if (this.mt()) {
            return this.adO = 0;
        }
        this.adO = this.mn();
        if (this.adO == 0) {
            throw ks.mB();
        }
        return this.adO;
    }
    
    public void mi() throws IOException {
        int mh;
        do {
            mh = this.mh();
        } while (mh != 0 && this.cQ(mh));
    }
    
    public long mj() throws IOException {
        return this.mo();
    }
    
    public int mk() throws IOException {
        return this.mn();
    }
    
    public boolean ml() throws IOException {
        return this.mn() != 0;
    }
    
    public long mm() throws IOException {
        return x(this.mo());
    }
    
    public int mn() throws IOException {
        int mu = this.mu();
        if (mu < 0) {
            final int n = mu & 0x7F;
            final byte mu2 = this.mu();
            if (mu2 >= 0) {
                return n | mu2 << 7;
            }
            final int n2 = n | (mu2 & 0x7F) << 7;
            final byte mu3 = this.mu();
            if (mu3 >= 0) {
                return n2 | mu3 << 14;
            }
            final int n3 = n2 | (mu3 & 0x7F) << 14;
            final byte mu4 = this.mu();
            if (mu4 >= 0) {
                return n3 | mu4 << 21;
            }
            final byte mu5 = this.mu();
            final int n4 = mu = (n3 | (mu4 & 0x7F) << 21 | mu5 << 28);
            if (mu5 < 0) {
                for (int i = 0; i < 5; ++i) {
                    mu = n4;
                    if (this.mu() >= 0) {
                        return mu;
                    }
                }
                throw ks.mA();
            }
        }
        return mu;
    }
    
    public long mo() throws IOException {
        int i = 0;
        long n = 0L;
        while (i < 64) {
            final byte mu = this.mu();
            n |= (mu & 0x7F) << i;
            if ((mu & 0x80) == 0x0) {
                return n;
            }
            i += 7;
        }
        throw ks.mA();
    }
    
    public int mp() throws IOException {
        return (this.mu() & 0xFF) | (this.mu() & 0xFF) << 8 | (this.mu() & 0xFF) << 16 | (this.mu() & 0xFF) << 24;
    }
    
    public long mq() throws IOException {
        return (this.mu() & 0xFFL) << 8 | (this.mu() & 0xFFL) | (this.mu() & 0xFFL) << 16 | (this.mu() & 0xFFL) << 24 | (this.mu() & 0xFFL) << 32 | (this.mu() & 0xFFL) << 40 | (this.mu() & 0xFFL) << 48 | (this.mu() & 0xFFL) << 56;
    }
    
    public int ms() {
        if (this.adP == Integer.MAX_VALUE) {
            return -1;
        }
        return this.adP - this.adN;
    }
    
    public boolean mt() {
        return this.adN == this.adL;
    }
    
    public byte mu() throws IOException {
        if (this.adN == this.adL) {
            throw ks.my();
        }
        return this.buffer[this.adN++];
    }
    
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.mp());
    }
    
    public String readString() throws IOException {
        final int mn = this.mn();
        if (mn <= this.adL - this.adN && mn > 0) {
            final String s = new String(this.buffer, this.adN, mn, "UTF-8");
            this.adN += mn;
            return s;
        }
        return new String(this.cU(mn), "UTF-8");
    }
}
