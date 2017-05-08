// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

public class NetflixUrlProviderFactory$TestNetflixUrlProvider implements NetflixUrlProvider
{
    @Override
    public String getApiUri(final String s) {
        return String.format("http://api.test.netflix.com%s", s);
    }
    
    @Override
    public String getAppbootUri(final String s) {
        return String.format("http://api.test.netflix.com/appboot/%s", s);
    }
    
    @Override
    public String getNccpUri(final String s) {
        return String.format("http://api.test.netflix.com/nccp/controller%s", s);
    }
}
