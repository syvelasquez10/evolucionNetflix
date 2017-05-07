// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;

@ez
public abstract class fg extends gg
{
    private final fi pQ;
    private final ff$a tu;
    
    public fg(final fi pq, final ff$a tu) {
        this.pQ = pq;
        this.tu = tu;
    }
    
    private static fk a(final fm fm, final fi fi) {
        try {
            return fm.b(fi);
        }
        catch (RemoteException ex) {
            gs.d("Could not fetch ad response from ad request service.", (Throwable)ex);
            return null;
        }
        catch (NullPointerException ex2) {
            gs.d("Could not fetch ad response from ad request service due to an Exception.", ex2);
            return null;
        }
        catch (SecurityException ex3) {
            gs.d("Could not fetch ad response from ad request service due to an Exception.", ex3);
            return null;
        }
        catch (Throwable t) {
            gb.e(t);
            return null;
        }
    }
    
    public abstract void cD();
    
    public abstract fm cE();
    
    @Override
    public final void cp() {
        try {
            final fm ce = this.cE();
            fk a;
            if (ce == null) {
                a = new fk(0);
            }
            else if ((a = a(ce, this.pQ)) == null) {
                a = new fk(0);
            }
            this.cD();
            this.tu.a(a);
        }
        finally {
            this.cD();
        }
    }
    
    @Override
    public final void onStop() {
        this.cD();
    }
}
