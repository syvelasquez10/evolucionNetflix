// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

@ez
public final class co extends cv.a
{
    private final Object mw;
    private cq.a qm;
    private cn qn;
    
    public co() {
        this.mw = new Object();
    }
    
    public void a(final cn qn) {
        synchronized (this.mw) {
            this.qn = qn;
        }
    }
    
    public void a(final cq.a qm) {
        synchronized (this.mw) {
            this.qm = qm;
        }
    }
    
    public void onAdClicked() {
        synchronized (this.mw) {
            if (this.qn != null) {
                this.qn.ae();
            }
        }
    }
    
    public void onAdClosed() {
        synchronized (this.mw) {
            if (this.qn != null) {
                this.qn.af();
            }
        }
    }
    
    public void onAdFailedToLoad(int n) {
        while (true) {
            while (true) {
                Label_0044: {
                    synchronized (this.mw) {
                        if (this.qm != null) {
                            if (n != 3) {
                                break Label_0044;
                            }
                            n = 1;
                            this.qm.j(n);
                            this.qm = null;
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
        synchronized (this.mw) {
            if (this.qn != null) {
                this.qn.ag();
            }
        }
    }
    
    public void onAdLoaded() {
        synchronized (this.mw) {
            if (this.qm != null) {
                this.qm.j(0);
                this.qm = null;
                return;
            }
            if (this.qn != null) {
                this.qn.ai();
            }
        }
    }
    
    public void onAdOpened() {
        synchronized (this.mw) {
            if (this.qn != null) {
                this.qn.ah();
            }
        }
    }
}
