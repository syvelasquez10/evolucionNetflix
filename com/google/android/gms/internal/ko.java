// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public final class ko
{
    private final int adT;
    private final byte[] buffer;
    private int position;
    
    private ko(final byte[] buffer, final int position, final int n) {
        this.buffer = buffer;
        this.position = position;
        this.adT = position + n;
    }
    
    public static int A(final long n) {
        return D(n);
    }
    
    public static int B(final long n) {
        return D(E(n));
    }
    
    public static int D(final long n) {
        if ((0xFFFFFFFFFFFFFF80L & n) == 0x0L) {
            return 1;
        }
        if ((0xFFFFFFFFFFFFC000L & n) == 0x0L) {
            return 2;
        }
        if ((0xFFFFFFFFFFE00000L & n) == 0x0L) {
            return 3;
        }
        if ((0xFFFFFFFFF0000000L & n) == 0x0L) {
            return 4;
        }
        if ((0xFFFFFFF800000000L & n) == 0x0L) {
            return 5;
        }
        if ((0xFFFFFC0000000000L & n) == 0x0L) {
            return 6;
        }
        if ((0xFFFE000000000000L & n) == 0x0L) {
            return 7;
        }
        if ((0xFF00000000000000L & n) == 0x0L) {
            return 8;
        }
        if ((Long.MIN_VALUE & n) == 0x0L) {
            return 9;
        }
        return 10;
    }
    
    public static int E(final boolean b) {
        return 1;
    }
    
    public static long E(final long n) {
        return n << 1 ^ n >> 63;
    }
    
    public static int b(final int n, final kt kt) {
        return cZ(n) + c(kt);
    }
    
    public static int b(final int n, final boolean b) {
        return cZ(n) + E(b);
    }
    
    public static ko b(final byte[] array, final int n, final int n2) {
        return new ko(array, n, n2);
    }
    
    public static int c(final int n, final float n2) {
        return cZ(n) + e(n2);
    }
    
    public static int c(final kt kt) {
        final int c = kt.c();
        return c + db(c);
    }
    
    public static int cX(final int n) {
        if (n >= 0) {
            return db(n);
        }
        return 10;
    }
    
    public static int cZ(final int n) {
        return db(kw.l(n, 0));
    }
    
    public static int cf(final String s) {
        try {
            final byte[] bytes = s.getBytes("UTF-8");
            return bytes.length + db(bytes.length);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }
    
    public static int d(final int n, final long n2) {
        return cZ(n) + A(n2);
    }
    
    public static int db(final int n) {
        if ((n & 0xFFFFFF80) == 0x0) {
            return 1;
        }
        if ((n & 0xFFFFC000) == 0x0) {
            return 2;
        }
        if ((0xFFE00000 & n) == 0x0) {
            return 3;
        }
        if ((0xF0000000 & n) == 0x0) {
            return 4;
        }
        return 5;
    }
    
    public static int e(final float n) {
        return 4;
    }
    
    public static int e(final int n, final long n2) {
        return cZ(n) + B(n2);
    }
    
    public static int g(final int n, final String s) {
        return cZ(n) + cf(s);
    }
    
    public static int j(final int n, final int n2) {
        return cZ(n) + cX(n2);
    }
    
    public static ko o(final byte[] array) {
        return b(array, 0, array.length);
    }
    
    public void C(long n) throws IOException {
        while ((0xFFFFFFFFFFFFFF80L & n) != 0x0L) {
            this.cY(((int)n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.cY((int)n);
    }
    
    public void D(final boolean b) throws IOException {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.cY(n);
    }
    
    public void a(final int n, final kt kt) throws IOException {
        this.k(n, 2);
        this.b(kt);
    }
    
    public void a(final int n, final boolean b) throws IOException {
        this.k(n, 0);
        this.D(b);
    }
    
    public void b(final byte b) throws IOException {
        if (this.position == this.adT) {
            throw new a(this.position, this.adT);
        }
        this.buffer[this.position++] = b;
    }
    
    public void b(final int n, final float n2) throws IOException {
        this.k(n, 5);
        this.d(n2);
    }
    
    public void b(final int n, final long n2) throws IOException {
        this.k(n, 0);
        this.y(n2);
    }
    
    public void b(final int n, final String s) throws IOException {
        this.k(n, 2);
        this.ce(s);
    }
    
    public void b(final kt kt) throws IOException {
        this.da(kt.mF());
        kt.a(this);
    }
    
    public void c(final int n, final long n2) throws IOException {
        this.k(n, 0);
        this.z(n2);
    }
    
    public void c(final byte[] array, final int n, final int n2) throws IOException {
        if (this.adT - this.position >= n2) {
            System.arraycopy(array, n, this.buffer, this.position, n2);
            this.position += n2;
            return;
        }
        throw new a(this.position, this.adT);
    }
    
    public void cW(final int n) throws IOException {
        if (n >= 0) {
            this.da(n);
            return;
        }
        this.C(n);
    }
    
    public void cY(final int n) throws IOException {
        this.b((byte)n);
    }
    
    public void ce(final String s) throws IOException {
        final byte[] bytes = s.getBytes("UTF-8");
        this.da(bytes.length);
        this.p(bytes);
    }
    
    public void d(final float n) throws IOException {
        this.dc(Float.floatToIntBits(n));
    }
    
    public void da(int n) throws IOException {
        while ((n & 0xFFFFFF80) != 0x0) {
            this.cY((n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.cY(n);
    }
    
    public void dc(final int n) throws IOException {
        this.cY(n & 0xFF);
        this.cY(n >> 8 & 0xFF);
        this.cY(n >> 16 & 0xFF);
        this.cY(n >> 24 & 0xFF);
    }
    
    public void i(final int n, final int n2) throws IOException {
        this.k(n, 0);
        this.cW(n2);
    }
    
    public void k(final int n, final int n2) throws IOException {
        this.da(kw.l(n, n2));
    }
    
    public int mv() {
        return this.adT - this.position;
    }
    
    public void mw() {
        if (this.mv() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
    
    public void p(final byte[] array) throws IOException {
        this.c(array, 0, array.length);
    }
    
    public void y(final long n) throws IOException {
        this.C(n);
    }
    
    public void z(final long n) throws IOException {
        this.C(E(n));
    }
    
    public static class a extends IOException
    {
        a(final int n, final int n2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + n + " limit " + n2 + ").");
        }
    }
}
