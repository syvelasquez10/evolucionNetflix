// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import android.os.ConditionVariable;

public final class y implements z
{
    private ConditionVariable a;
    private Map b;
    private at c;
    
    public y(final ConditionVariable a, final at c) {
        this.a = a;
        this.b = new HashMap();
        this.c = c;
    }
    
    @Override
    public final void a() {
        this.a.block();
        if (!this.c.d()) {
            final an b = this.c.b();
            if (b != null) {
                this.b.put("didCrashOnLastAppLoad", b.a);
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
