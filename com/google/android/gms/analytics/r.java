// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.content.Intent;
import java.util.TimerTask;
import com.google.android.gms.internal.hb;
import java.util.Collection;
import com.google.android.gms.internal.jw;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Timer;
import java.util.Queue;
import com.google.android.gms.internal.ju;
import android.content.Context;

class r implements af, com.google.android.gms.analytics.c.b, com.google.android.gms.analytics.c.c
{
    private final Context mContext;
    private boolean yA;
    private boolean yB;
    private boolean yC;
    private ju yD;
    private long yE;
    private com.google.android.gms.analytics.d yd;
    private final f ye;
    private boolean yg;
    private volatile long yq;
    private volatile a yr;
    private volatile com.google.android.gms.analytics.b ys;
    private com.google.android.gms.analytics.d yt;
    private final GoogleAnalytics yu;
    private final Queue<d> yv;
    private volatile int yw;
    private volatile Timer yx;
    private volatile Timer yy;
    private volatile Timer yz;
    
    r(final Context context, final f f) {
        this(context, f, null, GoogleAnalytics.getInstance(context));
    }
    
    r(final Context mContext, final f ye, final com.google.android.gms.analytics.d yt, final GoogleAnalytics yu) {
        this.yv = new ConcurrentLinkedQueue<d>();
        this.yE = 300000L;
        this.yt = yt;
        this.mContext = mContext;
        this.ye = ye;
        this.yu = yu;
        this.yD = jw.hA();
        this.yw = 0;
        this.yr = a.yN;
    }
    
    private Timer a(final Timer timer) {
        if (timer != null) {
            timer.cancel();
        }
        return null;
    }
    
    private void cD() {
        synchronized (this) {
            if (this.ys != null && this.yr == a.yI) {
                this.yr = a.yM;
                this.ys.disconnect();
            }
        }
    }
    
    private void eg() {
        this.yx = this.a(this.yx);
        this.yy = this.a(this.yy);
        this.yz = this.a(this.yz);
    }
    
    private void ei() {
        while (true) {
            Label_0340: {
                Label_0205: {
                    Label_0188: {
                        Label_0219: {
                            synchronized (this) {
                                if (!Thread.currentThread().equals(this.ye.getThread())) {
                                    this.ye.dP().add(new Runnable() {
                                        @Override
                                        public void run() {
                                            r.this.ei();
                                        }
                                    });
                                    return;
                                }
                                if (this.yA) {
                                    this.dI();
                                }
                                switch (r$2.yG[this.yr.ordinal()]) {
                                    case 1: {
                                        while (!this.yv.isEmpty()) {
                                            final d d = this.yv.poll();
                                            z.V("Sending hit to store  " + d);
                                            this.yd.a(d.en(), d.eo(), d.getPath(), d.ep());
                                        }
                                        break Label_0205;
                                    }
                                    case 7: {
                                        break;
                                    }
                                    case 2: {
                                        break Label_0219;
                                    }
                                    case 6: {
                                        break Label_0340;
                                    }
                                    default: {
                                        return;
                                    }
                                }
                            }
                            break Label_0188;
                        }
                        while (!this.yv.isEmpty()) {
                            final d d2 = this.yv.peek();
                            z.V("Sending hit to service   " + d2);
                            if (!this.yu.isDryRunEnabled()) {
                                this.ys.a(d2.en(), d2.eo(), d2.getPath(), d2.ep());
                            }
                            else {
                                z.V("Dry run enabled. Hit not actually sent to service.");
                            }
                            this.yv.poll();
                        }
                        this.yq = this.yD.elapsedRealtime();
                        return;
                    }
                    z.V("Blocked. Dropping hits.");
                    this.yv.clear();
                    return;
                }
                if (this.yg) {
                    this.ej();
                    return;
                }
                return;
            }
            z.V("Need to reconnect");
            if (!this.yv.isEmpty()) {
                this.el();
            }
        }
    }
    
    private void ej() {
        this.yd.dispatch();
        this.yg = false;
    }
    
    private void ek() {
        while (true) {
            Label_0072: {
                synchronized (this) {
                    if (this.yr != a.yJ) {
                        if (this.mContext == null || !"com.google.android.gms".equals(this.mContext.getPackageName())) {
                            break Label_0072;
                        }
                        this.yr = a.yK;
                        this.ys.disconnect();
                        z.W("Attempted to fall back to local store from service.");
                    }
                    return;
                }
            }
            this.eg();
            z.V("falling back to local store");
            if (this.yt != null) {
                this.yd = this.yt;
            }
            else {
                final q ea = q.ea();
                ea.a(this.mContext, this.ye);
                this.yd = ea.ed();
            }
            this.yr = a.yJ;
            this.ei();
        }
    }
    
    private void el() {
        while (true) {
            synchronized (this) {
                if (!this.yC && this.ys != null && this.yr != a.yJ) {
                    try {
                        ++this.yw;
                        this.a(this.yy);
                        this.yr = a.yH;
                        (this.yy = new Timer("Failed Connect")).schedule(new c(), 3000L);
                        z.V("connecting to Analytics service");
                        this.ys.connect();
                        return;
                    }
                    catch (SecurityException ex) {
                        z.W("security exception on connectToService");
                        this.ek();
                    }
                }
            }
            z.W("client not initialized.");
            this.ek();
        }
    }
    
