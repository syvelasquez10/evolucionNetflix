// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.webkit.WebView;
import java.util.List;
import android.os.SystemClock;
import org.json.JSONException;
import android.text.TextUtils;
import android.content.Context;

public final class bv extends cm implements bw.a, cx.a
{
    private final bb ed;
    private final Object fx;
    private au fy;
    private final cw gv;
    private final bu.a hb;
    private final Object hc;
    private final bz.a hd;
    private final h he;
    private cm hf;
    private cb hg;
    private boolean hh;
    private as hi;
    private ay hj;
    private final Context mContext;
    
    public bv(final Context mContext, final bz.a hd, final h he, final cw gv, final bb ed, final bu.a hb) {
        this.hc = new Object();
        this.fx = new Object();
        this.hh = false;
        this.ed = ed;
        this.hb = hb;
        this.gv = gv;
        this.mContext = mContext;
        this.hd = hd;
        this.he = he;
    }
    
    private x a(final bz bz) throws a {
        if (this.hg.hB == null) {
            throw new a("The ad response must specify one of the supported ad sizes.", 0);
        }
        final String[] split = this.hg.hB.split("x");
        if (split.length != 2) {
            throw new a("Could not parse the ad size from the ad response: " + this.hg.hB, 0);
        }
        while (true) {
            int int1;
            int int2;
            x[] eh;
            int length;
            int n = 0;
            x x = null;
            float density;
            int width;
            int height;
            Label_0156_Outer:Label_0177_Outer:
            while (true) {
            Label_0263:
                while (true) {
                Label_0253:
                    while (true) {
                        try {
                            int1 = Integer.parseInt(split[0]);
                            int2 = Integer.parseInt(split[1]);
                            eh = bz.em.eH;
                            length = eh.length;
                            n = 0;
                            if (n >= length) {
                                break;
                            }
                            x = eh[n];
                            density = this.mContext.getResources().getDisplayMetrics().density;
                            if (x.width == -1) {
                                width = (int)(x.widthPixels / density);
                                if (x.height != -2) {
                                    break Label_0253;
                                }
                                height = (int)(x.heightPixels / density);
                                if (int1 == width && int2 == height) {
                                    return new x(x, bz.em.eH);
                                }
                                break Label_0263;
                            }
                        }
                        catch (NumberFormatException ex) {
                            throw new a("Could not parse the ad size from the ad response: " + this.hg.hB, 0);
                        }
                        width = x.width;
                        continue Label_0177_Outer;
                    }
                    height = x.height;
                    continue;
                }
                ++n;
                continue Label_0156_Outer;
            }
        }
        throw new a("The ad size from the ad response was not one of the requested sizes: " + this.hg.hB, 0);
    }
    
    private void a(final bz bz, final long n) throws a {
        synchronized (this.hc) {
            this.hi = new as(this.mContext, bz, this.ed, this.fy);
            // monitorexit(this.hc)
            this.hj = this.hi.a(n, 60000L);
            switch (this.hj.ga) {
                default: {
                    throw new a("Unexpected mediation result: " + this.hj.ga, 0);
                }
                case 1: {
                    break;
                }
                case 0: {
                    return;
                }
            }
        }
        throw new a("No fill from any mediation ad networks.", 3);
    }
    
    private void aj() throws a {
        if (this.hg.errorCode != -3) {
            if (TextUtils.isEmpty((CharSequence)this.hg.hw)) {
                throw new a("No fill from ad server.", 3);
            }
            if (this.hg.hy) {
                try {
                    this.fy = new au(this.hg.hw);
                }
                catch (JSONException ex) {
                    throw new a("Could not parse mediation config: " + this.hg.hw, 0);
                }
            }
        }
    }
    
