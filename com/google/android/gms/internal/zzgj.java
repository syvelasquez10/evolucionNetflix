// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Build$VERSION;
import android.net.Uri$Builder;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.lang.annotation.Annotation;
import android.text.TextUtils;
import java.util.List;
import com.google.android.gms.ads.internal.zzp;
import android.os.Looper;
import java.util.ArrayList;
import java.util.LinkedList;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.Context;

@zzgk
public class zzgj implements UncaughtExceptionHandler
{
    private Context mContext;
    private VersionInfoParcel zzBp;
    private UncaughtExceptionHandler zzDn;
    private UncaughtExceptionHandler zzDo;
    
    public zzgj(final Context mContext, final VersionInfoParcel zzBp, final UncaughtExceptionHandler zzDn, final UncaughtExceptionHandler zzDo) {
        this.zzDn = zzDn;
        this.zzDo = zzDo;
        this.mContext = mContext;
        this.zzBp = zzBp;
    }
    
    private Throwable zzb(Throwable cause) {
        if (zzby.zzuc.get()) {
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
                if (this.zzao(stackTraceElement.getClassName())) {
                    list2.add(stackTraceElement);
                    b = true;
                }
                else if (this.zzap(stackTraceElement.getClassName())) {
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
    
    private static boolean zzz(final Context context) {
        return zzby.zzub.get();
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        Label_0031: {
            if (!this.zza(t)) {
                break Label_0031;
            }
            if (Looper.getMainLooper().getThread() == thread) {
                this.zza(t, false);
                break Label_0031;
            }
            this.zza(t, true);
            return;
        }
        if (this.zzDn != null) {
            this.zzDn.uncaughtException(thread, t);
            return;
        }
        if (this.zzDo != null) {
            this.zzDo.uncaughtException(thread, t);
        }
    }
    
    public void zza(Throwable zzb, final boolean b) {
        if (zzz(this.mContext)) {
            zzb = this.zzb(zzb);
            if (zzb != null) {
                final ArrayList<String> list = new ArrayList<String>();
                list.add(this.zzb(zzb, b));
                zzp.zzbx().zza(this.mContext, this.zzBp.zzIz, list, zzp.zzbA().zzgf());
            }
        }
    }
    
    protected boolean zza(Throwable cause) {
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
                if (this.zzao(stackTraceElement.getClassName())) {
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
    
    protected boolean zzao(final String s) {
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
            return Class.forName(s).isAnnotationPresent(zzgk.class);
        }
        catch (Exception ex) {
            zzb.zza("Fail to check class type for class " + s, ex);
            return false;
        }
    }
    
    protected boolean zzap(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && (s.startsWith("android.") || s.startsWith("java."));
    }
    
    String zzb(final Throwable t, final boolean b) {
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return new Uri$Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build$VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build$VERSION.SDK_INT)).appendQueryParameter("device", zzp.zzbx().zzgt()).appendQueryParameter("js", this.zzBp.zzIz).appendQueryParameter("appid", this.mContext.getApplicationContext().getPackageName()).appendQueryParameter("stacktrace", stringWriter.toString()).appendQueryParameter("eids", TextUtils.join((CharSequence)",", (Iterable)zzby.zzde())).appendQueryParameter("trapped", String.valueOf(b)).toString();
    }
}
