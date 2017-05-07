// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

public final class CommonRequestParameters
{
    public String appVersion;
    public String country;
    public String deviceCategory;
    public String deviceType;
    public String geolocationCountry;
    public String languages;
    public String osVersion;
    public String profileToken;
    public String uiVersion;
    public Boolean withCredentials;
    
    public static CommonRequestParameters getInstanceWithCredentials() {
        final CommonRequestParameters commonRequestParameters = new CommonRequestParameters();
        commonRequestParameters.withCredentials = Boolean.TRUE;
        return commonRequestParameters;
    }
}
