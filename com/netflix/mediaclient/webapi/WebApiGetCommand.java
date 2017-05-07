// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

public abstract class WebApiGetCommand extends WebApiCommand
{
    public WebApiGetCommand(final String s, final AuthorizationCredentials authorizationCredentials, final CommonRequestParameters commonRequestParameters) {
        super(s, authorizationCredentials, commonRequestParameters);
    }
    
    @Override
    protected HttpUriRequest getHttpMethod() {
        return (HttpUriRequest)new HttpGet(this.getUrl().toString());
    }
    
    @Override
    protected StringBuilder getUrl() {
        final StringBuilder url = super.getUrl();
        url.append('?');
        url.append("output").append('=').append(this.getOuput()).append('&');
        url.append("routing").append('=').append(this.getRouting()).append('&');
        url.append("timestamp").append('=').append(System.currentTimeMillis()).append('&');
        url.append("v").append('=').append(this.getVersion());
        this.addIfNotNull(url, "esn", this.esn);
        if (this.commonRequestParameters != null) {
            this.addIfNotNull(url, "withCredentials", String.valueOf(this.commonRequestParameters.withCredentials));
            this.addIfNotNull(url, "app_version", this.commonRequestParameters.appVersion);
            this.addIfNotNull(url, "ui_version", this.commonRequestParameters.uiVersion);
            this.addIfNotNull(url, "os_version", this.commonRequestParameters.osVersion);
            this.addIfNotNull(url, "country", this.commonRequestParameters.country);
            this.addIfNotNull(url, "geolocation_country", this.commonRequestParameters.geolocationCountry);
            this.addIfNotNull(url, "device_cat", this.commonRequestParameters.deviceCategory);
            this.addIfNotNull(url, "device_type", this.commonRequestParameters.deviceType);
            this.addIfNotNull(url, "languages", this.commonRequestParameters.languages);
        }
        return url;
    }
}
