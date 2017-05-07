// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Message;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.internal.n;
import android.os.Looper;

public final class c<L>
{
    private final a Jl;
    private volatile L mListener;
    
    c(final Looper looper, final L l) {
        this.Jl = new a(looper);
        this.mListener = n.b(l, "Listener must not be null");
    }
    
    public void a(final b<L> b) {
        n.b(b, "Notifier must not be null");
        this.Jl.sendMessage(this.Jl.obtainMessage(1, (Object)b));
    }
    
    void b(final b<L> b) {
        final L mListener = this.mListener;
        if (mListener == null) {
            b.gs();
            return;
        }
        try {
            b.d(mListener);
        }
        catch (Exception ex) {
            Log.w("ListenerHolder", "Notifying listener failed", (Throwable)ex);
            b.gs();
        }
    }
    
    public void clear() {
        this.mListener = null;
    }
    
    private final class a extends Handler
    {
        public a(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            boolean b = true;
            if (message.what != 1) {
                b = false;
            }
            n.K(b);
            c.this.b((b<L>)message.obj);
        }
    }
    
    public interface b<L>
    {
        void d(final L p0);
        
        void gs();
    }
}
