// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class aw extends bd.a
{
    private ay.a fP;
    private av fQ;
    private final Object fx;
    
    public aw() {
        this.fx = new Object();
    }
    
    public void a(final av fq) {
        synchronized (this.fx) {
            this.fQ = fq;
        }
    }
    
    public void a(final ay.a fp) {
        synchronized (this.fx) {
            this.fP = fp;
        }
    }
    
    public void onAdClosed() {
        synchronized (this.fx) {
            if (this.fQ != null) {
                this.fQ.D();
            }
        }
    }
    
    public void onAdFailedToLoad(int n) {
        while (true) {
            while (true) {
                Label_0044: {
                    synchronized (this.fx) {
                        if (this.fP != null) {
                            if (n != 3) {
                                break Label_0044;
                            }
                            n = 1;
                            this.fP.f(n);
                            this.fP = null;
                        }
                        return;
                    }
                }
                n = 2;
                continue;
            }
        }
    }
    
    public void onAdLeftApplication() {
        synchronized (this.fx) {
            if (this.fQ != null) {
                this.fQ.E();
            }
        }
    }
    
    public void onAdLoaded() {
        synchronized (this.fx) {
            if (this.fP != null) {
                this.fP.f(0);
                this.fP = null;
                return;
            }
            if (this.fQ != null) {
                this.fQ.G();
            }
        }
    }
    
    public void onAdOpened() {
        synchronized (this.fx) {
            if (this.fQ != null) {
                this.fQ.F();
            }
        }
    }
    
    public void w() {
        synchronized (this.fx) {
            if (this.fQ != null) {
                this.fQ.C();
            }
        }
    }
}
