// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.google.android.gms.internal.b;
import java.util.Set;
import com.google.android.gms.internal.d;
import com.google.android.gms.internal.c;

class cq
{
    private static com.google.android.gms.internal.d.a a(final int n, final com.google.android.gms.internal.c.f f, final com.google.android.gms.internal.d.a[] array, final Set<Integer> set) throws g {
        final int n2 = 0;
        final int n3 = 0;
        int n4 = 0;
        if (set.contains(n)) {
            bL("Value cycle detected.  Current value reference: " + n + "." + "  Previous value references: " + set + ".");
        }
        final com.google.android.gms.internal.d.a a = a(f.eX, n, "values");
        if (array[n] != null) {
            return array[n];
        }
        com.google.android.gms.internal.d.a g = null;
        set.add(n);
        Label_0152: {
            switch (a.type) {
                case 2: {
                    final com.google.android.gms.internal.c.h h = h(a);
                    final com.google.android.gms.internal.d.a g2 = g(a);
                    g2.fO = new com.google.android.gms.internal.d.a[h.fz.length];
                    final int[] fz = h.fz;
                    final int length = fz.length;
                    int n5 = 0;
                    while (true) {
                        g = g2;
                        if (n4 >= length) {
                            break Label_0152;
                        }
                        g2.fO[n5] = a(fz[n4], f, array, set);
                        ++n4;
                        ++n5;
                    }
                    break;
                }
                case 3: {
                    final com.google.android.gms.internal.d.a g3 = g(a);
                    final com.google.android.gms.internal.c.h h2 = h(a);
                    if (h2.fA.length != h2.fB.length) {
                        bL("Uneven map keys (" + h2.fA.length + ") and map values (" + h2.fB.length + ")");
                    }
                    g3.fP = new com.google.android.gms.internal.d.a[h2.fA.length];
                    g3.fQ = new com.google.android.gms.internal.d.a[h2.fA.length];
                    final int[] fa = h2.fA;
                    for (int length2 = fa.length, i = 0, n6 = 0; i < length2; ++i, ++n6) {
                        g3.fP[n6] = a(fa[i], f, array, set);
                    }
                    final int[] fb = h2.fB;
                    final int length3 = fb.length;
                    int n7 = 0;
                    int n8 = n2;
                    while (true) {
                        g = g3;
                        if (n8 >= length3) {
                            break Label_0152;
                        }
                        g3.fQ[n7] = a(fb[n8], f, array, set);
                        ++n8;
                        ++n7;
                    }
                    break;
                }
                case 4: {
                    g = g(a);
                    g.fR = dh.j(a(h(a).fE, f, array, set));
                    break;
                }
                case 7: {
                    final com.google.android.gms.internal.d.a g4 = g(a);
                    final com.google.android.gms.internal.c.h h3 = h(a);
                    g4.fV = new com.google.android.gms.internal.d.a[h3.fD.length];
                    final int[] fd = h3.fD;
                    final int length4 = fd.length;
                    int n9 = 0;
                    int n10 = n3;
                    while (true) {
                        g = g4;
                        if (n10 >= length4) {
                            break Label_0152;
                        }
                        g4.fV[n9] = a(fd[n10], f, array, set);
                        ++n10;
                        ++n9;
                    }
                    break;
                }
                case 1:
                case 5:
                case 6:
                case 8: {
                    g = a;
                    break;
                }
            }
        }
        if (g == null) {
            bL("Invalid value: " + a);
        }
        array[n] = g;
        set.remove(n);
        return g;
    }
    
    private static a a(final com.google.android.gms.internal.c.b b, final com.google.android.gms.internal.c.f f, final com.google.android.gms.internal.d.a[] array, int i) throws g {
        final b ld = a.ld();
        final int[] eh = b.eH;
        int length;
        com.google.android.gms.internal.c.e e;
        String s;
        com.google.android.gms.internal.d.a a;
        for (length = eh.length, i = 0; i < length; ++i) {
            e = a(f.eY, Integer.valueOf(eh[i]), "properties");
            s = a(f.eW, e.key, "keys");
            a = a(array, e.value, "values");
            if (b.dB.toString().equals(s)) {
                ld.i(a);
            }
            else {
                ld.b(s, a);
            }
        }
        return ld.lg();
    }
    
