// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import android.net.Uri$Builder;
import java.util.List;
import android.net.Uri;
import com.google.android.gms.internal.d$a;
import java.util.Map;
import java.util.HashSet;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;
import android.content.Context;
import java.util.Set;

class i extends dg
{
    private static final String ID;
    private static final String URL;
    private static final String anK;
    private static final String anL;
    static final String anM;
    private static final Set<String> anN;
    private final i$a anO;
    private final Context mContext;
    
    static {
        ID = a.ay.toString();
        URL = b.eX.toString();
        anK = b.bl.toString();
        anL = b.eW.toString();
        anM = "gtm_" + i.ID + "_unrepeatable";
        anN = new HashSet<String>();
    }
    
    public i(final Context context) {
        this(context, new i$1(context));
    }
    
    i(final Context mContext, final i$a anO) {
        super(i.ID, new String[] { i.URL });
        this.anO = anO;
        this.mContext = mContext;
    }
    
    private boolean cg(final String s) {
        while (true) {
            boolean b = true;
            synchronized (this) {
                if (!this.ci(s)) {
                    if (!this.ch(s)) {
                        return false;
                    }
                    i.anN.add(s);
                }
                return b;
            }
            b = false;
            return b;
        }
    }
    
    @Override
    public void E(final Map<String, d$a> map) {
        String j;
        if (map.get(i.anL) != null) {
            j = di.j(map.get(i.anL));
        }
        else {
            j = null;
        }
        if (j == null || !this.cg(j)) {
            final Uri$Builder buildUpon = Uri.parse(di.j(map.get(i.URL))).buildUpon();
            final d$a d$a = map.get(i.anK);
            if (d$a != null) {
                final Object o = di.o(d$a);
                if (!(o instanceof List)) {
                    bh.T("ArbitraryPixel: additional params not a list: not sending partial hit: " + buildUpon.build().toString());
                    return;
                }
                for (final Map<Object, V> next : (List<Object>)o) {
                    if (!(next instanceof Map)) {
                        bh.T("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + buildUpon.build().toString());
                        return;
                    }
                    for (final Map.Entry<Object, V> entry : next.entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            final String string = buildUpon.build().toString();
            this.anO.nM().cw(string);
            bh.V("ArbitraryPixel: url = " + string);
            if (j != null) {
                synchronized (i.class) {
                    i.anN.add(j);
                    cz.a(this.mContext, i.anM, j, "true");
                }
            }
        }
    }
    
    boolean ch(final String s) {
        return this.mContext.getSharedPreferences(i.anM, 0).contains(s);
    }
    
    boolean ci(final String s) {
        return i.anN.contains(s);
    }
}
