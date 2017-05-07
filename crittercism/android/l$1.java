// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.util.Log;

public final class l$1 implements Runnable
{
    final /* synthetic */ l a;
    
    public l$1(final l a) {
        this.a = a;
    }
    
    @Override
    public final void run() {
        final l i = l.i();
        final Thread r = i.r;
        while (!i.q && r != null && r.isAlive()) {
            try {
                r.join();
                continue;
            }
            catch (InterruptedException ex2) {
                if (!i.q) {
                    continue;
                }
            }
            catch (Exception ex3) {
                Log.w("CrittercismInstance", "Exception in Thread in sendAppLoadData");
                continue;
            }
            break;
        }
        try {
            i.a(false);
            i.k();
        }
        catch (Exception ex) {
            new StringBuilder("Exception in AppLoadRunnable: ").append(ex.getClass().getName());
        }
    }
}
