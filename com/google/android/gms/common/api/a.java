// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.util.Log;
import android.os.Message;
import android.util.Pair;
import android.os.Looper;
import android.os.Handler;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import com.google.android.gms.internal.eg;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class a
{
    public abstract static class a<R extends Result, A extends Api.a> implements GoogleApiClient.b<A>, PendingResult<R>, c<R>
    {
        private final Api.b<A> mU;
        private final Object mV;
        private final com.google.android.gms.common.api.a.b<R> mW;
        private final CountDownLatch mX;
        private final ArrayList<PendingResult.a> mY;
        private ResultCallback<R> mZ;
        private R na;
        private boolean nb;
        private GoogleApiClient.a nc;
        
        public a(final Api.b<A> mu) {
            this.mV = new Object();
            this.mX = new CountDownLatch(1);
            this.mY = new ArrayList<PendingResult.a>();
            this.mU = mu;
            this.mW = new com.google.android.gms.common.api.a.b<R>() {
                @Override
                protected void a(final ResultCallback<R> resultCallback, final R r) {
                    resultCallback.onResult(r);
                }
            };
        }
        
        private R bl() {
            while (true) {
                while (true) {
                    synchronized (this.mV) {
                        if (!this.nb) {
                            final boolean b = true;
                            eg.a(b, (Object)"Result has already been consumed.");
                            eg.a(this.isReady(), (Object)"Result is not ready.");
                            final Result na = this.na;
                            this.bm();
                            return (R)na;
                        }
                    }
                    final boolean b = false;
                    continue;
                }
            }
        }
        
        protected abstract void a(final A p0);
        
        @Override
        public void a(final GoogleApiClient.a nc) {
            this.nc = nc;
        }
        
        public final void a(final R na) {
            final boolean b = true;
            // monitorexit(o)
            while (true) {
                Label_0129: {
                    if (this.isReady()) {
                        break Label_0129;
                    }
                    final boolean b2 = true;
                    eg.a(b2, (Object)"Results have already been set");
                    eg.a(!this.nb && b, (Object)"Result has already been consumed");
                    Label_0139: {
                        synchronized (this.mV) {
                            this.na = na;
                            this.mX.countDown();
                            final Status status = this.na.getStatus();
                            if (this.mZ != null) {
                                this.mW.b(this.mZ, this.bl());
                            }
                            final Iterator<PendingResult.a> iterator = this.mY.iterator();
                            while (iterator.hasNext()) {
                                iterator.next().l(status);
                            }
                            break Label_0139;
                        }
                        break Label_0129;
                    }
                    this.mY.clear();
                    return;
                }
                final boolean b2 = false;
                continue;
            }
        }
        
        @Override
        public final R await() {
            Label_0036: {
                if (this.nb) {
                    break Label_0036;
                }
                boolean b = true;
                while (true) {
                    eg.a(b, (Object)"Results has already been consumed");
                    while (true) {
                        try {
                            this.mX.await();
                            eg.a(this.isReady(), (Object)"Result is not ready.");
                            return this.bl();
                            b = false;
                        }
                        catch (InterruptedException ex) {
                            this.a(this.e(Status.nB));
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        @Override
        public final R await(final long n, final TimeUnit timeUnit) {
            Label_0054: {
                if (this.nb) {
                    break Label_0054;
                }
                boolean b = true;
                while (true) {
                    eg.a(b, (Object)"Result has already been consumed.");
                    while (true) {
                        try {
                            if (!this.mX.await(n, timeUnit)) {
                                this.a(this.e(Status.nC));
                            }
                            eg.a(this.isReady(), (Object)"Result is not ready.");
                            return this.bl();
                            b = false;
                        }
                        catch (InterruptedException ex) {
                            this.a(this.e(Status.nB));
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        @Override
        public final void b(final A a) {
            this.a(a);
        }
        
        @Override
        public final Api.b<A> bj() {
            return this.mU;
        }
        
        void bm() {
            this.nb = true;
            this.na = null;
            if (this.nc != null) {
                this.nc.b(this);
            }
        }
        
        public final boolean isReady() {
            return this.mX.getCount() == 0L;
        }
        
        @Override
        public final void setResultCallback(final ResultCallback<R> mz) {
            while (true) {
                Label_0057: {
                    if (this.nb) {
                        break Label_0057;
                    }
                    final boolean b = true;
                    eg.a(b, (Object)"Result has already been consumed.");
                    synchronized (this.mV) {
                        if (this.isReady()) {
                            this.mW.b(mz, this.bl());
                        }
                        else {
                            this.mZ = mz;
                        }
                        return;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    abstract static class b<R extends Result> extends Handler
    {
        public b() {
            this(Looper.getMainLooper());
        }
        
        public b(final Looper looper) {
            super(looper);
        }
        
        protected abstract void a(final ResultCallback<R> p0, final R p1);
        
        public void b(final ResultCallback<R> resultCallback, final R r) {
            this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)resultCallback, (Object)r)));
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.wtf("GoogleApi", "Don't know how to handle this message.");
                }
                case 1: {
                    final Pair pair = (Pair)message.obj;
                    this.a((ResultCallback<Result>)pair.first, (Result)pair.second);
                }
            }
        }
    }
    
    public interface c<R>
    {
        void a(final R p0);
    }
}
