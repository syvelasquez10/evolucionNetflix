// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import org.json.JSONException;
import org.json.JSONObject;
import com.crittercism.internal.at;
import android.os.Build$VERSION;
import android.content.Context;
import com.crittercism.internal.ax;
import com.crittercism.internal.dw;
import com.crittercism.internal.bl$a;

public class Crittercism
{
    private static void a(final bl$a bl$a) {
        throw new IllegalArgumentException("Crittercism cannot be initialized. " + bl$a.getMessage());
    }
    
    private static void a(final String s) {
        dw.b("Crittercism cannot be initialized", new NullPointerException(s + " was null"));
    }
    
    public static boolean didCrashOnLastLoad() {
        try {
            final ax c = ax.C();
            if (c.g.a()) {
                g("didCrashOnLastLoad");
                return false;
            }
            if (!c.b) {
                g("didCrashOnLoad");
                return false;
            }
            goto Label_0038;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
        return false;
    }
    
    private static void g(final String s) {
        dw.b("Must initialize Crittercism before calling " + Crittercism.class.getName() + "." + s + "(). Request is being ignored...", new IllegalStateException());
    }
    
    private static void h(final String s) {
        dw.d("User has opted out of Crittercism. " + Crittercism.class.getName() + "." + s + "() call is being ignored...");
    }
    
    public static void initialize(final Context context, final String e, final CrittercismConfig t) {
        // monitorenter(Crittercism.class)
        Label_0019: {
            if (e != null) {
                break Label_0019;
            }
        Label_0097_Outer:
            while (true) {
            Label_0097:
                while (true) {
                    ax c;
                    try {
                        a(String.class.getCanonicalName());
                        return;
                        while (true) {
                            a(Context.class.getCanonicalName());
                            return;
                            continue Label_0097_Outer;
                        }
                    }
                    // iftrue(Label_0048:, context != null)
                    catch (bl$a bl$a) {
                        a(bl$a);
                        return;
                    }
                    catch (ThreadDeath threadDeath) {
                        throw threadDeath;
                        // iftrue(Label_0142:, Build$VERSION.SDK_INT >= 14)
                        long nanoTime;
                        while (true) {
                            dw.b("Crittercism is not supported for Android API less than 14 (ICS).");
                            break Label_0097;
                            nanoTime = System.nanoTime();
                            c = ax.C();
                            continue Label_0097_Outer;
                        }
                        nanoTime = (System.nanoTime() - nanoTime) / 1000000L;
                        dw.d("Crittercism finished initializing in " + nanoTime + "ms");
                        return;
                    }
                    catch (Throwable t2) {
                        dw.b(t2);
                        return;
                    }
                    finally {
                    }
                    // monitorexit(Crittercism.class)
                    Label_0142: {
                        c.e = e;
                    }
                    final Context c2;
                    c.c = c2;
                    c.d = new at(c2);
                    c.t = t;
                    if (c.g.a()) {
                        dw.d("User opted out. Not initializing Crittercism");
                        continue Label_0097;
                    }
                    c.D();
                    continue Label_0097;
                }
            }
        }
    }
    
    public static void leaveBreadcrumb(final String s) {
        ax c;
        try {
            c = ax.C();
            if (c.g.a()) {
                h("leaveBreadcrumb");
                return;
            }
            if (!c.b) {
                g("leaveBreadcrumb");
                return;
            }
            goto Label_0036;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        c.a(s);
    }
    
    public static void logHandledException(final Throwable t) {
        try {
            final ax c = ax.C();
            if (c.g.a()) {
                h("logHandledException");
                return;
            }
            if (!c.b) {
                g("logHandledException");
                return;
            }
            goto Label_0036;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public static void setMetadata(final JSONObject jsonObject) {
        try {
            final ax c = ax.C();
            if (c.g.a()) {
                h("setMetadata");
                return;
            }
            if (!c.b) {
                g("setMetadata");
                return;
            }
            goto Label_0036;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public static void setUsername(final String s) {
        ax c;
        try {
            c = ax.C();
            if (c.g.a()) {
                h("setUsername");
                return;
            }
            if (!c.b) {
                g("setUsername");
                return;
            }
            goto Label_0036;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.putOpt("username", (Object)s);
            c.a(jsonObject);
        }
        catch (JSONException ex) {
            dw.b("Crittercism.setUsername()", (Throwable)ex);
        }
    }
}
