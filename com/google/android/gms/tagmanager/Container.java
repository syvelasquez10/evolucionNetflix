// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.internal.c;
import android.content.Context;
import java.util.Map;

public class Container
{
    private final String WJ;
    private final DataLayer WK;
    private cs WL;
    private Map<String, FunctionCallMacroCallback> WM;
    private Map<String, FunctionCallTagCallback> WN;
    private volatile long WO;
    private volatile String WP;
    private final Context mContext;
    
    Container(final Context mContext, final DataLayer wk, final String wj, final long wo, final c.j j) {
        this.WM = new HashMap<String, FunctionCallMacroCallback>();
        this.WN = new HashMap<String, FunctionCallTagCallback>();
        this.WP = "";
        this.mContext = mContext;
        this.WK = wk;
        this.WJ = wj;
        this.WO = wo;
        this.a(j.fK);
        if (j.fJ != null) {
            this.a(j.fJ);
        }
    }
    
    Container(final Context mContext, final DataLayer wk, final String wj, final long wo, final cq.c c) {
        this.WM = new HashMap<String, FunctionCallMacroCallback>();
        this.WN = new HashMap<String, FunctionCallTagCallback>();
        this.WP = "";
        this.mContext = mContext;
        this.WK = wk;
        this.WJ = wj;
        this.WO = wo;
        this.a(c);
    }
    
    private void a(final c.f f) {
        if (f == null) {
            throw new NullPointerException();
        }
        try {
            this.a(cq.b(f));
        }
        catch (cq.g g) {
            bh.w("Not loading resource: " + f + " because it is invalid: " + g.toString());
        }
    }
    
    private void a(final cq.c c) {
        this.WP = c.getVersion();
        this.a(new cs(this.mContext, c, this.WK, new a(), new b(), this.bq(this.WP)));
    }
    
    private void a(final cs wl) {
        synchronized (this) {
            this.WL = wl;
        }
    }
    
    private void a(final c.i[] array) {
        final ArrayList<c.i> list = new ArrayList<c.i>();
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        this.kd().e(list);
    }
    
    private cs kd() {
        synchronized (this) {
            return this.WL;
        }
    }
    
    FunctionCallMacroCallback bn(final String s) {
        synchronized (this.WM) {
            return this.WM.get(s);
        }
    }
    
    FunctionCallTagCallback bo(final String s) {
        synchronized (this.WN) {
            return this.WN.get(s);
        }
    }
    
    void bp(final String s) {
        this.kd().bp(s);
    }
    
    ag bq(final String s) {
        if (cd.kT().kU().equals(cd.a.YV)) {}
        return new bq();
    }
    
    public boolean getBoolean(final String s) {
        final cs kd = this.kd();
        if (kd == null) {
            bh.w("getBoolean called for closed container.");
            return dh.lQ();
        }
        try {
            return dh.n(kd.bR(s).getObject());
        }
        catch (Exception ex) {
            bh.w("Calling getBoolean() threw an exception: " + ex.getMessage() + " Returning default value.");
            return dh.lQ();
        }
    }
    
    public String getContainerId() {
        return this.WJ;
    }
    
    public double getDouble(final String s) {
        final cs kd = this.kd();
        if (kd == null) {
            bh.w("getDouble called for closed container.");
            return dh.lP();
        }
        try {
            return dh.m(kd.bR(s).getObject());
        }
        catch (Exception ex) {
            bh.w("Calling getDouble() threw an exception: " + ex.getMessage() + " Returning default value.");
            return dh.lP();
        }
    }
    
    public long getLastRefreshTime() {
        return this.WO;
    }
    
    public long getLong(final String s) {
        final cs kd = this.kd();
        if (kd == null) {
            bh.w("getLong called for closed container.");
            return dh.lO();
        }
        try {
            return dh.l(kd.bR(s).getObject());
        }
        catch (Exception ex) {
            bh.w("Calling getLong() threw an exception: " + ex.getMessage() + " Returning default value.");
            return dh.lO();
        }
    }
    
    public String getString(String j) {
        final cs kd = this.kd();
        if (kd == null) {
            bh.w("getString called for closed container.");
            return dh.lS();
        }
        try {
            j = dh.j(kd.bR(j).getObject());
            return j;
        }
        catch (Exception ex) {
            bh.w("Calling getString() threw an exception: " + ex.getMessage() + " Returning default value.");
            return dh.lS();
        }
    }
    
    public boolean isDefault() {
        return this.getLastRefreshTime() == 0L;
    }
    
    String kc() {
        return this.WP;
    }
    
    public void registerFunctionCallMacroCallback(final String s, final FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.WM) {
            this.WM.put(s, functionCallMacroCallback);
        }
    }
    
    public void registerFunctionCallTagCallback(final String s, final FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.WN) {
            this.WN.put(s, functionCallTagCallback);
        }
    }
    
    void release() {
        this.WL = null;
    }
    
    public void unregisterFunctionCallMacroCallback(final String s) {
        synchronized (this.WM) {
            this.WM.remove(s);
        }
    }
    
    public void unregisterFunctionCallTagCallback(final String s) {
        synchronized (this.WN) {
            this.WN.remove(s);
        }
    }
    
    public interface FunctionCallMacroCallback
    {
        Object getValue(final String p0, final Map<String, Object> p1);
    }
    
    public interface FunctionCallTagCallback
    {
        void execute(final String p0, final Map<String, Object> p1);
    }
    
    private class a implements s.a
    {
        @Override
        public Object b(final String s, final Map<String, Object> map) {
            final FunctionCallMacroCallback bn = Container.this.bn(s);
            if (bn == null) {
                return null;
            }
            return bn.getValue(s, map);
        }
    }
    
    private class b implements s.a
    {
        @Override
        public Object b(final String s, final Map<String, Object> map) {
            final FunctionCallTagCallback bo = Container.this.bo(s);
            if (bo != null) {
                bo.execute(s, map);
            }
            return dh.lS();
        }
    }
}
