// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

import java.util.ArrayList;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import java.util.List;

public abstract class WebApiPostCommand extends WebApiCommand
{
    public WebApiPostCommand(final String s, final AuthorizationCredentials authorizationCredentials, final CommonRequestParameters commonRequestParameters) {
        super(s, authorizationCredentials, commonRequestParameters);
    }
    
    protected void addIfNotNull(final List<NameValuePair> list, final String s, final String s2) {
        if (s2 != null) {
            list.add((NameValuePair)new BasicNameValuePair(s, s2));
        }
    }
    
    protected HttpEntity getEntity() throws UnsupportedEncodingException {
        return (HttpEntity)new UrlEncodedFormEntity((List)this.getParameters());
    }
    
    @Override
    protected HttpUriRequest getHttpMethod() throws UnsupportedEncodingException {
        final HttpPost httpPost = new HttpPost(this.getUrl().toString());
        httpPost.setEntity(this.getEntity());
        return (HttpUriRequest)httpPost;
    }
    
    protected List<NameValuePair> getParameters() {
        final ArrayList<NameValuePair> list = new ArrayList<NameValuePair>(5);
        this.addIfNotNull(list, "output", this.getOuput());
        this.addIfNotNull(list, "v", this.getVersion());
        this.addIfNotNull(list, "routing", this.getRouting());
        this.addIfNotNull(list, "timestamp", String.valueOf(System.currentTimeMillis()));
        this.addIfNotNull(list, "esn", this.esn);
        if (this.commonRequestParameters != null) {
            this.addIfNotNull(list, "withCredentials", String.valueOf(this.commonRequestParameters.withCredentials));
            this.addIfNotNull(list, "app_version", this.commonRequestParameters.appVersion);
            this.addIfNotNull(list, "ui_version", this.commonRequestParameters.uiVersion);
            this.addIfNotNull(list, "os_version", this.commonRequestParameters.osVersion);
            this.addIfNotNull(list, "country", this.commonRequestParameters.country);
            this.addIfNotNull(list, "geolocation_country", this.commonRequestParameters.geolocationCountry);
            this.addIfNotNull(list, "device_cat", this.commonRequestParameters.deviceCategory);
            this.addIfNotNull(list, "device_type", this.commonRequestParameters.deviceType);
            this.addIfNotNull(list, "languages", this.commonRequestParameters.languages);
        }
        return list;
    }
}
