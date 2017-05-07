// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.util.Log;
import com.google.android.gms.common.internal.n;
import android.os.Looper;

public final class c<L>
{
    private final c$a Jl;
    private volatile L mListener;
    
    c(final Looper looper, final L l) {
        this.Jl = new c$a(this, looper);
        this.mListener = n.b(l, "Listener must not be null");
    }
    
    public void a(final c$b<L> c$b) {
        n.b(c$b, "Notifier must not be null");
        this.Jl.sendMessage(this.Jl.obtainMessage(1, (Object)c$b));
    }
    
    void b(final c$b<L> c$b) {
        final L mListener = this.mListener;
        if (mListener == null) {
            c$b.gs();
            return;
        }
        try {
            c$b.d(mListener);
        }
        catch (Exception ex) {
            Log.w("ListenerHolder", "Notifying listener failed", (Throwable)ex);
            c$b.gs();
        }
    }
    
    public void clear() {
        this.mListener = null;
    }
}
