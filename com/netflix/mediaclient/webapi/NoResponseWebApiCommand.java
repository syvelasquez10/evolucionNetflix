// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

import org.json.JSONException;
import org.apache.http.HttpException;
import java.io.IOException;

public abstract class NoResponseWebApiCommand extends WebApiGetCommand
{
    public NoResponseWebApiCommand(final String s, final AuthorizationCredentials authorizationCredentials, final CommonRequestParameters commonRequestParameters) {
        super(s, authorizationCredentials, commonRequestParameters);
    }
    
    public void execute() throws IOException, HttpException, JSONException {
        this.doExecute();
    }
}
