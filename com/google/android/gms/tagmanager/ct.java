// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.c$i;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;
import android.content.Context;
import java.util.Set;
import java.util.Map;
import com.google.android.gms.internal.d$a;

class ct
{
    private static final bz<d$a> aqH;
    private final DataLayer anS;
    private final cr$c aqI;
    private final ag aqJ;
    private final Map<String, aj> aqK;
    private final Map<String, aj> aqL;
    private final Map<String, aj> aqM;
    private final k<cr$a, bz<d$a>> aqN;
    private final k<String, ct$b> aqO;
    private final Set<cr$e> aqP;
    private final Map<String, ct$c> aqQ;
    private volatile String aqR;
    private int aqS;
    
    static {
        aqH = new bz<d$a>(di.pI(), true);
    }
    
    public ct(final Context context, final cr$c aqI, final DataLayer anS, final s$a s$a, final s$a s$a2, final ag aqJ) {
        if (aqI == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.aqI = aqI;
        this.aqP = new HashSet<cr$e>(aqI.oW());
        this.anS = anS;
        this.aqJ = aqJ;
        this.aqN = new l<cr$a, bz<d$a>>().a(1048576, new ct$1(this));
        this.aqO = new l<String, ct$b>().a(1048576, new ct$2(this));
        this.aqK = new HashMap<String, aj>();
        this.b(new i(context));
        this.b(new s(s$a2));
        this.b(new w(anS));
        this.b(new dj(context, anS));
        this.aqL = new HashMap<String, aj>();
        this.c(new q());
        this.c(new ad());
        this.c(new ae());
        this.c(new al());
        this.c(new am());
        this.c(new bd());
        this.c(new be());
        this.c(new ci());
        this.c(new dc());
        this.aqM = new HashMap<String, aj>();
        this.a(new b(context));
        this.a(new c(context));
        this.a(new e(context));
        this.a(new f(context));
        this.a(new g(context));
        this.a(new h(context));
        this.a(new m());
        this.a(new p(this.aqI.getVersion()));
        this.a(new s(s$a));
        this.a(new u(anS));
        this.a(new z(context));
        this.a(new aa());
        this.a(new ac());
        this.a(new ah(this));
        this.a(new an());
        this.a(new ao());
        this.a(new ax(context));
        this.a(new az());
        this.a(new bc());
        this.a(new bj());
        this.a(new bl(context));
        this.a(new ca());
        this.a(new cc());
        this.a(new cf());
        this.a(new ch());
        this.a(new cj(context));
        this.a(new cu());
        this.a(new cv());
        this.a(new de());
        this.a(new dk());
        this.aqQ = new HashMap<String, ct$c>();
        for (final cr$e cr$e : this.aqP) {
            if (aqJ.oo()) {
                a(cr$e.pe(), cr$e.pf(), "add macro");
                a(cr$e.pj(), cr$e.pg(), "remove macro");
                a(cr$e.pc(), cr$e.ph(), "add tag");
                a(cr$e.pd(), cr$e.pi(), "remove tag");
            }
            for (int i = 0; i < cr$e.pe().size(); ++i) {
                final cr$a cr$a = cr$e.pe().get(i);
                String s = "Unknown";
                if (aqJ.oo()) {
                    s = s;
                    if (i < cr$e.pf().size()) {
                        s = cr$e.pf().get(i);
                    }
                }
                final ct$c e = e(this.aqQ, h(cr$a));
                e.b(cr$e);
                e.a(cr$e, cr$a);
                e.a(cr$e, s);
            }
            for (int j = 0; j < cr$e.pj().size(); ++j) {
                final cr$a cr$a2 = cr$e.pj().get(j);
                String s2 = "Unknown";
                if (aqJ.oo()) {
                    s2 = s2;
                    if (j < cr$e.pg().size()) {
                        s2 = cr$e.pg().get(j);
                    }
                }
                final ct$c e2 = e(this.aqQ, h(cr$a2));
                e2.b(cr$e);
                e2.b(cr$e, cr$a2);
                e2.b(cr$e, s2);
            }
        }
        for (final Map.Entry<String, List<cr$a>> entry : this.aqI.oX().entrySet()) {
            for (final cr$a cr$a3 : entry.getValue()) {
                if (!di.n(cr$a3.oS().get(com.google.android.gms.internal.b.dG.toString()))) {
                    e(this.aqQ, entry.getKey()).i(cr$a3);
                }
            }
        }
    }
    
    private bz<d$a> a(final d$a d$a, final Set<String> set, final dl dl) {
        if (!d$a.gF) {
            return new bz<d$a>(d$a, true);
        }
        switch (d$a.type) {
            default: {
                bh.T("Unknown type: " + d$a.type);
                return ct.aqH;
            }
            case 2: {
                final d$a g = cr.g(d$a);
                g.gw = new d$a[d$a.gw.length];
                for (int i = 0; i < d$a.gw.length; ++i) {
                    final bz<d$a> a = this.a(d$a.gw[i], set, dl.fh(i));
                    if (a == ct.aqH) {
                        return ct.aqH;
                    }
                    g.gw[i] = a.getObject();
                }
                return new bz<d$a>(g, false);
            }
            case 3: {
                final d$a g2 = cr.g(d$a);
                if (d$a.gx.length != d$a.gy.length) {
                    bh.T("Invalid serving value: " + d$a.toString());
                    return ct.aqH;
                }
                g2.gx = new d$a[d$a.gx.length];
                g2.gy = new d$a[d$a.gx.length];
                for (int j = 0; j < d$a.gx.length; ++j) {
                    final bz<d$a> a2 = this.a(d$a.gx[j], set, dl.fi(j));
                    final bz<d$a> a3 = this.a(d$a.gy[j], set, dl.fj(j));
                    if (a2 == ct.aqH || a3 == ct.aqH) {
                        return ct.aqH;
                    }
                    g2.gx[j] = a2.getObject();
                    g2.gy[j] = a3.getObject();
                }
                return new bz<d$a>(g2, false);
            }
            case 4: {
                if (set.contains(d$a.gz)) {
                    bh.T("Macro cycle detected.  Current macro reference: " + d$a.gz + "." + "  Previous macro references: " + set.toString() + ".");
                    return ct.aqH;
                }
                set.add(d$a.gz);
                final bz<d$a> a4 = dm.a(this.a(d$a.gz, set, dl.oD()), d$a.gE);
                set.remove(d$a.gz);
                return a4;
            }
            case 7: {
                final d$a g3 = cr.g(d$a);
                g3.gD = new d$a[d$a.gD.length];
                for (int k = 0; k < d$a.gD.length; ++k) {
                    final bz<d$a> a5 = this.a(d$a.gD[k], set, dl.fk(k));
                    if (a5 == ct.aqH) {
                        return ct.aqH;
                    }
                    g3.gD[k] = a5.getObject();
                }
                return new bz<d$a>(g3, false);
            }
        }
    }
    
    private bz<d$a> a(final String s, final Set<String> set, final bk bk) {
        ++this.aqS;
        final ct$b ct$b = this.aqO.get(s);
        if (ct$b != null && !this.aqJ.oo()) {
            this.a(ct$b.oT(), set);
            --this.aqS;
            return ct$b.pn();
        }
        final ct$c ct$c = this.aqQ.get(s);
        if (ct$c == null) {
            bh.T(this.pm() + "Invalid macro: " + s);
            --this.aqS;
            return ct.aqH;
        }
        final bz<Set<cr$a>> a = this.a(s, ct$c.po(), ct$c.pp(), ct$c.pq(), ct$c.ps(), ct$c.pr(), set, bk.of());
        cr$a pt;
        if (a.getObject().isEmpty()) {
            pt = ct$c.pt();
        }
        else {
            if (a.getObject().size() > 1) {
                bh.W(this.pm() + "Multiple macros active for macroName " + s);
            }
            pt = a.getObject().iterator().next();
        }
        if (pt == null) {
            --this.aqS;
            return ct.aqH;
        }
        final bz<d$a> a2 = this.a(this.aqM, pt, set, bk.ou());
        final boolean b = a.oE() && a2.oE();
        bz<d$a> aqH;
        if (a2 == ct.aqH) {
            aqH = ct.aqH;
        }
        else {
            aqH = new bz<d$a>(a2.getObject(), b);
        }
        final d$a ot = pt.oT();
        if (aqH.oE()) {
            this.aqO.e(s, new ct$b(aqH, ot));
        }
        this.a(ot, set);
        --this.aqS;
        return aqH;
    }
    
    private bz<d$a> a(final Map<String, aj> map, final cr$a cr$a, final Set<String> set, final ck ck) {
        boolean b = true;
        final d$a d$a = cr$a.oS().get(com.google.android.gms.internal.b.cU.toString());
        bz<d$a> aqH;
        if (d$a == null) {
            bh.T("No function id in properties");
            aqH = ct.aqH;
        }
        else {
            final String ga = d$a.gA;
            final aj aj = map.get(ga);
            if (aj == null) {
                bh.T(ga + " has no backing implementation.");
                return ct.aqH;
            }
            aqH = this.aqN.get(cr$a);
            if (aqH == null || this.aqJ.oo()) {
                final HashMap<String, Object> hashMap = new HashMap<String, Object>();
                final Iterator<Map.Entry<String, d$a>> iterator = cr$a.oS().entrySet().iterator();
                boolean b2 = true;
                while (iterator.hasNext()) {
                    final Map.Entry<String, d$a> entry = iterator.next();
                    final bz<d$a> a = this.a(entry.getValue(), set, ck.cE(entry.getKey()).e(entry.getValue()));
                    if (a == ct.aqH) {
                        return ct.aqH;
                    }
                    if (a.oE()) {
                        cr$a.a(entry.getKey(), a.getObject());
                    }
                    else {
                        b2 = false;
                    }
                    hashMap.put(entry.getKey(), a.getObject());
                }
                if (!aj.a(hashMap.keySet())) {
                    bh.T("Incorrect keys for function " + ga + " required " + aj.oq() + " had " + hashMap.keySet());
                    return ct.aqH;
                }
                if (!b2 || !aj.nL()) {
                    b = false;
                }
                final bz bz = new bz<d$a>(aj.C((Map<String, d$a>)hashMap), b);
                if (b) {
                    this.aqN.e(cr$a, (bz<d$a>)bz);
                }
                ck.d(bz.getObject());
                return (bz<d$a>)bz;
            }
        }
        return aqH;
    }
    
    private bz<Set<cr$a>> a(final Set<cr$e> set, final Set<String> set2, final ct$a ct$a, final cs cs) {
        final HashSet<cr$a> set3 = new HashSet<cr$a>();
        final HashSet<cr$a> set4 = new HashSet<cr$a>();
        final Iterator<cr$e> iterator = set.iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final cr$e cr$e = iterator.next();
            final cn oc = cs.oC();
            final bz<Boolean> a = this.a(cr$e, set2, oc);
            if (a.getObject()) {
                ct$a.a(cr$e, set3, set4, oc);
            }
            b = (b && a.oE());
        }
        set3.removeAll(set4);
        cs.b(set3);
        return new bz<Set<cr$a>>(set3, b);
    }
    
