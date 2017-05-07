// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

public enum PreAppAgentEventType
{
    ACCOUNT_DEACTIVATED, 
    ALL_MEMBER_UPDATED, 
    CW_UPDATED, 
    IQ_UPDATED, 
    NON_MEMBER_UPDATED;
    
    public static boolean isBBUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return PreAppAgentEventType.ALL_MEMBER_UPDATED.equals(preAppAgentEventType);
    }
    
    public static boolean isCWUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return PreAppAgentEventType.CW_UPDATED.equals(preAppAgentEventType) || PreAppAgentEventType.ALL_MEMBER_UPDATED.equals(preAppAgentEventType);
    }
    
    public static boolean isFirstStandardListUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return PreAppAgentEventType.ALL_MEMBER_UPDATED.equals(preAppAgentEventType);
    }
    
    public static boolean isIQUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return PreAppAgentEventType.IQ_UPDATED.equals(preAppAgentEventType) || PreAppAgentEventType.ALL_MEMBER_UPDATED.equals(preAppAgentEventType);
    }
    
    public static boolean isListInfoUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return PreAppAgentEventType.ALL_MEMBER_UPDATED.equals(preAppAgentEventType);
    }
    
    public static boolean isNonMemberListUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return PreAppAgentEventType.NON_MEMBER_UPDATED.equals(preAppAgentEventType);
    }
    
    public static boolean isSecondStandardListUpdated(final PreAppAgentEventType preAppAgentEventType) {
        return PreAppAgentEventType.ALL_MEMBER_UPDATED.equals(preAppAgentEventType);
    }
    
    public static boolean shouldClearData(final PreAppAgentEventType preAppAgentEventType) {
        return PreAppAgentEventType.ACCOUNT_DEACTIVATED.equals(preAppAgentEventType);
    }
}
