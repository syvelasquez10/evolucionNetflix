// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

enum AuthorizationClient$Result$Code
{
    CANCEL("cancel"), 
    ERROR("error"), 
    SUCCESS("success");
    
    private final String loggingValue;
    
    private AuthorizationClient$Result$Code(final String loggingValue) {
        this.loggingValue = loggingValue;
    }
    
    String getLoggingValue() {
        return this.loggingValue;
    }
}
