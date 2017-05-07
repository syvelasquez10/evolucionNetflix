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

public abstract class e implements d
{
    protected MotionEvent du;
    protected DisplayMetrics dv;
    protected j dw;
    private k dx;
    
    protected e(final Context context, final j dw, final k dx) {
        this.dw = dw;
        this.dx = dx;
        try {
            this.dv = context.getResources().getDisplayMetrics();
        }
        catch (UnsupportedOperationException ex) {
            this.dv = new DisplayMetrics();
            this.dv.density = 1.0f;
        }
    }
    
    private String a(final Context context, final String s, final boolean b) {
        try {
            synchronized (this) {
                this.b();
                if (b) {
                    this.c(context);
                }
                else {
                    this.b(context);
                }
                final byte[] c = this.c();
                // monitorexit(this)
                if (c.length == 0) {
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
    
    private void b() {
        this.dx.reset();
    }
    
    private byte[] c() throws IOException {
        return this.dx.h();
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
        byte[] c = array;
        if (array.length > 239) {
            this.b();
            this.a(20, 1L);
            c = this.c();
        }
        if (c.length < 239) {
            array = new byte[239 - c.length];
            new SecureRandom().nextBytes(array);
            array = ByteBuffer.allocate(240).put((byte)c.length).put(c).put(array).array();
        }
        else {
            array = ByteBuffer.allocate(240).put((byte)c.length).put(c).array();
        }
        final MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(array);
        array = ByteBuffer.allocate(256).put(instance.digest()).put(array).array();
        final byte[] array2 = new byte[256];
        new b().a(array, array2);
        if (s != null && s.length() > 0) {
            this.a(s, array2);
        }
        return this.dw.a(array2, true);
    }
    
    @Override
    public void a(final int n, final int n2, final int n3) {
        if (this.du != null) {
            this.du.recycle();
        }
        this.du = MotionEvent.obtain(0L, (long)n3, 1, n * this.dv.density, n2 * this.dv.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
    }
    
    protected void a(final int n, final long n2) throws IOException {
        this.dx.b(n, n2);
    }
    
    protected void a(final int n, final String s) throws IOException {
        this.dx.b(n, s);
    }
    
    @Override
    public void a(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.du != null) {
                this.du.recycle();
            }
            this.du = MotionEvent.obtain(motionEvent);
        }
    }
    
    void a(final String s, final byte[] array) throws UnsupportedEncodingException {
        String substring = s;
        if (s.length() > 32) {
            substring = s.substring(0, 32);
        }
        new iv(substring.getBytes("UTF-8")).h(array);
    }
    
    protected abstract void b(final Context p0);
    
    protected abstract void c(final Context p0);
}
