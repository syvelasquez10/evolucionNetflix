// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Map;

public final class dk extends di
{
    public Map a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    private dr g;
    private ar h;
    
    private void a(final String s, final Object o) {
        synchronized (this) {
            this.a.put(s, o);
        }
    }
    
    @Override
    public final void a() {
        final boolean b = false;
        final boolean a = this.g.a();
        if (this.b) {
            this.a("optOutStatus", a);
        }
        if (!a) {
            if (this.c) {
                this.a("crashedOnLastLoad", dp.a);
            }
            if (this.d) {
                this.a("userUUID", this.h.c());
            }
            if (this.e) {
                final ds a2 = ax.C().A;
                if (a2 != null) {
                    boolean b2 = b;
                    if (a2.a.getBoolean("rateMyAppEnabled", false)) {
                        b2 = b;
                        if (!a2.a.getBoolean("hasRatedApp", false)) {
                            final int a3 = a2.a();
                            final int int1 = a2.a.getInt("rateAfterNumLoads", 5);
                            b2 = b;
                            if (a3 >= int1) {
                                b2 = b;
                                if ((a3 - int1) % a2.a.getInt("remindAfterNumLoads", 5) == 0) {
                                    b2 = true;
                                }
                            }
                        }
                    }
                    this.a("shouldShowRateAppAlert", b2);
                    this.a("message", a2.b());
                    this.a("title", a2.c());
                }
            }
        }
    }
}
