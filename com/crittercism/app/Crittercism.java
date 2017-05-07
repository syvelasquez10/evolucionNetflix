// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import crittercism.android.as;
import crittercism.android.l$3;
import crittercism.android.l$1;
import crittercism.android.l$9;
import android.os.Build$VERSION;
import crittercism.android.s;
import crittercism.android.af;
import crittercism.android.j;
import crittercism.android.i;
import crittercism.android.ai;
import java.io.File;
import crittercism.android.v;
import crittercism.android.u;
import crittercism.android.x;
import crittercism.android.n;
import crittercism.android.m;
import org.json.JSONObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import android.util.Log;
import crittercism.android.aq;
import android.app.AlertDialog;
import android.content.Context;
import crittercism.android.l;

public class Crittercism
{
    @Deprecated
    public static boolean didCrashOnLastAppLoad() {
        try {
            return l.i().p;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static AlertDialog generateRateMyAppAlertDialog(final Context context) {
        String g = null;
        final l i = l.i();
        final aq e = i.e.e();
        String f;
        if (e != null) {
            f = e.f;
        }
        else {
            f = null;
        }
        if (e != null) {
            g = e.g;
        }
        return i.a(context, g, f);
    }
    
    public static AlertDialog generateRateMyAppAlertDialog(final Context context, final String s, final String s2) {
        return l.i().a(context, s, s2);
    }
    
    public static String getNotificationTitle() {
        return l.i().t.getNotificationTitle();
    }
    
    @Deprecated
    public static boolean getOptOutStatus() {
        Log.w("Crittercism", "getOptOutStatus: This method is deprecated!");
        return l.i().e.d();
    }
    
    @Deprecated
    public static String getUserUUID() {
        final FutureTask<String> futureTask = new FutureTask<String>(new Crittercism$2());
        final ExecutorService l = crittercism.android.l.i().l;
        try {
            l.execute(futureTask);
            while (!futureTask.isDone()) {
                final String s = futureTask.get(2500L, TimeUnit.MILLISECONDS);
            }
            goto Label_0058;
        }
        catch (TimeoutException ex2) {}
        catch (Exception ex) {
            new StringBuilder("Exception in getUserUUID: ").append(ex.getClass().getName());
            return null;
        }
    }
    
    @Deprecated
    public static boolean init(final Context context, final String s, final JSONObject... array) {
        boolean didCrashOnLastAppLoad = false;
        synchronized (Crittercism.class) {
            if (s.contains("CRITTERCISM_APP_ID")) {
                Log.e("Crittercism", "ERROR: Crittercism will not work unless you enter a valid Crittercism App ID. Check your settings page to find the ID.");
            }
            else {
                JSONObject jsonObject = new JSONObject();
                if (array.length > 0) {
                    jsonObject = array[0];
                }
                initialize(context, s, new CrittercismConfig(jsonObject));
                didCrashOnLastAppLoad = didCrashOnLastAppLoad();
            }
            return didCrashOnLastAppLoad;
        }
    }
    
    public static void initialize(final Context context, final String s) {
        synchronized (Crittercism.class) {
            initialize(context, s, new CrittercismConfig());
        }
    }
    
    public static void initialize(final Context f, final String s, final CrittercismConfig crittercismConfig) {
        while (true) {
            while (true) {
                Label_0343: {
                    synchronized (Crittercism.class) {
                        if (s.contains("CRITTERCISM_APP_ID")) {
                            Log.e("Crittercism", "ERROR: Crittercism will not work unless you enter a valid Crittercism App ID. Check your settings page to find the ID.");
                        }
                        else if (!l.i().b) {
                            try {
                                final l i = l.i();
                                i.t = new m(crittercismConfig);
                                i.b = true;
                                i.f = f;
                                i.g = new n(i.t);
                                i.h = new x(i.t);
                                i.i = new u(i.t);
                                i.j = new v(i.t);
                                i.s = new File(i.f.getFilesDir().getAbsolutePath() + "/" + "didCrash.txt");
                                if (i.s == null) {
                                    break Label_0343;
                                }
                                final boolean exists = i.s.exists();
                                i.p = exists;
                                i.k = new ai(i);
                                i.c = new i(f, s, "3.1.4", i.t);
                                final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
                                if (!(defaultUncaughtExceptionHandler instanceof j)) {
                                    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new j(defaultUncaughtExceptionHandler));
                                }
                                (i.r = new Thread(i.k)).start();
                                Log.i("CrittercismInstance", "Crittercism Initialized.");
                            }
                            catch (Exception ex) {
                                new StringBuilder("Exception in init > getInstance().initialize(..): ").append(ex.getClass().getName());
                            }
                        }
                        return;
                    }
                }
                final boolean exists = false;
                continue;
            }
        }
    }
    
    public static void leaveBreadcrumb(final String s) {
        // monitorenter(Crittercism.class)
        Label_0020: {
            if (s != null) {
                break Label_0020;
            }
            try {
                Log.w("Crittercism", "Cannot leave null breadcrumb");
                return;
                l.i().a(s);
            }
            finally {
            }
            // monitorexit(Crittercism.class)
        }
    }
    
    public static void logHandledException(final Throwable t) {
        synchronized (Crittercism.class) {
            try {
                if (!l.i().e.d()) {
                    final l i = l.i();
                    if (i.i.h() < 50 && i.i.f().size() < 5 && i.i.f().size() + i.i.h() < 50) {
                        i.i.a(t);
                        try {
                            if (i.i.a()) {
                                new af(i.i).a();
                            }
                        }
                        catch (Exception ex) {
                            new StringBuilder("Exception in logHandledException: ").append(ex.getClass().getName());
                        }
                    }
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public static void performRateMyAppButtonAction(final CritterRateMyAppButtons critterRateMyAppButtons) {
        if (l.i().e.d()) {
            Log.w("Crittercism", "User has opted out of crittercism.  performRateMyAppButtonAction exiting.");
        }
        else {
            final l i = l.i();
            if (Build$VERSION.SDK_INT < 5) {
                Log.w("Crittercism", "Rate my app not supported below api level 5");
                return;
            }
            final String p = i.p();
            if (p == null) {
                Log.e("Crittercism", "Cannot create proper URI to open app market.  Returning null.");
                return;
            }
            switch (l$9.a[critterRateMyAppButtons.ordinal()]) {
                case 3: {
                    break;
                }
                default: {}
                case 1: {
                    try {
                        i.b(p);
                    }
                    catch (Exception ex) {
                        Log.w("Crittercism", "performRateMyAppButtonAction(CritterRateMyAppButtons.YES) failed.  Email support@crittercism.com.");
                    }
                }
                case 2: {
                    try {
                        i.o();
                        return;
                    }
                    catch (Exception ex2) {
                        Log.w("Crittercism", "performRateMyAppButtonAction(CritterRateMyAppButtons.NO) failed.  Email support@crittercism.com.");
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    public static void sendAppLoadData() {
        try {
            if (!l.i().t.delaySendingAppLoad()) {
                Log.i("Crittercism", "sendAppLoadData() will only send data to Crittercism if \"delaySendingAppLoad\" is set to true in the configuration settings you include in the init call.");
                return;
            }
            if (l.i().e.d()) {
                return;
            }
            new Thread(new l$1(l.i())).start();
        }
        catch (Exception ex) {}
    }
    
    public static void setMetadata(JSONObject j) {
        try {
            if (l.i().e.d()) {
                return;
            }
            l.i().c.c(j);
            if (l.i().b) {
                j = l.i().c.j();
                new Thread(new l$3(l.i(), j)).start();
                return;
            }
            Log.e("Crittercism", "Initialize the Crittercism library before using its methods.");
        }
        catch (Exception ex) {}
    }
    
    public static void setOptOutStatus(final boolean b) {
        new Thread(new Crittercism$1(b)).start();
    }
    
    public static void setUsername(final String a) {
        try {
            if (!l.i().b) {
                Log.e("Crittercism", "Initialize the Crittercism library before using its methods.");
                return;
            }
            if (l.i().e.d()) {
                return;
            }
            if (l.i().d == null) {
                l.i().d = new as();
            }
            l.i().d.a = a;
            final JSONObject j = l.i().c.j();
            j.put("username", (Object)a);
            setMetadata(j);
        }
        catch (Exception ex) {}
    }
}
