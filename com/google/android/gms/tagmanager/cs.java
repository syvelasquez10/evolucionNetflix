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

class cs
{
    private static final by<d.a> ZE;
    private final DataLayer WK;
    private final cq.c ZF;
    private final ag ZG;
    private final Map<String, aj> ZH;
    private final Map<String, aj> ZI;
    private final Map<String, aj> ZJ;
    private final k<cq.a, by<d.a>> ZK;
    private final k<String, b> ZL;
    private final Set<cq.e> ZM;
    private final Map<String, c> ZN;
    private volatile String ZO;
    private int ZP;
    
    static {
        ZE = new by<d.a>(dh.lT(), true);
    }
    
    public cs(final Context context, final cq.c zf, final DataLayer wk, final s.a a, final s.a a2, final ag zg) {
        if (zf == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.ZF = zf;
        this.ZM = new HashSet<cq.e>(zf.li());
        this.WK = wk;
        this.ZG = zg;
        this.ZK = new l<cq.a, by<d.a>>().a(1048576, (l.a<cq.a, by<d.a>>)new l.a<cq.a, by<d.a>>() {
            public int a(final cq.a a, final by<d.a> by) {
                return by.getObject().mF();
            }
        });
        this.ZL = new l<String, b>().a(1048576, (l.a<String, b>)new l.a<String, b>() {
            public int a(final String s, final b b) {
                return s.length() + b.getSize();
            }
        });
        this.ZH = new HashMap<String, aj>();
        this.b(new i(context));
        this.b(new s(a2));
        this.b(new w(wk));
        this.b(new di(context, wk));
        this.ZI = new HashMap<String, aj>();
        this.c(new q());
        this.c(new ad());
        this.c(new ae());
        this.c(new al());
        this.c(new am());
        this.c(new bd());
        this.c(new be());
        this.c(new ch());
        this.c(new db());
        this.ZJ = new HashMap<String, aj>();
        this.a(new com.google.android.gms.tagmanager.b(context));
        this.a(new com.google.android.gms.tagmanager.c(context));
        this.a(new e(context));
        this.a(new f(context));
        this.a(new g(context));
        this.a(new h(context));
        this.a(new m());
        this.a(new p(this.ZF.getVersion()));
        this.a(new s(a));
        this.a(new u(wk));
        this.a(new z(context));
        this.a(new aa());
        this.a(new ac());
        this.a(new ah(this));
        this.a(new an());
        this.a(new ao());
        this.a(new ax(context));
        this.a(new az());
        this.a(new bc());
        this.a(new bk(context));
        this.a(new bz());
        this.a(new cb());
        this.a(new ce());
        this.a(new cg());
        this.a(new ci(context));
        this.a(new ct());
        this.a(new cu());
        this.a(new dd());
        this.ZN = new HashMap<String, c>();
        for (final cq.e e : this.ZM) {
            if (zg.kA()) {
                a(e.lq(), e.lr(), "add macro");
                a(e.lv(), e.ls(), "remove macro");
                a(e.lo(), e.lt(), "add tag");
                a(e.lp(), e.lu(), "remove tag");
            }
            for (int i = 0; i < e.lq().size(); ++i) {
                final cq.a a3 = e.lq().get(i);
                String s = "Unknown";
                if (zg.kA()) {
                    s = s;
                    if (i < e.lr().size()) {
                        s = e.lr().get(i);
                    }
                }
                final c d = d(this.ZN, h(a3));
                d.b(e);
                d.a(e, a3);
                d.a(e, s);
            }
            for (int j = 0; j < e.lv().size(); ++j) {
                final cq.a a4 = e.lv().get(j);
                String s2 = "Unknown";
                if (zg.kA()) {
                    s2 = s2;
                    if (j < e.ls().size()) {
                        s2 = e.ls().get(j);
                    }
                }
                final c d2 = d(this.ZN, h(a4));
                d2.b(e);
                d2.b(e, a4);
                d2.b(e, s2);
            }
        }
        for (final Map.Entry<String, List<cq.a>> entry : this.ZF.lj().entrySet()) {
            for (final cq.a a5 : entry.getValue()) {
                if (!dh.n(a5.le().get(com.google.android.gms.internal.b.dh.toString()))) {
                    d(this.ZN, entry.getKey()).i(a5);
                }
            }
        }
    }
    
    private by<d.a> a(final d.a a, final Set<String> set, final dj dj) {
        if (!a.fX) {
            return new by<d.a>(a, true);
        }
        switch (a.type) {
            default: {
                bh.w("Unknown type: " + a.type);
                return cs.ZE;
            }
            case 2: {
                final d.a g = cq.g(a);
                g.fO = new d.a[a.fO.length];
                for (int i = 0; i < a.fO.length; ++i) {
                    final by<d.a> a2 = this.a(a.fO[i], set, dj.cd(i));
                    if (a2 == cs.ZE) {
                        return cs.ZE;
                    }
                    g.fO[i] = (d.a)a2.getObject();
                }
                return new by<d.a>(g, false);
            }
            case 3: {
                final d.a g2 = cq.g(a);
                if (a.fP.length != a.fQ.length) {
                    bh.w("Invalid serving value: " + a.toString());
                    return cs.ZE;
                }
                g2.fP = new d.a[a.fP.length];
                g2.fQ = new d.a[a.fP.length];
                for (int j = 0; j < a.fP.length; ++j) {
                    final by<d.a> a3 = this.a(a.fP[j], set, dj.ce(j));
                    final by<d.a> a4 = this.a(a.fQ[j], set, dj.cf(j));
                    if (a3 == cs.ZE || a4 == cs.ZE) {
                        return cs.ZE;
                    }
                    g2.fP[j] = (d.a)a3.getObject();
                    g2.fQ[j] = (d.a)a4.getObject();
                }
                return new by<d.a>(g2, false);
            }
            case 4: {
                if (set.contains(a.fR)) {
                    bh.w("Macro cycle detected.  Current macro reference: " + a.fR + "." + "  Previous macro references: " + set.toString() + ".");
                    return cs.ZE;
                }
                set.add(a.fR);
                final by<d.a> a5 = dk.a(this.a(a.fR, set, dj.kP()), a.fW);
                set.remove(a.fR);
                return a5;
            }
            case 7: {
                final d.a g3 = cq.g(a);
                g3.fV = new d.a[a.fV.length];
                for (int k = 0; k < a.fV.length; ++k) {
                    final by<d.a> a6 = this.a(a.fV[k], set, dj.cg(k));
                    if (a6 == cs.ZE) {
                        return cs.ZE;
                    }
                    g3.fV[k] = (d.a)a6.getObject();
                }
                return new by<d.a>(g3, false);
            }
        }
    }
    
    private by<d.a> a(final String s, final Set<String> set, final bj bj) {
        ++this.ZP;
        final b b = this.ZL.get(s);
        if (b != null && !this.ZG.kA()) {
            this.a(b.lf(), set);
            --this.ZP;
            return b.lz();
        }
        final c c = this.ZN.get(s);
        if (c == null) {
            bh.w(this.ly() + "Invalid macro: " + s);
            --this.ZP;
            return cs.ZE;
        }
        final by<Set<cq.a>> a = this.a(s, c.lA(), c.lB(), c.lC(), c.lE(), c.lD(), set, bj.kr());
        cq.a lf;
        if (a.getObject().isEmpty()) {
            lf = c.lF();
        }
        else {
            if (a.getObject().size() > 1) {
                bh.z(this.ly() + "Multiple macros active for macroName " + s);
            }
            lf = a.getObject().iterator().next();
        }
        if (lf == null) {
            --this.ZP;
            return cs.ZE;
        }
        final by<d.a> a2 = this.a(this.ZJ, lf, set, bj.kG());
        final boolean b2 = a.kQ() && a2.kQ();
        by<d.a> ze;
        if (a2 == cs.ZE) {
            ze = cs.ZE;
        }
        else {
            ze = new by<d.a>(a2.getObject(), b2);
        }
        final d.a lf2 = lf.lf();
        if (ze.kQ()) {
            this.ZL.e(s, new b(ze, lf2));
        }
        this.a(lf2, set);
        --this.ZP;
        return ze;
    }
    
    private by<d.a> a(final Map<String, aj> map, final cq.a a, final Set<String> set, final cj cj) {
        boolean b = true;
        final d.a a2 = a.le().get(com.google.android.gms.internal.b.cx.toString());
        by<d.a> ze;
        if (a2 == null) {
            bh.w("No function id in properties");
            ze = cs.ZE;
        }
        else {
            final String fs = a2.fS;
            final aj aj = map.get(fs);
            if (aj == null) {
                bh.w(fs + " has no backing implementation.");
                return cs.ZE;
            }
            ze = this.ZK.get(a);
            if (ze == null || this.ZG.kA()) {
                final HashMap<String, Object> hashMap = new HashMap<String, Object>();
                final Iterator<Map.Entry<String, d.a>> iterator = a.le().entrySet().iterator();
                boolean b2 = true;
                while (iterator.hasNext()) {
                    final Map.Entry<String, d.a> entry = iterator.next();
                    final by<d.a> a3 = this.a(entry.getValue(), set, cj.bH(entry.getKey()).e(entry.getValue()));
                    if (a3 == cs.ZE) {
                        return cs.ZE;
                    }
                    if (a3.kQ()) {
                        a.a(entry.getKey(), a3.getObject());
                    }
                    else {
                        b2 = false;
                    }
                    hashMap.put(entry.getKey(), a3.getObject());
                }
                if (!aj.a(hashMap.keySet())) {
                    bh.w("Incorrect keys for function " + fs + " required " + aj.kC() + " had " + hashMap.keySet());
                    return cs.ZE;
                }
                if (!b2 || !aj.jX()) {
                    b = false;
                }
                final by by = new by<d.a>(aj.x((Map<String, d.a>)hashMap), b);
                if (b) {
                    this.ZK.e(a, (by<d.a>)by);
                }
                cj.d(by.getObject());
                return (by<d.a>)by;
            }
        }
        return ze;
    }
    
    private by<Set<cq.a>> a(final Set<cq.e> set, final Set<String> set2, final a a, final cr cr) {
        final HashSet<cq.a> set3 = new HashSet<cq.a>();
        final HashSet<cq.a> set4 = new HashSet<cq.a>();
        final Iterator<cq.e> iterator = set.iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final cq.e e = iterator.next();
            final cm ko = cr.kO();
            final by<Boolean> a2 = this.a(e, set2, ko);
            if (a2.getObject()) {
                a.a(e, set3, set4, ko);
            }
            b = (b && a2.kQ());
        }
        set3.removeAll(set4);
        cr.b(set3);
        return new by<Set<cq.a>>(set3, b);
    }
    
