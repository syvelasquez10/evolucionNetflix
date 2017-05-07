// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.io.OutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.google.android.gms.internal.c$g;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.c$e;
import com.google.android.gms.internal.c$b;
import com.google.android.gms.internal.c$h;
import java.util.Set;
import com.google.android.gms.internal.d$a;
import com.google.android.gms.internal.c$f;

class cr
{
    private static d$a a(final int n, final c$f c$f, final d$a[] array, final Set<Integer> set) {
        final int n2 = 0;
        final int n3 = 0;
        int n4 = 0;
        if (set.contains(n)) {
            cI("Value cycle detected.  Current value reference: " + n + "." + "  Previous value references: " + set + ".");
        }
        final d$a d$a = a(c$f.fG, n, "values");
        if (array[n] != null) {
            return array[n];
        }
        d$a g = null;
        set.add(n);
        Label_0152: {
            switch (d$a.type) {
                case 2: {
                    final c$h h = h(d$a);
                    final d$a g2 = g(d$a);
                    g2.gw = new d$a[h.gh.length];
                    final int[] gh = h.gh;
                    final int length = gh.length;
                    int n5 = 0;
                    while (true) {
                        g = g2;
                        if (n4 >= length) {
                            break Label_0152;
                        }
                        g2.gw[n5] = a(gh[n4], c$f, array, set);
                        ++n4;
                        ++n5;
                    }
                    break;
                }
                case 3: {
                    final d$a g3 = g(d$a);
                    final c$h h2 = h(d$a);
                    if (h2.gi.length != h2.gj.length) {
                        cI("Uneven map keys (" + h2.gi.length + ") and map values (" + h2.gj.length + ")");
                    }
                    g3.gx = new d$a[h2.gi.length];
                    g3.gy = new d$a[h2.gi.length];
                    final int[] gi = h2.gi;
                    for (int length2 = gi.length, i = 0, n6 = 0; i < length2; ++i, ++n6) {
                        g3.gx[n6] = a(gi[i], c$f, array, set);
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
                        g3.gy[n7] = a(gj[n8], c$f, array, set);
                        ++n8;
                        ++n7;
                    }
                    break;
                }
                case 4: {
                    g = g(d$a);
                    g.gz = di.j(a(h(d$a).gm, c$f, array, set));
                    break;
                }
                case 7: {
                    final d$a g4 = g(d$a);
                    final c$h h3 = h(d$a);
                    g4.gD = new d$a[h3.gl.length];
                    final int[] gl = h3.gl;
                    final int length4 = gl.length;
                    int n9 = 0;
                    int n10 = n3;
                    while (true) {
                        g = g4;
                        if (n10 >= length4) {
                            break Label_0152;
                        }
                        g4.gD[n9] = a(gl[n10], c$f, array, set);
                        ++n10;
                        ++n9;
                    }
                    break;
                }
                case 1:
                case 5:
                case 6:
                case 8: {
                    g = d$a;
                    break;
                }
            }
        }
        if (g == null) {
            cI("Invalid value: " + d$a);
        }
        array[n] = g;
        set.remove(n);
        return g;
    }
    
    private static cr$a a(final c$b c$b, final c$f c$f, final d$a[] array, int i) {
        final cr$b or = cr$a.oR();
        final int[] fq = c$b.fq;
        int length;
        c$e c$e;
        String s;
        d$a d$a;
        for (length = fq.length, i = 0; i < length; ++i) {
            c$e = a(c$f.fH, Integer.valueOf(fq[i]), "properties");
            s = a(c$f.fF, c$e.key, "keys");
            d$a = a(array, c$e.value, "values");
            if (b.ec.toString().equals(s)) {
                or.i(d$a);
            }
            else {
                or.b(s, d$a);
            }
        }
        return or.oU();
    }
    