    private void b(final long n) throws a {
        cs.iI.post((Runnable)new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (bv.this.fx) {
                        if (bv.this.hg.errorCode != -2) {
                            return;
                        }
                        bv.this.gv.aC().a((cx.a)bv.this);
                        if (bv.this.hg.errorCode == -3) {
                            ct.u("Loading URL in WebView: " + bv.this.hg.gL);
                            bv.this.gv.loadUrl(bv.this.hg.gL);
                            return;
                        }
                    }
                    ct.u("Loading HTML in WebView.");
                    bv.this.gv.loadDataWithBaseURL(co.o(bv.this.hg.gL), bv.this.hg.hw, "text/html", "UTF-8", (String)null);
                }
            }
        });
        this.d(n);
    }
    
    private void c(final long n) throws a {
        while (this.e(n)) {
            if (this.hg != null) {
                synchronized (this.hc) {
                    this.hf = null;
                    // monitorexit(this.hc)
                    if (this.hg.errorCode != -2 && this.hg.errorCode != -3) {
                        throw new a("There was a problem getting an ad response. ErrorCode: " + this.hg.errorCode, this.hg.errorCode);
                    }
                }
                return;
            }
        }
        throw new a("Timed out waiting for ad response.", 2);
    }
    
    private void d(final long n) throws a {
        while (this.e(n)) {
            if (this.hh) {
                return;
            }
        }
        throw new a("Timed out waiting for WebView to finish loading.", 2);
    }
    
    private boolean e(long n) throws a {
        n = 60000L - (SystemClock.elapsedRealtime() - n);
        if (n <= 0L) {
            return false;
        }
        try {
            this.fx.wait(n);
            return true;
        }
        catch (InterruptedException ex) {
            throw new a("Ad request cancelled.", -1);
        }
    }
    
    @Override
    public void a(final cb hg) {
        synchronized (this.fx) {
            ct.r("Received ad response.");
            this.hg = hg;
            this.fx.notify();
        }
    }
    
    @Override
    public void a(final cw cw) {
        synchronized (this.fx) {
            ct.r("WebView finished loading.");
            this.hh = true;
            this.fx.notify();
        }
    }
    
    @Override
    public void ai() {
    Label_0192_Outer:
        while (true) {
            Object gd;
            bc gc;
            int errorCode;
            Object gb;
            long n;
            Object hf;
            Object hc;
            x x = null;
            cw gv;
            List<String> fk;
            List<String> fl;
            List<String> ha;
            int orientation;
            String hu;
            boolean hy;
            au fy;
            aw ge;
            x a2;
            Label_0280_Outer:Label_0296_Outer:Label_0312_Outer:Label_0334_Outer:
            while (true) {
            Label_0537:
                while (true) {
                Label_0531:
                    while (true) {
                    Label_0525:
                        while (true) {
                        Label_0519:
                            while (true) {
                                Label_0513:Block_15_Outer:
                                while (true) {
                                    Label_0502: {
                                        synchronized (this.fx) {
                                            ct.r("AdLoaderBackgroundTask started.");
                                            gd = new bz(this.hd, this.he.g().a(this.mContext));
                                            gc = null;
                                            errorCode = -2;
                                            gb = gc;
                                            Label_0407: {
                                                try {
                                                    n = SystemClock.elapsedRealtime();
                                                    gb = gc;
                                                    hf = bw.a(this.mContext, (bz)gd, (bw.a)this);
                                                    gb = gc;
                                                    hc = this.hc;
                                                    gb = gc;
                                                    // monitorenter(hc)
                                                    try {
                                                        this.hf = (cm)hf;
                                                        if (this.hf == null) {
                                                            throw new a("Could not start the ad request service.", 0);
                                                        }
                                                        break Label_0407;
                                                    }
                                                    finally {
                                                        // monitorexit(hc)
                                                        gb = gc;
                                                    }
                                                }
                                                catch (a a) {
                                                    errorCode = a.getErrorCode();
                                                    if (errorCode != 3 && errorCode != -1) {
                                                        break Label_0502;
                                                    }
                                                    ct.t(a.getMessage());
                                                    this.hg = new cb(errorCode);
                                                    cs.iI.post((Runnable)new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            bv.this.onStop();
                                                        }
                                                    });
                                                    x = (x)gb;
                                                    hf = ((bz)gd).hr;
                                                    gv = this.gv;
                                                    fk = this.hg.fK;
                                                    fl = this.hg.fL;
                                                    ha = this.hg.hA;
                                                    orientation = this.hg.orientation;
                                                    n = this.hg.fO;
                                                    hu = ((bz)gd).hu;
                                                    hy = this.hg.hy;
                                                    if (this.hj == null) {
                                                        break Label_0513;
                                                    }
                                                    gb = this.hj.gb;
                                                    if (this.hj == null) {
                                                        break Label_0519;
                                                    }
                                                    gc = this.hj.gc;
                                                    if (this.hj == null) {
                                                        break Label_0525;
                                                    }
                                                    gd = this.hj.gd;
                                                    fy = this.fy;
                                                    if (this.hj != null) {
                                                        ge = this.hj.ge;
                                                        cs.iI.post((Runnable)new Runnable() {
                                                            final /* synthetic */ cj hl = new cj((v)hf, gv, fk, errorCode, fl, ha, orientation, n, hu, hy, (at)gb, gc, (String)gd, fy, ge, bv.this.hg.hz, x, bv.this.hg.hx);
                                                            
                                                            @Override
                                                            public void run() {
                                                                synchronized (bv.this.fx) {
                                                                    bv.this.hb.a(this.hl);
                                                                }
                                                            }
                                                        });
                                                        return;
                                                    }
                                                    break Label_0531;
                                                    // iftrue(Label_0482:, !this.hg.hy)
                                                    // monitorexit(hc)
                                                    // iftrue(Label_0454:, gd.em.eH == null)
                                                    Block_16: {
                                                        while (true) {
                                                            while (true) {
                                                                gb = a2;
                                                                break Block_16;
                                                                gb = gc;
                                                                a2 = this.a((bz)gd);
                                                                continue Block_15_Outer;
                                                            }
                                                            Label_0482: {
                                                                gb = a2;
                                                            }
                                                            this.b(n);
                                                            break Label_0537;
                                                            gb = gc;
                                                            this.c(n);
                                                            gb = gc;
                                                            this.aj();
                                                            gb = gc;
                                                            continue;
                                                        }
                                                    }
                                                    gb = a2;
                                                    this.a((bz)gd, n);
                                                }
                                            }
                                        }
                                    }
                                    ct.v(((Throwable)x).getMessage());
                                    continue Label_0192_Outer;
                                }
                                gb = null;
                                continue Label_0296_Outer;
                            }
                            gc = null;
                            continue Label_0312_Outer;
                        }
                        gd = null;
                        continue Label_0334_Outer;
                    }
                    ge = null;
                    continue;
                }
                continue Label_0280_Outer;
            }
        }
    }
    
    @Override
    public void onStop() {
        synchronized (this.hc) {
            if (this.hf != null) {
                this.hf.cancel();
            }
            this.gv.stopLoading();
            co.a(this.gv);
            if (this.hi != null) {
                this.hi.cancel();
            }
        }
    }
    
    private static final class a extends Exception
    {
        private final int hm;
        
        public a(final String s, final int hm) {
            super(s);
            this.hm = hm;
        }
        
        public int getErrorCode() {
            return this.hm;
        }
    }
}
