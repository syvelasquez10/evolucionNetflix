// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;
import android.content.Context;
import java.util.Set;
import java.util.Map;
import com.google.android.gms.internal.d;

class ct
{
    private static final bz<d.a> aqH;
    private final DataLayer anS;
    private final cr.c aqI;
    private final ag aqJ;
    private final Map<String, aj> aqK;
    private final Map<String, aj> aqL;
    private final Map<String, aj> aqM;
    private final k<cr.a, bz<d.a>> aqN;
    private final k<String, b> aqO;
    private final Set<cr.e> aqP;
    private final Map<String, c> aqQ;
    private volatile String aqR;
    private int aqS;
    
    static {
        aqH = new bz<d.a>(di.pI(), true);
    }
    
    public ct(final Context context, final cr.c aqI, final DataLayer anS, final s.a a, final s.a a2, final ag aqJ) {
        if (aqI == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.aqI = aqI;
        this.aqP = new HashSet<cr.e>(aqI.oW());
        this.anS = anS;
        this.aqJ = aqJ;
        this.aqN = new l<cr.a, bz<d.a>>().a(1048576, (l.a<cr.a, bz<d.a>>)new l.a<cr.a, bz<d.a>>() {
            public int a(final cr.a a, final bz<d.a> bz) {
                return bz.getObject().qF();
            }
        });
        this.aqO = new l<String, b>().a(1048576, (l.a<String, b>)new l.a<String, b>() {
            public int a(final String s, final b b) {
                return s.length() + b.getSize();
            }
        });
        this.aqK = new HashMap<String, aj>();
        this.b(new i(context));
        this.b(new s(a2));
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
        this.a(new com.google.android.gms.tagmanager.b(context));
        this.a(new com.google.android.gms.tagmanager.c(context));
        this.a(new e(context));
        this.a(new f(context));
        this.a(new g(context));
        this.a(new h(context));
        this.a(new m());
        this.a(new p(this.aqI.getVersion()));
        this.a(new s(a));
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
        this.aqQ = new HashMap<String, c>();
        for (final cr.e e : this.aqP) {
            if (aqJ.oo()) {
                a(e.pe(), e.pf(), "add macro");
                a(e.pj(), e.pg(), "remove macro");
                a(e.pc(), e.ph(), "add tag");
                a(e.pd(), e.pi(), "remove tag");
            }
            for (int i = 0; i < e.pe().size(); ++i) {
                final cr.a a3 = e.pe().get(i);
                String s = "Unknown";
                if (aqJ.oo()) {
                    s = s;
                    if (i < e.pf().size()) {
                        s = e.pf().get(i);
                    }
                }
                final c e2 = e(this.aqQ, h(a3));
                e2.b(e);
                e2.a(e, a3);
                e2.a(e, s);
            }
            for (int j = 0; j < e.pj().size(); ++j) {
                final cr.a a4 = e.pj().get(j);
                String s2 = "Unknown";
                if (aqJ.oo()) {
                    s2 = s2;
                    if (j < e.pg().size()) {
                        s2 = e.pg().get(j);
                    }
                }
                final c e3 = e(this.aqQ, h(a4));
                e3.b(e);
                e3.b(e, a4);
                e3.b(e, s2);
            }
        }
        for (final Map.Entry<String, List<cr.a>> entry : this.aqI.oX().entrySet()) {
            for (final cr.a a5 : entry.getValue()) {
                if (!di.n(a5.oS().get(com.google.android.gms.internal.b.dG.toString()))) {
                    e(this.aqQ, entry.getKey()).i(a5);
                }
            }
        }
    }
    
    private bz<d.a> a(final d.a a, final Set<String> set, final dl dl) {
        if (!a.gF) {
            return new bz<d.a>(a, true);
        }
        switch (a.type) {
            default: {
                bh.T("Unknown type: " + a.type);
                return ct.aqH;
            }
            case 2: {
                final d.a g = cr.g(a);
                g.gw = new d.a[a.gw.length];
                for (int i = 0; i < a.gw.length; ++i) {
                    final bz<d.a> a2 = this.a(a.gw[i], set, dl.fh(i));
                    if (a2 == ct.aqH) {
                        return ct.aqH;
                    }
                    g.gw[i] = (d.a)a2.getObject();
                }
                return new bz<d.a>(g, false);
            }
            case 3: {
                final d.a g2 = cr.g(a);
                if (a.gx.length != a.gy.length) {
                    bh.T("Invalid serving value: " + a.toString());
                    return ct.aqH;
                }
                g2.gx = new d.a[a.gx.length];
                g2.gy = new d.a[a.gx.length];
                for (int j = 0; j < a.gx.length; ++j) {
                    final bz<d.a> a3 = this.a(a.gx[j], set, dl.fi(j));
                    final bz<d.a> a4 = this.a(a.gy[j], set, dl.fj(j));
                    if (a3 == ct.aqH || a4 == ct.aqH) {
                        return ct.aqH;
                    }
                    g2.gx[j] = (d.a)a3.getObject();
                    g2.gy[j] = (d.a)a4.getObject();
                }
                return new bz<d.a>(g2, false);
            }
            case 4: {
                if (set.contains(a.gz)) {
                    bh.T("Macro cycle detected.  Current macro reference: " + a.gz + "." + "  Previous macro references: " + set.toString() + ".");
                    return ct.aqH;
                }
                set.add(a.gz);
                final bz<d.a> a5 = dm.a(this.a(a.gz, set, dl.oD()), a.gE);
                set.remove(a.gz);
                return a5;
            }
            case 7: {
                final d.a g3 = cr.g(a);
                g3.gD = new d.a[a.gD.length];
                for (int k = 0; k < a.gD.length; ++k) {
                    final bz<d.a> a6 = this.a(a.gD[k], set, dl.fk(k));
                    if (a6 == ct.aqH) {
                        return ct.aqH;
                    }
                    g3.gD[k] = (d.a)a6.getObject();
                }
                return new bz<d.a>(g3, false);
            }
        }
    }
    
    private bz<d.a> a(final String s, final Set<String> set, final bk bk) {
        ++this.aqS;
        final b b = this.aqO.get(s);
        if (b != null && !this.aqJ.oo()) {
            this.a(b.oT(), set);
            --this.aqS;
            return b.pn();
        }
        final c c = this.aqQ.get(s);
        if (c == null) {
            bh.T(this.pm() + "Invalid macro: " + s);
            --this.aqS;
            return ct.aqH;
        }
        final bz<Set<cr.a>> a = this.a(s, c.po(), c.pp(), c.pq(), c.ps(), c.pr(), set, bk.of());
        cr.a pt;
        if (a.getObject().isEmpty()) {
            pt = c.pt();
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
        final bz<d.a> a2 = this.a(this.aqM, pt, set, bk.ou());
        final boolean b2 = a.oE() && a2.oE();
        bz<d.a> aqH;
        if (a2 == ct.aqH) {
            aqH = ct.aqH;
        }
        else {
            aqH = new bz<d.a>(a2.getObject(), b2);
        }
        final d.a ot = pt.oT();
        if (aqH.oE()) {
            this.aqO.e(s, new b(aqH, ot));
        }
        this.a(ot, set);
        --this.aqS;
        return aqH;
    }
    
    private bz<d.a> a(final Map<String, aj> map, final cr.a a, final Set<String> set, final ck ck) {
        boolean b = true;
        final d.a a2 = a.oS().get(com.google.android.gms.internal.b.cU.toString());
        bz<d.a> aqH;
        if (a2 == null) {
            bh.T("No function id in properties");
            aqH = ct.aqH;
        }
        else {
            final String ga = a2.gA;
            final aj aj = map.get(ga);
            if (aj == null) {
                bh.T(ga + " has no backing implementation.");
                return ct.aqH;
            }
            aqH = this.aqN.get(a);
            if (aqH == null || this.aqJ.oo()) {
                final HashMap<String, Object> hashMap = new HashMap<String, Object>();
                final Iterator<Map.Entry<String, d.a>> iterator = a.oS().entrySet().iterator();
                boolean b2 = true;
                while (iterator.hasNext()) {
                    final Map.Entry<String, d.a> entry = iterator.next();
                    final bz<d.a> a3 = this.a(entry.getValue(), set, ck.cE(entry.getKey()).e(entry.getValue()));
                    if (a3 == ct.aqH) {
                        return ct.aqH;
                    }
                    if (a3.oE()) {
                        a.a(entry.getKey(), a3.getObject());
                    }
                    else {
                        b2 = false;
                    }
                    hashMap.put(entry.getKey(), a3.getObject());
                }
                if (!aj.a(hashMap.keySet())) {
                    bh.T("Incorrect keys for function " + ga + " required " + aj.oq() + " had " + hashMap.keySet());
                    return ct.aqH;
                }
                if (!b2 || !aj.nL()) {
                    b = false;
                }
                final bz bz = new bz<d.a>(aj.C((Map<String, d.a>)hashMap), b);
                if (b) {
                    this.aqN.e(a, (bz<d.a>)bz);
                }
                ck.d(bz.getObject());
                return (bz<d.a>)bz;
            }
        }
        return aqH;
    }
    
    private bz<Set<cr.a>> a(final Set<cr.e> set, final Set<String> set2, final a a, final cs cs) {
        final HashSet<cr.a> set3 = new HashSet<cr.a>();
        final HashSet<cr.a> set4 = new HashSet<cr.a>();
        final Iterator<cr.e> iterator = set.iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final cr.e e = iterator.next();
            final cn oc = cs.oC();
            final bz<Boolean> a2 = this.a(e, set2, oc);
            if (a2.getObject()) {
                a.a(e, set3, set4, oc);
            }
            b = (b && a2.oE());
        }
        set3.removeAll(set4);
        cs.b(set3);
        return new bz<Set<cr.a>>(set3, b);
    }
    
    private void a(final d.a a, final Set<String> set) {
        if (a != null) {
            final bz<d.a> a2 = this.a(a, set, new bx());
            if (a2 != ct.aqH) {
                final Object o = di.o(a2.getObject());
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
    
    private static void a(final List<cr.a> list, final List<String> list2, final String s) {
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
    
    private static c e(final Map<String, c> map, final String s) {
        c c;
        if ((c = map.get(s)) == null) {
            c = new c();
            map.put(s, c);
        }
        return c;
    }
    
    private static String h(final cr.a a) {
        return di.j(a.oS().get(com.google.android.gms.internal.b.df.toString()));
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
    
    bz<Boolean> a(final cr.a a, final Set<String> set, final ck ck) {
        final bz<d.a> a2 = this.a(this.aqL, a, set, ck);
        final Boolean n = di.n(a2.getObject());
        ck.d(di.u(n));
        return new bz<Boolean>(n, a2.oE());
    }
    
    bz<Boolean> a(final cr.e e, final Set<String> set, final cn cn) {
        final Iterator<cr.a> iterator = e.pb().iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final bz<Boolean> a = this.a(iterator.next(), set, cn.ow());
            if (a.getObject()) {
                cn.f(di.u(false));
                return new bz<Boolean>(false, a.oE());
            }
            b = (b && a.oE());
        }
        final Iterator<cr.a> iterator2 = e.pa().iterator();
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
    
    bz<Set<cr.a>> a(final String s, final Set<cr.e> set, final Map<cr.e, List<cr.a>> map, final Map<cr.e, List<String>> map2, final Map<cr.e, List<cr.a>> map3, final Map<cr.e, List<String>> map4, final Set<String> set2, final cs cs) {
        return this.a(set, set2, (a)new a() {
            @Override
            public void a(final cr.e e, final Set<cr.a> set, final Set<cr.a> set2, final cn cn) {
                final List<? extends cr.a> list = map.get(e);
                final List<String> list2 = map2.get(e);
                if (list != null) {
                    set.addAll(list);
                    cn.oy().c((List<cr.a>)list, list2);
                }
                final List<? extends cr.a> list3 = map3.get(e);
                final List<String> list4 = map4.get(e);
                if (list3 != null) {
                    set2.addAll(list3);
                    cn.oz().c((List<cr.a>)list3, list4);
                }
            }
        }, cs);
    }
    
    bz<Set<cr.a>> a(final Set<cr.e> set, final cs cs) {
        return this.a(set, new HashSet<String>(), (a)new a() {
            @Override
            public void a(final cr.e e, final Set<cr.a> set, final Set<cr.a> set2, final cn cn) {
                set.addAll(e.pc());
                set2.addAll(e.pd());
                cn.oA().c(e.pc(), e.ph());
                cn.oB().c(e.pd(), e.pi());
            }
        }, cs);
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
    
    public bz<d.a> cO(final String s) {
        this.aqS = 0;
        final af cx = this.aqJ.cx(s);
        final bz<d.a> a = this.a(s, new HashSet<String>(), cx.ol());
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
            final Iterator<cr.a> iterator = this.a(this.aqP, om.of()).getObject().iterator();
            while (iterator.hasNext()) {
                this.a(this.aqK, iterator.next(), new HashSet<String>(), om.oe());
            }
        }
        final af af;
        af.on();
        this.cP(null);
    }
    // monitorexit(this)
    
    public void k(final List<com.google.android.gms.internal.c.i> list) {
        while (true) {
            while (true) {
                com.google.android.gms.internal.c.i i = null;
                Label_0079: {
                    synchronized (this) {
                        final Iterator<com.google.android.gms.internal.c.i> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            i = iterator.next();
                            if (i.name != null && i.name.startsWith("gaExperiment:")) {
                                break Label_0079;
                            }
                            bh.V("Ignored supplemental: " + i);
                        }
                        break;
                    }
                }
                ai.a(this.anS, i);
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
    
    interface a
    {
        void a(final cr.e p0, final Set<cr.a> p1, final Set<cr.a> p2, final cn p3);
    }
    
    private static class b
    {
        private bz<d.a> aqY;
        private d.a aqt;
        
        public b(final bz<d.a> aqY, final d.a aqt) {
            this.aqY = aqY;
            this.aqt = aqt;
        }
        
        public int getSize() {
            final int qf = this.aqY.getObject().qF();
            int qf2;
            if (this.aqt == null) {
                qf2 = 0;
            }
            else {
                qf2 = this.aqt.qF();
            }
            return qf2 + qf;
        }
        
        public d.a oT() {
            return this.aqt;
        }
        
        public bz<d.a> pn() {
            return this.aqY;
        }
    }
    
    private static class c
    {
        private final Set<cr.e> aqP;
        private final Map<cr.e, List<cr.a>> aqZ;
        private final Map<cr.e, List<cr.a>> ara;
        private final Map<cr.e, List<String>> arb;
        private final Map<cr.e, List<String>> arc;
        private cr.a ard;
        
        public c() {
            this.aqP = new HashSet<cr.e>();
            this.aqZ = new HashMap<cr.e, List<cr.a>>();
            this.arb = new HashMap<cr.e, List<String>>();
            this.ara = new HashMap<cr.e, List<cr.a>>();
            this.arc = new HashMap<cr.e, List<String>>();
        }
        
        public void a(final cr.e e, final cr.a a) {
            List<cr.a> list;
            if ((list = this.aqZ.get(e)) == null) {
                list = new ArrayList<cr.a>();
                this.aqZ.put(e, list);
            }
            list.add(a);
        }
        
        public void a(final cr.e e, final String s) {
            List<String> list;
            if ((list = this.arb.get(e)) == null) {
                list = new ArrayList<String>();
                this.arb.put(e, list);
            }
            list.add(s);
        }
        
        public void b(final cr.e e) {
            this.aqP.add(e);
        }
        
        public void b(final cr.e e, final cr.a a) {
            List<cr.a> list;
            if ((list = this.ara.get(e)) == null) {
                list = new ArrayList<cr.a>();
                this.ara.put(e, list);
            }
            list.add(a);
        }
        
        public void b(final cr.e e, final String s) {
            List<String> list;
            if ((list = this.arc.get(e)) == null) {
                list = new ArrayList<String>();
                this.arc.put(e, list);
            }
            list.add(s);
        }
        
        public void i(final cr.a ard) {
            this.ard = ard;
        }
        
        public Set<cr.e> po() {
            return this.aqP;
        }
        
        public Map<cr.e, List<cr.a>> pp() {
            return this.aqZ;
        }
        
        public Map<cr.e, List<String>> pq() {
            return this.arb;
        }
        
        public Map<cr.e, List<String>> pr() {
            return this.arc;
        }
        
        public Map<cr.e, List<cr.a>> ps() {
            return this.ara;
        }
        
        public cr.a pt() {
            return this.ard;
        }
    }
}
