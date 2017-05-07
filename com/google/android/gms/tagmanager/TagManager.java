// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import android.net.Uri;
import android.content.ComponentCallbacks;
import android.os.Build$VERSION;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import android.content.Context;
import java.util.concurrent.ConcurrentMap;

public class TagManager
{
    private static TagManager arC;
    private final DataLayer anS;
    private final r aqj;
    private final cx arA;
    private final ConcurrentMap<n, Boolean> arB;
    private final TagManager$a arz;
    private final Context mContext;
    
    TagManager(final Context context, final TagManager$a arz, final DataLayer anS, final cx arA) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.arA = arA;
        this.arz = arz;
        this.arB = new ConcurrentHashMap<n, Boolean>();
        (this.anS = anS).a(new TagManager$1(this));
        this.anS.a(new d(this.mContext));
        this.aqj = new r();
        this.pw();
    }
    
    private void cQ(final String s) {
        final Iterator<n> iterator = this.arB.keySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().cm(s);
        }
    }
    
    public static TagManager getInstance(final Context context) {
        Label_0068: {
            synchronized (TagManager.class) {
                if (TagManager.arC != null) {
                    break Label_0068;
                }
                if (context == null) {
                    bh.T("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
            }
            final Context context2;
            TagManager.arC = new TagManager(context2, new TagManager$2(), new DataLayer(new v(context2)), cy.pu());
        }
        // monitorexit(TagManager.class)
        return TagManager.arC;
    }
    
    private void pw() {
        if (Build$VERSION.SDK_INT >= 14) {
            this.mContext.registerComponentCallbacks((ComponentCallbacks)new TagManager$3(this));
        }
    }
    
    void a(final n n) {
        this.arB.put(n, true);
    }
    
    boolean b(final n n) {
        return this.arB.remove(n) != null;
    }
    
    public void dispatch() {
        this.arA.dispatch();
    }
    
    public DataLayer getDataLayer() {
        return this.anS;
    }
    
    boolean i(final Uri uri) {
    Label_0064_Outer:
        while (true) {
            while (true) {
                final ce oh;
                final String containerId;
                Label_0139: {
                    synchronized (this) {
                        oh = ce.oH();
                        if (oh.i(uri)) {
                            containerId = oh.getContainerId();
                            switch (TagManager$4.arE[oh.oI().ordinal()]) {
                                case 1: {
                                    for (final n n : this.arB.keySet()) {
                                        if (n.getContainerId().equals(containerId)) {
                                            n.co(null);
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
                for (final n n2 : this.arB.keySet()) {
                    if (n2.getContainerId().equals(containerId)) {
                        n2.co(oh.oJ());
                        n2.refresh();
                    }
                    else {
                        if (n2.nS() == null) {
                            continue Label_0064_Outer;
                        }
                        n2.co(null);
                        n2.refresh();
                    }
                }
                continue;
            }
            return false;
        }
    }
    
    public PendingResult<ContainerHolder> loadContainerDefaultOnly(final String s, final int n) {
        final o a = this.arz.a(this.mContext, this, null, s, n, this.aqj);
        a.nV();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerDefaultOnly(final String s, final int n, final Handler handler) {
        final o a = this.arz.a(this.mContext, this, handler.getLooper(), s, n, this.aqj);
        a.nV();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferFresh(final String s, final int n) {
        final o a = this.arz.a(this.mContext, this, null, s, n, this.aqj);
        a.nX();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferFresh(final String s, final int n, final Handler handler) {
        final o a = this.arz.a(this.mContext, this, handler.getLooper(), s, n, this.aqj);
        a.nX();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(final String s, final int n) {
        final o a = this.arz.a(this.mContext, this, null, s, n, this.aqj);
        a.nW();
        return a;
    }
    
    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(final String s, final int n, final Handler handler) {
        final o a = this.arz.a(this.mContext, this, handler.getLooper(), s, n, this.aqj);
        a.nW();
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
}
