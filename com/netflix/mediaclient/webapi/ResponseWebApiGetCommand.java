// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.webapi;

public abstract class ResponseWebApiGetCommand extends WebApiGetCommand
{
    public ResponseWebApiGetCommand(final String s, final AuthorizationCredentials authorizationCredentials, final CommonRequestParameters commonRequestParameters) {
        super(s, authorizationCredentials, commonRequestParameters);
    }
    
    public String execute() {
        return this.doExecute();
    }
}
