// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Handler;
import com.google.android.gms.common.api.PendingResult;
import android.net.Uri;
import android.os.Looper;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Context;
import java.util.concurrent.ConcurrentMap;

public class TagManager
{
    private static TagManager aay;
    private final DataLayer WK;
    private final r Zg;
    private final a aaw;
    private final ConcurrentMap<n, Boolean> aax;
    private final Context mContext;
    
    TagManager(final Context context, final a aaw, final DataLayer wk) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.aaw = aaw;
        this.aax = new ConcurrentHashMap<n, Boolean>();
        (this.WK = wk).a((DataLayer.b)new DataLayer.b() {
            @Override
            public void y(final Map<String, Object> map) {
                final Object value = map.get("event");
                if (value != null) {
                    TagManager.this.bT(value.toString());
                }
            }
        });
        this.WK.a((DataLayer.b)new d(this.mContext));
        this.Zg = new r();
    }
    
    private void bT(final String s) {
        final Iterator<n> iterator = this.aax.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().bp(s);
        }
    }
    
    public static TagManager getInstance(final Context context) {
        Label_0065: {
            synchronized (TagManager.class) {
                if (TagManager.aay != null) {
                    break Label_0065;
                }
                if (context == null) {
                    bh.w("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
            }
            final Context context2;
            TagManager.aay = new TagManager(context2, (a)new a() {
                @Override
                public o a(final Context context, final TagManager tagManager, final Looper looper, final String s, final int n, final r r) {
                    return new o(context, tagManager, looper, s, n, r);
                }
            }, new DataLayer((DataLayer.c)new v(context2)));
        }
        // monitorexit(TagManager.class)
        return TagManager.aay;
    }
    
    void a(final n n) {
        this.aax.put(n, true);
    }
    
    boolean b(final n n) {
        return this.aax.remove(n) != null;
    }
    
    boolean g(final Uri uri) {
    Label_0064_Outer:
        while (true) {
            while (true) {
                final cd kt;
                final String containerId;
                Label_0139: {
                    synchronized (this) {
                        kt = cd.kT();
                        if (kt.g(uri)) {
                            containerId = kt.getContainerId();
                            switch (TagManager$3.aaA[kt.kU().ordinal()]) {
                                case 1: {
                                    for (final n n : this.aax.keySet()) {
                                        if (n.getContainerId().equals(containerId)) {
                                            n.br(null);
                                            n.refresh();
                                        }
                                    }
                                    break;
                                }
                                case 2:
                                case 3: {
                                    break Label_0139;
                                }
                            }
                            return true;
                        }
                        return false;
                    }
                }
                for (final n n2 : this.aax.keySet()) {
                    if (n2.getContainerId().equals(containerId)) {
                        n2.br(kt.kV());
                        n2.refresh();
                    }
                    else {
                        if (n2.ke() == null) {
                            continue Label_0064_Outer;
                        }
                        n2.br(null);
                        n2.refresh();
                    }
                }
                continue;
            }
            return false;
        }
    }
    
    public DataLayer getDataLayer() {
        return this.WK;
    }
    
    public PendingResult<ContainerHolder> loadContainerDefaultOnly(final String s, final int n) {
        final o a = this.aaw.a(this.mContext, this, null, s, n, this.Zg);
        a.kh();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerDefaultOnly(final String s, final int n, final Handler handler) {
        final o a = this.aaw.a(this.mContext, this, handler.getLooper(), s, n, this.Zg);
        a.kh();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferFresh(final String s, final int n) {
        final o a = this.aaw.a(this.mContext, this, null, s, n, this.Zg);
        a.kj();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferFresh(final String s, final int n, final Handler handler) {
        final o a = this.aaw.a(this.mContext, this, handler.getLooper(), s, n, this.Zg);
        a.kj();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(final String s, final int n) {
        final o a = this.aaw.a(this.mContext, this, null, s, n, this.Zg);
        a.ki();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(final String s, final int n, final Handler handler) {
        final o a = this.aaw.a(this.mContext, this, handler.getLooper(), s, n, this.Zg);
        a.ki();
        return a;
    }
    
    public void setVerboseLoggingEnabled(final boolean b) {
        int logLevel;
        if (b) {
            logLevel = 2;
        }
        else {
            logLevel = 5;
        }
        bh.setLogLevel(logLevel);
    }
    
    interface a
    {
        o a(final Context p0, final TagManager p1, final Looper p2, final String p3, final int p4, final r p5);
    }
}
