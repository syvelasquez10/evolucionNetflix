// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d$a;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.internal.c$i;
import com.google.android.gms.internal.c$f;
import java.util.HashMap;
import com.google.android.gms.internal.c$j;
import android.content.Context;
import java.util.Map;

public class Container
{
    private final String anR;
    private final DataLayer anS;
    private ct anT;
    private Map<String, Container$FunctionCallMacroCallback> anU;
    private Map<String, Container$FunctionCallTagCallback> anV;
    private volatile long anW;
    private volatile String anX;
    private final Context mContext;
    
    Container(final Context mContext, final DataLayer anS, final String anR, final long anW, final c$j c$j) {
        this.anU = new HashMap<String, Container$FunctionCallMacroCallback>();
        this.anV = new HashMap<String, Container$FunctionCallTagCallback>();
        this.anX = "";
        this.mContext = mContext;
        this.anS = anS;
        this.anR = anR;
        this.anW = anW;
        this.a(c$j.gs);
        if (c$j.gr != null) {
            this.a(c$j.gr);
        }
    }
    
    Container(final Context mContext, final DataLayer anS, final String anR, final long anW, final cr$c cr$c) {
        this.anU = new HashMap<String, Container$FunctionCallMacroCallback>();
        this.anV = new HashMap<String, Container$FunctionCallTagCallback>();
        this.anX = "";
        this.mContext = mContext;
        this.anS = anS;
        this.anR = anR;
        this.anW = anW;
        this.a(cr$c);
    }
    
    private void a(final c$f c$f) {
        if (c$f == null) {
            throw new NullPointerException();
        }
        try {
            this.a(cr.b(c$f));
        }
        catch (cr$g cr$g) {
            bh.T("Not loading resource: " + c$f + " because it is invalid: " + cr$g.toString());
        }
    }
    
    private void a(final cr$c cr$c) {
        this.anX = cr$c.getVersion();
        this.a(new ct(this.mContext, cr$c, this.anS, new Container$a(this, null), new Container$b(this, null), this.cn(this.anX)));
    }
    
    private void a(final ct anT) {
        synchronized (this) {
            this.anT = anT;
        }
    }
    
    private void a(final c$i[] array) {
        final ArrayList<c$i> list = new ArrayList<c$i>();
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        this.nR().k(list);
    }
    
    private ct nR() {
        synchronized (this) {
            return this.anT;
        }
    }
    
    Container$FunctionCallMacroCallback ck(final String s) {
        synchronized (this.anU) {
            return this.anU.get(s);
        }
    }
    
    Container$FunctionCallTagCallback cl(final String s) {
        synchronized (this.anV) {
            return this.anV.get(s);
        }
    }
    
    void cm(final String s) {
        this.nR().cm(s);
    }
    
    ag cn(final String s) {
        if (ce.oH().oI().equals(ce$a.apY)) {}
        return new br();
    }
    
    public boolean getBoolean(final String s) {
        final ct nr = this.nR();
        if (nr == null) {
            bh.T("getBoolean called for closed container.");
            return di.pF();
        }
        try {
            return di.n(nr.cO(s).getObject());
        }
        catch (Exception ex) {
            bh.T("Calling getBoolean() threw an exception: " + ex.getMessage() + " Returning default value.");
            return di.pF();
        }
    }
    
    public String getContainerId() {
        return this.anR;
    }
    
    public double getDouble(final String s) {
        final ct nr = this.nR();
        if (nr == null) {
            bh.T("getDouble called for closed container.");
            return di.pE();
        }
        try {
            return di.m(nr.cO(s).getObject());
        }
        catch (Exception ex) {
            bh.T("Calling getDouble() threw an exception: " + ex.getMessage() + " Returning default value.");
            return di.pE();
        }
    }
    
    public long getLastRefreshTime() {
        return this.anW;
    }
    
    public long getLong(final String s) {
        final ct nr = this.nR();
        if (nr == null) {
            bh.T("getLong called for closed container.");
            return di.pD();
        }
        try {
            return di.l(nr.cO(s).getObject());
        }
        catch (Exception ex) {
            bh.T("Calling getLong() threw an exception: " + ex.getMessage() + " Returning default value.");
            return di.pD();
        }
    }
    
    public String getString(String j) {
        final ct nr = this.nR();
        if (nr == null) {
            bh.T("getString called for closed container.");
            return di.pH();
        }
        try {
            j = di.j(nr.cO(j).getObject());
            return j;
        }
        catch (Exception ex) {
            bh.T("Calling getString() threw an exception: " + ex.getMessage() + " Returning default value.");
            return di.pH();
        }
    }
    
    public boolean isDefault() {
        return this.getLastRefreshTime() == 0L;
    }
    
    String nQ() {
        return this.anX;
    }
    
    public void registerFunctionCallMacroCallback(final String s, final Container$FunctionCallMacroCallback container$FunctionCallMacroCallback) {
        if (container$FunctionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.anU) {
            this.anU.put(s, container$FunctionCallMacroCallback);
        }
    }
    
    public void registerFunctionCallTagCallback(final String s, final Container$FunctionCallTagCallback container$FunctionCallTagCallback) {
        if (container$FunctionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.anV) {
            this.anV.put(s, container$FunctionCallTagCallback);
        }
    }
    
    void release() {
        this.anT = null;
    }
    
    public void unregisterFunctionCallMacroCallback(final String s) {
        synchronized (this.anU) {
            this.anU.remove(s);
        }
    }
    
    public void unregisterFunctionCallTagCallback(final String s) {
        synchronized (this.anV) {
            this.anV.remove(s);
        }
    }
}