    private void a(final d$a d$a, final Set<String> set) {
        if (d$a != null) {
            final bz<d$a> a = this.a(d$a, set, new bx());
            if (a != ct.aqH) {
                final Object o = di.o(a.getObject());
                if (o instanceof Map) {
                    this.anS.push((Map<String, Object>)o);
                    return;
                }
                if (!(o instanceof List)) {
                    bh.W("pushAfterEvaluate: value not a Map or List");
                    return;
                }
                for (final Map<String, Object> next : (List<Object>)o) {
                    if (next instanceof Map) {
                        this.anS.push(next);
                    }
                    else {
                        bh.W("pushAfterEvaluate: value not a Map");
                    }
                }
            }
        }
    }
    
    private static void a(final List<cr$a> list, final List<String> list2, final String s) {
        if (list.size() != list2.size()) {
            bh.U("Invalid resource: imbalance of rule names of functions for " + s + " operation. Using default rule name instead");
        }
    }
    
    private static void a(final Map<String, aj> map, final aj aj) {
        if (map.containsKey(aj.op())) {
            throw new IllegalArgumentException("Duplicate function type name: " + aj.op());
        }
        map.put(aj.op(), aj);
    }
    
    private static ct$c e(final Map<String, ct$c> map, final String s) {
        ct$c ct$c;
        if ((ct$c = map.get(s)) == null) {
            ct$c = new ct$c();
            map.put(s, ct$c);
        }
        return ct$c;
    }
    
