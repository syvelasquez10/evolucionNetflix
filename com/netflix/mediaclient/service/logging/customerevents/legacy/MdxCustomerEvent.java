// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerevents.legacy;

import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import java.util.List;
import com.netflix.mediaclient.webapi.CommonRequestParameters;
import com.netflix.mediaclient.webapi.AuthorizationCredentials;

public class MdxCustomerEvent extends MdxCeWebApiCommand
{
    private static final String DATA_FIELD = "data";
    private final String mEventData;
    
    public MdxCustomerEvent(final String s, final AuthorizationCredentials authorizationCredentials, final String mEventData) {
        super(s, authorizationCredentials, CommonRequestParameters.getInstanceWithCredentials());
        this.mEventData = mEventData;
    }
    
    protected void addIfNotNull(final List<NameValuePair> list, final String s, final String s2) {
        if (s2 != null) {
            list.add((NameValuePair)new BasicNameValuePair(s, s2));
        }
    }
    
    public void execute() {
        this.doExecute();
    }
    
    @Override
    protected StringBuilder getBaseUrl() {
        final StringBuilder sb = new StringBuilder();
        if (this.isSecure()) {
            sb.append("https://");
        }
        else {
            sb.append("http://");
        }
        sb.append(MdxCustomerEvent.mCustomerEventEndPoint);
        sb.append("/users/customerevents");
        sb.append('?').append("output").append('=').append(this.getOuput());
        sb.append('&').append("withCredentials").append("=true");
        return sb;
    }
    
    @Override
    public String getCommandPath() {
        return "/users";
    }
    
    @Override
    protected HttpUriRequest getHttpMethod() {
        final HttpPost httpPost = new HttpPost(this.getUrl().toString());
        httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity((List)this.getParameters()));
        return (HttpUriRequest)httpPost;
    }
    
    protected List<NameValuePair> getParameters() {
        final ArrayList<NameValuePair> list = new ArrayList<NameValuePair>(3);
        this.addIfNotNull(list, "output", this.getOuput());
        this.addIfNotNull(list, "data", this.mEventData);
        this.addIfNotNull(list, "withCredentials", String.valueOf(this.commonRequestParameters.withCredentials));
        return list;
    }
}
