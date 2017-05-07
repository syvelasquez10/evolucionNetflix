// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public enum DeviceCategory
{
    ANDROID_TV("android-tv"), 
    GOOGLE_TV("google-tv"), 
    PHONE("phone"), 
    TABLET("tablet"), 
    UNKNOWN("unknown");
    
    private String value;
    
    private DeviceCategory(final String value) {
        this.value = value;
    }
    
    public static DeviceCategory find(final String s) {
        if (s == null) {
            return null;
        }
        if (s.equalsIgnoreCase(DeviceCategory.PHONE.value)) {
            return DeviceCategory.PHONE;
        }
        if (s.equalsIgnoreCase(DeviceCategory.TABLET.value)) {
            return DeviceCategory.TABLET;
        }
        if (s.equalsIgnoreCase(DeviceCategory.GOOGLE_TV.value)) {
            return DeviceCategory.GOOGLE_TV;
        }
        if (s.equalsIgnoreCase(DeviceCategory.ANDROID_TV.value)) {
            return DeviceCategory.ANDROID_TV;
        }
        if (s.equalsIgnoreCase(DeviceCategory.UNKNOWN.value)) {
            return DeviceCategory.UNKNOWN;
        }
        return DeviceCategory.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
