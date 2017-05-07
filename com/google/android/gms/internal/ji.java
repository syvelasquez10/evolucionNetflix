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
    private void a(final StringBuilder sb, final ji$a ji$a, final Object o) {
        if (ji$a.hd() == 11) {
            sb.append(((ji)ji$a.hn().cast(o)).toString());
            return;
        }
        if (ji$a.hd() == 7) {
            sb.append("\"");
            sb.append(jz.bf((String)o));
            sb.append("\"");
            return;
        }
        sb.append(o);
    }
    
    private void a(final StringBuilder sb, final ji$a ji$a, final ArrayList<Object> list) {
        sb.append("[");
        for (int i = 0; i < list.size(); ++i) {
            if (i > 0) {
                sb.append(",");
            }
            final Object value = list.get(i);
            if (value != null) {
                this.a(sb, ji$a, value);
            }
        }
        sb.append("]");
    }
    
    protected <O, I> I a(final ji$a<I, O> ji$a, final Object o) {
        Object convertBack = o;
        if (((ji$a<Object, Object>)ji$a).Mz != null) {
            convertBack = ji$a.convertBack(o);
        }
        return (I)convertBack;
    }
    
    protected boolean a(final ji$a ji$a) {
        if (ji$a.he() != 11) {
            return this.bb(ji$a.hl());
        }
        if (ji$a.hk()) {
            return this.bd(ji$a.hl());
        }
        return this.bc(ji$a.hl());
    }
    
    protected Object b(final ji$a ji$a) {
        final String hl = ji$a.hl();
        if (ji$a.hn() != null) {
            n.a(this.ba(ji$a.hl()) == null, "Concrete field shouldn't be value object: %s", ji$a.hl());
            HashMap<String, Object> hashMap;
            if (ji$a.hk()) {
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
        return this.ba(ji$a.hl());
    }
    
    protected abstract Object ba(final String p0);
    
    protected abstract boolean bb(final String p0);
    
    protected boolean bc(final String s) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }
    
    protected boolean bd(final String s) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }
    
    public abstract HashMap<String, ji$a<?, ?>> hf();
    
    public HashMap<String, Object> hg() {
        return null;
    }
    
    public HashMap<String, Object> hh() {
        return null;
    }
    
    @Override
    public String toString() {
        final HashMap<String, ji$a<?, ?>> hf = this.hf();
        final StringBuilder sb = new StringBuilder(100);
        for (final String s : hf.keySet()) {
            final ji$a<Object, Object> ji$a = hf.get(s);
            if (this.a(ji$a)) {
                final HashMap<String, String> a = this.a((ji$a<HashMap<String, String>, Object>)ji$a, this.b(ji$a));
                if (sb.length() == 0) {
                    sb.append("{");
                }
                else {
                    sb.append(",");
                }
                sb.append("\"").append(s).append("\":");
                if (a == null) {
                    sb.append("null");
                }
                else {
                    switch (ji$a.he()) {
                        default: {
                            if (ji$a.hj()) {
                                this.a(sb, ji$a, (ArrayList<Object>)a);
                                continue;
                            }
                            this.a(sb, ji$a, a);
                            continue;
                        }
                        case 8: {
                            sb.append("\"").append(js.d((byte[])(Object)a)).append("\"");
                            continue;
                        }
                        case 9: {
                            sb.append("\"").append(js.e((byte[])(Object)a)).append("\"");
                            continue;
                        }
                        case 10: {
                            ka.a(sb, a);
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
}
