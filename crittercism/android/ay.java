// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONArray;

public final class ay implements UncaughtExceptionHandler
{
    private UncaughtExceptionHandler a;
    private final az b;
    
    public ay(final az b, final UncaughtExceptionHandler a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public final void uncaughtException(final Thread thread, final Throwable t) {
        try {
            final az b = this.b;
            if (b.q == null) {
                dy.b("Unable to handle application crash. Crittercism not yet initialized");
            }
            else {
                b.q.b();
                dr.a(b.c, true);
                if (!b.f.b()) {
                    if (!b.t) {
                        goto Label_0204;
                    }
                    new dk(new cv(b).a(br.e.f(), new JSONArray().put((Object)new bk(t, Thread.currentThread().getId()).a())), new dd(new dc(b.u.b(), "/android_v2/handle_crashes").a()), null).run();
                }
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t2) {
            dy.a("Unable to send crash", t2);
        }
        finally {
            if (this.a != null && !(this.a instanceof ay)) {
                this.a.uncaughtException(Thread.currentThread(), t);
            }
        }
    }
}
