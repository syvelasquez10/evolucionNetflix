// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

import org.json.JSONException;
import org.apache.http.HttpException;
import java.io.IOException;

public abstract class ResponseWebApiPostCommand extends WebApiPostCommand
{
    public ResponseWebApiPostCommand(final String s, final AuthorizationCredentials authorizationCredentials, final CommonRequestParameters commonRequestParameters) {
        super(s, authorizationCredentials, commonRequestParameters);
    }
    
    public String execute() throws IOException, HttpException, JSONException {
        return this.doExecute();
    }
}
