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

class cr
{
    private static com.google.android.gms.internal.d.a a(final int n, final com.google.android.gms.internal.c.f f, final com.google.android.gms.internal.d.a[] array, final Set<Integer> set) throws g {
        final int n2 = 0;
        final int n3 = 0;
        int n4 = 0;
        if (set.contains(n)) {
            cI("Value cycle detected.  Current value reference: " + n + "." + "  Previous value references: " + set + ".");
        }
        final com.google.android.gms.internal.d.a a = a(f.fG, n, "values");
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
                    g2.gw = new com.google.android.gms.internal.d.a[h.gh.length];
                    final int[] gh = h.gh;
                    final int length = gh.length;
                    int n5 = 0;
                    while (true) {
                        g = g2;
                        if (n4 >= length) {
                            break Label_0152;
                        }
                        g2.gw[n5] = a(gh[n4], f, array, set);
                        ++n4;
                        ++n5;
                    }
                    break;
                }
                case 3: {
                    final com.google.android.gms.internal.d.a g3 = g(a);
                    final com.google.android.gms.internal.c.h h2 = h(a);
                    if (h2.gi.length != h2.gj.length) {
                        cI("Uneven map keys (" + h2.gi.length + ") and map values (" + h2.gj.length + ")");
                    }
                    g3.gx = new com.google.android.gms.internal.d.a[h2.gi.length];
                    g3.gy = new com.google.android.gms.internal.d.a[h2.gi.length];
                    final int[] gi = h2.gi;
                    for (int length2 = gi.length, i = 0, n6 = 0; i < length2; ++i, ++n6) {
                        g3.gx[n6] = a(gi[i], f, array, set);
                    }
                    final int[] gj = h2.gj;
                    final int length3 = gj.length;
                    int n7 = 0;
                    int n8 = n2;
                    while (true) {
                        g = g3;
                        if (n8 >= length3) {
                            break Label_0152;
                        }
                        g3.gy[n7] = a(gj[n8], f, array, set);
                        ++n8;
                        ++n7;
                    }
                    break;
                }
                case 4: {
                    g = g(a);
                    g.gz = di.j(a(h(a).gm, f, array, set));
                    break;
                }
                case 7: {
                    final com.google.android.gms.internal.d.a g4 = g(a);
                    final com.google.android.gms.internal.c.h h3 = h(a);
                    g4.gD = new com.google.android.gms.internal.d.a[h3.gl.length];
                    final int[] gl = h3.gl;
                    final int length4 = gl.length;
                    int n9 = 0;
                    int n10 = n3;
                    while (true) {
                        g = g4;
                        if (n10 >= length4) {
                            break Label_0152;
                        }
                        g4.gD[n9] = a(gl[n10], f, array, set);
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
            cI("Invalid value: " + a);
        }
        array[n] = g;
        set.remove(n);
        return g;
    }
    
    private static a a(final com.google.android.gms.internal.c.b b, final com.google.android.gms.internal.c.f f, final com.google.android.gms.internal.d.a[] array, int i) throws g {
        final b or = a.oR();
        final int[] fq = b.fq;
        int length;
        com.google.android.gms.internal.c.e e;
        String s;
        com.google.android.gms.internal.d.a a;
        for (length = fq.length, i = 0; i < length; ++i) {
            e = a(f.fH, Integer.valueOf(fq[i]), "properties");
            s = a(f.fF, e.key, "keys");
            a = a(array, e.value, "values");
            if (b.ec.toString().equals(s)) {
                or.i(a);
            }
            else {
                or.b(s, a);
            }
        }
        return or.oU();
    }
    
