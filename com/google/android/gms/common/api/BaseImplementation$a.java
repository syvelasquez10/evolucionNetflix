// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.DeadObjectException;
import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.internal.n;

public abstract class BaseImplementation$a<R extends Result, A extends Api$a> extends BaseImplementation$AbstractPendingResult<R> implements b$c<A>
{
    private final Api$c<A> Ik;
    private b$a Iu;
    
    protected BaseImplementation$a(final Api$c<A> api$c) {
        this.Ik = n.i(api$c);
    }
    
    private void a(final RemoteException ex) {
        this.m(new Status(8, ex.getLocalizedMessage(), null));
    }
    
    protected abstract void a(final A p0);
    
    @Override
    public void a(final b$a iu) {
        this.Iu = iu;
    }
    
    @Override
    public final void b(final A a) {
        if (this.mHandler == null) {
            this.a(new BaseImplementation$CallbackHandler<R>(a.getLooper()));
        }
        try {
            this.a(a);
        }
        catch (DeadObjectException ex) {
            this.a((RemoteException)ex);
            throw ex;
        }
        catch (RemoteException ex2) {
            this.a(ex2);
        }
    }
    
    @Override
    public final Api$c<A> gf() {
        return this.Ik;
    }
    
    @Override
    protected void gh() {
        super.gh();
        if (this.Iu != null) {
            this.Iu.b(this);
            this.Iu = null;
        }
    }
    
    @Override
    public int gk() {
        return 0;
    }
    
    @Override
    public final void m(final Status status) {
        n.b(!status.isSuccess(), "Failed result must not be success");
        this.b(this.c(status));
    }
}
