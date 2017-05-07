// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.util.StringUtils;

public enum UserActionLogging$Streams
{
    _1("1"), 
    _2("2"), 
    _3("3"), 
    _4("4");
    
    private String mValue;
    
    private UserActionLogging$Streams(final String mValue) {
        this.mValue = mValue;
    }
    
    public static UserActionLogging$Streams find(final String s) {
        if (!StringUtils.isEmpty(s)) {
            final UserActionLogging$Streams[] values = values();
            for (int length = values.length, i = 0; i < length; ++i) {
                final UserActionLogging$Streams userActionLogging$Streams = values[i];
                if (userActionLogging$Streams.getValue().equals(s)) {
                    return userActionLogging$Streams;
                }
            }
        }
        return null;
    }
    
    public String getValue() {
        return this.mValue;
    }
}