    private static String h(final cr$a cr$a) {
        return di.j(cr$a.oS().get(com.google.android.gms.internal.b.df.toString()));
    }
    
    private String pm() {
        if (this.aqS <= 1) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.aqS));
        for (int i = 2; i < this.aqS; ++i) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }
    
    bz<Boolean> a(final cr$a cr$a, final Set<String> set, final ck ck) {
        final bz<d$a> a = this.a(this.aqL, cr$a, set, ck);
        final Boolean n = di.n(a.getObject());
        ck.d(di.u(n));
        return new bz<Boolean>(n, a.oE());
    }
    
    bz<Boolean> a(final cr$e cr$e, final Set<String> set, final cn cn) {
        final Iterator<cr$a> iterator = cr$e.pb().iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final bz<Boolean> a = this.a(iterator.next(), set, cn.ow());
            if (a.getObject()) {
                cn.f(di.u(false));
                return new bz<Boolean>(false, a.oE());
            }
            b = (b && a.oE());
        }
        final Iterator<cr$a> iterator2 = cr$e.pa().iterator();
        while (iterator2.hasNext()) {
            final bz<Boolean> a2 = this.a(iterator2.next(), set, cn.ox());
            if (!a2.getObject()) {
                cn.f(di.u(false));
                return new bz<Boolean>(false, a2.oE());
            }
            b = (b && a2.oE());
        }
        cn.f(di.u(true));
        return new bz<Boolean>(true, b);
    }
    
    bz<Set<cr$a>> a(final String s, final Set<cr$e> set, final Map<cr$e, List<cr$a>> map, final Map<cr$e, List<String>> map2, final Map<cr$e, List<cr$a>> map3, final Map<cr$e, List<String>> map4, final Set<String> set2, final cs cs) {
        return this.a(set, set2, new ct$3(this, map, map2, map3, map4), cs);
    }
    
    bz<Set<cr$a>> a(final Set<cr$e> set, final cs cs) {
        return this.a(set, new HashSet<String>(), new ct$4(this), cs);
    }
    
    void a(final aj aj) {
        a(this.aqM, aj);
    }
    
    void b(final aj aj) {
        a(this.aqK, aj);
    }
    
    void c(final aj aj) {
        a(this.aqL, aj);
    }
    
    public bz<d$a> cO(final String s) {
        this.aqS = 0;
        final af cx = this.aqJ.cx(s);
        final bz<d$a> a = this.a(s, new HashSet<String>(), cx.ol());
        cx.on();
        return a;
    }
    
    void cP(final String aqR) {
        synchronized (this) {
            this.aqR = aqR;
        }
    }
    
    public void cm(final String s) {
        synchronized (this) {
            this.cP(s);
            final t om = this.aqJ.cy(s).om();
            final Iterator<cr$a> iterator = this.a(this.aqP, om.of()).getObject().iterator();
            while (iterator.hasNext()) {
                this.a(this.aqK, iterator.next(), new HashSet<String>(), om.oe());
            }
        }
        final af af;
        af.on();
        this.cP(null);
    }
    // monitorexit(this)
    
    public void k(final List<c$i> list) {
        while (true) {
            while (true) {
                c$i c$i = null;
                Label_0079: {
                    synchronized (this) {
                        final Iterator<c$i> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            c$i = iterator.next();
                            if (c$i.name != null && c$i.name.startsWith("gaExperiment:")) {
                                break Label_0079;
                            }
                            bh.V("Ignored supplemental: " + c$i);
                        }
                        break;
                    }
                }
                ai.a(this.anS, c$i);
                continue;
            }
        }
    }
    // monitorexit(this)
    
    String pl() {
        synchronized (this) {
            return this.aqR;
        }
    }
}
