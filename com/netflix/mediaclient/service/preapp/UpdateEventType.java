// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

public enum UpdateEventType
{
    ALL_UPDATED, 
    CW_UPDATED, 
    IQ_UPDATED;
    
    public static boolean isBBUpdated(final UpdateEventType updateEventType) {
        return updateEventType == UpdateEventType.ALL_UPDATED;
    }
    
    public static boolean isCWUpdated(final UpdateEventType updateEventType) {
        return updateEventType == UpdateEventType.CW_UPDATED || updateEventType == UpdateEventType.ALL_UPDATED;
    }
    
    public static boolean isIQUpdated(final UpdateEventType updateEventType) {
        return updateEventType == UpdateEventType.IQ_UPDATED || updateEventType == UpdateEventType.ALL_UPDATED;
    }
    
    public static boolean isRecoListUpdated(final UpdateEventType updateEventType) {
        return updateEventType == UpdateEventType.ALL_UPDATED;
    }
}
