// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.app;

import com.crittercism.internal.ba;
import android.location.Location;
import org.json.JSONException;
import com.crittercism.internal.dr;
import org.json.JSONObject;
import com.crittercism.internal.ap;
import com.crittercism.internal.ar;
import com.crittercism.internal.au;
import com.crittercism.internal.ax$5;
import com.crittercism.internal.ax$b;
import com.crittercism.internal.ax$1;
import java.net.URL;
import android.webkit.WebView;
import com.crittercism.internal.at;
import android.os.Build$VERSION;
import android.app.AlertDialog;
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
    
    private static void a(final String s, final int n) {
        final ax c = ax.C();
        if (c.g.a()) {
            h("setTransactionValue");
            return;
        }
        if (!c.b) {
            g("setTransactionValue");
            return;
        }
        c.a(s, n);
    }
    
    private static void b(final String s) {
        final ax c = ax.C();
        if (c.g.a()) {
            h("beginTransaction");
            return;
        }
        if (!c.b) {
            g("beginTransaction");
            return;
        }
        c.c(s);
    }
    
    @Deprecated
    public static void beginTransaction(final String s) {
        try {
            b(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public static void beginUserflow(final String s) {
        try {
            b(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    private static void c(final String s) {
        final ax c = ax.C();
        if (c.g.a()) {
            h("endTransaction");
            return;
        }
        if (!c.b) {
            g("endTransaction");
            return;
        }
        c.d(s);
    }
    
    @Deprecated
    public static void cancelTransaction(final String s) {
        try {
            e(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public static void cancelUserflow(final String s) {
        try {
            e(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    private static void d(final String s) {
        final ax c = ax.C();
        if (c.g.a()) {
            h("failTransaction");
            return;
        }
        if (!c.b) {
            g("failTransaction");
            return;
        }
        c.e(s);
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
    
    private static void e(final String s) {
        final ax c = ax.C();
        if (c.g.a()) {
            h("cancelTransaction");
            return;
        }
        if (!c.b) {
            g("cancelTransaction");
            return;
        }
        c.f(s);
    }
    
    @Deprecated
    public static void endTransaction(final String s) {
        try {
            c(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public static void endUserflow(final String s) {
        try {
            c(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    private static int f(final String s) {
        final ax c = ax.C();
        if (c.g.a()) {
            h("getTransactionValue");
            return -1;
        }
        if (!c.b) {
            g("getTransactionValue");
            return -1;
        }
        return c.g(s);
    }
    
    @Deprecated
    public static void failTransaction(final String s) {
        try {
            d(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public static void failUserflow(final String s) {
        try {
            d(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    private static void g(final String s) {
        dw.b("Must initialize Crittercism before calling " + Crittercism.class.getName() + "." + s + "(). Request is being ignored...", new IllegalStateException());
    }
    
    public static AlertDialog generateRateMyAppAlertDialog(final Context context) {
        while (true) {
            while (true) {
                try {
                    final ax c = ax.C();
                    if (c.A != null) {
                        final String b = c.A.b();
                        final String c2 = c.A.c();
                        return c.a(context, c2, b);
                    }
                }
                catch (ThreadDeath threadDeath) {
                    throw threadDeath;
                }
                catch (Throwable t) {
                    dw.b(t);
                    return null;
                }
                final String c2 = null;
                final String b = null;
                continue;
            }
        }
    }
    
    public static AlertDialog generateRateMyAppAlertDialog(final Context context, final String s, final String s2) {
        try {
            return ax.C().a(context, s, s2);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return null;
        }
    }
    
    public static boolean getOptOutStatus() {
        try {
            return ax.C().g.a();
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return false;
        }
    }
    
    @Deprecated
    public static int getTransactionValue(final String s) {
        try {
            return f(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return -1;
        }
    }
    
    public static int getUserflowValue(final String s) {
        try {
            return f(s);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return -1;
        }
    }
    
    private static void h(final String s) {
        dw.d("User has opted out of Crittercism. " + Crittercism.class.getName() + "." + s + "() call is being ignored...");
    }
    
    public static void initialize(final Context context, final String s) {
        synchronized (Crittercism.class) {
            initialize(context, s, new CrittercismConfig());
        }
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
                        // iftrue(Label_0048:, context != null)
                        a(Context.class.getCanonicalName());
                        return;
                    }
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
    
    public static void instrumentWebView(final WebView webView) {
        ax c;
        try {
            c = ax.C();
            if (c.g.a()) {
                h("instrumentWebView");
                return;
            }
            if (!c.b) {
                g("instrumentWebView");
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
        c.a(webView);
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
    
    public static void logNetworkRequest(final String s, final URL url, final long n, final long n2, final long n3, final int n4, final Exception ex) {
        long currentTimeMillis;
        ax c;
        try {
            currentTimeMillis = System.currentTimeMillis();
            c = ax.C();
            if (c.g.a()) {
                h("logNetworkRequest");
                return;
            }
            if (!c.b) {
                g("logNetworkRequest");
                return;
            }
            goto Label_0046;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        c.a(s, url.toExternalForm(), n, n2, n3, n4, new ax$1(c, ex), currentTimeMillis - n);
    }
    
    public static void performRateMyAppButtonAction(final CritterRateMyAppButtons critterRateMyAppButtons) {
        ax c;
        try {
            c = ax.C();
            if (c.g.a()) {
                h("performRateMyAppButtonAction");
                return;
            }
            if (!c.b) {
                g("preformRateMyAppButtonAction");
                return;
            }
            goto Label_0038;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        final String f = c.F();
        if (f == null) {
            dw.a("Cannot create proper URI to open app market.  Returning null.");
            return;
        }
        switch (ax$5.a[critterRateMyAppButtons.ordinal()]) {
            default: {}
            case 1: {
                try {
                    c.b(f);
                }
                catch (Exception ex) {
                    dw.b("performRateMyAppButtonAction(CritterRateMyAppButtons.YES) failed.  Email support@crittercism.com.");
                    dw.a(ex);
                }
            }
            case 2: {
                try {
                    c.E();
                    return;
                }
                catch (Exception ex2) {
                    dw.b("performRateMyAppButtonAction(CritterRateMyAppButtons.NO) failed.  Email support@crittercism.com.");
                    return;
                }
                break;
            }
        }
    }
    
    public static void sendAppLoadData() {
        try {
            final ax c = ax.C();
            if (c.g.a()) {
                h("sendAppLoadData");
                return;
            }
            if (!c.b) {
                g("sendAppLoadData");
                return;
            }
            goto Label_0038;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        final ap ap;
        ap.c.setDelaySendingAppLoad(false);
        if (ap.d()) {
            ap.f.a(ap.a(ap.f, ap.e, ap.c, ap.f, ap.f));
        }
    }
    
    public static void setLoggingLevel(final Crittercism$LoggingLevel crittercism$LoggingLevel) {
        try {
            dw.a(crittercism$LoggingLevel);
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
            goto Label_0038;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public static void setOptOutStatus(final boolean b) {
        try {
            final dr g = ax.C().g;
            if (!ax.C().B()) {
                dw.b("Crittercism has not been initialized with a context and cannot save opt out status to disk. Ignoring request to set opt out status...");
                return;
            }
            synchronized (g) {
                if (g.a != b && g.b) {
                    dw.b("Opt out status can only be changed once per session. setOptOutStatus() call is being ignored...");
                    return;
                }
                goto Label_0093;
            }
        }
        catch (bl$a bl$a) {
            a(bl$a);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @Deprecated
    public static void setTransactionValue(final String s, final int n) {
        try {
            a(s, n);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    public static void setUserflowValue(final String s, final int n) {
        try {
            a(s, n);
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
            goto Label_0038;
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
    
    public static void updateLocation(final Location location) {
        try {
            final ax c = ax.C();
            if (c.g.a()) {
                h("updateLocation");
                return;
            }
            if (!c.b) {
                g("updateLocation");
                return;
            }
            goto Label_0038;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        ba.a(location);
    }
}