    private void a(final d.a a, final Set<String> set) {
        if (a != null) {
            final by<d.a> a2 = this.a(a, set, new bw());
            if (a2 != cs.ZE) {
                final Object o = dh.o(a2.getObject());
                if (o instanceof Map) {
                    this.WK.push((Map<String, Object>)o);
                    return;
                }
                if (!(o instanceof List)) {
                    bh.z("pushAfterEvaluate: value not a Map or List");
                    return;
                }
                for (final Map<String, Object> next : (List<Object>)o) {
                    if (next instanceof Map) {
                        this.WK.push(next);
                    }
                    else {
                        bh.z("pushAfterEvaluate: value not a Map");
                    }
                }
            }
        }
    }
    
    private static void a(final List<cq.a> list, final List<String> list2, final String s) {
        if (list.size() != list2.size()) {
            bh.x("Invalid resource: imbalance of rule names of functions for " + s + " operation. Using default rule name instead");
        }
    }
    
    private static void a(final Map<String, aj> map, final aj aj) {
        if (map.containsKey(aj.kB())) {
            throw new IllegalArgumentException("Duplicate function type name: " + aj.kB());
        }
        map.put(aj.kB(), aj);
    }
    
    private static c d(final Map<String, c> map, final String s) {
        c c;
        if ((c = map.get(s)) == null) {
            c = new c();
            map.put(s, c);
        }
        return c;
    }
    
