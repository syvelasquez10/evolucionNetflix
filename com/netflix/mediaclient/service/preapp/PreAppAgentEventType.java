// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

public enum PreAppAgentEventType
{
    ALL_UPDATED, 
    CW_UPDATED, 
    IQ_UPDATED, 
    WRITING_TO_DISK;
    
    public static boolean isBBUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return preAppAgentEventType == PreAppAgentEventType.ALL_UPDATED;
    }
    
    public static boolean isCWUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return preAppAgentEventType == PreAppAgentEventType.CW_UPDATED || preAppAgentEventType == PreAppAgentEventType.ALL_UPDATED;
    }
    
    public static boolean isFirstStandardListUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return preAppAgentEventType == PreAppAgentEventType.ALL_UPDATED;
    }
    
    public static boolean isIQUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return preAppAgentEventType == PreAppAgentEventType.IQ_UPDATED || preAppAgentEventType == PreAppAgentEventType.ALL_UPDATED;
    }
    
    public static boolean isWriteToDiskInProgress(final PreAppAgentEventType preAppAgentEventType) {
        return preAppAgentEventType == PreAppAgentEventType.WRITING_TO_DISK;
    }
}
