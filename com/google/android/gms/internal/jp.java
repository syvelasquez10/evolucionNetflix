// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a$a;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Iterator;
import java.util.HashMap;
import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class jp extends ji implements SafeParcelable
{
    public static final jq CREATOR;
    private final int BR;
    private final Parcel MF;
    private final int MG;
    private int MH;
    private int MI;
    private final jm My;
    private final String mClassName;
    
    static {
        CREATOR = new jq();
    }
    
    jp(final int br, final Parcel parcel, final jm my) {
        this.BR = br;
        this.MF = n.i(parcel);
        this.MG = 2;
        this.My = my;
        if (this.My == null) {
            this.mClassName = null;
        }
        else {
            this.mClassName = this.My.hv();
        }
        this.MH = 2;
    }
    
    private jp(final SafeParcelable safeParcelable, final jm jm, final String s) {
        this.BR = 1;
        safeParcelable.writeToParcel(this.MF = Parcel.obtain(), 0);
        this.MG = 1;
        this.My = n.i(jm);
        this.mClassName = n.i(s);
        this.MH = 2;
    }
    
    public static <T extends com.google.android.gms.internal.ji> jp a(final T t) {
        return new jp((SafeParcelable)t, b((ji)t), t.getClass().getCanonicalName());
    }
    
    private static void a(final jm jm, ji ji) {
        final Class<? extends ji> class1 = ji.getClass();
        if (!jm.b(class1)) {
            final HashMap<String, ji$a<?, ?>> hf = ji.hf();
            jm.a(class1, ji.hf());
            final Iterator<String> iterator = hf.keySet().iterator();
            while (iterator.hasNext()) {
                ji = (ji)hf.get(iterator.next());
                final Class<? extends ji> hn = ((ji$a)ji).hn();
                if (hn != null) {
                    try {
                        a(jm, (ji)hn.newInstance());
                        continue;
                    }
                    catch (InstantiationException ex) {
                        throw new IllegalStateException("Could not instantiate an object of type " + ((ji$a)ji).hn().getCanonicalName(), ex);
                    }
                    catch (IllegalAccessException ex2) {
                        throw new IllegalStateException("Could not access object of type " + ((ji$a)ji).hn().getCanonicalName(), ex2);
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
                sb.append("\"").append(jz.bf(o.toString())).append("\"");
            }
            case 8: {
                sb.append("\"").append(js.d((byte[])o)).append("\"");
            }
            case 9: {
                sb.append("\"").append(js.e((byte[])o));
                sb.append("\"");
            }
            case 10: {
                ka.a(sb, (HashMap<String, String>)o);
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void a(final StringBuilder sb, final ji$a<?, ?> ji$a, final Parcel parcel, final int n) {
        switch (ji$a.he()) {
            default: {
                throw new IllegalArgumentException("Unknown field out type = " + ji$a.he());
            }
            case 0: {
                this.b(sb, ji$a, this.a(ji$a, a.g(parcel, n)));
            }
            case 1: {
                this.b(sb, ji$a, this.a(ji$a, a.k(parcel, n)));
            }
            case 2: {
                this.b(sb, ji$a, this.a(ji$a, a.i(parcel, n)));
            }
            case 3: {
                this.b(sb, ji$a, this.a(ji$a, a.l(parcel, n)));
            }
            case 4: {
                this.b(sb, ji$a, this.a(ji$a, a.m(parcel, n)));
            }
            case 5: {
                this.b(sb, ji$a, this.a(ji$a, a.n(parcel, n)));
            }
            case 6: {
                this.b(sb, ji$a, this.a(ji$a, a.c(parcel, n)));
            }
            case 7: {
                this.b(sb, ji$a, this.a(ji$a, a.o(parcel, n)));
            }
            case 8:
            case 9: {
                this.b(sb, ji$a, this.a(ji$a, a.r(parcel, n)));
            }
            case 10: {
                this.b(sb, ji$a, this.a(ji$a, e(a.q(parcel, n))));
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void a(final StringBuilder sb, final String s, final ji$a<?, ?> ji$a, final Parcel parcel, final int n) {
        sb.append("\"").append(s).append("\":");
        if (ji$a.hp()) {
            this.a(sb, ji$a, parcel, n);
            return;
        }
        this.b(sb, ji$a, parcel, n);
    }
    
    private void a(final StringBuilder sb, final HashMap<String, ji$a<?, ?>> hashMap, final Parcel parcel) {
        final HashMap<Integer, Map.Entry<String, ji$a<?, ?>>> b = b(hashMap);
        sb.append('{');
        final int c = a.C(parcel);
        int n = 0;
        while (parcel.dataPosition() < c) {
            final int b2 = a.B(parcel);
            final Map.Entry<String, V> entry = (Map.Entry<String, V>)b.get(a.aD(b2));
            if (entry != null) {
                if (n != 0) {
                    sb.append(",");
                }
                this.a(sb, entry.getKey(), (ji$a<?, ?>)entry.getValue(), parcel, b2);
                n = 1;
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        sb.append('}');
    }
    
    private static jm b(final ji ji) {
        final jm jm = new jm(ji.getClass());
        a(jm, ji);
        jm.ht();
        jm.hs();
        return jm;
    }
    
    private static HashMap<Integer, Map.Entry<String, ji$a<?, ?>>> b(final HashMap<String, ji$a<?, ?>> hashMap) {
        final HashMap<Integer, Map.Entry<K, ji$a>> hashMap2 = (HashMap<Integer, Map.Entry<K, ji$a>>)new HashMap<Integer, Map.Entry<String, ji$a<?, ?>>>();
        for (final Map.Entry<String, ji$a<?, ?>> entry : hashMap.entrySet()) {
            hashMap2.put(entry.getValue().hm(), entry);
        }
        return (HashMap<Integer, Map.Entry<String, ji$a<?, ?>>>)hashMap2;
    }
    
    private void b(final StringBuilder sb, final ji$a<?, ?> ji$a, Parcel d, int i) {
        if (ji$a.hk()) {
            sb.append("[");
            switch (ji$a.he()) {
                default: {
                    throw new IllegalStateException("Unknown field type out.");
                }
                case 0: {
                    jr.a(sb, a.u(d, i));
                    break;
                }
                case 1: {
                    jr.a(sb, a.w(d, i));
                    break;
                }
                case 2: {
                    jr.a(sb, a.v(d, i));
                    break;
                }
                case 3: {
                    jr.a(sb, a.x(d, i));
                    break;
                }
                case 4: {
                    jr.a(sb, a.y(d, i));
                    break;
                }
                case 5: {
                    jr.a(sb, a.z(d, i));
                    break;
                }
                case 6: {
                    jr.a(sb, a.t(d, i));
                    break;
                }
                case 7: {
                    jr.a(sb, a.A(d, i));
                    break;
                }
                case 8:
                case 9:
                case 10: {
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                }
                case 11: {
                    final Parcel[] e = a.E(d, i);
                    int length;
                    for (length = e.length, i = 0; i < length; ++i) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        e[i].setDataPosition(0);
                        this.a(sb, ji$a.hr(), e[i]);
                    }
                    break;
                }
            }
            sb.append("]");
            return;
        }
        switch (ji$a.he()) {
            default: {
                throw new IllegalStateException("Unknown field type out");
            }
            case 0: {
                sb.append(a.g(d, i));
            }
            case 1: {
                sb.append(a.k(d, i));
            }
            case 2: {
                sb.append(a.i(d, i));
            }
            case 3: {
                sb.append(a.l(d, i));
            }
            case 4: {
                sb.append(a.m(d, i));
            }
            case 5: {
                sb.append(a.n(d, i));
            }
            case 6: {
                sb.append(a.c(d, i));
            }
            case 7: {
                sb.append("\"").append(jz.bf(a.o(d, i))).append("\"");
            }
            case 8: {
                sb.append("\"").append(js.d(a.r(d, i))).append("\"");
            }
            case 9: {
                sb.append("\"").append(js.e(a.r(d, i)));
                sb.append("\"");
            }
            case 10: {
                final Bundle q = a.q(d, i);
                final Set keySet = q.keySet();
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
                    sb.append("\"").append(jz.bf(q.getString(s))).append("\"");
                    i = 0;
                }
                sb.append("}");
            }
            case 11: {
                d = a.D(d, i);
                d.setDataPosition(0);
                this.a(sb, ji$a.hr(), d);
            }
        }
    }
    
    private void b(final StringBuilder sb, final ji$a<?, ?> ji$a, final Object o) {
        if (ji$a.hj()) {
            this.b(sb, ji$a, (ArrayList<?>)o);
            return;
        }
        this.a(sb, ji$a.hd(), o);
    }
    
    private void b(final StringBuilder sb, final ji$a<?, ?> ji$a, final ArrayList<?> list) {
        sb.append("[");
        for (int size = list.size(), i = 0; i < size; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            this.a(sb, ji$a.hd(), list.get(i));
        }
        sb.append("]");
    }
    
    public static HashMap<String, String> e(final Bundle bundle) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : bundle.keySet()) {
            hashMap.put(s, bundle.getString(s));
        }
        return hashMap;
    }
    
    @Override
    protected Object ba(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    @Override
    protected boolean bb(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    public int describeContents() {
        final jq creator = jp.CREATOR;
        return 0;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public HashMap<String, ji$a<?, ?>> hf() {
        if (this.My == null) {
            return null;
        }
        return this.My.be(this.mClassName);
    }
    
    public Parcel hx() {
        switch (this.MH) {
            case 0: {
                this.MI = b.D(this.MF);
                b.H(this.MF, this.MI);
                this.MH = 2;
                break;
            }
            case 1: {
                b.H(this.MF, this.MI);
                this.MH = 2;
                break;
            }
        }
        return this.MF;
    }
    
    jm hy() {
        switch (this.MG) {
            default: {
                throw new IllegalStateException("Invalid creation type: " + this.MG);
            }
            case 0: {
                return null;
            }
            case 1: {
                return this.My;
            }
            case 2: {
                return this.My;
            }
        }
    }
    
    @Override
    public String toString() {
        n.b(this.My, "Cannot convert to JSON on client side.");
        final Parcel hx = this.hx();
        hx.setDataPosition(0);
        final StringBuilder sb = new StringBuilder(100);
        this.a(sb, this.My.be(this.mClassName), hx);
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final jq creator = jp.CREATOR;
        jq.a(this, parcel, n);
    }
}
