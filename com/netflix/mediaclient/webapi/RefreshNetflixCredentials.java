// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

public class RefreshNetflixCredentials extends NoResponseWebApiCommand
{
    public RefreshNetflixCredentials(final String s, final String s2, final String s3) {
        super(s, new AuthorizationCredentials(s2, s3), null);
    }
    
    @Override
    public String getCommandPath() {
        return "/users/current";
    }
}
