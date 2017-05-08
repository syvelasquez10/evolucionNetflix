// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONArray;

public final class aw implements UncaughtExceptionHandler
{
    private UncaughtExceptionHandler a;
    private final ax b;
    
    public aw(final ax b, final UncaughtExceptionHandler a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public final void uncaughtException(final Thread thread, final Throwable t) {
        try {
            final ax b = this.b;
            if (b.o == null) {
                dw.a("Unable to handle application crash. Crittercism not yet initialized");
            }
            else {
                b.o.a.block();
                dp.a(b.c, true);
                if (!b.g.a()) {
                    if (!b.s) {
                        goto Label_0210;
                    }
                    new dj(new cu(b).a(bp.e.q, new JSONArray().put((Object)new bi(t, Thread.currentThread().getId()).a())), new dc(new db(b.u.c.b, "/android_v2/handle_crashes").a()), null).run();
                }
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t2) {
            dw.a("Unable to send crash", t2);
        }
        finally {
            if (this.a != null && !(this.a instanceof aw)) {
                this.a.uncaughtException(Thread.currentThread(), t);
            }
        }
    }
}
