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

public class ey extends es implements SafeParcelable
{
    public static final ez CREATOR;
    private final int kg;
    private final String mClassName;
    private int qA;
    private int qB;
    private final ev qq;
    private final Parcel qy;
    private final int qz;
    
    static {
        CREATOR = new ez();
    }
    
    ey(final int kg, final Parcel parcel, final ev qq) {
        this.kg = kg;
        this.qy = eg.f(parcel);
        this.qz = 2;
        this.qq = qq;
        if (this.qq == null) {
            this.mClassName = null;
        }
        else {
            this.mClassName = this.qq.cz();
        }
        this.qA = 2;
    }
    
    private ey(final SafeParcelable safeParcelable, final ev ev, final String s) {
        this.kg = 1;
        safeParcelable.writeToParcel(this.qy = Parcel.obtain(), 0);
        this.qz = 1;
        this.qq = eg.f(ev);
        this.mClassName = eg.f(s);
        this.qA = 2;
    }
    
    public static <T extends com.google.android.gms.internal.es> ey a(final T t) {
        return new ey((SafeParcelable)t, b((es)t), t.getClass().getCanonicalName());
    }
    
    private static void a(final ev ev, es es) {
        final Class<? extends es> class1 = es.getClass();
        if (!ev.b(class1)) {
            final HashMap<String, a<?, ?>> cj = es.cj();
            ev.a(class1, es.cj());
            final Iterator<String> iterator = cj.keySet().iterator();
            while (iterator.hasNext()) {
                es = (es)cj.get(iterator.next());
                final Class<? extends es> cr = ((a)es).cr();
                if (cr != null) {
                    try {
                        a(ev, (es)cr.newInstance());
                        continue;
                    }
                    catch (InstantiationException ex) {
                        throw new IllegalStateException("Could not instantiate an object of type " + ((a)es).cr().getCanonicalName(), ex);
                    }
                    catch (IllegalAccessException ex2) {
                        throw new IllegalStateException("Could not access object of type " + ((a)es).cr().getCanonicalName(), ex2);
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
                sb.append("\"").append(fe.aa(o.toString())).append("\"");
            }
            case 8: {
                sb.append("\"").append(fb.b((byte[])o)).append("\"");
            }
            case 9: {
                sb.append("\"").append(fb.c((byte[])o));
                sb.append("\"");
            }
            case 10: {
                ff.a(sb, (HashMap<String, String>)o);
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void a(final StringBuilder sb, final a<?, ?> a, final Parcel parcel, final int n) {
        switch (a.ci()) {
            default: {
                throw new IllegalArgumentException("Unknown field out type = " + a.ci());
            }
            case 0: {
                this.b(sb, a, this.a(a, a.g(parcel, n)));
            }
            case 1: {
                this.b(sb, a, this.a(a, a.i(parcel, n)));
            }
            case 2: {
                this.b(sb, a, this.a(a, a.h(parcel, n)));
            }
            case 3: {
                this.b(sb, a, this.a(a, a.j(parcel, n)));
            }
            case 4: {
                this.b(sb, a, this.a(a, a.k(parcel, n)));
            }
            case 5: {
                this.b(sb, a, this.a(a, a.l(parcel, n)));
            }
            case 6: {
                this.b(sb, a, this.a(a, a.c(parcel, n)));
            }
            case 7: {
                this.b(sb, a, this.a(a, a.m(parcel, n)));
            }
            case 8:
            case 9: {
                this.b(sb, a, this.a(a, a.p(parcel, n)));
            }
            case 10: {
                this.b(sb, a, this.a(a, c(a.o(parcel, n))));
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void a(final StringBuilder sb, final String s, final a<?, ?> a, final Parcel parcel, final int n) {
        sb.append("\"").append(s).append("\":");
        if (a.ct()) {
            this.a(sb, a, parcel, n);
            return;
        }
        this.b(sb, a, parcel, n);
    }
    
    private void a(final StringBuilder sb, final HashMap<String, a<?, ?>> hashMap, final Parcel parcel) {
        final HashMap<Integer, Map.Entry<String, a<?, ?>>> c = c(hashMap);
        sb.append('{');
        final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
        int n2 = 0;
        while (parcel.dataPosition() < n) {
            final int m = com.google.android.gms.common.internal.safeparcel.a.m(parcel);
            final Map.Entry<String, V> entry = (Map.Entry<String, V>)c.get(com.google.android.gms.common.internal.safeparcel.a.M(m));
            if (entry != null) {
                if (n2 != 0) {
                    sb.append(",");
                }
                this.a(sb, entry.getKey(), (a<?, ?>)entry.getValue(), parcel, m);
                n2 = 1;
            }
        }
        if (parcel.dataPosition() != n) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + n, parcel);
        }
        sb.append('}');
    }
    
    private static ev b(final es es) {
        final ev ev = new ev(es.getClass());
        a(ev, es);
        ev.cx();
        ev.cw();
        return ev;
    }
    
    private void b(final StringBuilder sb, final a<?, ?> a, Parcel z, int i) {
        if (a.co()) {
            sb.append("[");
            switch (a.ci()) {
                default: {
                    throw new IllegalStateException("Unknown field type out.");
                }
                case 0: {
                    fa.a(sb, a.r(z, i));
                    break;
                }
                case 1: {
                    fa.a(sb, a.t(z, i));
                    break;
                }
                case 2: {
                    fa.a(sb, a.s(z, i));
                    break;
                }
                case 3: {
                    fa.a(sb, a.u(z, i));
                    break;
                }
                case 4: {
                    fa.a(sb, a.v(z, i));
                    break;
                }
                case 5: {
                    fa.a(sb, a.w(z, i));
                    break;
                }
                case 6: {
                    fa.a(sb, a.q(z, i));
                    break;
                }
                case 7: {
                    fa.a(sb, a.x(z, i));
                    break;
                }
                case 8:
                case 9:
                case 10: {
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                }
                case 11: {
                    final Parcel[] a2 = a.A(z, i);
                    int length;
                    for (length = a2.length, i = 0; i < length; ++i) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        a2[i].setDataPosition(0);
                        this.a(sb, a.cv(), a2[i]);
                    }
                    break;
                }
            }
            sb.append("]");
            return;
        }
        switch (a.ci()) {
            default: {
                throw new IllegalStateException("Unknown field type out");
            }
            case 0: {
                sb.append(a.g(z, i));
            }
            case 1: {
                sb.append(a.i(z, i));
            }
            case 2: {
                sb.append(a.h(z, i));
            }
            case 3: {
                sb.append(a.j(z, i));
            }
            case 4: {
                sb.append(a.k(z, i));
            }
            case 5: {
                sb.append(a.l(z, i));
            }
            case 6: {
                sb.append(a.c(z, i));
            }
            case 7: {
                sb.append("\"").append(fe.aa(a.m(z, i))).append("\"");
            }
            case 8: {
                sb.append("\"").append(fb.b(a.p(z, i))).append("\"");
            }
            case 9: {
                sb.append("\"").append(fb.c(a.p(z, i)));
                sb.append("\"");
            }
            case 10: {
                final Bundle o = a.o(z, i);
                final Set keySet = o.keySet();
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
                    sb.append("\"").append(fe.aa(o.getString(s))).append("\"");
                    i = 0;
                }
                sb.append("}");
            }
            case 11: {
                z = a.z(z, i);
                z.setDataPosition(0);
                this.a(sb, a.cv(), z);
            }
        }
    }
    
    private void b(final StringBuilder sb, final a<?, ?> a, final Object o) {
        if (a.cn()) {
            this.b(sb, a, (ArrayList<?>)o);
            return;
        }
        this.a(sb, a.ch(), o);
    }
    
    private void b(final StringBuilder sb, final a<?, ?> a, final ArrayList<?> list) {
        sb.append("[");
        for (int size = list.size(), i = 0; i < size; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            this.a(sb, a.ch(), list.get(i));
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
            hashMap2.put(((a)entry.getValue()).cq(), entry);
        }
        return (HashMap<Integer, Map.Entry<String, a<?, ?>>>)hashMap2;
    }
    
    @Override
    protected Object V(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    @Override
    protected boolean W(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    public Parcel cB() {
        switch (this.qA) {
            case 0: {
                this.qB = com.google.android.gms.common.internal.safeparcel.b.o(this.qy);
                com.google.android.gms.common.internal.safeparcel.b.D(this.qy, this.qB);
                this.qA = 2;
                break;
            }
            case 1: {
                com.google.android.gms.common.internal.safeparcel.b.D(this.qy, this.qB);
                this.qA = 2;
                break;
            }
        }
        return this.qy;
    }
    
    ev cC() {
        switch (this.qz) {
            default: {
                throw new IllegalStateException("Invalid creation type: " + this.qz);
            }
            case 0: {
                return null;
            }
            case 1: {
                return this.qq;
            }
            case 2: {
                return this.qq;
            }
        }
    }
    
    @Override
    public HashMap<String, a<?, ?>> cj() {
        if (this.qq == null) {
            return null;
        }
        return this.qq.Z(this.mClassName);
    }
    
    public int describeContents() {
        final ez creator = ey.CREATOR;
        return 0;
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public String toString() {
        eg.b(this.qq, "Cannot convert to JSON on client side.");
        final Parcel cb = this.cB();
        cb.setDataPosition(0);
        final StringBuilder sb = new StringBuilder(100);
        this.a(sb, this.qq.Z(this.mClassName), cb);
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ez creator = ey.CREATOR;
        ez.a(this, parcel, n);
    }
}
