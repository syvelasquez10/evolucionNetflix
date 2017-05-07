// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.StringUtils;

public enum Payload$ActionInfoType
{
    EVENT_LOLOMO_REFRESH("NLL"), 
    EVENT_MYLIST_CHANGED("M"), 
    EVENT_NOTIFICATION_LIST_CHANGED("N"), 
    EVENT_NOTIFICATION_READ("NR"), 
    EVENT_PLAYBACK_ENDED("P"), 
    UNKNOWN("");
    
    private String value;
    
    private Payload$ActionInfoType(final String value) {
        this.value = value;
    }
    
    public static Payload$ActionInfoType create(final String s) {
        final Payload$ActionInfoType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final Payload$ActionInfoType payload$ActionInfoType = values[i];
            if (payload$ActionInfoType.value.equalsIgnoreCase(s)) {
                return payload$ActionInfoType;
            }
        }
        return Payload$ActionInfoType.UNKNOWN;
    }
    
    public static boolean isLolomoRefreshEvent(final String s) {
        return StringUtils.safeEquals(s, Payload$ActionInfoType.EVENT_LOLOMO_REFRESH.getValue());
    }
    
    public static boolean isMylistChangedEvent(final String s) {
        return StringUtils.safeEquals(s, Payload$ActionInfoType.EVENT_MYLIST_CHANGED.getValue());
    }
    
    public static boolean isNotificationListChangedEvent(final String s) {
        return StringUtils.safeEquals(s, Payload$ActionInfoType.EVENT_NOTIFICATION_LIST_CHANGED.getValue());
    }
    
    public static boolean isNotificationReadEvent(final String s) {
        return StringUtils.safeEquals(s, Payload$ActionInfoType.EVENT_NOTIFICATION_READ.getValue());
    }
    
    public static boolean isPlayEndEvent(final String s) {
        return StringUtils.safeEquals(s, Payload$ActionInfoType.EVENT_PLAYBACK_ENDED.getValue());
    }
    
    public String getValue() {
        return this.value;
    }
}
