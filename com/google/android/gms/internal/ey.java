// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Looper;
import android.os.Build$VERSION;
import android.net.Uri$Builder;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.lang.annotation.Annotation;
import android.text.TextUtils;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedList;
import android.os.Build;
import android.content.Context;

@ez
public class ey implements UncaughtExceptionHandler
{
    private Context mContext;
    private UncaughtExceptionHandler sR;
    private UncaughtExceptionHandler sS;
    private gt sT;
    
    public ey(final Context mContext, final gt st, final UncaughtExceptionHandler sr, final UncaughtExceptionHandler ss) {
        this.sR = sr;
        this.sS = ss;
        this.mContext = mContext;
        this.sT = st;
    }
    
    public static ey a(final Context context, final Thread thread, final gt gt) {
        if (context == null || thread == null || gt == null) {
            return null;
        }
        gb.bD();
        if (!k(context)) {
            return null;
        }
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
        final ey uncaughtExceptionHandler2 = new ey(context, gt, uncaughtExceptionHandler, Thread.getDefaultUncaughtExceptionHandler());
        if (uncaughtExceptionHandler != null) {
            if (uncaughtExceptionHandler instanceof ey) {
                return (ey)uncaughtExceptionHandler;
            }
        }
        try {
            thread.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)uncaughtExceptionHandler2);
            return uncaughtExceptionHandler2;
        }
        catch (SecurityException ex) {
            gs.c("Fail to set UncaughtExceptionHandler.", ex);
            return null;
        }
        return (ey)uncaughtExceptionHandler;
    }
    
    private String cx() {
        final String manufacturer = Build.MANUFACTURER;
        final String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        }
        return manufacturer + " " + model;
    }
    
    private Throwable d(Throwable cause) {
        final Bundle bd = gb.bD();
        if (bd != null && bd.getBoolean("gads:sdk_crash_report_full_stacktrace", false)) {
            return cause;
        }
        final LinkedList<Throwable> list = new LinkedList<Throwable>();
        while (cause != null) {
            list.push(cause);
            cause = cause.getCause();
        }
        Throwable t = null;
        while (!list.isEmpty()) {
            final Throwable t2 = list.pop();
            final StackTraceElement[] stackTrace = t2.getStackTrace();
            final ArrayList<StackTraceElement> list2 = new ArrayList<StackTraceElement>();
            list2.add(new StackTraceElement(t2.getClass().getName(), "<filtered>", "<filtered>", 1));
            final int length = stackTrace.length;
            int i = 0;
            boolean b = false;
            while (i < length) {
                final StackTraceElement stackTraceElement = stackTrace[i];
                if (this.G(stackTraceElement.getClassName())) {
                    list2.add(stackTraceElement);
                    b = true;
                }
                else if (this.H(stackTraceElement.getClassName())) {
                    list2.add(stackTraceElement);
                }
                else {
                    list2.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                }
                ++i;
            }
            if (b) {
                if (t == null) {
                    t = new Throwable(t2.getMessage());
                }
                else {
                    t = new Throwable(t2.getMessage(), t);
                }
                t.setStackTrace(list2.toArray(new StackTraceElement[0]));
            }
        }
        return t;
    }
    
    private static boolean k(final Context context) {
        final Bundle bd = gb.bD();
        return bd != null && bd.getBoolean("gads:sdk_crash_report_enabled", false);
    }
    
    protected boolean G(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return false;
        }
        if (s.startsWith("com.google.android.gms.ads")) {
            return true;
        }
        if (s.startsWith("com.google.ads")) {
            return true;
        }
        try {
            return Class.forName(s).isAnnotationPresent(ez.class);
        }
        catch (Exception ex) {
            gs.a("Fail to check class type for class " + s, ex);
            return false;
        }
    }
    
    protected boolean H(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && (s.startsWith("android.") || s.startsWith("java."));
    }
    
    protected boolean a(Throwable cause) {
        boolean b = true;
        if (cause == null) {
            return false;
        }
        boolean b2 = false;
        boolean b3 = false;
        while (cause != null) {
            final StackTraceElement[] stackTrace = cause.getStackTrace();
            for (int length = stackTrace.length, i = 0; i < length; ++i) {
                final StackTraceElement stackTraceElement = stackTrace[i];
                if (this.G(stackTraceElement.getClassName())) {
                    b3 = true;
                }
                if (this.getClass().getName().equals(stackTraceElement.getClassName())) {
                    b2 = true;
                }
            }
            cause = cause.getCause();
        }
        if (!b3 || b2) {
            b = false;
        }
        return b;
    }
    
    public void b(Throwable d) {
        if (k(this.mContext)) {
            d = this.d(d);
            if (d != null) {
                final ArrayList<String> list = new ArrayList<String>();
                list.add(this.c(d));
                gj.a(this.mContext, this.sT.wD, list, gb.df());
            }
        }
    }
    
    protected String c(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return new Uri$Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build$VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build$VERSION.SDK_INT)).appendQueryParameter("device", this.cx()).appendQueryParameter("js", this.sT.wD).appendQueryParameter("appid", this.mContext.getApplicationContext().getPackageName()).appendQueryParameter("stacktrace", stringWriter.toString()).toString();
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        Label_0024: {
            if (!this.a(t)) {
                break Label_0024;
            }
            this.b(t);
            if (Looper.getMainLooper().getThread() == thread) {
                break Label_0024;
            }
            return;
        }
        if (this.sR != null) {
            this.sR.uncaughtException(thread, t);
            return;
        }
        if (this.sS != null) {
            this.sS.uncaughtException(thread, t);
        }
    }
}