    private static e a(final com.google.android.gms.internal.c.g g, final List<a> list, final List<a> list2, final List<a> list3, final com.google.android.gms.internal.c.f f) {
        final f oz = e.oZ();
        final int[] fv = g.fV;
        for (int length = fv.length, i = 0; i < length; ++i) {
            oz.b((a)list3.get(Integer.valueOf(fv[i])));
        }
        final int[] fw = g.fW;
        for (int length2 = fw.length, j = 0; j < length2; ++j) {
            oz.c((a)list3.get(Integer.valueOf(fw[j])));
        }
        final int[] fx = g.fX;
        for (int length3 = fx.length, k = 0; k < length3; ++k) {
            oz.d((a)list.get(Integer.valueOf(fx[k])));
        }
        final int[] fz = g.fZ;
        for (int length4 = fz.length, l = 0; l < length4; ++l) {
            oz.cK(f.fG[Integer.valueOf(fz[l])].gv);
        }
        final int[] fy = g.fY;
        for (int length5 = fy.length, n = 0; n < length5; ++n) {
            oz.e((a)list.get(Integer.valueOf(fy[n])));
        }
        final int[] ga = g.ga;
        for (int length6 = ga.length, n2 = 0; n2 < length6; ++n2) {
            oz.cL(f.fG[Integer.valueOf(ga[n2])].gv);
        }
        final int[] gb = g.gb;
        for (int length7 = gb.length, n3 = 0; n3 < length7; ++n3) {
            oz.f((a)list2.get(Integer.valueOf(gb[n3])));
        }
        final int[] gd = g.gd;
        for (int length8 = gd.length, n4 = 0; n4 < length8; ++n4) {
            oz.cM(f.fG[Integer.valueOf(gd[n4])].gv);
        }
        final int[] gc = g.gc;
        for (int length9 = gc.length, n5 = 0; n5 < length9; ++n5) {
            oz.g((a)list2.get(Integer.valueOf(gc[n5])));
        }
        final int[] ge = g.ge;
        for (int length10 = ge.length, n6 = 0; n6 < length10; ++n6) {
            oz.cN(f.fG[Integer.valueOf(ge[n6])].gv);
        }
        return oz.pk();
    }
    
    private static <T> T a(final T[] array, final int n, final String s) throws g {
        if (n < 0 || n >= array.length) {
            cI("Index out of bounds detected: " + n + " in " + s);
        }
        return array[n];
    }
    
