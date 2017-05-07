// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

@ez
public class ab
{
    private final ab$a mj;
    private final Runnable mk;
    private av ml;
    private boolean mm;
    private boolean mn;
    private long mo;
    
    public ab(final u u) {
        this(u, new ab$a(gr.wC));
    }
    
    ab(final u u, final ab$a mj) {
        this.mm = false;
        this.mn = false;
        this.mo = 0L;
        this.mj = mj;
        this.mk = new ab$1(this, u);
    }
    
    public void a(final av ml, final long mo) {
        if (this.mm) {
            gs.W("An ad refresh is already scheduled.");
        }
        else {
            this.ml = ml;
            this.mm = true;
            this.mo = mo;
            if (!this.mn) {
                gs.U("Scheduling ad refresh " + mo + " milliseconds from now.");
                this.mj.postDelayed(this.mk, mo);
            }
        }
    }
    
    public boolean ay() {
        return this.mm;
    }
    
    public void c(final av av) {
        this.a(av, 60000L);
    }
    
    public void cancel() {
        this.mm = false;
        this.mj.removeCallbacks(this.mk);
    }
    
    public void pause() {
        this.mn = true;
        if (this.mm) {
            this.mj.removeCallbacks(this.mk);
        }
    }
    
    public void resume() {
        this.mn = false;
        if (this.mm) {
            this.mm = false;
            this.a(this.ml, this.mo);
        }
    }
}
