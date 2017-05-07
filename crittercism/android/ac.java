// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import android.os.ConditionVariable;

public final class ac implements z
{
    private ConditionVariable a;
    private Map b;
    private at c;
    
    public ac(final ConditionVariable a, final at c) {
        this.a = a;
        this.b = new HashMap();
        this.c = c;
    }
    
    @Override
    public final void a() {
        this.a.block();
        if (!this.c.d()) {
            final aq e = this.c.e();
            if (e != null) {
                final boolean b = e.a && !e.b && e.c >= e.d && (e.c - e.d) % e.e == 0;
                this.b.put("shouldShowRateAppAlert", b);
                if (b) {
                    this.b.put("message", e.f);
                    this.b.put("title", e.g);
                }
            }
        }
    }
    
    @Override
    public final void b() {
        this.a.open();
    }
    
    @Override
    public final Map c() {
        return this.b;
    }
}
