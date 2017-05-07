// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public final class pf
{
    private final int awx;
    private final byte[] buffer;
    private int position;
    
    private pf(final byte[] buffer, final int position, final int n) {
        this.buffer = buffer;
        this.position = position;
        this.awx = position + n;
    }
    
    public static int D(final long n) {
        return G(n);
    }
    
    public static int E(final long n) {
        return G(I(n));
    }
    
    public static int G(final long n) {
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
    
    public static long I(final long n) {
        return n << 1 ^ n >> 63;
    }
    
    public static int V(final boolean b) {
        return 1;
    }
    
    public static int b(final int n, final double n2) {
        return gy(n) + f(n2);
    }
    
    public static int b(final int n, final pm pm) {
        return gy(n) * 2 + d(pm);
    }
    
    public static int b(final int n, final byte[] array) {
        return gy(n) + s(array);
    }
    
    public static pf b(final byte[] array, final int n, final int n2) {
        return new pf(array, n, n2);
    }
    
    public static int c(final int n, final float n2) {
        return gy(n) + e(n2);
    }
    
    public static int c(final int n, final pm pm) {
        return gy(n) + e(pm);
    }
    
    public static int c(final int n, final boolean b) {
        return gy(n) + V(b);
    }
    
    public static int d(final int n, final long n2) {
        return gy(n) + D(n2);
    }
    
    public static int d(final pm pm) {
        return pm.qG();
    }
    
    public static int df(final String s) {
        try {
            final byte[] bytes = s.getBytes("UTF-8");
            return bytes.length + gA(bytes.length);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }
    
    public static int e(final float n) {
        return 4;
    }
    
    public static int e(final int n, final long n2) {
        return gy(n) + E(n2);
    }
    
    public static int e(final pm pm) {
        final int qg = pm.qG();
        return qg + gA(qg);
    }
    
    public static int f(final double n) {
        return 8;
    }
    
    public static int gA(final int n) {
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
    
    public static int gC(final int n) {
        return n << 1 ^ n >> 31;
    }
    
    public static int gv(final int n) {
        if (n >= 0) {
            return gA(n);
        }
        return 10;
    }
    
    public static int gw(final int n) {
        return gA(gC(n));
    }
    
    public static int gy(final int n) {
        return gA(pp.x(n, 0));
    }
    
    public static int j(final int n, final String s) {
        return gy(n) + df(s);
    }
    
    public static pf q(final byte[] array) {
        return b(array, 0, array.length);
    }
    
    public static int s(final byte[] array) {
        return gA(array.length) + array.length;
    }
    
    public static int u(final int n, final int n2) {
        return gy(n) + gv(n2);
    }
    
    public static int v(final int n, final int n2) {
        return gy(n) + gw(n2);
    }
    
    public void B(final long n) throws IOException {
        this.F(n);
    }
    
    public void C(final long n) throws IOException {
        this.F(I(n));
    }
    
    public void F(long n) throws IOException {
        while ((0xFFFFFFFFFFFFFF80L & n) != 0x0L) {
            this.gx(((int)n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.gx((int)n);
    }
    
    public void H(final long n) throws IOException {
        this.gx((int)n & 0xFF);
        this.gx((int)(n >> 8) & 0xFF);
        this.gx((int)(n >> 16) & 0xFF);
        this.gx((int)(n >> 24) & 0xFF);
        this.gx((int)(n >> 32) & 0xFF);
        this.gx((int)(n >> 40) & 0xFF);
        this.gx((int)(n >> 48) & 0xFF);
        this.gx((int)(n >> 56) & 0xFF);
    }
    
    public void U(final boolean b) throws IOException {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.gx(n);
    }
    
    public void a(final int n, final double n2) throws IOException {
        this.w(n, 1);
        this.e(n2);
    }
    
    public void a(final int n, final pm pm) throws IOException {
        this.w(n, 2);
        this.c(pm);
    }
    
    public void a(final int n, final byte[] array) throws IOException {
        this.w(n, 2);
        this.r(array);
    }
    
    public void b(final byte b) throws IOException {
        if (this.position == this.awx) {
            throw new a(this.position, this.awx);
        }
        this.buffer[this.position++] = b;
    }
    
    public void b(final int n, final float n2) throws IOException {
        this.w(n, 5);
        this.d(n2);
    }
    
    public void b(final int n, final long n2) throws IOException {
        this.w(n, 0);
        this.B(n2);
    }
    
    public void b(final int n, final String s) throws IOException {
        this.w(n, 2);
        this.de(s);
    }
    
    public void b(final int n, final boolean b) throws IOException {
        this.w(n, 0);
        this.U(b);
    }
    
    public void b(final pm pm) throws IOException {
        pm.a(this);
    }
    
    public void c(final int n, final long n2) throws IOException {
        this.w(n, 0);
        this.C(n2);
    }
    
    public void c(final pm pm) throws IOException {
        this.gz(pm.qF());
        pm.a(this);
    }
    
    public void c(final byte[] array, final int n, final int n2) throws IOException {
        if (this.awx - this.position >= n2) {
            System.arraycopy(array, n, this.buffer, this.position, n2);
            this.position += n2;
            return;
        }
        throw new a(this.position, this.awx);
    }
    
    public void d(final float n) throws IOException {
        this.gB(Float.floatToIntBits(n));
    }
    
    public void de(final String s) throws IOException {
        final byte[] bytes = s.getBytes("UTF-8");
        this.gz(bytes.length);
        this.t(bytes);
    }
    
    public void e(final double n) throws IOException {
        this.H(Double.doubleToLongBits(n));
    }
    
    public void gB(final int n) throws IOException {
        this.gx(n & 0xFF);
        this.gx(n >> 8 & 0xFF);
        this.gx(n >> 16 & 0xFF);
        this.gx(n >> 24 & 0xFF);
    }
    
    public void gt(final int n) throws IOException {
        if (n >= 0) {
            this.gz(n);
            return;
        }
        this.F(n);
    }
    
    public void gu(final int n) throws IOException {
        this.gz(gC(n));
    }
    
    public void gx(final int n) throws IOException {
        this.b((byte)n);
    }
    
    public void gz(int n) throws IOException {
        while ((n & 0xFFFFFF80) != 0x0) {
            this.gx((n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.gx(n);
    }
    
    public int qv() {
        return this.awx - this.position;
    }
    
    public void qw() {
        if (this.qv() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
    
    public void r(final byte[] array) throws IOException {
        this.gz(array.length);
        this.t(array);
    }
    
    public void s(final int n, final int n2) throws IOException {
        this.w(n, 0);
        this.gt(n2);
    }
    
    public void t(final int n, final int n2) throws IOException {
        this.w(n, 0);
        this.gu(n2);
    }
    
    public void t(final byte[] array) throws IOException {
        this.c(array, 0, array.length);
    }
    
    public void w(final int n, final int n2) throws IOException {
        this.gz(pp.x(n, n2));
    }
    
    public static class a extends IOException
    {
        a(final int n, final int n2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + n + " limit " + n2 + ").");
        }
    }
}