    private static e a(final com.google.android.gms.internal.c.g g, final List<a> list, final List<a> list2, final List<a> list3, final com.google.android.gms.internal.c.f f) {
        final f ll = e.ll();
        final int[] fn = g.fn;
        for (int length = fn.length, i = 0; i < length; ++i) {
            ll.b((a)list3.get(Integer.valueOf(fn[i])));
        }
        final int[] fo = g.fo;
        for (int length2 = fo.length, j = 0; j < length2; ++j) {
            ll.c((a)list3.get(Integer.valueOf(fo[j])));
        }
        final int[] fp = g.fp;
        for (int length3 = fp.length, k = 0; k < length3; ++k) {
            ll.d((a)list.get(Integer.valueOf(fp[k])));
        }
        final int[] fr = g.fr;
        for (int length4 = fr.length, l = 0; l < length4; ++l) {
            ll.bN(f.eX[Integer.valueOf(fr[l])].fN);
        }
        final int[] fq = g.fq;
        for (int length5 = fq.length, n = 0; n < length5; ++n) {
            ll.e((a)list.get(Integer.valueOf(fq[n])));
        }
        final int[] fs = g.fs;
        for (int length6 = fs.length, n2 = 0; n2 < length6; ++n2) {
            ll.bO(f.eX[Integer.valueOf(fs[n2])].fN);
        }
        final int[] ft = g.ft;
        for (int length7 = ft.length, n3 = 0; n3 < length7; ++n3) {
            ll.f((a)list2.get(Integer.valueOf(ft[n3])));
        }
        final int[] fv = g.fv;
        for (int length8 = fv.length, n4 = 0; n4 < length8; ++n4) {
            ll.bP(f.eX[Integer.valueOf(fv[n4])].fN);
        }
        final int[] fu = g.fu;
        for (int length9 = fu.length, n5 = 0; n5 < length9; ++n5) {
            ll.g((a)list2.get(Integer.valueOf(fu[n5])));
        }
        final int[] fw = g.fw;
        for (int length10 = fw.length, n6 = 0; n6 < length10; ++n6) {
            ll.bQ(f.eX[Integer.valueOf(fw[n6])].fN);
        }
        return ll.lw();
    }
    
    private static <T> T a(final T[] array, final int n, final String s) throws g {
        if (n < 0 || n >= array.length) {
            bL("Index out of bounds detected: " + n + " in " + s);
        }
        return array[n];
    }
    
    public static c b(final com.google.android.gms.internal.c.f f) throws g {
        final int n = 0;
        final com.google.android.gms.internal.d.a[] array = new com.google.android.gms.internal.d.a[f.eX.length];
        for (int i = 0; i < f.eX.length; ++i) {
            a(i, f, array, new HashSet<Integer>(0));
        }
        final d lh = c.lh();
        final ArrayList<a> list = new ArrayList<a>();
        for (int j = 0; j < f.fa.length; ++j) {
            list.add(a(f.fa[j], f, array, j));
        }
        final ArrayList<a> list2 = new ArrayList<a>();
        for (int k = 0; k < f.fb.length; ++k) {
            list2.add(a(f.fb[k], f, array, k));
        }
        final ArrayList<a> list3 = new ArrayList<a>();
        for (int l = 0; l < f.eZ.length; ++l) {
            final a a = a(f.eZ[l], f, array, l);
            lh.a(a);
            list3.add(a);
        }
        final com.google.android.gms.internal.c.g[] fc = f.fc;
        for (int length = fc.length, n2 = n; n2 < length; ++n2) {
            lh.a(a(fc[n2], list, list3, list2, f));
        }
        lh.bM(f.fg);
        lh.ch(f.fl);
        return lh.lk();
    }
    
