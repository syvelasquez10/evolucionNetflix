// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public final class ix
{
    private final int Hw;
    private final byte[] buffer;
    private int position;
    
    private ix(final byte[] buffer, final int position, final int n) {
        this.buffer = buffer;
        this.position = position;
        this.Hw = position + n;
    }
    
    public static int aD(final String s) {
        try {
            final byte[] bytes = s.getBytes("UTF-8");
            return bytes.length + bR(bytes.length);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }
    
    public static ix b(final byte[] array, final int n, final int n2) {
        return new ix(array, n, n2);
    }
    
    public static int bN(final int n) {
        if (n >= 0) {
            return bR(n);
        }
        return 10;
    }
    
    public static int bP(final int n) {
        return bR(jb.g(n, 0));
    }
    
    public static int bR(final int n) {
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
    
    public static int d(final int n, final long n2) {
        return bP(n) + q(n2);
    }
    
    public static int e(final int n, final int n2) {
        return bP(n) + bN(n2);
    }
    
    public static int e(final int n, final String s) {
        return bP(n) + aD(s);
    }
    
    public static ix i(final byte[] array) {
        return b(array, 0, array.length);
    }
    
    public static int q(final long n) {
        return s(t(n));
    }
    
    public static int s(final long n) {
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
    
    public static long t(final long n) {
        return n << 1 ^ n >> 63;
    }
    
    public void aC(final String s) throws IOException {
        final byte[] bytes = s.getBytes("UTF-8");
        this.bQ(bytes.length);
        this.j(bytes);
    }
    
    public void b(final byte b) throws IOException {
        if (this.position == this.Hw) {
            throw new a(this.position, this.Hw);
        }
        this.buffer[this.position++] = b;
    }
    
    public void b(final int n, final long n2) throws IOException {
        this.f(n, 0);
        this.o(n2);
    }
    
    public void b(final int n, final String s) throws IOException {
        this.f(n, 2);
        this.aC(s);
    }
    
    public void bM(final int n) throws IOException {
        if (n >= 0) {
            this.bQ(n);
            return;
        }
        this.r(n);
    }
    
    public void bO(final int n) throws IOException {
        this.b((byte)n);
    }
    
    public void bQ(int n) throws IOException {
        while ((n & 0xFFFFFF80) != 0x0) {
            this.bO((n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.bO(n);
    }
    
    public void c(final int n, final long n2) throws IOException {
        this.f(n, 0);
        this.p(n2);
    }
    
    public void c(final byte[] array, final int n, final int n2) throws IOException {
        if (this.Hw - this.position >= n2) {
            System.arraycopy(array, n, this.buffer, this.position, n2);
            this.position += n2;
            return;
        }
        throw new a(this.position, this.Hw);
    }
    
    public void d(final int n, final int n2) throws IOException {
        this.f(n, 0);
        this.bM(n2);
    }
    
    public void f(final int n, final int n2) throws IOException {
        this.bQ(jb.g(n, n2));
    }
    
    public int ge() {
        return this.Hw - this.position;
    }
    
    public void gf() {
        if (this.ge() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
    
    public void j(final byte[] array) throws IOException {
        this.c(array, 0, array.length);
    }
    
    public void o(final long n) throws IOException {
        this.r(n);
    }
    
    public void p(final long n) throws IOException {
        this.r(t(n));
    }
    
    public void r(long n) throws IOException {
        while ((0xFFFFFFFFFFFFFF80L & n) != 0x0L) {
            this.bO(((int)n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.bO((int)n);
    }
    
    public static class a extends IOException
    {
        a(final int n, final int n2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + n + " limit " + n2 + ").");
        }
    }
}
