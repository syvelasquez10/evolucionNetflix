// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.File;
import android.util.Log;

public final class j implements UncaughtExceptionHandler
{
    private UncaughtExceptionHandler a;
    
    public j(final UncaughtExceptionHandler a) {
        this.a = a;
    }
    
    @Override
    public final void uncaughtException(final Thread thread, final Throwable t) {
        while (true) {
            try {
                Object o = l.i();
                final at e = ((l)o).e;
                e.a((h)o);
                Label_0112: {
                    if (e.d()) {
                        break Label_0112;
                    }
                    try {
                        final File s = ((l)o).s;
                        if (s != null && !s.exists()) {
                            new StringBuilder("path = ").append(s.getAbsolutePath());
                            s.createNewFile();
                        }
                        ((l)o).h.a(t);
                        ((l)o).a(true);
                        ((l)o).m.a((h)o, ae.b.a(), ae.b.b());
                        if (this.a != null && !(this.a instanceof j)) {
                            o = this.a;
                            ((Thread.UncaughtExceptionHandler)o).uncaughtException(thread, t);
                        }
                    }
                    catch (Exception ex) {
                        new StringBuilder("Exception in setDidCrashOnLastAppLoad: ").append(ex.getClass().getName());
                    }
                }
            }
            catch (Exception o) {
                Log.w("CrittercismExceptionHandler", "Failed to log error with Crittercism.  Please contact us at support@crittercism.com.");
                new StringBuilder("Did not log error to Crittercism.  EXCEPTION: ").append(((l)o).getClass().getName());
                if (this.a != null && !(this.a instanceof j)) {
                    o = this.a;
                    continue;
                }
            }
            finally {
                if (this.a != null && !(this.a instanceof j)) {
                    this.a.uncaughtException(thread, t);
                }
            }
            break;
        }
    }
}
