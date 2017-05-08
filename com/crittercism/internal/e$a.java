// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.List;
import java.util.Iterator;

final class e$a implements Runnable
{
    final /* synthetic */ e a;
    private c b;
    
    private e$a(final e a, final c b) {
        this.a = a;
        this.b = b;
    }
    
    private boolean a(final c c) {
        final String a = c.a();
        synchronized (this.a.b) {
            final Iterator<String> iterator = (Iterator<String>)this.a.b.iterator();
            while (iterator.hasNext()) {
                if (a.contains(iterator.next())) {
                    return true;
                }
            }
            return false;
        }
    }
    
    private boolean a(final String s) {
        synchronized (this.a.c) {
            final Iterator<String> iterator = this.a.c.iterator();
            while (iterator.hasNext()) {
                if (s.contains(iterator.next())) {
                    return false;
                }
            }
            return true;
        }
    }
    
    @Override
    public final void run() {
        if (this.a(this.b)) {
            return;
        }
        final String a = this.b.a();
        if (this.a(a)) {
            final int index = a.indexOf("?");
            if (index != -1) {
                this.b.a(a.substring(0, index));
            }
        }
        synchronized (this.a.a) {
            final Iterator<f> iterator = this.a.a.iterator();
            while (iterator.hasNext()) {
                iterator.next().a(this.b);
            }
        }
    }
    // monitorexit(list)
}