    private static String h(final cq.a a) {
        return dh.j(a.le().get(com.google.android.gms.internal.b.cI.toString()));
    }
    
    private String ly() {
        if (this.ZP <= 1) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.ZP));
        for (int i = 2; i < this.ZP; ++i) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }
    
    by<Boolean> a(final cq.a a, final Set<String> set, final cj cj) {
        final by<d.a> a2 = this.a(this.ZI, a, set, cj);
        final Boolean n = dh.n(a2.getObject());
        cj.d(dh.r(n));
        return new by<Boolean>(n, a2.kQ());
    }
    
    by<Boolean> a(final cq.e e, final Set<String> set, final cm cm) {
        final Iterator<cq.a> iterator = e.ln().iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final by<Boolean> a = this.a(iterator.next(), set, cm.kI());
            if (a.getObject()) {
                cm.f(dh.r(false));
                return new by<Boolean>(false, a.kQ());
            }
            b = (b && a.kQ());
        }
        final Iterator<cq.a> iterator2 = e.lm().iterator();
        while (iterator2.hasNext()) {
            final by<Boolean> a2 = this.a(iterator2.next(), set, cm.kJ());
            if (!a2.getObject()) {
                cm.f(dh.r(false));
                return new by<Boolean>(false, a2.kQ());
            }
            b = (b && a2.kQ());
        }
        cm.f(dh.r(true));
        return new by<Boolean>(true, b);
    }
    
    by<Set<cq.a>> a(final String s, final Set<cq.e> set, final Map<cq.e, List<cq.a>> map, final Map<cq.e, List<String>> map2, final Map<cq.e, List<cq.a>> map3, final Map<cq.e, List<String>> map4, final Set<String> set2, final cr cr) {
        return this.a(set, set2, (a)new a() {
            @Override
            public void a(final cq.e e, final Set<cq.a> set, final Set<cq.a> set2, final cm cm) {
                final List<? extends cq.a> list = map.get(e);
                final List<String> list2 = map2.get(e);
                if (list != null) {
                    set.addAll(list);
                    cm.kK().b((List<cq.a>)list, list2);
                }
                final List<? extends cq.a> list3 = map3.get(e);
                final List<String> list4 = map4.get(e);
                if (list3 != null) {
                    set2.addAll(list3);
                    cm.kL().b((List<cq.a>)list3, list4);
                }
            }
        }, cr);
    }
    
    by<Set<cq.a>> a(final Set<cq.e> set, final cr cr) {
        return this.a(set, new HashSet<String>(), (a)new a() {
            @Override
            public void a(final cq.e e, final Set<cq.a> set, final Set<cq.a> set2, final cm cm) {
                set.addAll(e.lo());
                set2.addAll(e.lp());
                cm.kM().b(e.lo(), e.lt());
                cm.kN().b(e.lp(), e.lu());
            }
        }, cr);
    }
    
    void a(final aj aj) {
        a(this.ZJ, aj);
    }
    
    void b(final aj aj) {
        a(this.ZH, aj);
    }
    
    public by<d.a> bR(final String s) {
        this.ZP = 0;
        final af ba = this.ZG.bA(s);
        final by<d.a> a = this.a(s, new HashSet<String>(), ba.kx());
        ba.kz();
        return a;
    }
    
    void bS(final String zo) {
        synchronized (this) {
            this.ZO = zo;
        }
    }
    
    public void bp(final String s) {
        synchronized (this) {
            this.bS(s);
            final t ky = this.ZG.bB(s).ky();
            final Iterator<cq.a> iterator = this.a(this.ZM, ky.kr()).getObject().iterator();
            while (iterator.hasNext()) {
                this.a(this.ZH, iterator.next(), new HashSet<String>(), ky.kq());
            }
        }
        final af af;
        af.kz();
        this.bS(null);
    }
    // monitorexit(this)
    
    void c(final aj aj) {
        a(this.ZI, aj);
    }
    
    public void e(final List<com.google.android.gms.internal.c.i> list) {
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
                            bh.y("Ignored supplemental: " + i);
                        }
                        break;
                    }
                }
                ai.a(this.WK, i);
                continue;
            }
        }
    }
    // monitorexit(this)
    
    String lx() {
        synchronized (this) {
            return this.ZO;
        }
    }
    
    interface a
    {
        void a(final cq.e p0, final Set<cq.a> p1, final Set<cq.a> p2, final cm p3);
    }
    
    private static class b
    {
        private by<d.a> ZV;
        private d.a Zq;
        
        public b(final by<d.a> zv, final d.a zq) {
            this.ZV = zv;
            this.Zq = zq;
        }
        
        public int getSize() {
            final int mf = this.ZV.getObject().mF();
            int mf2;
            if (this.Zq == null) {
                mf2 = 0;
            }
            else {
                mf2 = this.Zq.mF();
            }
            return mf2 + mf;
        }
        
        public d.a lf() {
            return this.Zq;
        }
        
        public by<d.a> lz() {
            return this.ZV;
        }
    }
    
    private static class c
    {
        private final Set<cq.e> ZM;
        private final Map<cq.e, List<cq.a>> ZW;
        private final Map<cq.e, List<cq.a>> ZX;
        private final Map<cq.e, List<String>> ZY;
        private final Map<cq.e, List<String>> ZZ;
        private cq.a aaa;
        
        public c() {
            this.ZM = new HashSet<cq.e>();
            this.ZW = new HashMap<cq.e, List<cq.a>>();
            this.ZY = new HashMap<cq.e, List<String>>();
            this.ZX = new HashMap<cq.e, List<cq.a>>();
            this.ZZ = new HashMap<cq.e, List<String>>();
        }
        
        public void a(final cq.e e, final cq.a a) {
            List<cq.a> list;
            if ((list = this.ZW.get(e)) == null) {
                list = new ArrayList<cq.a>();
                this.ZW.put(e, list);
            }
            list.add(a);
        }
        
        public void a(final cq.e e, final String s) {
            List<String> list;
            if ((list = this.ZY.get(e)) == null) {
                list = new ArrayList<String>();
                this.ZY.put(e, list);
            }
            list.add(s);
        }
        
        public void b(final cq.e e) {
            this.ZM.add(e);
        }
        
        public void b(final cq.e e, final cq.a a) {
            List<cq.a> list;
            if ((list = this.ZX.get(e)) == null) {
                list = new ArrayList<cq.a>();
                this.ZX.put(e, list);
            }
            list.add(a);
        }
        
        public void b(final cq.e e, final String s) {
            List<String> list;
            if ((list = this.ZZ.get(e)) == null) {
                list = new ArrayList<String>();
                this.ZZ.put(e, list);
            }
            list.add(s);
        }
        
        public void i(final cq.a aaa) {
            this.aaa = aaa;
        }
        
        public Set<cq.e> lA() {
            return this.ZM;
        }
        
        public Map<cq.e, List<cq.a>> lB() {
            return this.ZW;
        }
        
        public Map<cq.e, List<String>> lC() {
            return this.ZY;
        }
        
        public Map<cq.e, List<String>> lD() {
            return this.ZZ;
        }
        
        public Map<cq.e, List<cq.a>> lE() {
            return this.ZX;
        }
        
        public cq.a lF() {
            return this.aaa;
        }
    }
}
