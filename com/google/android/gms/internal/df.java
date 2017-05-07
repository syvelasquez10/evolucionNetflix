// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public abstract class df
{
    protected final dk lx;
    private final String ly;
    private dm lz;
    
    protected df(final String ly, final String s) {
        this.ly = ly;
        this.lx = new dk(s);
    }
    
    public void B(final String s) {
    }
    
    public void a(final long n, final int n2) {
    }
    
    public final void a(final dm lz) {
        this.lz = lz;
        if (this.lz == null) {
            this.aT();
        }
    }
    
    protected final void a(final String s, final long n, final String s2) throws IOException {
        this.lx.a("Sending text message: %s to: %s", s, s2);
        this.lz.a(this.ly, s, n, s2);
    }
    
    protected final long aS() {
        return this.lz.aR();
    }
    
    public void aT() {
    }
    
    public final String getNamespace() {
        return this.ly;
    }
}
