// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Iterator;
import java.util.ArrayList;
import android.content.Context;
import java.util.List;

public final class df
{
    public List a;
    private Context b;
    
    public df(final Context b) {
        this.b = b;
        this.a = new ArrayList();
    }
    
    public final void a() {
        final ArrayList<Thread> list = new ArrayList<Thread>();
        final Iterator<di> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            list.add(new Thread(iterator.next()));
        }
        final Iterator<Thread> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().start();
        }
        final Iterator<Thread> iterator3 = list.iterator();
        while (iterator3.hasNext()) {
            iterator3.next().join();
        }
    }
    
    public final void a(final bq bq, final cz cz, final String s, final String s2, final String s3, final ar ar, final cx cx) {
        synchronized (this) {
            if (bq.b() > 0) {
                final bq a = bq.a(this.b);
                this.a.add(new dh(a, bq, ar, new db(s, s2).a(), cz.a(a, bq, s3, this.b, ar), cx));
            }
        }
    }
}
