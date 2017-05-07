// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.Intent;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

abstract class AuthorizationClient$AuthHandler implements Serializable
{
    Map<String, String> methodLoggingExtras;
    final /* synthetic */ AuthorizationClient this$0;
    
    AuthorizationClient$AuthHandler(final AuthorizationClient this$0) {
        this.this$0 = this$0;
    }
    
    protected void addLoggingExtra(final String s, final Object o) {
        if (this.methodLoggingExtras == null) {
            this.methodLoggingExtras = new HashMap<String, String>();
        }
        final Map<String, String> methodLoggingExtras = this.methodLoggingExtras;
        String string;
        if (o == null) {
            string = null;
        }
        else {
            string = o.toString();
        }
        methodLoggingExtras.put(s, string);
    }
    
    void cancel() {
    }
    
    abstract String getNameForLogging();
    
    boolean needsInternetPermission() {
        return false;
    }
    
    boolean needsRestart() {
        return false;
    }
    
    boolean onActivityResult(final int n, final int n2, final Intent intent) {
        return false;
    }
    
    abstract boolean tryAuthorize(final AuthorizationClient$AuthorizationRequest p0);
}
