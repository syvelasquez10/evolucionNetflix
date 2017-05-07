// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.user;

public enum ProfileType
{
    JFK("jfk"), 
    STANDARD("standard");
    
    private String value;
    
    private ProfileType(final String value) {
        this.value = value;
    }
    
    public static ProfileType create(final String s) {
        final ProfileType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final ProfileType profileType = values[i];
            if (profileType.value.equalsIgnoreCase(s)) {
                return profileType;
            }
        }
        throw new IllegalStateException("Unknown profile type");
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
