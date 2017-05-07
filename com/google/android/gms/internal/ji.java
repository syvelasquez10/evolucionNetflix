// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;
import java.util.HashMap;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;

public abstract class ji
{
    private void a(final StringBuilder sb, final a a, final Object o) {
        if (a.hd() == 11) {
            sb.append(((ji)a.hn().cast(o)).toString());
            return;
        }
        if (a.hd() == 7) {
            sb.append("\"");
            sb.append(jz.bf((String)o));
            sb.append("\"");
            return;
        }
        sb.append(o);
    }
    
    private void a(final StringBuilder sb, final a a, final ArrayList<Object> list) {
        sb.append("[");
        for (int i = 0; i < list.size(); ++i) {
            if (i > 0) {
                sb.append(",");
            }
            final Object value = list.get(i);
            if (value != null) {
                this.a(sb, a, value);
            }
        }
        sb.append("]");
    }
    
    protected <O, I> I a(final a<I, O> a, final Object o) {
        Object convertBack = o;
        if (((a<Object, Object>)a).Mz != null) {
            convertBack = a.convertBack(o);
        }
        return (I)convertBack;
    }
    
    protected boolean a(final a a) {
        if (a.he() != 11) {
            return this.bb(a.hl());
        }
        if (a.hk()) {
            return this.bd(a.hl());
        }
        return this.bc(a.hl());
    }
    
    protected Object b(final a a) {
        final String hl = a.hl();
        if (a.hn() != null) {
            n.a(this.ba(a.hl()) == null, "Concrete field shouldn't be value object: %s", a.hl());
            HashMap<String, Object> hashMap;
            if (a.hk()) {
                hashMap = this.hh();
            }
            else {
                hashMap = this.hg();
            }
            if (hashMap != null) {
                return hashMap.get(hl);
            }
            try {
                return this.getClass().getMethod("get" + Character.toUpperCase(hl.charAt(0)) + hl.substring(1), (Class<?>[])new Class[0]).invoke(this, new Object[0]);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return this.ba(a.hl());
    }
    
    protected abstract Object ba(final String p0);
    
    protected abstract boolean bb(final String p0);
    
    protected boolean bc(final String s) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }
    
    protected boolean bd(final String s) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }
    
    public abstract HashMap<String, a<?, ?>> hf();
    
    public HashMap<String, Object> hg() {
        return null;
    }
    
    public HashMap<String, Object> hh() {
        return null;
    }
    
