// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.m;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.n;

public abstract class d
{
    protected final DataHolder IC;
    protected int JQ;
    private int JR;
    
    public d(final DataHolder dataHolder, final int n) {
        this.IC = n.i(dataHolder);
        this.ap(n);
    }
    
    protected void a(final String s, final CharArrayBuffer charArrayBuffer) {
        this.IC.a(s, this.JQ, this.JR, charArrayBuffer);
    }
    
    public boolean aQ(final String s) {
        return this.IC.aQ(s);
    }
    
    protected Uri aR(final String s) {
        return this.IC.g(s, this.JQ, this.JR);
    }
    
    protected boolean aS(final String s) {
        return this.IC.h(s, this.JQ, this.JR);
    }
    
    protected void ap(final int jq) {
        n.I(jq >= 0 && jq < this.IC.getCount());
        this.JQ = jq;
        this.JR = this.IC.ar(this.JQ);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof d) {
            final d d = (d)o;
            b2 = b;
            if (m.equal(d.JQ, this.JQ)) {
                b2 = b;
                if (m.equal(d.JR, this.JR)) {
                    b2 = b;
                    if (d.IC == this.IC) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    protected int gA() {
        return this.JQ;
    }
    
    protected boolean getBoolean(final String s) {
        return this.IC.d(s, this.JQ, this.JR);
    }
    
    protected byte[] getByteArray(final String s) {
        return this.IC.f(s, this.JQ, this.JR);
    }
    
    protected float getFloat(final String s) {
        return this.IC.e(s, this.JQ, this.JR);
    }
    
    protected int getInteger(final String s) {
        return this.IC.b(s, this.JQ, this.JR);
    }
    
    protected long getLong(final String s) {
        return this.IC.a(s, this.JQ, this.JR);
    }
    
    protected String getString(final String s) {
        return this.IC.c(s, this.JQ, this.JR);
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.JQ, this.JR, this.IC);
    }
    
    public boolean isDataValid() {
        return !this.IC.isClosed();
    }
}
