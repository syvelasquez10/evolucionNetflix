// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public final class pe
{
    private int awo;
    private int awp;
    private int awq;
    private int awr;
    private int aws;
    private int awt;
    private int awu;
    private int awv;
    private int aww;
    private final byte[] buffer;
    
    private pe(final byte[] buffer, final int n, final int n2) {
        this.awt = Integer.MAX_VALUE;
        this.awv = 64;
        this.aww = 67108864;
        this.buffer = buffer;
        this.awo = n;
        this.awp = n + n2;
        this.awr = n;
    }
    
    public static long A(final long n) {
        return n >>> 1 ^ -(0x1L & n);
    }
    
    public static pe a(final byte[] array, final int n, final int n2) {
        return new pe(array, n, n2);
    }
    
    public static int gn(final int n) {
        return n >>> 1 ^ -(n & 0x1);
    }
    
    public static pe p(final byte[] array) {
        return a(array, 0, array.length);
    }
    
    private void qr() {
        this.awp += this.awq;
        final int awp = this.awp;
        if (awp > this.awt) {
            this.awq = awp - this.awt;
            this.awp -= this.awq;
            return;
        }
        this.awq = 0;
    }
    
    public void a(final pm pm) throws IOException {
        final int qn = this.qn();
        if (this.awu >= this.awv) {
            throw pl.qE();
        }
        final int go = this.go(qn);
        ++this.awu;
        pm.b(this);
        this.gl(0);
        --this.awu;
        this.gp(go);
    }
    
    public void a(final pm pm, final int n) throws IOException {
        if (this.awu >= this.awv) {
            throw pl.qE();
        }
        ++this.awu;
        pm.b(this);
        this.gl(pp.x(n, 4));
        --this.awu;
    }
    
    public int getPosition() {
        return this.awr - this.awo;
    }
    
    public void gl(final int n) throws pl {
        if (this.aws != n) {
            throw pl.qC();
        }
    }
    
    public boolean gm(final int n) throws IOException {
        switch (pp.gG(n)) {
            default: {
                throw pl.qD();
            }
            case 0: {
                this.qj();
                return true;
            }
            case 1: {
                this.qq();
                return true;
            }
            case 2: {
                this.gs(this.qn());
                return true;
            }
            case 3: {
                this.qh();
                this.gl(pp.x(pp.gH(n), 4));
                return true;
            }
            case 4: {
                return false;
            }
            case 5: {
                this.qp();
                return true;
            }
        }
    }
    
    public int go(int awt) throws pl {
        if (awt < 0) {
            throw pl.qz();
        }
        awt += this.awr;
        final int awt2 = this.awt;
        if (awt > awt2) {
            throw pl.qy();
        }
        this.awt = awt;
        this.qr();
        return awt2;
    }
    
    public void gp(final int awt) {
        this.awt = awt;
        this.qr();
    }
    
    public void gq(final int n) {
        if (n > this.awr - this.awo) {
            throw new IllegalArgumentException("Position " + n + " is beyond current " + (this.awr - this.awo));
        }
        if (n < 0) {
            throw new IllegalArgumentException("Bad position " + n);
        }
        this.awr = this.awo + n;
    }
    
    public byte[] gr(final int n) throws IOException {
        if (n < 0) {
            throw pl.qz();
        }
        if (this.awr + n > this.awt) {
            this.gs(this.awt - this.awr);
            throw pl.qy();
        }
        if (n <= this.awp - this.awr) {
            final byte[] array = new byte[n];
            System.arraycopy(this.buffer, this.awr, array, 0, n);
            this.awr += n;
            return array;
        }
        throw pl.qy();
    }
    
    public void gs(final int n) throws IOException {
        if (n < 0) {
            throw pl.qz();
        }
        if (this.awr + n > this.awt) {
            this.gs(this.awt - this.awr);
            throw pl.qy();
        }
        if (n <= this.awp - this.awr) {
            this.awr += n;
            return;
        }
        throw pl.qy();
    }
    
    public int qg() throws IOException {
        if (this.qt()) {
            return this.aws = 0;
        }
        this.aws = this.qn();
        if (this.aws == 0) {
            throw pl.qB();
        }
        return this.aws;
    }
    
    public void qh() throws IOException {
        int qg;
        do {
            qg = this.qg();
        } while (qg != 0 && this.gm(qg));
    }
    
    public long qi() throws IOException {
        return this.qo();
    }
    
    public int qj() throws IOException {
        return this.qn();
    }
    
    public boolean qk() throws IOException {
        return this.qn() != 0;
    }
    
    public int ql() throws IOException {
        return gn(this.qn());
    }
    
    public long qm() throws IOException {
        return A(this.qo());
    }
    
    public int qn() throws IOException {
        int qu = this.qu();
        if (qu < 0) {
            final int n = qu & 0x7F;
            final byte qu2 = this.qu();
            if (qu2 >= 0) {
                return n | qu2 << 7;
            }
            final int n2 = n | (qu2 & 0x7F) << 7;
            final byte qu3 = this.qu();
            if (qu3 >= 0) {
                return n2 | qu3 << 14;
            }
            final int n3 = n2 | (qu3 & 0x7F) << 14;
            final byte qu4 = this.qu();
            if (qu4 >= 0) {
                return n3 | qu4 << 21;
            }
            final byte qu5 = this.qu();
            final int n4 = qu = (n3 | (qu4 & 0x7F) << 21 | qu5 << 28);
            if (qu5 < 0) {
                for (int i = 0; i < 5; ++i) {
                    qu = n4;
                    if (this.qu() >= 0) {
                        return qu;
                    }
                }
                throw pl.qA();
            }
        }
        return qu;
    }
    
    public long qo() throws IOException {
        int i = 0;
        long n = 0L;
        while (i < 64) {
            final byte qu = this.qu();
            n |= (qu & 0x7F) << i;
            if ((qu & 0x80) == 0x0) {
                return n;
            }
            i += 7;
        }
        throw pl.qA();
    }
    
    public int qp() throws IOException {
        return (this.qu() & 0xFF) | (this.qu() & 0xFF) << 8 | (this.qu() & 0xFF) << 16 | (this.qu() & 0xFF) << 24;
    }
    
    public long qq() throws IOException {
        return (this.qu() & 0xFFL) << 8 | (this.qu() & 0xFFL) | (this.qu() & 0xFFL) << 16 | (this.qu() & 0xFFL) << 24 | (this.qu() & 0xFFL) << 32 | (this.qu() & 0xFFL) << 40 | (this.qu() & 0xFFL) << 48 | (this.qu() & 0xFFL) << 56;
    }
    
    public int qs() {
        if (this.awt == Integer.MAX_VALUE) {
            return -1;
        }
        return this.awt - this.awr;
    }
    
    public boolean qt() {
        return this.awr == this.awp;
    }
    
    public byte qu() throws IOException {
        if (this.awr == this.awp) {
            throw pl.qy();
        }
        return this.buffer[this.awr++];
    }
    
    public byte[] r(final int n, final int n2) {
        if (n2 == 0) {
            return pp.awS;
        }
        final byte[] array = new byte[n2];
        System.arraycopy(this.buffer, this.awo + n, array, 0, n2);
        return array;
    }
    
    public byte[] readBytes() throws IOException {
        final int qn = this.qn();
        if (qn <= this.awp - this.awr && qn > 0) {
            final byte[] array = new byte[qn];
            System.arraycopy(this.buffer, this.awr, array, 0, qn);
            this.awr += qn;
            return array;
        }
        return this.gr(qn);
    }
    
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(this.qq());
    }
    
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.qp());
    }
    
    public String readString() throws IOException {
        final int qn = this.qn();
        if (qn <= this.awp - this.awr && qn > 0) {
            final String s = new String(this.buffer, this.awr, qn, "UTF-8");
            this.awr += qn;
            return s;
        }
        return new String(this.gr(qn), "UTF-8");
    }
}
