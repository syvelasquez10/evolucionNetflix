// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public enum ExpiringContentAction
{
    LOG_WHEN_NOTICE_SHOWN("log_when_shown"), 
    NEVER_SHOW_NOTICE_AGAIN("never_show_again"), 
    SHOULD_SHOW_NOTICE("should_show");
    
    private final String name;
    
    private ExpiringContentAction(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
