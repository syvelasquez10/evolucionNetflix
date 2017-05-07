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
    private final UncaughtExceptionHandler xX;
    private final Tracker xY;
    private ExceptionParser xZ;
    
    public ExceptionReporter(final Tracker xy, final UncaughtExceptionHandler xx, final Context context) {
        if (xy == null) {
            throw new NullPointerException("tracker cannot be null");
        }
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.xX = xx;
        this.xY = xy;
        this.xZ = new StandardExceptionParser(context, new ArrayList<String>());
        this.mContext = context.getApplicationContext();
        final StringBuilder append = new StringBuilder().append("ExceptionReporter created, original handler is ");
        String name;
        if (xx == null) {
            name = "null";
        }
        else {
            name = xx.getClass().getName();
        }
        z.V(append.append(name).toString());
    }
    
    UncaughtExceptionHandler dZ() {
        return this.xX;
    }
    
    public ExceptionParser getExceptionParser() {
        return this.xZ;
    }
    
    public void setExceptionParser(final ExceptionParser xz) {
        this.xZ = xz;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        String description = "UncaughtException";
        if (this.xZ != null) {
            String name;
            if (thread != null) {
                name = thread.getName();
            }
            else {
                name = null;
            }
            description = this.xZ.getDescription(name, t);
        }
        z.V("Tracking Exception: " + description);
        this.xY.send(new HitBuilders.ExceptionBuilder().setDescription(description).setFatal(true).build());
        GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
        if (this.xX != null) {
            z.V("Passing exception to original handler.");
            this.xX.uncaughtException(thread, t);
        }
    }
}
