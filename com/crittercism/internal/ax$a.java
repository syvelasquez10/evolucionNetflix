// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.os.Build$VERSION;
import java.net.URL;

final class ax$a implements Runnable
{
    @Override
    public final void run() {
        Block_0: {
            break Block_0;
        Label_0322:
            while (true) {
                boolean a;
                boolean a2;
                boolean a3;
                do {
                    while (true) {
                        d d = null;
                        e v = null;
                        Label_0257: {
                            break Label_0257;
                            a3 = false;
                            try {
                                ax.a.v.a(ax.a.u.a());
                                ax.a.v.b(ax.a.u.getPreserveQueryStringPatterns());
                                ax.a.p = new g(ax.a, new URL(ax.a.u.c.a + "/api/apm/network"));
                                ax.a.v.a(ax.a.p);
                                ax.a.v.a(ax.a);
                                new dx(ax.a.p, "OPTMZ").start();
                                if (h.a(ax.a.c).exists()) {
                                    break;
                                }
                                if (!ax.a.u.isServiceMonitoringEnabled()) {
                                    return;
                                }
                                d = new d(ax.a.c);
                                v = ax.a.v;
                                a = y.a(v, d);
                                dw.d(i.a(a, "non-SSL"));
                                if (Build$VERSION.SDK_INT < 19) {
                                    break Label_0257;
                                }
                                a2 = r.a(v, d);
                                dw.d(i.a(a2, "SSL"));
                                if (a2) {
                                    a3 = n.a(v, d);
                                    dw.d(i.a(a3, "provider"));
                                    continue Label_0322;
                                }
                                continue Label_0322;
                                dw.c("installed service monitoring");
                                return;
                            }
                            catch (Exception ex) {
                                dw.d("Exception in installApm: " + ex.getClass().getName());
                                dw.a(ex);
                                return;
                            }
                        }
                        a2 = (Build$VERSION.SDK_INT >= 14 && q.a(v, d));
                        continue;
                    }
                } while (a || a2 || a3);
                break;
            }
        }
    }
}
