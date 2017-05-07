// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;
import java.util.Set;
import android.os.Bundle;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Iterator;
import java.util.HashMap;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class gg extends ga implements SafeParcelable
{
    public static final gh CREATOR;
    private final gd Eg;
    private final Parcel En;
    private final int Eo;
    private int Ep;
    private int Eq;
    private final String mClassName;
    private final int xH;
    
    static {
        CREATOR = new gh();
    }
    
    gg(final int xh, final Parcel parcel, final gd eg) {
        this.xH = xh;
        this.En = fq.f(parcel);
        this.Eo = 2;
        this.Eg = eg;
        if (this.Eg == null) {
            this.mClassName = null;
        }
        else {
            this.mClassName = this.Eg.fo();
        }
        this.Ep = 2;
    }
    
    private gg(final SafeParcelable safeParcelable, final gd gd, final String s) {
        this.xH = 1;
        safeParcelable.writeToParcel(this.En = Parcel.obtain(), 0);
        this.Eo = 1;
        this.Eg = fq.f(gd);
        this.mClassName = fq.f(s);
        this.Ep = 2;
    }
    
    public static <T extends com.google.android.gms.internal.ga> gg a(final T t) {
        return new gg((SafeParcelable)t, b((ga)t), t.getClass().getCanonicalName());
    }
    
    private static void a(final gd gd, ga ga) {
        final Class<? extends ga> class1 = ga.getClass();
        if (!gd.b(class1)) {
            final HashMap<String, a<?, ?>> ey = ga.eY();
            gd.a(class1, ga.eY());
            final Iterator<String> iterator = ey.keySet().iterator();
            while (iterator.hasNext()) {
                ga = (ga)ey.get(iterator.next());
                final Class<? extends ga> fg = ((a)ga).fg();
                if (fg != null) {
                    try {
                        a(gd, (ga)fg.newInstance());
                        continue;
                    }
                    catch (InstantiationException ex) {
                        throw new IllegalStateException("Could not instantiate an object of type " + ((a)ga).fg().getCanonicalName(), ex);
                    }
                    catch (IllegalAccessException ex2) {
                        throw new IllegalStateException("Could not access object of type " + ((a)ga).fg().getCanonicalName(), ex2);
                    }
                    break;
                }
            }
        }
    }
    
    private void a(final StringBuilder sb, final int n, final Object o) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown type = " + n);
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: {
                sb.append(o);
            }
            case 7: {
                sb.append("\"").append(gp.av(o.toString())).append("\"");
            }
            case 8: {
                sb.append("\"").append(gj.d((byte[])o)).append("\"");
            }
            case 9: {
                sb.append("\"").append(gj.e((byte[])o));
                sb.append("\"");
            }
            case 10: {
                gq.a(sb, (HashMap<String, String>)o);
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void a(final StringBuilder sb, final a<?, ?> a, final Parcel parcel, final int n) {
        switch (a.eX()) {
            default: {
                throw new IllegalArgumentException("Unknown field out type = " + a.eX());
            }
            case 0: {
                this.b(sb, a, this.a(a, a.g(parcel, n)));
            }
            case 1: {
                this.b(sb, a, this.a(a, a.j(parcel, n)));
            }
            case 2: {
                this.b(sb, a, this.a(a, a.i(parcel, n)));
            }
            case 3: {
                this.b(sb, a, this.a(a, a.k(parcel, n)));
            }
            case 4: {
                this.b(sb, a, this.a(a, a.l(parcel, n)));
            }
            case 5: {
                this.b(sb, a, this.a(a, a.m(parcel, n)));
            }
            case 6: {
                this.b(sb, a, this.a(a, a.c(parcel, n)));
            }
            case 7: {
                this.b(sb, a, this.a(a, a.n(parcel, n)));
            }
            case 8:
            case 9: {
                this.b(sb, a, this.a(a, a.q(parcel, n)));
            }
            case 10: {
                this.b(sb, a, this.a(a, c(a.p(parcel, n))));
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void a(final StringBuilder sb, final String s, final a<?, ?> a, final Parcel parcel, final int n) {
        sb.append("\"").append(s).append("\":");
        if (a.fi()) {
            this.a(sb, a, parcel, n);
            return;
        }
        this.b(sb, a, parcel, n);
    }
    
    private void a(final StringBuilder sb, final HashMap<String, a<?, ?>> hashMap, final Parcel parcel) {
        final HashMap<Integer, Map.Entry<String, a<?, ?>>> c = c(hashMap);
        sb.append('{');
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int n = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            final Map.Entry<String, V> entry = (Map.Entry<String, V>)c.get(com.google.android.gms.common.internal.safeparcel.a.R(n2));
            if (entry != null) {
                if (n != 0) {
                    sb.append(",");
                }
                this.a(sb, entry.getKey(), (a<?, ?>)entry.getValue(), parcel, n2);
                n = 1;
            }
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        sb.append('}');
    }
    
    private static gd b(final ga ga) {
        final gd gd = new gd(ga.getClass());
        a(gd, ga);
        gd.fm();
        gd.fl();
        return gd;
    }
    
    private void b(final StringBuilder sb, final a<?, ?> a, Parcel b, int i) {
        if (a.fd()) {
            sb.append("[");
            switch (a.eX()) {
                default: {
                    throw new IllegalStateException("Unknown field type out.");
                }
                case 0: {
                    gi.a(sb, a.t(b, i));
                    break;
                }
                case 1: {
                    gi.a(sb, a.v(b, i));
                    break;
                }
                case 2: {
                    gi.a(sb, a.u(b, i));
                    break;
                }
                case 3: {
                    gi.a(sb, a.w(b, i));
                    break;
                }
                case 4: {
                    gi.a(sb, a.x(b, i));
                    break;
                }
                case 5: {
                    gi.a(sb, a.y(b, i));
                    break;
                }
                case 6: {
                    gi.a(sb, a.s(b, i));
                    break;
                }
                case 7: {
                    gi.a(sb, a.z(b, i));
                    break;
                }
                case 8:
                case 9:
                case 10: {
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                }
                case 11: {
                    final Parcel[] c = a.C(b, i);
                    int length;
                    for (length = c.length, i = 0; i < length; ++i) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        c[i].setDataPosition(0);
                        this.a(sb, a.fk(), c[i]);
                    }
                    break;
                }
            }
            sb.append("]");
            return;
        }
        switch (a.eX()) {
            default: {
                throw new IllegalStateException("Unknown field type out");
            }
            case 0: {
                sb.append(a.g(b, i));
            }
            case 1: {
                sb.append(a.j(b, i));
            }
            case 2: {
                sb.append(a.i(b, i));
            }
            case 3: {
                sb.append(a.k(b, i));
            }
            case 4: {
                sb.append(a.l(b, i));
            }
            case 5: {
                sb.append(a.m(b, i));
            }
            case 6: {
                sb.append(a.c(b, i));
            }
            case 7: {
                sb.append("\"").append(gp.av(a.n(b, i))).append("\"");
            }
            case 8: {
                sb.append("\"").append(gj.d(a.q(b, i))).append("\"");
            }
            case 9: {
                sb.append("\"").append(gj.e(a.q(b, i)));
                sb.append("\"");
            }
            case 10: {
                final Bundle p4 = a.p(b, i);
                final Set keySet = p4.keySet();
                keySet.size();
                sb.append("{");
                final Iterator<String> iterator = keySet.iterator();
                i = 1;
                while (iterator.hasNext()) {
                    final String s = iterator.next();
                    if (i == 0) {
                        sb.append(",");
                    }
                    sb.append("\"").append(s).append("\"");
                    sb.append(":");
                    sb.append("\"").append(gp.av(p4.getString(s))).append("\"");
                    i = 0;
                }
                sb.append("}");
            }
            case 11: {
                b = a.B(b, i);
                b.setDataPosition(0);
                this.a(sb, a.fk(), b);
            }
        }
    }
    
    private void b(final StringBuilder sb, final a<?, ?> a, final Object o) {
        if (a.fc()) {
            this.b(sb, a, (ArrayList<?>)o);
            return;
        }
        this.a(sb, a.eW(), o);
    }
    
    private void b(final StringBuilder sb, final a<?, ?> a, final ArrayList<?> list) {
        sb.append("[");
        for (int size = list.size(), i = 0; i < size; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            this.a(sb, a.eW(), list.get(i));
        }
        sb.append("]");
    }
    
    public static HashMap<String, String> c(final Bundle bundle) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : bundle.keySet()) {
            hashMap.put(s, bundle.getString(s));
        }
        return hashMap;
    }
    
    private static HashMap<Integer, Map.Entry<String, a<?, ?>>> c(final HashMap<String, a<?, ?>> hashMap) {
        final HashMap<Integer, Map.Entry<K, a>> hashMap2 = (HashMap<Integer, Map.Entry<K, a>>)new HashMap<Integer, Map.Entry<String, a<?, ?>>>();
        for (final Map.Entry<String, a<?, ?>> entry : hashMap.entrySet()) {
            hashMap2.put(((a)entry.getValue()).ff(), entry);
        }
        return (HashMap<Integer, Map.Entry<String, a<?, ?>>>)hashMap2;
    }
    
    @Override
    protected Object aq(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    @Override
    protected boolean ar(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    public int describeContents() {
        final gh creator = gg.CREATOR;
        return 0;
    }
    
    @Override
    public HashMap<String, a<?, ?>> eY() {
        if (this.Eg == null) {
            return null;
        }
        return this.Eg.au(this.mClassName);
    }
    
    public Parcel fq() {
        switch (this.Ep) {
            case 0: {
                this.Eq = com.google.android.gms.common.internal.safeparcel.b.p(this.En);
                com.google.android.gms.common.internal.safeparcel.b.F(this.En, this.Eq);
                this.Ep = 2;
                break;
            }
            case 1: {
                com.google.android.gms.common.internal.safeparcel.b.F(this.En, this.Eq);
                this.Ep = 2;
                break;
            }
        }
        return this.En;
    }
    
    gd fr() {
        switch (this.Eo) {
            default: {
                throw new IllegalStateException("Invalid creation type: " + this.Eo);
            }
            case 0: {
                return null;
            }
            case 1: {
                return this.Eg;
            }
            case 2: {
                return this.Eg;
            }
        }
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public String toString() {
        fq.b(this.Eg, "Cannot convert to JSON on client side.");
        final Parcel fq = this.fq();
        fq.setDataPosition(0);
        final StringBuilder sb = new StringBuilder(100);
        this.a(sb, this.Eg.au(this.mClassName), fq);
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final gh creator = gg.CREATOR;
        gh.a(this, parcel, n);
    }
}
