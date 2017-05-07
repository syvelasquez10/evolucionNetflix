// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.Application;
import android.app.Activity;
import android.content.res.Resources;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.math.BigInteger;
import android.content.Context;

@ez
public class gb implements b
{
    private static final gb vJ;
    public static final String vK;
    private Context mContext;
    private final Object mw;
    private am nu;
    private al nv;
    private ey nw;
    private gt qs;
    private boolean uH;
    private boolean uI;
    public final String vL;
    private final gc vM;
    private BigInteger vN;
    private final HashSet<ga> vO;
    private final HashMap<String, ge> vP;
    private boolean vQ;
    private boolean vR;
    private an vS;
    private LinkedList<Thread> vT;
    private boolean vU;
    private Bundle vV;
    private String vW;
    
    static {
        vJ = new gb();
        vK = gb.vJ.vL;
    }
    
    private gb() {
        this.mw = new Object();
        this.vN = BigInteger.ONE;
        this.vO = new HashSet<ga>();
        this.vP = new HashMap<String, ge>();
        this.vQ = false;
        this.uH = true;
        this.vR = false;
        this.uI = true;
        this.nu = null;
        this.vS = null;
        this.nv = null;
        this.vT = new LinkedList<Thread>();
        this.vU = false;
        this.vV = bn.bs();
        this.nw = null;
        this.vL = gj.dp();
        this.vM = new gc(this.vL);
    }
    
    public static Bundle a(final Context context, final gd gd, final String s) {
        return gb.vJ.b(context, gd, s);
    }
    
    public static void a(final Context context, final gt gt) {
        gb.vJ.b(context, gt);
    }
    
    public static void a(final Context context, final boolean b) {
        gb.vJ.b(context, b);
    }
    
    public static void b(final HashSet<ga> set) {
        gb.vJ.c(set);
    }
    
    public static Bundle bD() {
        return gb.vJ.dh();
    }
    
    public static String c(final int n, final String s) {
        return gb.vJ.d(n, s);
    }
    
    public static gb cV() {
        return gb.vJ;
    }
    
    public static String cX() {
        return gb.vJ.cY();
    }
    
    public static gc cZ() {
        return gb.vJ.da();
    }
    
    public static boolean db() {
        return gb.vJ.dc();
    }
    
    public static boolean dd() {
        return gb.vJ.de();
    }
    
    public static String df() {
        return gb.vJ.dg();
    }
    
    public static void e(final Throwable t) {
        gb.vJ.f(t);
    }
    
    @Override
    public void a(final Bundle vv) {
        synchronized (this.mw) {
            this.vU = true;
            this.vV = vv;
            while (!this.vT.isEmpty()) {
                ey.a(this.mContext, this.vT.remove(0), this.qs);
            }
        }
    }
    // monitorexit(o)
    
    public void a(final ga ga) {
        synchronized (this.mw) {
            this.vO.add(ga);
        }
    }
    
    public void a(final String s, final ge ge) {
        synchronized (this.mw) {
            this.vP.put(s, ge);
        }
    }
    
    public void a(final Thread thread) {
        synchronized (this.mw) {
            if (this.vU) {
                ey.a(this.mContext, thread, this.qs);
            }
            else {
                this.vT.add(thread);
            }
        }
    }
    
    public Bundle b(final Context context, final gd gd, final String s) {
        final Bundle bundle;
        synchronized (this.mw) {
            bundle = new Bundle();
            bundle.putBundle("app", this.vM.b(context, s));
            final Bundle bundle2 = new Bundle();
            for (final String s2 : this.vP.keySet()) {
                bundle2.putBundle(s2, this.vP.get(s2).toBundle());
            }
        }
        final Bundle bundle3;
        bundle.putBundle("slots", bundle3);
        final ArrayList<Bundle> list = new ArrayList<Bundle>();
        final Iterator<ga> iterator2 = this.vO.iterator();
        while (iterator2.hasNext()) {
            list.add(iterator2.next().toBundle());
        }
        bundle.putParcelableArrayList("ads", (ArrayList)list);
        gd.a(this.vO);
        this.vO.clear();
        // monitorexit(o)
        return bundle;
    }
    
    public void b(final Context context, final gt qs) {
        synchronized (this.mw) {
            if (!this.vR) {
                this.mContext = context.getApplicationContext();
                this.qs = qs;
                this.uH = gh.o(context);
                iv.H(context);
                cf.a(context, (cf.b)this);
                this.a(Thread.currentThread());
                this.vW = gj.c(context, qs.wD);
                this.vR = true;
            }
        }
    }
    
    public void b(final Context context, final boolean uh) {
        synchronized (this.mw) {
            if (uh != this.uH) {
                gh.a(context, this.uH = uh);
            }
        }
    }
    
    public void c(final HashSet<ga> set) {
        synchronized (this.mw) {
            this.vO.addAll((Collection<?>)set);
        }
    }
    
    public boolean cW() {
        synchronized (this.mw) {
            return this.uI;
        }
    }
    
    public String cY() {
        synchronized (this.mw) {
            final String string = this.vN.toString();
            this.vN = this.vN.add(BigInteger.ONE);
            return string;
        }
    }
    
    public String d(final int n, final String s) {
        Resources resources;
        if (this.qs.wG) {
            resources = this.mContext.getResources();
        }
        else {
            resources = GooglePlayServicesUtil.getRemoteResource(this.mContext);
        }
        if (resources == null) {
            return s;
        }
        return resources.getString(n);
    }
    
    public gc da() {
        synchronized (this.mw) {
            return this.vM;
        }
    }
    
    public boolean dc() {
        synchronized (this.mw) {
            final boolean vq = this.vQ;
            this.vQ = true;
            return vq;
        }
    }
    
    public boolean de() {
        synchronized (this.mw) {
            return this.uH;
        }
    }
    
    public String dg() {
        synchronized (this.mw) {
            return this.vW;
        }
    }
    
    public Bundle dh() {
        synchronized (this.mw) {
            return this.vV;
        }
    }
    
    public void f(final Throwable t) {
        if (this.vR) {
            new ey(this.mContext, this.qs, null, null).b(t);
        }
    }
    
    public an l(final Context context) {
        if (!bD().getBoolean(bn.pd.getKey(), false) || !kc.hE() || this.cW()) {
            return null;
        }
        Label_0083: {
            synchronized (this.mw) {
                if (this.nu != null) {
                    break Label_0083;
                }
                if (!(context instanceof Activity)) {
                    return null;
                }
            }
            final Context context2;
            this.nu = new am((Application)context2.getApplicationContext(), (Activity)context2);
        }
        if (this.nv == null) {
            this.nv = new al();
        }
        if (this.vS == null) {
            this.vS = new an(this.nu, this.nv, this.vV, new ey(this.mContext, this.qs, null, null));
        }
        this.vS.aV();
        // monitorexit(o)
        return this.vS;
    }
    
    public void v(final boolean ui) {
        synchronized (this.mw) {
            this.uI = ui;
        }
    }
}
