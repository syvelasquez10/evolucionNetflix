// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.logging;

import android.util.Log;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

public class FLogDefaultLoggingDelegate implements LoggingDelegate
{
    public static final FLogDefaultLoggingDelegate sInstance;
    private String mApplicationTag;
    private int mMinimumLoggingLevel;
    
    static {
        sInstance = new FLogDefaultLoggingDelegate();
    }
    
    private FLogDefaultLoggingDelegate() {
        this.mApplicationTag = "unknown";
        this.mMinimumLoggingLevel = 5;
    }
    
    public static FLogDefaultLoggingDelegate getInstance() {
        return FLogDefaultLoggingDelegate.sInstance;
    }
    
    private static String getMsg(final String s, final Throwable t) {
        return s + '\n' + getStackTraceString(t);
    }
    
    private static String getStackTraceString(final Throwable t) {
        if (t == null) {
            return "";
        }
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
    
    private String prefixTag(final String s) {
        String string = s;
        if (this.mApplicationTag != null) {
            string = this.mApplicationTag + ":" + s;
        }
        return string;
    }
    
    private void println(final int n, final String s, final String s2) {
        Log.println(n, this.prefixTag(s), s2);
    }
    
    private void println(final int n, final String s, final String s2, final Throwable t) {
        Log.println(n, this.prefixTag(s), getMsg(s2, t));
    }
    
    @Override
    public void d(final String s, final String s2) {
        this.println(3, s, s2);
    }
    
    @Override
    public void e(final String s, final String s2) {
        this.println(6, s, s2);
    }
    
    @Override
    public void e(final String s, final String s2, final Throwable t) {
        this.println(6, s, s2, t);
    }
    
    @Override
    public void i(final String s, final String s2) {
        this.println(4, s, s2);
    }
    
    @Override
    public boolean isLoggable(final int n) {
        return this.mMinimumLoggingLevel <= n;
    }
    
    @Override
    public void v(final String s, final String s2) {
        this.println(2, s, s2);
    }
    
    @Override
    public void w(final String s, final String s2) {
        this.println(5, s, s2);
    }
    
    @Override
    public void w(final String s, final String s2, final Throwable t) {
        this.println(5, s, s2, t);
    }
    
    @Override
    public void wtf(final String s, final String s2) {
        this.println(6, s, s2);
    }
    
    @Override
    public void wtf(final String s, final String s2, final Throwable t) {
        this.println(6, s, s2, t);
    }
}
