// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import com.google.android.gms.internal.fo;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.internal.fq;

public abstract class b
{
    protected final DataHolder BB;
    protected final int BD;
    private final int BE;
    
    public b(final DataHolder dataHolder, final int bd) {
        this.BB = fq.f(dataHolder);
        fq.x(bd >= 0 && bd < dataHolder.getCount());
        this.BD = bd;
        this.BE = dataHolder.G(this.BD);
    }
    
    protected void a(final String s, final CharArrayBuffer charArrayBuffer) {
        this.BB.copyToBuffer(s, this.BD, this.BE, charArrayBuffer);
    }
    
    protected Uri ah(final String s) {
        return this.BB.parseUri(s, this.BD, this.BE);
    }
    
    protected boolean ai(final String s) {
        return this.BB.hasNull(s, this.BD, this.BE);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof b) {
            final b b3 = (b)o;
            b2 = b;
            if (fo.equal(b3.BD, this.BD)) {
                b2 = b;
                if (fo.equal(b3.BE, this.BE)) {
                    b2 = b;
                    if (b3.BB == this.BB) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    protected boolean getBoolean(final String s) {
        return this.BB.getBoolean(s, this.BD, this.BE);
    }
    
    protected byte[] getByteArray(final String s) {
        return this.BB.getByteArray(s, this.BD, this.BE);
    }
    
    protected int getInteger(final String s) {
        return this.BB.getInteger(s, this.BD, this.BE);
    }
    
    protected long getLong(final String s) {
        return this.BB.getLong(s, this.BD, this.BE);
    }
    
    protected String getString(final String s) {
        return this.BB.getString(s, this.BD, this.BE);
    }
    
    public boolean hasColumn(final String s) {
        return this.BB.hasColumn(s);
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.BD, this.BE, this.BB);
    }
    
    public boolean isDataValid() {
        return !this.BB.isClosed();
    }
}
