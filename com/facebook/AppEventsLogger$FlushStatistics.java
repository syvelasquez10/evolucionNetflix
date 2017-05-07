// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

class AppEventsLogger$FlushStatistics
{
    public int numEvents;
    public AppEventsLogger$FlushResult result;
    
    private AppEventsLogger$FlushStatistics() {
        this.numEvents = 0;
        this.result = AppEventsLogger$FlushResult.SUCCESS;
    }
}
