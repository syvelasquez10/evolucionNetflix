// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import android.os.ConditionVariable;

public final class ab implements z
{
    private ConditionVariable a;
    private Map b;
    private at c;
    
    public ab(final ConditionVariable a, final at c) {
        this.a = a;
        this.b = new HashMap();
        this.c = c;
    }
    
    @Override
    public final void a() {
        this.a.block();
        final ap c = this.c.c();
        if (c != null) {
            this.b.put("optOutStatus", c.a());
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
