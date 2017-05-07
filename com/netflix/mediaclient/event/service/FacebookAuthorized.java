// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.service;

public class FacebookAuthorized extends Authorized
{
    public static final String TYPE = "nrdp-service-authorize-didauthorize";
    
    public FacebookAuthorized(final String s) {
        super("nrdp-service-authorize-didauthorize", Service.facebook, s);
    }
    
    @Override
    public int getVersion() {
        return 1;
    }
}
