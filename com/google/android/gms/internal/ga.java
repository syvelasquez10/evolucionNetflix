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

public abstract class ga
{
    private void a(final StringBuilder sb, final a a, final Object o) {
        if (a.eW() == 11) {
            sb.append(((ga)a.fg().cast(o)).toString());
            return;
        }
        if (a.eW() == 7) {
            sb.append("\"");
            sb.append(gp.av((String)o));
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
        Object g = o;
        if (((a<Object, Object>)a).Eh != null) {
            g = a.g(o);
        }
        return (I)g;
    }
    
    protected boolean a(final a a) {
        if (a.eX() != 11) {
            return this.ar(a.fe());
        }
        if (a.fd()) {
            return this.at(a.fe());
        }
        return this.as(a.fe());
    }
    
    protected abstract Object aq(final String p0);
    
    protected abstract boolean ar(final String p0);
    
    protected boolean as(final String s) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }
    
    protected boolean at(final String s) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }
    
    protected Object b(final a a) {
        boolean b = true;
        final String fe = a.fe();
        if (a.fg() != null) {
            if (this.aq(a.fe()) != null) {
                b = false;
            }
            fq.a(b, (Object)("Concrete field shouldn't be value object: " + a.fe()));
            HashMap<String, Object> hashMap;
            if (a.fd()) {
                hashMap = this.fa();
            }
            else {
                hashMap = this.eZ();
            }
            if (hashMap != null) {
                return hashMap.get(fe);
            }
            try {
                return this.getClass().getMethod("get" + Character.toUpperCase(fe.charAt(0)) + fe.substring(1), (Class<?>[])new Class[0]).invoke(this, new Object[0]);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return this.aq(a.fe());
    }
    
    public abstract HashMap<String, a<?, ?>> eY();
    
    public HashMap<String, Object> eZ() {
        return null;
    }
    
    public HashMap<String, Object> fa() {
        return null;
    }
    
    @Override
    public String toString() {
        final HashMap<String, a<?, ?>> ey = this.eY();
        final StringBuilder sb = new StringBuilder(100);
        for (final String s : ey.keySet()) {
            final a<Object, Object> a = ey.get(s);
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
                    switch (a.eX()) {
                        default: {
                            if (a.fc()) {
                                this.a(sb, a, (ArrayList<Object>)a2);
                                continue;
                            }
                            this.a(sb, a, a2);
                            continue;
                        }
                        case 8: {
                            sb.append("\"").append(gj.d((byte[])(Object)a2)).append("\"");
                            continue;
                        }
                        case 9: {
                            sb.append("\"").append(gj.e((byte[])(Object)a2)).append("\"");
                            continue;
                        }
                        case 10: {
                            gq.a(sb, a2);
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
        public static final gb CREATOR;
        protected final int DY;
        protected final boolean DZ;
        protected final int Ea;
        protected final boolean Eb;
        protected final String Ec;
        protected final int Ed;
        protected final Class<? extends ga> Ee;
        protected final String Ef;
        private gd Eg;
        private b<I, O> Eh;
        private final int xH;
        
        static {
            CREATOR = new gb();
        }
        
        a(final int xh, final int dy, final boolean dz, final int ea, final boolean eb, final String ec, final int ed, final String ef, final fv fv) {
            this.xH = xh;
            this.DY = dy;
            this.DZ = dz;
            this.Ea = ea;
            this.Eb = eb;
            this.Ec = ec;
            this.Ed = ed;
            if (ef == null) {
                this.Ee = null;
                this.Ef = null;
            }
            else {
                this.Ee = gg.class;
                this.Ef = ef;
            }
            if (fv == null) {
                this.Eh = null;
                return;
            }
            this.Eh = (b<I, O>)fv.eU();
        }
        
        protected a(final int dy, final boolean dz, final int ea, final boolean eb, final String ec, final int ed, final Class<? extends ga> ee, final b<I, O> eh) {
            this.xH = 1;
            this.DY = dy;
            this.DZ = dz;
            this.Ea = ea;
            this.Eb = eb;
            this.Ec = ec;
            this.Ed = ed;
            this.Ee = ee;
            if (ee == null) {
                this.Ef = null;
            }
            else {
                this.Ef = ee.getCanonicalName();
            }
            this.Eh = eh;
        }
        
        public static a a(final String s, final int n, final b<?, ?> b, final boolean b2) {
            return new a(b.eW(), b2, b.eX(), false, s, n, null, (b<I, O>)b);
        }
        
        public static <T extends ga> a<T, T> a(final String s, final int n, final Class<T> clazz) {
            return new a<T, T>(11, false, 11, false, s, n, clazz, null);
        }
        
        public static <T extends ga> a<ArrayList<T>, ArrayList<T>> b(final String s, final int n, final Class<T> clazz) {
            return new a<ArrayList<T>, ArrayList<T>>(11, true, 11, true, s, n, clazz, null);
        }
        
        public static a<Integer, Integer> g(final String s, final int n) {
            return new a<Integer, Integer>(0, false, 0, false, s, n, null, null);
        }
        
        public static a<Double, Double> h(final String s, final int n) {
            return new a<Double, Double>(4, false, 4, false, s, n, null, null);
        }
        
        public static a<Boolean, Boolean> i(final String s, final int n) {
            return new a<Boolean, Boolean>(6, false, 6, false, s, n, null, null);
        }
        
        public static a<String, String> j(final String s, final int n) {
            return new a<String, String>(7, false, 7, false, s, n, null, null);
        }
        
        public static a<ArrayList<String>, ArrayList<String>> k(final String s, final int n) {
            return new a<ArrayList<String>, ArrayList<String>>(7, true, 7, true, s, n, null, null);
        }
        
        public void a(final gd eg) {
            this.Eg = eg;
        }
        
        public int describeContents() {
            final gb creator = a.CREATOR;
            return 0;
        }
        
        public int eW() {
            return this.DY;
        }
        
        public int eX() {
            return this.Ea;
        }
        
        public a<I, O> fb() {
            return new a<I, O>(this.xH, this.DY, this.DZ, this.Ea, this.Eb, this.Ec, this.Ed, this.Ef, this.fj());
        }
        
        public boolean fc() {
            return this.DZ;
        }
        
        public boolean fd() {
            return this.Eb;
        }
        
        public String fe() {
            return this.Ec;
        }
        
        public int ff() {
            return this.Ed;
        }
        
        public Class<? extends ga> fg() {
            return this.Ee;
        }
        
        String fh() {
            if (this.Ef == null) {
                return null;
            }
            return this.Ef;
        }
        
        public boolean fi() {
            return this.Eh != null;
        }
        
        fv fj() {
            if (this.Eh == null) {
                return null;
            }
            return fv.a(this.Eh);
        }
        
        public HashMap<String, a<?, ?>> fk() {
            fq.f(this.Ef);
            fq.f(this.Eg);
            return this.Eg.au(this.Ef);
        }
        
        public I g(final O o) {
            return this.Eh.g(o);
        }
        
        public int getVersionCode() {
            return this.xH;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("Field\n");
            sb.append("            versionCode=").append(this.xH).append('\n');
            sb.append("                 typeIn=").append(this.DY).append('\n');
            sb.append("            typeInArray=").append(this.DZ).append('\n');
            sb.append("                typeOut=").append(this.Ea).append('\n');
            sb.append("           typeOutArray=").append(this.Eb).append('\n');
            sb.append("        outputFieldName=").append(this.Ec).append('\n');
            sb.append("      safeParcelFieldId=").append(this.Ed).append('\n');
            sb.append("       concreteTypeName=").append(this.fh()).append('\n');
            if (this.fg() != null) {
                sb.append("     concreteType.class=").append(this.fg().getCanonicalName()).append('\n');
            }
            final StringBuilder append = sb.append("          converterName=");
            String canonicalName;
            if (this.Eh == null) {
                canonicalName = "null";
            }
            else {
                canonicalName = this.Eh.getClass().getCanonicalName();
            }
            append.append(canonicalName).append('\n');
            return sb.toString();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final gb creator = a.CREATOR;
            gb.a(this, parcel, n);
        }
    }
    
    public interface b<I, O>
    {
        int eW();
        
        int eX();
        
        I g(final O p0);
    }
}
