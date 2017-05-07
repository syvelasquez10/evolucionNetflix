// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public enum LoggingBehavior
{
    APP_EVENTS, 
    CACHE, 
    DEVELOPER_ERRORS, 
    INCLUDE_ACCESS_TOKENS, 
    INCLUDE_RAW_RESPONSES;
    
    @Deprecated
    public static final LoggingBehavior INSIGHTS;
    
    REQUESTS;
    
    static {
        INSIGHTS = LoggingBehavior.APP_EVENTS;
    }
}
