// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import android.content.Context;

public class ExceptionReporter implements UncaughtExceptionHandler
{
    private final Context mContext;
    private final UncaughtExceptionHandler sA;
    private final Tracker sB;
    private ExceptionParser sC;
    
    public ExceptionReporter(final Tracker sb, final UncaughtExceptionHandler sa, final Context context) {
        if (sb == null) {
            throw new NullPointerException("tracker cannot be null");
        }
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.sA = sa;
        this.sB = sb;
        this.sC = new StandardExceptionParser(context, new ArrayList<String>());
        this.mContext = context.getApplicationContext();
        final StringBuilder append = new StringBuilder().append("ExceptionReporter created, original handler is ");
        String name;
        if (sa == null) {
            name = "null";
        }
        else {
            name = sa.getClass().getName();
        }
        aa.y(append.append(name).toString());
    }
    
    public ExceptionParser getExceptionParser() {
        return this.sC;
    }
    
    public void setExceptionParser(final ExceptionParser sc) {
        this.sC = sc;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        String description = "UncaughtException";
        if (this.sC != null) {
            String name;
            if (thread != null) {
                name = thread.getName();
            }
            else {
                name = null;
            }
            description = this.sC.getDescription(name, t);
        }
        aa.y("Tracking Exception: " + description);
        this.sB.send(new HitBuilders.ExceptionBuilder().setDescription(description).setFatal(true).build());
        GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
        if (this.sA != null) {
            aa.y("Passing exception to original handler.");
            this.sA.uncaughtException(thread, t);
        }
    }
}