    public static void b(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[1024];
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
        }
    }
    
    private static void bL(final String s) throws g {
        bh.w(s);
        throw new g(s);
    }
    
    public static com.google.android.gms.internal.d.a g(final com.google.android.gms.internal.d.a a) {
        final com.google.android.gms.internal.d.a a2 = new com.google.android.gms.internal.d.a();
        a2.type = a.type;
        a2.fW = a.fW.clone();
        if (a.fX) {
            a2.fX = a.fX;
        }
        return a2;
    }
    
    private static com.google.android.gms.internal.c.h h(final com.google.android.gms.internal.d.a a) throws g {
        if (a.a(com.google.android.gms.internal.c.h.fx) == null) {
            bL("Expected a ServingValue and didn't get one. Value is: " + a);
        }
        return a.a(com.google.android.gms.internal.c.h.fx);
    }
    
    public static class a
    {
        private final Map<String, com.google.android.gms.internal.d.a> Zp;
        private final com.google.android.gms.internal.d.a Zq;
        
        private a(final Map<String, com.google.android.gms.internal.d.a> zp, final com.google.android.gms.internal.d.a zq) {
            this.Zp = zp;
            this.Zq = zq;
        }
        
        public static b ld() {
            return new b();
        }
        
        public void a(final String s, final com.google.android.gms.internal.d.a a) {
            this.Zp.put(s, a);
        }
        
        public Map<String, com.google.android.gms.internal.d.a> le() {
            return Collections.unmodifiableMap((Map<? extends String, ? extends com.google.android.gms.internal.d.a>)this.Zp);
        }
        
        public com.google.android.gms.internal.d.a lf() {
            return this.Zq;
        }
        
        @Override
        public String toString() {
            return "Properties: " + this.le() + " pushAfterEvaluate: " + this.Zq;
        }
    }
    
    public static class b
    {
        private final Map<String, com.google.android.gms.internal.d.a> Zp;
        private com.google.android.gms.internal.d.a Zq;
        
        private b() {
            this.Zp = new HashMap<String, com.google.android.gms.internal.d.a>();
        }
        
        public b b(final String s, final com.google.android.gms.internal.d.a a) {
            this.Zp.put(s, a);
            return this;
        }
        
        public b i(final com.google.android.gms.internal.d.a zq) {
            this.Zq = zq;
            return this;
        }
        
        public a lg() {
            return new a((Map)this.Zp, this.Zq);
        }
    }
    
    public static class c
    {
        private final String Xl;
        private final List<e> Zr;
        private final Map<String, List<a>> Zs;
        private final int Zt;
        
        private c(final List<e> list, final Map<String, List<a>> map, final String xl, final int zt) {
            this.Zr = Collections.unmodifiableList((List<? extends e>)list);
            this.Zs = Collections.unmodifiableMap((Map<? extends String, ? extends List<a>>)map);
            this.Xl = xl;
            this.Zt = zt;
        }
        
        public static d lh() {
            return new d();
        }
        
        public String getVersion() {
            return this.Xl;
        }
        
        public List<e> li() {
            return this.Zr;
        }
        
        public Map<String, List<a>> lj() {
            return this.Zs;
        }
        
        @Override
        public String toString() {
            return "Rules: " + this.li() + "  Macros: " + this.Zs;
        }
    }
    
    public static class d
    {
        private String Xl;
        private final List<e> Zr;
        private final Map<String, List<a>> Zs;
        private int Zt;
        
        private d() {
            this.Zr = new ArrayList<e>();
            this.Zs = new HashMap<String, List<a>>();
            this.Xl = "";
            this.Zt = 0;
        }
        
        public d a(final a a) {
            final String j = dh.j(a.le().get(com.google.android.gms.internal.b.cI.toString()));
            List<a> list;
            if ((list = this.Zs.get(j)) == null) {
                list = new ArrayList<a>();
                this.Zs.put(j, list);
            }
            list.add(a);
            return this;
        }
        
        public d a(final e e) {
            this.Zr.add(e);
            return this;
        }
        
        public d bM(final String xl) {
            this.Xl = xl;
            return this;
        }
        
        public d ch(final int zt) {
            this.Zt = zt;
            return this;
        }
        
        public c lk() {
            return new c((List)this.Zr, (Map)this.Zs, this.Xl, this.Zt);
        }
    }
    
    public static class e
    {
        private final List<String> ZA;
        private final List<String> ZB;
        private final List<String> ZC;
        private final List<String> ZD;
        private final List<a> Zu;
        private final List<a> Zv;
        private final List<a> Zw;
        private final List<a> Zx;
        private final List<a> Zy;
        private final List<a> Zz;
        
        private e(final List<a> list, final List<a> list2, final List<a> list3, final List<a> list4, final List<a> list5, final List<a> list6, final List<String> list7, final List<String> list8, final List<String> list9, final List<String> list10) {
            this.Zu = Collections.unmodifiableList((List<? extends a>)list);
            this.Zv = Collections.unmodifiableList((List<? extends a>)list2);
            this.Zw = Collections.unmodifiableList((List<? extends a>)list3);
            this.Zx = Collections.unmodifiableList((List<? extends a>)list4);
            this.Zy = Collections.unmodifiableList((List<? extends a>)list5);
            this.Zz = Collections.unmodifiableList((List<? extends a>)list6);
            this.ZA = Collections.unmodifiableList((List<? extends String>)list7);
            this.ZB = Collections.unmodifiableList((List<? extends String>)list8);
            this.ZC = Collections.unmodifiableList((List<? extends String>)list9);
            this.ZD = Collections.unmodifiableList((List<? extends String>)list10);
        }
        
        public static f ll() {
            return new f();
        }
        
        public List<a> lm() {
            return this.Zu;
        }
        
        public List<a> ln() {
            return this.Zv;
        }
        
        public List<a> lo() {
            return this.Zw;
        }
        
        public List<a> lp() {
            return this.Zx;
        }
        
        public List<a> lq() {
            return this.Zy;
        }
        
        public List<String> lr() {
            return this.ZA;
        }
        
        public List<String> ls() {
            return this.ZB;
        }
        
        public List<String> lt() {
            return this.ZC;
        }
        
        public List<String> lu() {
            return this.ZD;
        }
        
        public List<a> lv() {
            return this.Zz;
        }
        
        @Override
        public String toString() {
            return "Positive predicates: " + this.lm() + "  Negative predicates: " + this.ln() + "  Add tags: " + this.lo() + "  Remove tags: " + this.lp() + "  Add macros: " + this.lq() + "  Remove macros: " + this.lv();
        }
    }
    
    public static class f
    {
        private final List<String> ZA;
        private final List<String> ZB;
        private final List<String> ZC;
        private final List<String> ZD;
        private final List<a> Zu;
        private final List<a> Zv;
        private final List<a> Zw;
        private final List<a> Zx;
        private final List<a> Zy;
        private final List<a> Zz;
        
        private f() {
            this.Zu = new ArrayList<a>();
            this.Zv = new ArrayList<a>();
            this.Zw = new ArrayList<a>();
            this.Zx = new ArrayList<a>();
            this.Zy = new ArrayList<a>();
            this.Zz = new ArrayList<a>();
            this.ZA = new ArrayList<String>();
            this.ZB = new ArrayList<String>();
            this.ZC = new ArrayList<String>();
            this.ZD = new ArrayList<String>();
        }
        
        public f b(final a a) {
            this.Zu.add(a);
            return this;
        }
        
        public f bN(final String s) {
            this.ZC.add(s);
            return this;
        }
        
        public f bO(final String s) {
            this.ZD.add(s);
            return this;
        }
        
        public f bP(final String s) {
            this.ZA.add(s);
            return this;
        }
        
        public f bQ(final String s) {
            this.ZB.add(s);
            return this;
        }
        
        public f c(final a a) {
            this.Zv.add(a);
            return this;
        }
        
        public f d(final a a) {
            this.Zw.add(a);
            return this;
        }
        
        public f e(final a a) {
            this.Zx.add(a);
            return this;
        }
        
        public f f(final a a) {
            this.Zy.add(a);
            return this;
        }
        
        public f g(final a a) {
            this.Zz.add(a);
            return this;
        }
        
        public e lw() {
            return new e((List)this.Zu, (List)this.Zv, (List)this.Zw, (List)this.Zx, (List)this.Zy, (List)this.Zz, (List)this.ZA, (List)this.ZB, (List)this.ZC, (List)this.ZD);
        }
    }
    
    public static class g extends Exception
    {
        public g(final String s) {
            super(s);
        }
    }
}
