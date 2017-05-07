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
import java.util.ArrayList;

public abstract class es
{
    private void a(final StringBuilder sb, final a a, final Object o) {
        if (a.ch() == 11) {
            sb.append(((es)a.cr().cast(o)).toString());
            return;
        }
        if (a.ch() == 7) {
            sb.append("\"");
            sb.append(fe.aa((String)o));
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
    
    protected abstract Object V(final String p0);
    
    protected abstract boolean W(final String p0);
    
    protected boolean X(final String s) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }
    
    protected boolean Y(final String s) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }
    
    protected <O, I> I a(final a<I, O> a, final Object o) {
        Object g = o;
        if (((a<Object, Object>)a).qr != null) {
            g = a.g(o);
        }
        return (I)g;
    }
    
    protected boolean a(final a a) {
        if (a.ci() != 11) {
            return this.W(a.cp());
        }
        if (a.co()) {
            return this.Y(a.cp());
        }
        return this.X(a.cp());
    }
    
    protected Object b(final a a) {
        boolean b = true;
        final String cp = a.cp();
        if (a.cr() != null) {
            if (this.V(a.cp()) != null) {
                b = false;
            }
            eg.a(b, (Object)("Concrete field shouldn't be value object: " + a.cp()));
            HashMap<String, Object> hashMap;
            if (a.co()) {
                hashMap = this.cl();
            }
            else {
                hashMap = this.ck();
            }
            if (hashMap != null) {
                return hashMap.get(cp);
            }
            try {
                return this.getClass().getMethod("get" + Character.toUpperCase(cp.charAt(0)) + cp.substring(1), (Class<?>[])new Class[0]).invoke(this, new Object[0]);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return this.V(a.cp());
    }
    
    public abstract HashMap<String, a<?, ?>> cj();
    
    public HashMap<String, Object> ck() {
        return null;
    }
    
    public HashMap<String, Object> cl() {
        return null;
    }
    
    @Override
    public String toString() {
        final HashMap<String, a<?, ?>> cj = this.cj();
        final StringBuilder sb = new StringBuilder(100);
        for (final String s : cj.keySet()) {
            final a<Object, Object> a = cj.get(s);
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
                    switch (a.ci()) {
                        default: {
                            if (a.cn()) {
                                this.a(sb, a, (ArrayList<Object>)a2);
                                continue;
                            }
                            this.a(sb, a, a2);
                            continue;
                        }
                        case 8: {
                            sb.append("\"").append(fb.b((byte[])(Object)a2)).append("\"");
                            continue;
                        }
                        case 9: {
                            sb.append("\"").append(fb.c((byte[])(Object)a2)).append("\"");
                            continue;
                        }
                        case 10: {
                            ff.a(sb, a2);
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
        public static final et CREATOR;
        private final int kg;
        protected final int qi;
        protected final boolean qj;
        protected final int qk;
        protected final boolean ql;
        protected final String qm;
        protected final int qn;
        protected final Class<? extends es> qo;
        protected final String qp;
        private ev qq;
        private b<I, O> qr;
        
        static {
            CREATOR = new et();
        }
        
        a(final int kg, final int qi, final boolean qj, final int qk, final boolean ql, final String qm, final int qn, final String qp, final en en) {
            this.kg = kg;
            this.qi = qi;
            this.qj = qj;
            this.qk = qk;
            this.ql = ql;
            this.qm = qm;
            this.qn = qn;
            if (qp == null) {
                this.qo = null;
                this.qp = null;
            }
            else {
                this.qo = ey.class;
                this.qp = qp;
            }
            if (en == null) {
                this.qr = null;
                return;
            }
            this.qr = (b<I, O>)en.cf();
        }
        
        protected a(final int qi, final boolean qj, final int qk, final boolean ql, final String qm, final int qn, final Class<? extends es> qo, final b<I, O> qr) {
            this.kg = 1;
            this.qi = qi;
            this.qj = qj;
            this.qk = qk;
            this.ql = ql;
            this.qm = qm;
            this.qn = qn;
            this.qo = qo;
            if (qo == null) {
                this.qp = null;
            }
            else {
                this.qp = qo.getCanonicalName();
            }
            this.qr = qr;
        }
        
        public static a a(final String s, final int n, final b<?, ?> b, final boolean b2) {
            return new a(b.ch(), b2, b.ci(), false, s, n, null, (b<I, O>)b);
        }
        
        public static <T extends es> a<T, T> a(final String s, final int n, final Class<T> clazz) {
            return new a<T, T>(11, false, 11, false, s, n, clazz, null);
        }
        
        public static <T extends es> a<ArrayList<T>, ArrayList<T>> b(final String s, final int n, final Class<T> clazz) {
            return new a<ArrayList<T>, ArrayList<T>>(11, true, 11, true, s, n, clazz, null);
        }
        
        public static a<Integer, Integer> d(final String s, final int n) {
            return new a<Integer, Integer>(0, false, 0, false, s, n, null, null);
        }
        
        public static a<Double, Double> e(final String s, final int n) {
            return new a<Double, Double>(4, false, 4, false, s, n, null, null);
        }
        
        public static a<Boolean, Boolean> f(final String s, final int n) {
            return new a<Boolean, Boolean>(6, false, 6, false, s, n, null, null);
        }
        
        public static a<String, String> g(final String s, final int n) {
            return new a<String, String>(7, false, 7, false, s, n, null, null);
        }
        
        public static a<ArrayList<String>, ArrayList<String>> h(final String s, final int n) {
            return new a<ArrayList<String>, ArrayList<String>>(7, true, 7, true, s, n, null, null);
        }
        
        public void a(final ev qq) {
            this.qq = qq;
        }
        
        public int ch() {
            return this.qi;
        }
        
        public int ci() {
            return this.qk;
        }
        
        public a<I, O> cm() {
            return new a<I, O>(this.kg, this.qi, this.qj, this.qk, this.ql, this.qm, this.qn, this.qp, this.cu());
        }
        
        public boolean cn() {
            return this.qj;
        }
        
        public boolean co() {
            return this.ql;
        }
        
        public String cp() {
            return this.qm;
        }
        
        public int cq() {
            return this.qn;
        }
        
        public Class<? extends es> cr() {
            return this.qo;
        }
        
        String cs() {
            if (this.qp == null) {
                return null;
            }
            return this.qp;
        }
        
        public boolean ct() {
            return this.qr != null;
        }
        
        en cu() {
            if (this.qr == null) {
                return null;
            }
            return en.a(this.qr);
        }
        
        public HashMap<String, a<?, ?>> cv() {
            eg.f(this.qp);
            eg.f(this.qq);
            return this.qq.Z(this.qp);
        }
        
        public int describeContents() {
            final et creator = a.CREATOR;
            return 0;
        }
        
        public I g(final O o) {
            return this.qr.g(o);
        }
        
        public int getVersionCode() {
            return this.kg;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("Field\n");
            sb.append("            versionCode=").append(this.kg).append('\n');
            sb.append("                 typeIn=").append(this.qi).append('\n');
            sb.append("            typeInArray=").append(this.qj).append('\n');
            sb.append("                typeOut=").append(this.qk).append('\n');
            sb.append("           typeOutArray=").append(this.ql).append('\n');
            sb.append("        outputFieldName=").append(this.qm).append('\n');
            sb.append("      safeParcelFieldId=").append(this.qn).append('\n');
            sb.append("       concreteTypeName=").append(this.cs()).append('\n');
            if (this.cr() != null) {
                sb.append("     concreteType.class=").append(this.cr().getCanonicalName()).append('\n');
            }
            final StringBuilder append = sb.append("          converterName=");
            String canonicalName;
            if (this.qr == null) {
                canonicalName = "null";
            }
            else {
                canonicalName = this.qr.getClass().getCanonicalName();
            }
            append.append(canonicalName).append('\n');
            return sb.toString();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final et creator = a.CREATOR;
            et.a(this, parcel, n);
        }
    }
    
    public interface b<I, O>
    {
        int ch();
        
        int ci();
        
        I g(final O p0);
    }
}
