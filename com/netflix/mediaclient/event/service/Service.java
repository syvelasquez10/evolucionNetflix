// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.service;

public enum Service
{
    facebook("fb");
    
    protected String name;
    
    private Service(final String name) {
        this.name = name;
    }
    
    public static Service find(final String s) {
        if (Service.facebook.name.equalsIgnoreCase(s)) {
            return Service.facebook;
        }
        return null;
    }
    
    public final String getName() {
        return this.name;
    }
}
