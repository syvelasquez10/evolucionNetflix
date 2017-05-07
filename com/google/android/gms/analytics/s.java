// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.content.Intent;
import java.util.TimerTask;
import com.google.android.gms.internal.ef;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Timer;
import java.util.Queue;
import android.content.Context;

class s implements ag, com.google.android.gms.analytics.c.b, com.google.android.gms.analytics.c.c
{
    private final Context mContext;
    private com.google.android.gms.analytics.d sG;
    private final f sH;
    private boolean sJ;
    private volatile long sT;
    private volatile a sU;
    private volatile com.google.android.gms.analytics.b sV;
    private com.google.android.gms.analytics.d sW;
    private final GoogleAnalytics sX;
    private final Queue<d> sY;
    private volatile int sZ;
    private volatile Timer ta;
    private volatile Timer tb;
    private volatile Timer tc;
    private boolean td;
    private boolean te;
    private boolean tf;
    private i tg;
    private long th;
    
    s(final Context context, final f f) {
        this(context, f, null, GoogleAnalytics.getInstance(context));
    }
    
    s(final Context mContext, final f sh, final com.google.android.gms.analytics.d sw, final GoogleAnalytics sx) {
        this.sY = new ConcurrentLinkedQueue<d>();
        this.th = 300000L;
        this.sW = sw;
        this.mContext = mContext;
        this.sH = sh;
        this.sX = sx;
        this.tg = new i() {
            @Override
            public long currentTimeMillis() {
                return System.currentTimeMillis();
            }
        };
        this.sZ = 0;
        this.sU = a.tq;
    }
    
    private Timer a(final Timer timer) {
        if (timer != null) {
            timer.cancel();
        }
        return null;
    }
    
    private void be() {
        synchronized (this) {
            if (this.sV != null && this.sU == a.tl) {
                this.sU = a.tp;
                this.sV.disconnect();
            }
        }
    }
    
    private void co() {
        this.ta = this.a(this.ta);
        this.tb = this.a(this.tb);
        this.tc = this.a(this.tc);
    }
    
    private void cq() {
        while (true) {
            Label_0317: {
                Label_0184: {
                    Label_0198: {
                        synchronized (this) {
                            if (!Thread.currentThread().equals(this.sH.getThread())) {
                                this.sH.bZ().add(new Runnable() {
                                    @Override
                                    public void run() {
                                        s.this.cq();
                                    }
                                });
                                return;
                            }
                            if (this.td) {
                                this.bR();
                            }
                            switch (s$3.tj[this.sU.ordinal()]) {
                                case 1: {
                                    while (!this.sY.isEmpty()) {
                                        final d d = this.sY.poll();
                                        aa.y("Sending hit to store  " + d);
                                        this.sG.a(d.cv(), d.cw(), d.getPath(), d.cx());
                                    }
                                    break;
                                }
                                case 2: {
                                    break Label_0198;
                                }
                                case 6: {
                                    break Label_0317;
                                }
                                default: {
                                    return;
                                }
                            }
                        }
                        break Label_0184;
                    }
                    while (!this.sY.isEmpty()) {
                        final d d2 = this.sY.peek();
                        aa.y("Sending hit to service   " + d2);
                        if (!this.sX.isDryRunEnabled()) {
                            this.sV.a(d2.cv(), d2.cw(), d2.getPath(), d2.cx());
                        }
                        else {
                            aa.y("Dry run enabled. Hit not actually sent to service.");
                        }
                        this.sY.poll();
                    }
                    this.sT = this.tg.currentTimeMillis();
                    return;
                }
                if (this.sJ) {
                    this.cr();
                    return;
                }
                return;
            }
            aa.y("Need to reconnect");
            if (!this.sY.isEmpty()) {
                this.ct();
            }
        }
    }
    
    private void cr() {
        this.sG.bW();
        this.sJ = false;
    }
    
    private void cs() {
        while (true) {
            while (true) {
                Label_0063: {
                    synchronized (this) {
                        if (this.sU != a.tm) {
                            this.co();
                            aa.y("falling back to local store");
                            if (this.sW == null) {
                                break Label_0063;
                            }
                            this.sG = this.sW;
                            this.sU = a.tm;
                            this.cq();
                        }
                        return;
                    }
                }
                final r ci = r.ci();
                ci.a(this.mContext, this.sH);
                this.sG = ci.cl();
                continue;
            }
        }
    }
    