    private static cr$e a(final c$g c$g, final List<cr$a> list, final List<cr$a> list2, final List<cr$a> list3, final c$f c$f) {
        final cr$f oz = cr$e.oZ();
        final int[] fv = c$g.fV;
        for (int length = fv.length, i = 0; i < length; ++i) {
            oz.b(list3.get(Integer.valueOf(fv[i])));
        }
        final int[] fw = c$g.fW;
        for (int length2 = fw.length, j = 0; j < length2; ++j) {
            oz.c(list3.get(Integer.valueOf(fw[j])));
        }
        final int[] fx = c$g.fX;
        for (int length3 = fx.length, k = 0; k < length3; ++k) {
            oz.d(list.get(Integer.valueOf(fx[k])));
        }
        final int[] fz = c$g.fZ;
        for (int length4 = fz.length, l = 0; l < length4; ++l) {
            oz.cK(c$f.fG[Integer.valueOf(fz[l])].gv);
        }
        final int[] fy = c$g.fY;
        for (int length5 = fy.length, n = 0; n < length5; ++n) {
            oz.e(list.get(Integer.valueOf(fy[n])));
        }
        final int[] ga = c$g.ga;
        for (int length6 = ga.length, n2 = 0; n2 < length6; ++n2) {
            oz.cL(c$f.fG[Integer.valueOf(ga[n2])].gv);
        }
        final int[] gb = c$g.gb;
        for (int length7 = gb.length, n3 = 0; n3 < length7; ++n3) {
            oz.f(list2.get(Integer.valueOf(gb[n3])));
        }
        final int[] gd = c$g.gd;
        for (int length8 = gd.length, n4 = 0; n4 < length8; ++n4) {
            oz.cM(c$f.fG[Integer.valueOf(gd[n4])].gv);
        }
        final int[] gc = c$g.gc;
        for (int length9 = gc.length, n5 = 0; n5 < length9; ++n5) {
            oz.g(list2.get(Integer.valueOf(gc[n5])));
        }
        final int[] ge = c$g.ge;
        for (int length10 = ge.length, n6 = 0; n6 < length10; ++n6) {
            oz.cN(c$f.fG[Integer.valueOf(ge[n6])].gv);
        }
        return oz.pk();
    }
    
    private static <T> T a(final T[] array, final int n, final String s) {
        if (n < 0 || n >= array.length) {
            cI("Index out of bounds detected: " + n + " in " + s);
        }
        return array[n];
    }
    
    public static cr$c b(final c$f c$f) {
        final int n = 0;
        final d$a[] array = new d$a[c$f.fG.length];
        for (int i = 0; i < c$f.fG.length; ++i) {
            a(i, c$f, array, new HashSet<Integer>(0));
        }
        final cr$d ov = cr$c.oV();
        final ArrayList<cr$a> list = new ArrayList<cr$a>();
        for (int j = 0; j < c$f.fJ.length; ++j) {
            list.add(a(c$f.fJ[j], c$f, array, j));
        }
        final ArrayList<cr$a> list2 = new ArrayList<cr$a>();
        for (int k = 0; k < c$f.fK.length; ++k) {
            list2.add(a(c$f.fK[k], c$f, array, k));
        }
        final ArrayList<cr$a> list3 = new ArrayList<cr$a>();
        for (int l = 0; l < c$f.fI.length; ++l) {
            final cr$a a = a(c$f.fI[l], c$f, array, l);
            ov.a(a);
            list3.add(a);
        }
        final c$g[] fl = c$f.fL;
        for (int length = fl.length, n2 = n; n2 < length; ++n2) {
            ov.a(a(fl[n2], list, list3, list2, c$f));
        }
        ov.cJ(c$f.version);
        ov.fl(c$f.fT);
        return ov.oY();
    }
    
    public static void b(final InputStream inputStream, final OutputStream outputStream) {
        final byte[] array = new byte[1024];
        while (true) {
            final int read = inputStream.read(array);
            if (read == -1) {
                break;
            }
            outputStream.write(array, 0, read);
        }
    }
    
    private static void cI(final String s) {
        bh.T(s);
        throw new cr$g(s);
    }
    
    public static d$a g(final d$a d$a) {
        final d$a d$a2 = new d$a();
        d$a2.type = d$a.type;
        d$a2.gE = d$a.gE.clone();
        if (d$a.gF) {
            d$a2.gF = d$a.gF;
        }
        return d$a2;
    }
    
    private static c$h h(final d$a d$a) {
        if (d$a.a(c$h.gf) == null) {
            cI("Expected a ServingValue and didn't get one. Value is: " + d$a);
        }
        return d$a.a(c$h.gf);
    }
}
