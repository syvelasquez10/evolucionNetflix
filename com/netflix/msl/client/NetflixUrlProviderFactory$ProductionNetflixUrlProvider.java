// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

public class NetflixUrlProviderFactory$ProductionNetflixUrlProvider implements NetflixUrlProvider
{
    @Override
    public String getApiUri(final String s) {
        return String.format("http://api.netflix.com%s", s);
    }
    
    @Override
    public String getAppbootUri(final String s) {
        return String.format("http://appboot.netflix.com/appboot/%s", s);
    }
    
    @Override
    public String getNccpUri(final String s) {
        return String.format("http://nrdp.nccp.netflix.com/nccp/controller%s", s);
    }
}
