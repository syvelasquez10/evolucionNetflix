// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import android.text.TextUtils;

public abstract class em
{
    protected final er yY;
    private final String yZ;
    private et za;
    
    protected em(final String yz, final String s, final String s2) {
        eo.W(yz);
        this.yZ = yz;
        this.yY = new er(s);
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            this.yY.ab(s2);
        }
    }
    
    public void U(final String s) {
    }
    
    public void a(final long n, final int n2) {
    }
    
    public final void a(final et za) {
        this.za = za;
        if (this.za == null) {
            this.dF();
        }
    }
    
    protected final void a(final String s, final long n, final String s2) throws IOException {
        this.yY.a("Sending text message: %s to: %s", s, s2);
        this.za.a(this.yZ, s, n, s2);
    }
    
    protected final long dE() {
        return this.za.dD();
    }
    
    public void dF() {
    }
    
    public final String getNamespace() {
        return this.yZ;
    }
}
