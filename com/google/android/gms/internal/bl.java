// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class bl extends bs.a
{
    private final Object li;
    private bn.a nl;
    private bk nm;
    
    public bl() {
        this.li = new Object();
    }
    
    public void P() {
        synchronized (this.li) {
            if (this.nm != null) {
                this.nm.X();
            }
        }
    }
    
    public void a(final bk nm) {
        synchronized (this.li) {
            this.nm = nm;
        }
    }
    
    public void a(final bn.a nl) {
        synchronized (this.li) {
            this.nl = nl;
        }
    }
    
    public void onAdClosed() {
        synchronized (this.li) {
            if (this.nm != null) {
                this.nm.Y();
            }
        }
    }
    
    public void onAdFailedToLoad(int n) {
        while (true) {
            while (true) {
                Label_0044: {
                    synchronized (this.li) {
                        if (this.nl != null) {
                            if (n != 3) {
                                break Label_0044;
                            }
                            n = 1;
                            this.nl.f(n);
                            this.nl = null;
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
        synchronized (this.li) {
            if (this.nm != null) {
                this.nm.Z();
            }
        }
    }
    
    public void onAdLoaded() {
        synchronized (this.li) {
            if (this.nl != null) {
                this.nl.f(0);
                this.nl = null;
                return;
            }
            if (this.nm != null) {
                this.nm.ab();
            }
        }
    }
    
    public void onAdOpened() {
        synchronized (this.li) {
            if (this.nm != null) {
                this.nm.aa();
            }
        }
    }
}
