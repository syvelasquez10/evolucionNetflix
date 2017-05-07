// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.security.MessageDigest;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

public abstract class h implements g
{
    protected MotionEvent kw;
    protected DisplayMetrics kx;
    protected m ky;
    private n kz;
    
    protected h(final Context context, final m ky, final n kz) {
        this.ky = ky;
        this.kz = kz;
        try {
            this.kx = context.getResources().getDisplayMetrics();
        }
        catch (UnsupportedOperationException ex) {
            this.kx = new DisplayMetrics();
            this.kx.density = 1.0f;
        }
    }
    
    private String a(final Context context, final String s, final boolean b) {
        try {
            synchronized (this) {
                this.t();
                if (b) {
                    this.c(context);
                }
                else {
                    this.b(context);
                }
                final byte[] u = this.u();
                // monitorexit(this)
                if (u.length == 0) {
                    return Integer.toString(5);
                }
                goto Label_0054;
            }
        }
        catch (NoSuchAlgorithmException ex) {
            return Integer.toString(7);
        }
        catch (UnsupportedEncodingException ex2) {
            return Integer.toString(7);
        }
        catch (IOException ex3) {
            return Integer.toString(3);
        }
    }
    
    private void t() {
        this.kz.reset();
    }
    
    private byte[] u() throws IOException {
        return this.kz.A();
    }
    
    @Override
    public String a(final Context context) {
        return this.a(context, null, false);
    }
    
    @Override
    public String a(final Context context, final String s) {
        return this.a(context, s, true);
    }
    
    String a(byte[] array, final String s) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
        byte[] u = array;
        if (array.length > 239) {
            this.t();
            this.a(20, 1L);
            u = this.u();
        }
        if (u.length < 239) {
            array = new byte[239 - u.length];
            new SecureRandom().nextBytes(array);
            array = ByteBuffer.allocate(240).put((byte)u.length).put(u).put(array).array();
        }
        else {
            array = ByteBuffer.allocate(240).put((byte)u.length).put(u).array();
        }
        final MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(array);
        array = ByteBuffer.allocate(256).put(instance.digest()).put(array).array();
        final byte[] array2 = new byte[256];
        new f().a(array, array2);
        if (s != null && s.length() > 0) {
            this.a(s, array2);
        }
        return this.ky.a(array2, true);
    }
    
    @Override
    public void a(final int n, final int n2, final int n3) {
        if (this.kw != null) {
            this.kw.recycle();
        }
        this.kw = MotionEvent.obtain(0L, (long)n3, 1, n * this.kx.density, n2 * this.kx.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
    }
    
    protected void a(final int n, final long n2) throws IOException {
        this.kz.b(n, n2);
    }
    
    protected void a(final int n, final String s) throws IOException {
        this.kz.b(n, s);
    }
    
    @Override
    public void a(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.kw != null) {
                this.kw.recycle();
            }
            this.kw = MotionEvent.obtain(motionEvent);
        }
    }
    
    void a(final String s, final byte[] array) throws UnsupportedEncodingException {
        String substring = s;
        if (s.length() > 32) {
            substring = s.substring(0, 32);
        }
        new pd(substring.getBytes("UTF-8")).o(array);
    }
    
    protected abstract void b(final Context p0);
    
    protected abstract void c(final Context p0);
}
