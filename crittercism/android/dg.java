// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.concurrent.ExecutorService;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

public final class dg
{
    private Context a;
    private List b;
    
    public dg(final Context a) {
        this.a = a;
        this.b = new ArrayList();
    }
    
    public final void a() {
        final ArrayList<Thread> list = new ArrayList<Thread>();
        final Iterator<dj> iterator = this.b.iterator();
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
    
    public final void a(final bs bs, final da da, final String s, final String s2, final String s3, final au au, final cy cy) {
        synchronized (this) {
            if (bs.b() > 0) {
                final bs a = bs.a(this.a);
                this.b.add(new di(a, bs, au, new dc(s, s2).a(), da.a(a, bs, s3, this.a, au), cy));
            }
        }
    }
    
    public final void a(final dh dh, final ExecutorService executorService) {
        for (final dj dj : this.b) {
            if (!dh.a(dj)) {
                executorService.execute(dj);
            }
        }
    }
}