    @Override
    public String toString() {
        final HashMap<String, a<?, ?>> hf = this.hf();
        final StringBuilder sb = new StringBuilder(100);
        for (final String s : hf.keySet()) {
            final a<Object, Object> a = hf.get(s);
            if (this.a(a)) {
                final HashMap<String, String> a2 = this.a((a<HashMap<String, String>, Object>)a, this.b(a));
                if (sb.length() == 0) {
                    sb.append("{");
                }
                else {
                    sb.append(",");
                }
                sb.append("\"").append(s).append("\":");
                if (a2 == null) {
                    sb.append("null");
                }
                else {
                    switch (a.he()) {
                        default: {
                            if (a.hj()) {
                                this.a(sb, a, (ArrayList<Object>)a2);
                                continue;
                            }
                            this.a(sb, a, a2);
                            continue;
                        }
                        case 8: {
                            sb.append("\"").append(js.d((byte[])(Object)a2)).append("\"");
                            continue;
                        }
                        case 9: {
                            sb.append("\"").append(js.e((byte[])(Object)a2)).append("\"");
                            continue;
                        }
                        case 10: {
                            ka.a(sb, a2);
                            continue;
                        }
                    }
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        }
        else {
            sb.append("{}");
        }
        return sb.toString();
    }
    
    public static class a<I, O> implements SafeParcelable
    {
        public static final jk CREATOR;
        private final int BR;
        protected final int Mq;
        protected final boolean Mr;
        protected final int Ms;
        protected final boolean Mt;
        protected final String Mu;
        protected final int Mv;
        protected final Class<? extends ji> Mw;
        protected final String Mx;
        private jm My;
        private b<I, O> Mz;
        
        static {
            CREATOR = new jk();
        }
        
        a(final int br, final int mq, final boolean mr, final int ms, final boolean mt, final String mu, final int mv, final String mx, final jd jd) {
            this.BR = br;
            this.Mq = mq;
            this.Mr = mr;
            this.Ms = ms;
            this.Mt = mt;
            this.Mu = mu;
            this.Mv = mv;
            if (mx == null) {
                this.Mw = null;
                this.Mx = null;
            }
            else {
                this.Mw = jp.class;
                this.Mx = mx;
            }
            if (jd == null) {
                this.Mz = null;
                return;
            }
            this.Mz = (b<I, O>)jd.hb();
        }
        
        protected a(final int mq, final boolean mr, final int ms, final boolean mt, final String mu, final int mv, final Class<? extends ji> mw, final b<I, O> mz) {
            this.BR = 1;
            this.Mq = mq;
            this.Mr = mr;
            this.Ms = ms;
            this.Mt = mt;
            this.Mu = mu;
            this.Mv = mv;
            this.Mw = mw;
            if (mw == null) {
                this.Mx = null;
            }
            else {
                this.Mx = mw.getCanonicalName();
            }
            this.Mz = mz;
        }
        
        public static a a(final String s, final int n, final b<?, ?> b, final boolean b2) {
            return new a(b.hd(), b2, b.he(), false, s, n, null, (b<I, O>)b);
        }
        
        public static <T extends ji> a<T, T> a(final String s, final int n, final Class<T> clazz) {
            return new a<T, T>(11, false, 11, false, s, n, clazz, null);
        }
        
        public static <T extends ji> a<ArrayList<T>, ArrayList<T>> b(final String s, final int n, final Class<T> clazz) {
            return new a<ArrayList<T>, ArrayList<T>>(11, true, 11, true, s, n, clazz, null);
        }
        
        public static a<Integer, Integer> i(final String s, final int n) {
            return new a<Integer, Integer>(0, false, 0, false, s, n, null, null);
        }
        
        public static a<Double, Double> j(final String s, final int n) {
            return new a<Double, Double>(4, false, 4, false, s, n, null, null);
        }
        
        public static a<Boolean, Boolean> k(final String s, final int n) {
            return new a<Boolean, Boolean>(6, false, 6, false, s, n, null, null);
        }
        
        public static a<String, String> l(final String s, final int n) {
            return new a<String, String>(7, false, 7, false, s, n, null, null);
        }
        
        public static a<ArrayList<String>, ArrayList<String>> m(final String s, final int n) {
            return new a<ArrayList<String>, ArrayList<String>>(7, true, 7, true, s, n, null, null);
        }
        
        public void a(final jm my) {
            this.My = my;
        }
        
        public I convertBack(final O o) {
            return this.Mz.convertBack(o);
        }
        
        public int describeContents() {
            final jk creator = a.CREATOR;
            return 0;
        }
        
        public int getVersionCode() {
            return this.BR;
        }
        
        public int hd() {
            return this.Mq;
        }
        
        public int he() {
            return this.Ms;
        }
        
        public a<I, O> hi() {
            return new a<I, O>(this.BR, this.Mq, this.Mr, this.Ms, this.Mt, this.Mu, this.Mv, this.Mx, this.hq());
        }
        
        public boolean hj() {
            return this.Mr;
        }
        
        public boolean hk() {
            return this.Mt;
        }
        
        public String hl() {
            return this.Mu;
        }
        
        public int hm() {
            return this.Mv;
        }
        
        public Class<? extends ji> hn() {
            return this.Mw;
        }
        
        String ho() {
            if (this.Mx == null) {
                return null;
            }
            return this.Mx;
        }
        
        public boolean hp() {
            return this.Mz != null;
        }
        
        jd hq() {
            if (this.Mz == null) {
                return null;
            }
            return jd.a(this.Mz);
        }
        
        public HashMap<String, a<?, ?>> hr() {
            n.i(this.Mx);
            n.i(this.My);
            return this.My.be(this.Mx);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("Field\n");
            sb.append("            versionCode=").append(this.BR).append('\n');
            sb.append("                 typeIn=").append(this.Mq).append('\n');
            sb.append("            typeInArray=").append(this.Mr).append('\n');
            sb.append("                typeOut=").append(this.Ms).append('\n');
            sb.append("           typeOutArray=").append(this.Mt).append('\n');
            sb.append("        outputFieldName=").append(this.Mu).append('\n');
            sb.append("      safeParcelFieldId=").append(this.Mv).append('\n');
            sb.append("       concreteTypeName=").append(this.ho()).append('\n');
            if (this.hn() != null) {
                sb.append("     concreteType.class=").append(this.hn().getCanonicalName()).append('\n');
            }
            final StringBuilder append = sb.append("          converterName=");
            String canonicalName;
            if (this.Mz == null) {
                canonicalName = "null";
            }
            else {
                canonicalName = this.Mz.getClass().getCanonicalName();
            }
            append.append(canonicalName).append('\n');
            return sb.toString();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final jk creator = a.CREATOR;
            jk.a(this, parcel, n);
        }
    }
    
    public interface b<I, O>
    {
        I convertBack(final O p0);
        
        int hd();
        
        int he();
    }
}
