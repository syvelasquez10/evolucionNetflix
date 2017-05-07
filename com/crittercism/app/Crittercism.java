// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import org.json.JSONObject;
import crittercism.android.bn$a;
import android.content.Context;
import crittercism.android.dr;
import crittercism.android.az;
import crittercism.android.dy;

public class Crittercism
{
    private static void a(final String s) {
        dy.b("Crittercism cannot be initialized", new NullPointerException(s + " was null"));
    }
    
    private static void b(final String s) {
        dy.b("Must initialize Crittercism before calling " + Crittercism.class.getName() + "." + s + "().  Request is being ignored...", new IllegalStateException());
    }
    
    public static boolean didCrashOnLastLoad() {
        try {
            final az a = az.A();
            if (!a.b) {
                b("didCrashOnLoad");
                return false;
            }
            if (!a.B()) {
                a.e.block();
                return dr.a;
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
        return false;
    }
    
    public static void initialize(final Context context, final String s, final CrittercismConfig crittercismConfig) {
        synchronized (Crittercism.class) {
            if (bn$a.a(s) == null) {
                throw new IllegalArgumentException("Invalid appID '" + s + "'. Crittercism cannot be initialized");
            }
        }
        Label_0061: {
            if (s != null) {
                break Label_0061;
            }
            while (true) {
            Block_8_Outer:
                while (true) {
                    try {
                        a(String.class.getCanonicalName());
                        // monitorexit(Crittercism.class)
                        return;
                        while (true) {
                            a(Context.class.getCanonicalName());
                            continue Block_8_Outer;
                            continue;
                        }
                    }
                    // iftrue(Label_0079:, context2 != null)
                    catch (ThreadDeath threadDeath) {
                        throw threadDeath;
                    }
                    catch (Throwable t) {
                        dy.a(t);
                        continue;
                    }
                    if (!az.A().b) {
                        try {
                            final long nanoTime = System.nanoTime();
                            final Context context2;
                            az.A().a(context2, s, crittercismConfig);
                            new StringBuilder("Crittercism finished initializing in ").append((System.nanoTime() - nanoTime) / 1000000L).append("ms");
                            dy.b();
                            continue;
                        }
                        catch (Exception ex) {
                            new StringBuilder("Exception in init > getInstance().initialize(..): ").append(ex.getClass().getName());
                            dy.b();
                            continue;
                        }
                        continue;
                    }
                    continue;
                }
            }
        }
    }
    
    public static void leaveBreadcrumb(final String s) {
        try {
            if (!az.A().b) {
                b("leaveBreadcrumb");
                return;
            }
            if (s == null) {
                dy.b("Cannot leave null breadcrumb", new NullPointerException());
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    public static void logHandledException(final Throwable t) {
        try {
            if (!az.A().b) {
                b("logHandledException");
                return;
            }
            if (!az.A().f.b()) {
                az.A().b(t);
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    public static void setMetadata(final JSONObject jsonObject) {
        try {
            if (!az.A().b) {
                b("setMetadata");
                return;
            }
            az.A().a(jsonObject);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
}
