// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import com.google.android.gms.internal.ee;
import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.internal.eg;

public abstract class b
{
    protected final DataHolder nE;
    protected final int nG;
    private final int nH;
    
    public b(final DataHolder dataHolder, final int ng) {
        this.nE = eg.f(dataHolder);
        eg.p(ng >= 0 && ng < dataHolder.getCount());
        this.nG = ng;
        this.nH = dataHolder.C(this.nG);
    }
    
    protected Uri L(final String s) {
        return this.nE.parseUri(s, this.nG, this.nH);
    }
    
    protected boolean M(final String s) {
        return this.nE.hasNull(s, this.nG, this.nH);
    }
    
    protected void a(final String s, final CharArrayBuffer charArrayBuffer) {
        this.nE.copyToBuffer(s, this.nG, this.nH, charArrayBuffer);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof b) {
            final b b3 = (b)o;
            b2 = b;
            if (ee.equal(b3.nG, this.nG)) {
                b2 = b;
                if (ee.equal(b3.nH, this.nH)) {
                    b2 = b;
                    if (b3.nE == this.nE) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    protected boolean getBoolean(final String s) {
        return this.nE.getBoolean(s, this.nG, this.nH);
    }
    
    protected byte[] getByteArray(final String s) {
        return this.nE.getByteArray(s, this.nG, this.nH);
    }
    
    protected int getInteger(final String s) {
        return this.nE.getInteger(s, this.nG, this.nH);
    }
    
    protected long getLong(final String s) {
        return this.nE.getLong(s, this.nG, this.nH);
    }
    
    protected String getString(final String s) {
        return this.nE.getString(s, this.nG, this.nH);
    }
    
    public boolean hasColumn(final String s) {
        return this.nE.hasColumn(s);
    }
    
    @Override
    public int hashCode() {
        return ee.hashCode(this.nG, this.nH, this.nE);
    }
    
    public boolean isDataValid() {
        return !this.nE.isClosed();
    }
}