    private void em() {
        this.yx = this.a(this.yx);
        (this.yx = new Timer("Service Reconnect")).schedule(new e(), 5000L);
    }
    
    @Override
    public void a(final int n, final Intent intent) {
        synchronized (this) {
            this.yr = a.yL;
            if (this.yw < 2) {
                z.W("Service unavailable (code=" + n + "), will retry.");
                this.em();
            }
            else {
                z.W("Service unavailable (code=" + n + "), using local store.");
                this.ek();
            }
        }
    }
    
    @Override
    public void b(final Map<String, String> map, final long n, final String s, final List<hb> list) {
        z.V("putHit called");
        this.yv.add(new d(map, n, s, list));
        this.ei();
    }
    
    @Override
    public void dI() {
        z.V("clearHits called");
        this.yv.clear();
        switch (r$2.yG[this.yr.ordinal()]) {
            default: {
                this.yA = true;
            }
            case 1: {
                this.yd.l(0L);
                this.yA = false;
            }
            case 2: {
                this.ys.dI();
                this.yA = false;
            }
        }
    }
    
    @Override
    public void dO() {
        while (true) {
            Label_0088: {
                synchronized (this) {
                    if (!this.yC) {
                        z.V("setForceLocalDispatch called.");
                        this.yC = true;
                        switch (r$2.yG[this.yr.ordinal()]) {
                            case 2: {
                                this.cD();
                            }
                            case 1:
                            case 4:
                            case 5:
                            case 6: {
                                break;
                            }
                            case 3: {
                                break Label_0088;
                            }
                            default: {}
                        }
                    }
                    return;
                }
            }
            this.yB = true;
        }
    }
    
    @Override
    public void dispatch() {
        switch (r$2.yG[this.yr.ordinal()]) {
            default: {
                this.yg = true;
            }
            case 2: {}
            case 1: {
                this.ej();
            }
        }
    }
    
    @Override
    public void eh() {
        if (this.ys != null) {
            return;
        }
        this.ys = new com.google.android.gms.analytics.c(this.mContext, (com.google.android.gms.analytics.c.b)this, (com.google.android.gms.analytics.c.c)this);
        this.el();
    }
    
    @Override
    public void onConnected() {
        synchronized (this) {
            this.yy = this.a(this.yy);
            this.yw = 0;
            z.V("Connected to service");
            this.yr = a.yI;
            if (this.yB) {
                this.cD();
                this.yB = false;
            }
            else {
                this.ei();
                this.yz = this.a(this.yz);
                (this.yz = new Timer("disconnect check")).schedule(new b(), this.yE);
            }
        }
    }
    
    @Override
    public void onDisconnected() {
        while (true) {
            Label_0060: {
                synchronized (this) {
                    if (this.yr == a.yK) {
                        z.V("Service blocked.");
                        this.eg();
                    }
                    else {
                        if (this.yr != a.yM) {
                            break Label_0060;
                        }
                        z.V("Disconnected from service");
                        this.eg();
                        this.yr = a.yN;
                    }
                    return;
                }
            }
            z.V("Unexpected disconnect.");
            this.yr = a.yL;
            if (this.yw < 2) {
                this.em();
                return;
            }
            this.ek();
        }
    }
    
    private enum a
    {
        yH, 
        yI, 
        yJ, 
        yK, 
        yL, 
        yM, 
        yN;
    }
    
    private class b extends TimerTask
    {
        @Override
        public void run() {
            if (r.this.yr == a.yI && r.this.yv.isEmpty() && r.this.yq + r.this.yE < r.this.yD.elapsedRealtime()) {
                z.V("Disconnecting due to inactivity");
                r.this.cD();
                return;
            }
            r.this.yz.schedule(new b(), r.this.yE);
        }
    }
    
    private class c extends TimerTask
    {
        @Override
        public void run() {
            if (r.this.yr == a.yH) {
                r.this.ek();
            }
        }
    }
    
    private static class d
    {
        private final Map<String, String> yP;
        private final long yQ;
        private final String yR;
        private final List<hb> yS;
        
        public d(final Map<String, String> yp, final long yq, final String yr, final List<hb> ys) {
            this.yP = yp;
            this.yQ = yq;
            this.yR = yr;
            this.yS = ys;
        }
        
        public Map<String, String> en() {
            return this.yP;
        }
        
        public long eo() {
            return this.yQ;
        }
        
        public List<hb> ep() {
            return this.yS;
        }
        
        public String getPath() {
            return this.yR;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("PATH: ");
            sb.append(this.yR);
            if (this.yP != null) {
                sb.append("  PARAMS: ");
                for (final Map.Entry<String, String> entry : this.yP.entrySet()) {
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                    sb.append(",  ");
                }
            }
            return sb.toString();
        }
    }
    
    private class e extends TimerTask
    {
        @Override
        public void run() {
            r.this.el();
        }
    }
}
