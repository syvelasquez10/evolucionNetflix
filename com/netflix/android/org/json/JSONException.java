// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

public class JSONException extends RuntimeException
{
    private static final long serialVersionUID = 0L;
    private Throwable cause;
    
    public JSONException(final String s) {
        super(s);
    }
    
    public JSONException(final Throwable cause) {
        super(cause.getMessage());
        this.cause = cause;
    }
    
    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
