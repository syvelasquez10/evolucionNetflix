// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import android.content.Context;
import java.util.TreeSet;

public class StandardExceptionParser implements ExceptionParser
{
    private final TreeSet<String> Bl;
    
    public StandardExceptionParser(final Context context, final Collection<String> collection) {
        this.Bl = new TreeSet<String>();
        this.setIncludedPackages(context, collection);
    }
    
    protected StackTraceElement getBestStackTraceElement(final Throwable t) {
        final StackTraceElement[] stackTrace = t.getStackTrace();
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        }
        for (int length = stackTrace.length, i = 0; i < length; ++i) {
            final StackTraceElement stackTraceElement = stackTrace[i];
            final String className = stackTraceElement.getClassName();
            final Iterator<String> iterator = this.Bl.iterator();
            while (iterator.hasNext()) {
                if (className.startsWith(iterator.next())) {
                    return stackTraceElement;
                }
            }
        }
        return stackTrace[0];
    }
    
    protected Throwable getCause(Throwable cause) {
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        return cause;
    }
    
    @Override
    public String getDescription(final String s, final Throwable t) {
        return this.getDescription(this.getCause(t), this.getBestStackTraceElement(this.getCause(t)), s);
    }
    
    protected String getDescription(final Throwable t, final StackTraceElement stackTraceElement, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append(t.getClass().getSimpleName());
        if (stackTraceElement != null) {
            final String[] split = stackTraceElement.getClassName().split("\\.");
            String s2 = "unknown";
            if (split != null) {
                s2 = s2;
                if (split.length > 0) {
                    s2 = split[split.length - 1];
                }
            }
            sb.append(String.format(" (@%s:%s:%s)", s2, stackTraceElement.getMethodName(), stackTraceElement.getLineNumber()));
        }
        if (s != null) {
            sb.append(String.format(" {%s}", s));
        }
        return sb.toString();
    }
    
    public void setIncludedPackages(final Context context, final Collection<String> collection) {
        this.Bl.clear();
        final HashSet<String> set = new HashSet<String>();
        if (collection != null) {
            set.addAll((Collection<?>)collection);
        }
        if (context != null) {
            try {
                final String packageName = context.getApplicationContext().getPackageName();
                this.Bl.add(packageName);
                final ActivityInfo[] activities = context.getApplicationContext().getPackageManager().getPackageInfo(packageName, 15).activities;
                if (activities != null) {
                    for (int length = activities.length, i = 0; i < length; ++i) {
                        set.add(activities[i].packageName);
                    }
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                z.U("No package found");
            }
        }
        for (final String s : set) {
            final Iterator<String> iterator2 = this.Bl.iterator();
            int n = 1;
            while (iterator2.hasNext()) {
                final String s2 = iterator2.next();
                if (!s.startsWith(s2)) {
                    if (s2.startsWith(s)) {
                        this.Bl.remove(s2);
                        break;
                    }
                    break;
                }
                else {
                    n = 0;
                }
            }
            if (n != 0) {
                this.Bl.add(s);
            }
        }
    }
}