    public static c b(final com.google.android.gms.internal.c.f f) throws g {
        final int n = 0;
        final com.google.android.gms.internal.d.a[] array = new com.google.android.gms.internal.d.a[f.fG.length];
        for (int i = 0; i < f.fG.length; ++i) {
            a(i, f, array, new HashSet<Integer>(0));
        }
        final d ov = c.oV();
        final ArrayList<a> list = new ArrayList<a>();
        for (int j = 0; j < f.fJ.length; ++j) {
            list.add(a(f.fJ[j], f, array, j));
        }
        final ArrayList<a> list2 = new ArrayList<a>();
        for (int k = 0; k < f.fK.length; ++k) {
            list2.add(a(f.fK[k], f, array, k));
        }
        final ArrayList<a> list3 = new ArrayList<a>();
        for (int l = 0; l < f.fI.length; ++l) {
            final a a = a(f.fI[l], f, array, l);
            ov.a(a);
            list3.add(a);
        }
        final com.google.android.gms.internal.c.g[] fl = f.fL;
        for (int length = fl.length, n2 = n; n2 < length; ++n2) {
            ov.a(a(fl[n2], list, list3, list2, f));
        }
        ov.cJ(f.version);
        ov.fl(f.fT);
        return ov.oY();
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
    
    private static void cI(final String s) throws g {
        bh.T(s);
        throw new g(s);
    }
    
    public static com.google.android.gms.internal.d.a g(final com.google.android.gms.internal.d.a a) {
        final com.google.android.gms.internal.d.a a2 = new com.google.android.gms.internal.d.a();
        a2.type = a.type;
        a2.gE = a.gE.clone();
        if (a.gF) {
            a2.gF = a.gF;
        }
        return a2;
    }
    
    private static com.google.android.gms.internal.c.h h(final com.google.android.gms.internal.d.a a) throws g {
        if (a.a(com.google.android.gms.internal.c.h.gf) == null) {
            cI("Expected a ServingValue and didn't get one. Value is: " + a);
        }
        return a.a(com.google.android.gms.internal.c.h.gf);
    }
    
    public static class a
    {
        private final Map<String, com.google.android.gms.internal.d.a> aqs;
        private final com.google.android.gms.internal.d.a aqt;
        
        private a(final Map<String, com.google.android.gms.internal.d.a> aqs, final com.google.android.gms.internal.d.a aqt) {
            this.aqs = aqs;
            this.aqt = aqt;
        }
        
        public static b oR() {
            return new b();
        }
        
        public void a(final String s, final com.google.android.gms.internal.d.a a) {
            this.aqs.put(s, a);
        }
        
        public Map<String, com.google.android.gms.internal.d.a> oS() {
            return Collections.unmodifiableMap((Map<? extends String, ? extends com.google.android.gms.internal.d.a>)this.aqs);
        }
        
        public com.google.android.gms.internal.d.a oT() {
            return this.aqt;
        }
        
        @Override
        public String toString() {
            return "Properties: " + this.oS() + " pushAfterEvaluate: " + this.aqt;
        }
    }
    
    public static class b
    {
        private final Map<String, com.google.android.gms.internal.d.a> aqs;
        private com.google.android.gms.internal.d.a aqt;
        
        private b() {
            this.aqs = new HashMap<String, com.google.android.gms.internal.d.a>();
        }
        
        public b b(final String s, final com.google.android.gms.internal.d.a a) {
            this.aqs.put(s, a);
            return this;
        }
        
        public b i(final com.google.android.gms.internal.d.a aqt) {
            this.aqt = aqt;
            return this;
        }
        
        public a oU() {
            return new a((Map)this.aqs, this.aqt);
        }
    }
    
    public static class c
    {
        private final String Sq;
        private final List<e> aqu;
        private final Map<String, List<a>> aqv;
        private final int aqw;
        
        private c(final List<e> list, final Map<String, List<a>> map, final String sq, final int aqw) {
            this.aqu = Collections.unmodifiableList((List<? extends e>)list);
            this.aqv = Collections.unmodifiableMap((Map<? extends String, ? extends List<a>>)map);
            this.Sq = sq;
            this.aqw = aqw;
        }
        
        public static d oV() {
            return new d();
        }
        
        public String getVersion() {
            return this.Sq;
        }
        
        public List<e> oW() {
            return this.aqu;
        }
        
        public Map<String, List<a>> oX() {
            return this.aqv;
        }
        
        @Override
        public String toString() {
            return "Rules: " + this.oW() + "  Macros: " + this.aqv;
        }
    }
    
    public static class d
    {
        private String Sq;
        private final List<e> aqu;
        private final Map<String, List<a>> aqv;
        private int aqw;
        
        private d() {
            this.aqu = new ArrayList<e>();
            this.aqv = new HashMap<String, List<a>>();
            this.Sq = "";
            this.aqw = 0;
        }
        
        public d a(final a a) {
            final String j = di.j(a.oS().get(com.google.android.gms.internal.b.df.toString()));
            List<a> list;
            if ((list = this.aqv.get(j)) == null) {
                list = new ArrayList<a>();
                this.aqv.put(j, list);
            }
            list.add(a);
            return this;
        }
        
        public d a(final e e) {
            this.aqu.add(e);
            return this;
        }
        
        public d cJ(final String sq) {
            this.Sq = sq;
            return this;
        }
        
        public d fl(final int aqw) {
            this.aqw = aqw;
            return this;
        }
        
        public c oY() {
            return new c((List)this.aqu, (Map)this.aqv, this.Sq, this.aqw);
        }
    }
    
    public static class e
    {
        private final List<a> aqA;
        private final List<a> aqB;
        private final List<a> aqC;
        private final List<String> aqD;
        private final List<String> aqE;
        private final List<String> aqF;
        private final List<String> aqG;
        private final List<a> aqx;
        private final List<a> aqy;
        private final List<a> aqz;
        
        private e(final List<a> list, final List<a> list2, final List<a> list3, final List<a> list4, final List<a> list5, final List<a> list6, final List<String> list7, final List<String> list8, final List<String> list9, final List<String> list10) {
            this.aqx = Collections.unmodifiableList((List<? extends a>)list);
            this.aqy = Collections.unmodifiableList((List<? extends a>)list2);
            this.aqz = Collections.unmodifiableList((List<? extends a>)list3);
            this.aqA = Collections.unmodifiableList((List<? extends a>)list4);
            this.aqB = Collections.unmodifiableList((List<? extends a>)list5);
            this.aqC = Collections.unmodifiableList((List<? extends a>)list6);
            this.aqD = Collections.unmodifiableList((List<? extends String>)list7);
            this.aqE = Collections.unmodifiableList((List<? extends String>)list8);
            this.aqF = Collections.unmodifiableList((List<? extends String>)list9);
            this.aqG = Collections.unmodifiableList((List<? extends String>)list10);
        }
        
        public static f oZ() {
            return new f();
        }
        
        public List<a> pa() {
            return this.aqx;
        }
        
        public List<a> pb() {
            return this.aqy;
        }
        
        public List<a> pc() {
            return this.aqz;
        }
        
        public List<a> pd() {
            return this.aqA;
        }
        
        public List<a> pe() {
            return this.aqB;
        }
        
        public List<String> pf() {
            return this.aqD;
        }
        
        public List<String> pg() {
            return this.aqE;
        }
        
        public List<String> ph() {
            return this.aqF;
        }
        
        public List<String> pi() {
            return this.aqG;
        }
        
        public List<a> pj() {
            return this.aqC;
        }
        
        @Override
        public String toString() {
            return "Positive predicates: " + this.pa() + "  Negative predicates: " + this.pb() + "  Add tags: " + this.pc() + "  Remove tags: " + this.pd() + "  Add macros: " + this.pe() + "  Remove macros: " + this.pj();
        }
    }
    
    public static class f
    {
        private final List<a> aqA;
        private final List<a> aqB;
        private final List<a> aqC;
        private final List<String> aqD;
        private final List<String> aqE;
        private final List<String> aqF;
        private final List<String> aqG;
        private final List<a> aqx;
        private final List<a> aqy;
        private final List<a> aqz;
        
        private f() {
            this.aqx = new ArrayList<a>();
            this.aqy = new ArrayList<a>();
            this.aqz = new ArrayList<a>();
            this.aqA = new ArrayList<a>();
            this.aqB = new ArrayList<a>();
            this.aqC = new ArrayList<a>();
            this.aqD = new ArrayList<String>();
            this.aqE = new ArrayList<String>();
            this.aqF = new ArrayList<String>();
            this.aqG = new ArrayList<String>();
        }
        
        public f b(final a a) {
            this.aqx.add(a);
            return this;
        }
        
        public f c(final a a) {
            this.aqy.add(a);
            return this;
        }
        
        public f cK(final String s) {
            this.aqF.add(s);
            return this;
        }
        
        public f cL(final String s) {
            this.aqG.add(s);
            return this;
        }
        
        public f cM(final String s) {
            this.aqD.add(s);
            return this;
        }
        
        public f cN(final String s) {
            this.aqE.add(s);
            return this;
        }
        
        public f d(final a a) {
            this.aqz.add(a);
            return this;
        }
        
        public f e(final a a) {
            this.aqA.add(a);
            return this;
        }
        
        public f f(final a a) {
            this.aqB.add(a);
            return this;
        }
        
        public f g(final a a) {
            this.aqC.add(a);
            return this;
        }
        
        public e pk() {
            return new e((List)this.aqx, (List)this.aqy, (List)this.aqz, (List)this.aqA, (List)this.aqB, (List)this.aqC, (List)this.aqD, (List)this.aqE, (List)this.aqF, (List)this.aqG);
        }
    }
    
    public static class g extends Exception
    {
        public g(final String s) {
            super(s);
        }
    }
}
