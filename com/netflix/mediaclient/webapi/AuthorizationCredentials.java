// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

public final class AuthorizationCredentials
{
    public String netflixId;
    public String secureNetflixId;
    
    public AuthorizationCredentials(final String netflixId, final String secureNetflixId) {
        this.netflixId = netflixId;
        this.secureNetflixId = secureNetflixId;
    }
}
