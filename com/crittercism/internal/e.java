// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.Set;
import java.util.List;

public final class e
{
    List a;
    final Set b;
    final Set c;
    private Executor d;
    
    public e(final Executor executor) {
        this(executor, new LinkedList(), new LinkedList());
    }
    
    private e(final Executor d, final List list, final List list2) {
        this.a = new LinkedList();
        this.b = new HashSet();
        this.c = new HashSet();
        this.d = d;
        this.a(list);
        this.b(list2);
    }
    
    @Deprecated
    public final void a(final c c) {
        this.a(c, c$a.e);
    }
    
    public final void a(final c c, final c$a c2) {
        if (c.b) {
            return;
        }
        c.b = true;
        c.c = c2;
        this.d.execute(new e$a(this, c, (byte)0));
    }
    
    public final void a(final f f) {
        synchronized (this.a) {
            this.a.add(f);
        }
    }
    
    public final void a(final List list) {
        synchronized (this.b) {
            this.b.addAll(list);
            this.b.remove(null);
        }
    }
    
    public final void b(final List list) {
        synchronized (this.c) {
            this.c.addAll(list);
            this.c.remove(null);
        }
    }
}
