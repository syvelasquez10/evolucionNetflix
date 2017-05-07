// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import android.net.Uri$Builder;
import java.util.List;
import android.net.Uri;
import com.google.android.gms.internal.d;
import java.util.Map;
import java.util.HashSet;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;
import android.content.Context;
import java.util.Set;

class i extends df
{
    private static final String ID;
    private static final String URL;
    private static final String WC;
    private static final String WD;
    static final String WE;
    private static final Set<String> WF;
    private final a WG;
    private final Context mContext;
    
    static {
        ID = com.google.android.gms.internal.a.ap.toString();
        URL = b.eo.toString();
        WC = b.aX.toString();
        WD = b.en.toString();
        WE = "gtm_" + i.ID + "_unrepeatable";
        WF = new HashSet<String>();
    }
    
    public i(final Context context) {
        this(context, (a)new a() {
            @Override
            public aq jY() {
                return y.F(context);
            }
        });
    }
    
    i(final Context mContext, final a wg) {
        super(i.ID, new String[] { i.URL });
        this.WG = wg;
        this.mContext = mContext;
    }
    
    private boolean bj(final String s) {
        while (true) {
            boolean b = true;
            synchronized (this) {
                if (!this.bl(s)) {
                    if (!this.bk(s)) {
                        return false;
                    }
                    i.WF.add(s);
                }
                return b;
            }
            b = false;
            return b;
        }
    }
    
    boolean bk(final String s) {
        return this.mContext.getSharedPreferences(i.WE, 0).contains(s);
    }
    
    boolean bl(final String s) {
        return i.WF.contains(s);
    }
    
    @Override
    public void z(final Map<String, d.a> map) {
        String j;
        if (map.get(i.WD) != null) {
            j = dh.j(map.get(i.WD));
        }
        else {
            j = null;
        }
        if (j == null || !this.bj(j)) {
            final Uri$Builder buildUpon = Uri.parse(dh.j(map.get(i.URL))).buildUpon();
            final d.a a = map.get(i.WC);
            if (a != null) {
                final Object o = dh.o(a);
                if (!(o instanceof List)) {
                    bh.w("ArbitraryPixel: additional params not a list: not sending partial hit: " + buildUpon.build().toString());
                    return;
                }
                for (final Map<Object, V> next : (List<Object>)o) {
                    if (!(next instanceof Map)) {
                        bh.w("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + buildUpon.build().toString());
                        return;
                    }
                    for (final Map.Entry<Object, V> entry : next.entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            final String string = buildUpon.build().toString();
            this.WG.jY().bz(string);
            bh.y("ArbitraryPixel: url = " + string);
            if (j != null) {
                synchronized (i.class) {
                    i.WF.add(j);
                    cy.a(this.mContext, i.WE, j, "true");
                }
            }
        }
    }
    
    public interface a
    {
        aq jY();
    }
}