    private void ct() {
        while (true) {
            synchronized (this) {
                if (!this.tf && this.sV != null && this.sU != a.tm) {
                    try {
                        ++this.sZ;
                        this.a(this.tb);
                        this.sU = a.tk;
                        (this.tb = new Timer("Failed Connect")).schedule(new c(), 3000L);
                        aa.y("connecting to Analytics service");
                        this.sV.connect();
                        return;
                    }
                    catch (SecurityException ex) {
                        aa.z("security exception on connectToService");
                        this.cs();
                    }
                }
            }
            aa.z("client not initialized.");
            this.cs();
        }
    }
    
    private void cu() {
        this.ta = this.a(this.ta);
        (this.ta = new Timer("Service Reconnect")).schedule(new e(), 5000L);
    }
    
    @Override
    public void a(final int n, final Intent intent) {
        synchronized (this) {
            this.sU = a.to;
            if (this.sZ < 2) {
                aa.z("Service unavailable (code=" + n + "), will retry.");
                this.cu();
            }
            else {
                aa.z("Service unavailable (code=" + n + "), using local store.");
                this.cs();
            }
        }
    }
    
    @Override
    public void b(final Map<String, String> map, final long n, final String s, final List<ef> list) {
        aa.y("putHit called");
        this.sY.add(new d(map, n, s, list));
        this.cq();
    }
    
    @Override
    public void bR() {
        aa.y("clearHits called");
        this.sY.clear();
        switch (s$3.tj[this.sU.ordinal()]) {
            default: {
                this.td = true;
            }
            case 1: {
                this.sG.j(0L);
                this.td = false;
            }
            case 2: {
                this.sV.bR();
                this.td = false;
            }
        }
    }
    
    @Override
    public void bW() {
        switch (s$3.tj[this.sU.ordinal()]) {
            default: {
                this.sJ = true;
            }
            case 2: {}
            case 1: {
                this.cr();
            }
        }
    }
    
    @Override
    public void bY() {
        while (true) {
            Label_0088: {
                synchronized (this) {
                    if (!this.tf) {
                        aa.y("setForceLocalDispatch called.");
                        this.tf = true;
                        switch (s$3.tj[this.sU.ordinal()]) {
                            case 2: {
                                this.be();
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
            this.te = true;
        }
    }
    
    @Override
    public void cp() {
        if (this.sV != null) {
            return;
        }
        this.sV = new com.google.android.gms.analytics.c(this.mContext, (com.google.android.gms.analytics.c.b)this, (com.google.android.gms.analytics.c.c)this);
        this.ct();
    }
    
    @Override
    public void onConnected() {
        synchronized (this) {
            this.tb = this.a(this.tb);
            this.sZ = 0;
            aa.y("Connected to service");
            this.sU = a.tl;
            if (this.te) {
                this.be();
                this.te = false;
            }
            else {
                this.cq();
                this.tc = this.a(this.tc);
                (this.tc = new Timer("disconnect check")).schedule(new b(), this.th);
            }
        }
    }
    
    @Override
    public void onDisconnected() {
        while (true) {
            Label_0065: {
                synchronized (this) {
                    if (this.sU == a.tp) {
                        aa.y("Disconnected from service");
                        this.co();
                        this.sU = a.tq;
                    }
                    else {
                        aa.y("Unexpected disconnect.");
                        this.sU = a.to;
                        if (this.sZ >= 2) {
                            break Label_0065;
                        }
                        this.cu();
                    }
                    return;
                }
            }
            this.cs();
        }
    }
    
    private enum a
    {
        tk, 
        tl, 
        tm, 
        tn, 
        to, 
        tp, 
        tq;
    }
    
    private class b extends TimerTask
    {
        @Override
        public void run() {
            if (s.this.sU == a.tl && s.this.sY.isEmpty() && s.this.sT + s.this.th < s.this.tg.currentTimeMillis()) {
                aa.y("Disconnecting due to inactivity");
                s.this.be();
                return;
            }
            s.this.tc.schedule(new b(), s.this.th);
        }
    }
    
    private class c extends TimerTask
    {
        @Override
        public void run() {
            if (s.this.sU == a.tk) {
                s.this.cs();
            }
        }
    }
    
    private static class d
    {
        private final Map<String, String> ts;
        private final long tt;
        private final String tu;
        private final List<ef> tv;
        
        public d(final Map<String, String> ts, final long tt, final String tu, final List<ef> tv) {
            this.ts = ts;
            this.tt = tt;
            this.tu = tu;
            this.tv = tv;
        }
        
        public Map<String, String> cv() {
            return this.ts;
        }
        
        public long cw() {
            return this.tt;
        }
        
        public List<ef> cx() {
            return this.tv;
        }
        
        public String getPath() {
            return this.tu;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("PATH: ");
            sb.append(this.tu);
            if (this.ts != null) {
                sb.append("  PARAMS: ");
                for (final Map.Entry<String, String> entry : this.ts.entrySet()) {
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
            s.this.ct();
        }
    }
}
